package storybook.importer;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.lang.WordUtils;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * This parser uses Stanford's POS tagger to pick out all of the names from the
 * document (ideally pairing up first and last names into a single token) Takes
 * in raw text as input and returns solely the materials tagged as "PERSON". No
 * duplicates should be permitted.
 * 
 * @author Travis Dutko
 */
public class StanfordCharacterParser extends Parser {

	/**
	 * MINIMAL: removes only word-for-word duplicates ("Lyra Belacqua" and
	 * "Lyra" are considered different names) FIRSTLAST: removes duplicates of
	 * known first and last names only (given the name "Ludger Will Kresnik",
	 * "Ludger" on its own would be removed, but "Will" on its own would not)
	 * STRICT: removes all duplicates spotted, keeping the longest names
	 * 
	 * @author Travis Dutko
	 */
	public enum Strictness {
		MINIMAL, FIRSTLAST, STRICT
	};

	private Strictness strictness;

	/**
	 * Constructor. Takes the string which is to be parsed Default strictness of firstlast is used.
	 * 
	 * @param s
	 *            the content string
	 */
	public StanfordCharacterParser(InputStream in) {
		super(in);
		strictness = Strictness.STRICT;
	}

	/**
	 * Constructor. Takes the string which is to be parsed and a Strictness
	 * parameter
	 * 
	 * @param s
	 * @param strict
	 */
	public StanfordCharacterParser(InputStream in, Strictness strict) {
		super(in);
		strictness = strict;
	}

	public List<String> getTokens() { 

		// intialize the classifier
		AbstractSequenceClassifier<CoreLabel> classifier = null;
		try {
			classifier = CRFClassifier
					.getClassifier("classifiers/english.all.3class.distsim.crf.ser.gz");
		} catch (ClassCastException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}

		// initialize the list of names to return
		List<String> names = new ArrayList<String>();

		Scanner scanner = new Scanner(is);
		String nextLine = scanner.nextLine();
		
		while (nextLine != null) {
			// tag everything
			List<List<CoreLabel>> out = classifier.classify(nextLine);

			// loop over the tags and pull out just the relevant ones
			// try to pair up first and last names if possible
			for (int i = 0; i < out.size(); i++) {
				List<CoreLabel> sentence = out.get(i);

				int lastFound = -1; // used for pairing up first + last names
				String nameString = ""; // used to store the first+last name
										// pairs.
				for (int j = 0; j < sentence.size(); j++) {
					CoreLabel word = sentence.get(j);

					// if it is tagged as "PERSON"...
					if (word.getString(CoreAnnotations.AnswerAnnotation.class)
							.equals("PERSON") && containsLetters(word.word())) {

						// check to see the coordinate of the previously found
						// name
						// if we've already found one in this sentence...
						if (lastFound != -1) {

							// if the previous name was directly prior, it's
							// probably combined with this name (should catch
							// middle
							// names too)
							// NOTE: Stanford separates out punctuation from
							// words,
							// so this should NOT catch sentences that end then
							// start with names.
							if (lastFound == j - 1) {

								// just add a space then the current name, as
								// they
								// likely belong to the same individual
								nameString += " " + word.word();

								// the previous name was not directly prior, it
								// likely does not belong to the same
								// individual,
								// and should be separated from them.
							} else {
								nameString += ";" + word.word();
							}

							// if we haven't found another name this sentence...
						} else {

							// if the nameString is empty, start it up
							if (nameString.equals("")) {
								nameString += word.word();

								// otherwise, append a separtor to the string,
								// then
								// add the new name.
							} else {
								nameString += ";" + word.word();
							}
						}
						lastFound = j;
					}
				}

				if (!nameString.equals("")) {
					String[] nameArray = nameString.split(";");
					for (String name : nameArray) {
						names.add(name);
					}
				}
			}
			if (scanner.hasNext())
				nextLine = scanner.nextLine();
			else 
				break;
		}
		
		scanner.close();
		
		//maybe
		List<String> culled = removeDuplicates(names);
		List<String> finalList = new ArrayList<String>();
		for (String s : culled){
			finalList.add(WordUtils.capitalize(s));
		}
		//finish this
		
		return finalList;
	}

	/**
	 * Removes duplicates based on the strictness of the parser. MINIMAL:
	 * removes only word-for-word duplicates
	 * 
	 * STRICT: removes all duplicate names, preserving longer names over shorter
	 * names.
	 * 
	 * @param names
	 */
	private List<String> removeDuplicates(List<String> names) {
		
		List<String> culledList = new ArrayList<String>();
		Map<Integer,List<String>> strictMap = null;
		Integer highest = null;
		
		if (strictness != Strictness.MINIMAL) {
			strictMap = new HashMap<Integer, List<String>>();
			highest = 0;
		}
		
		// loop through all found names
		for (String name : names) {
			name = name.toLowerCase();
			// if it doesn't contain the name, move onto the next step
			if (!culledList.contains(name)) {

				// if the strictness is minimal, just add it to the list
				if (strictness == Strictness.MINIMAL) {
					culledList.add(name);

				} else {
					String[] parts = name.split(" ");
					int n = parts.length;
					if (strictMap.containsKey(n)){
						if (!strictMap.get(n).contains(name)){
							strictMap.get(n).add(name);
						}
					} else {
						strictMap.put(n, new ArrayList<String>());
						strictMap.get(n).add(name);
						
						if (n > highest){
							highest = n;
						}
					}
				}
			}
		}
		
		if (strictness == Strictness.MINIMAL) {
			return culledList;

		} else if (strictness == Strictness.FIRSTLAST) {

			for(int key : strictMap.keySet()){
				if (key != 1){
					for (String name : strictMap.get(key)){
						culledList.add(name);
					}
				}
			}
			if (strictMap.containsKey(1)) {
				for (String singleName : strictMap.get(1)) {
					if (!alreadyPresent(culledList, singleName)) {
						culledList.add(singleName);
					}
				}
			}
			return culledList;
		} else if (strictness == Strictness.STRICT) {
			for (int n = highest; n > 1; n--) {
				if (strictMap.containsKey(n)) {
					for (String name : strictMap.get(n)) {
						if (!alreadyPresent(culledList, name)) {
							culledList.add(name);
						}
					}
				}
			}
			return culledList;
		} else {
			System.out.println("Unreachable code Exception! Parsing failed...");
			return null;
		}

	}
	
	private boolean containsLetters(String s){
		for (char c: s.toCharArray()){
			if (Character.isLetter(c)){
				return true;
			}
		}
		return false;
	}

	/**
	 * Determines if a name is present
	 * 
	 * @param names
	 * @param testName
	 * @return
	 */
	private boolean alreadyPresent(List<String> names, String testName) {

		//if the list passed in is empty, return false
		if (names.size() == 0)
			return false;

		//if we're doing firstlast, just compare single names with the first/last names
		if (strictness == Strictness.FIRSTLAST) {
			for (String fullName : names) {
				String[] parts = fullName.split(" ");
				if (parts.length > 1) {
					if (parts[0].equalsIgnoreCase(testName) || parts[parts.length-1].equalsIgnoreCase(testName)) {
						return true;
					}
				} else {
					if (fullName.equalsIgnoreCase(testName)) {
						return true;
					}
				}
			}
			
		//otherwise, we need to compare multiple name segments against one another
		} else if (strictness == Strictness.STRICT) {
			for (String fullName : names){
				String[] savedParts = fullName.split(" ");
				String[] newParts = testName.split(" ");
				int savedSize = savedParts.length;
				int newSize = newParts.length;
				//break up the names into pieces and compare them in sets of newSize. If all match, return true.
				for (int i = 0; i<savedSize-newSize; i++){
					boolean matched = true;
					for(int j = 0; j<newSize; j++){
						if (!(savedParts[i+j].equalsIgnoreCase(newParts[j]))){
							matched = false;
							break;
						}
					}
					if (matched) {
						return true;
					}
				}
				
			}
			return false;
		}
		return false;
	}
}

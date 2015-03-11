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
	 * Constructor. Takes the string which is to be parsed Default strictness of firstlast is used.
	 * 
	 * @param s
	 *            the content string
	 */
	public StanfordCharacterParser(InputStream in) {
		super(in);
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
		
		for (String s: names){
			System.out.println("PreRemoval: "+s);
		}
		
		List<String> culled = removeDuplicates(names);
		for (String s: culled){
			System.out.println("PostRemova: "+s);
		}
		
		List<String> finalList = new ArrayList<String>();
		for (String s : culled){
			finalList.add(WordUtils.capitalize(s));
		}
		
		
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
		
		strictMap = new HashMap<Integer, List<String>>();
		highest = 0;

		// loop through all found names
		for (String name : names) {
			name = name.toLowerCase();
			// if it doesn't contain the name, move onto the next step
			if (!culledList.contains(name)) {
				String[] parts = name.split(" ");
				int n = parts.length;
				if (strictMap.containsKey(n)) {
					if (!strictMap.get(n).contains(name)) {
						strictMap.get(n).add(name);
					}
				} else {
					strictMap.put(n, new ArrayList<String>());
					strictMap.get(n).add(name);

					if (n > highest) {
						highest = n;
					}
				}
			}
		}
		
		//go through the map and remove exact duplicates and add them to the list. 
		for (int n = highest; n > 0; n--) {
			if (strictMap.containsKey(n)) {
				for (String name : strictMap.get(n)) {
					if (!alreadyPresent(culledList, name)) {
						culledList.add(name);
					}
				}
			}
		}
		return culledList;
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

		// if the list passed in is empty, return false
		if (names.size() == 0)
			return false;

		for (String fullName : names) {
			String[] savedParts = fullName.split(" ");
			String[] newParts = testName.split(" ");
			int savedSize = savedParts.length;
			int newSize = newParts.length;

			if (newSize == 1) {
				for (int i = 0; i < savedSize; i++) {
					if (savedParts[i].equalsIgnoreCase(testName))
						return true;
				}
			} else if (newSize == savedSize) {
				if (fullName.equalsIgnoreCase(testName))
					return true;

			} else {
				// break up the names into pieces and compare them in sets
				// of newSize. If all match, return true.
				for (int i = 0; i < savedSize - newSize; i++) {
					boolean matched = true;
					for (int j = 0; j < newSize; j++) {

						if (!(savedParts[i + j].equalsIgnoreCase(newParts[j]))) {
							matched = false;
							break;
						}
					}
					if (matched) {
						return true;
					}
				}
			}

		}
		return false;
	}
}

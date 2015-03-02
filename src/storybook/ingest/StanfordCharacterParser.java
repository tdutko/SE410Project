package storybook.ingest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class StanfordCharacterParser implements Parser {

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

	private String words;
	private Strictness strictness;

	/**
	 * Constructor. Takes the string which is to be parsed
	 * 
	 * @param s
	 *            the content string
	 */
	public StanfordCharacterParser(String s) {
		words = s;
		strictness = Strictness.MINIMAL;
	}

	/**
	 * Constructor. Takes the string which is to be parsed and a Strictness
	 * parameter
	 * 
	 * @param s
	 * @param strict
	 */
	public StanfordCharacterParser(String s, Strictness strict) {
		words = s;
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

		// tag everything
		List<List<CoreLabel>> out = classifier.classify(words);

		// initialize the list of names to return
		List<String> names = new ArrayList<String>();

		// loop over the tags and pull out just the relevant ones
		// try to pair up first and last names if possible
		for (int i = 0; i < out.size(); i++) {
			List<CoreLabel> sentence = out.get(i);

			int lastFound = -1; // used for pairing up first + last names
			String nameString = ""; // used to store the first+last name pairs.
			for (int j = 0; j < sentence.size(); j++) {
				CoreLabel word = sentence.get(j);

				// if it is tagged as "PERSON"...
				if (word.getString(CoreAnnotations.AnswerAnnotation.class)
						.equals("PERSON")) {

					// check to see the coordinate of the previously found name
					// if we've already found one in this sentence...
					if (lastFound != -1) {

						// if the previous name was directly prior, it's
						// probably combined with this name (should catch middle
						// names too)
						// NOTE: Stanford separates out punctuation from words,
						// so this should NOT catch sentences that end then
						// start with names.
						if (lastFound == j - 1) {

							// just add a space then the current name, as they
							// likely belong to the same individual
							nameString += " " + word.word();

							// the previous name was not directly prior, it
							// likely does not belong to the same individual,
							// and should be separated from them.
						} else {
							nameString += ";" + word.word();
						}

						// if we haven't found another name this sentence...
					} else {

						// if the nameString is empty, start it up
						if (nameString.equals("")) {
							nameString += word.word();

							// otherwise, append a separtor to the string, then
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

		return removeDuplicates(names);
	}

	/**
	 * Removes duplicates bassed on the strictness of the parser. MINIMAL:
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
			
			for (String singleName : strictMap.get(1)) {
				if (!alreadyPresent(culledList, singleName)) {
					culledList.add(singleName);
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
					if (parts[0].equals(testName) || parts[parts.length-1].equals(testName)) {
						return true;
					}
				} else {
					if (fullName.equals(testName)) {
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
						if (!(savedParts[i+j].equals(newParts[j]))){
							matched = false;
							break;
						}
					}
					if (matched)
						return true;
				}
				
			}
			return false;
		}
		

		return false;
	}

	/* Test Main method TODO remove this
	public static void main(String args[]) {
		String sourcePath = "C:/Users/Mordio/Desktop/test.txt";
		Ingestor ing = new TextFileIngestor(sourcePath);
		String testString = ing.getContent();
		
		System.out.println("Testing Parser using the statement:\n"+testString+"\n\n");

		System.out.println("MINIMAL Strictness: ");
		{
			StanfordCharacterParser scp = new StanfordCharacterParser(
					testString, Strictness.MINIMAL);

			List<String> names = scp.getTokens();

			for (String s : names) {
				System.out.println(s + " is a name.");
			}
		}
		
		System.out.println("\nFIRSTLAST Strictness: ");
		{
			StanfordCharacterParser scp = new StanfordCharacterParser(
					testString, Strictness.FIRSTLAST);

			List<String> names = scp.getTokens();

			for (String s : names) {
				System.out.println(s + " is a name.");
			}
		}
		
		System.out.println("\nSTRICT Strictness: ");
		{
			StanfordCharacterParser scp = new StanfordCharacterParser(
					testString, Strictness.STRICT);

			List<String> names = scp.getTokens();

			for (String s : names) {
				System.out.println(s + " is a name.");
			}
		}
	}
	*/
}
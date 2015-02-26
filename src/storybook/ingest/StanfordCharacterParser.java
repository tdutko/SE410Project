package storybook.ingest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;

/**
 * This parser uses Stanford's POS tagger to pick out all of the names from the document (ideally pairing up first and last names into a single token)
 * Takes in raw text as input and returns solely the materials tagged as "PERSON". No duplicates should be permitted.
 * 
 * @author Travis Dutko
 */
public class StanfordCharacterParser implements Parser{

	private String words;
	
	/**
	 * Constructor. Takes the string which is to be parsed
	 * @param s the content string
	 */
	public StanfordCharacterParser(String s){
		words = s;
	}
	
	public List<String> getTokens(){

		//intialize the classifier
		AbstractSequenceClassifier<CoreLabel> classifier = null;
		try {
			classifier = CRFClassifier.getClassifier("classifiers/english.all.3class.distsim.crf.ser.gz");
		} catch (ClassCastException | ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		
		//tag everything
		List<List<CoreLabel>> out = classifier.classify(words);
		
		//initialize the list of names to return
		List<String> names = new ArrayList<String>();
		
		//loop over the tags and pull out just the relevant ones
		//try to pair up first and last names if possible
		int lastI = -1; //used for pairing up first + last names
		for (int i = 0; i< out.size(); i++){
			List<CoreLabel> sentence = out.get(i);
			
			int lastJ = -1; //used for pairing up first + last names
			for (int j = 0; j<sentence.size(); j++){
				CoreLabel word = sentence.get(j);
				
				//if it is tagged as "PERSON"...
				if (word.getString(CoreAnnotations.AnswerAnnotation.class).equals("PERSON")){
					System.out.println(word.word()+" is a name");
				
					//FIXME temporary! remove this later adds first+last names separately
					names.add(word.word());
				}
				//if not, move along
				
				lastJ++;
			}
			lastI ++;
		}
		
		return names;
	}
	
}

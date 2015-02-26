package storybook.ingest;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import edu.stanford.nlp.ie.AbstractSequenceClassifier;
import edu.stanford.nlp.ie.crf.CRFClassifier;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;


/**
 * This class pulls out the text from a file, tags the individual words, and
 * saves them as an array for processing
 * @author Travis Dutko
 */
public class TextFileIngestor implements Ingestor{

	private File file;
	
	/**
	 * Constructor. Takes the file to ingest as a parameter
	 * @param f
	 */
	public TextFileIngestor(File f){
		file = f;
	}
	
	/**
	 * Constructor. Takes the filepath to the file to ingest as a parameter
	 * @param s
	 */
	public TextFileIngestor(String s){
		file = new File(s);
	}

	@Override
	public String getContent() {
		//this will store the file contents
		String contents = "";
		
		//Perform the read-in from the file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String nextLine = reader.readLine();
			
			while (nextLine != null){
				contents += nextLine +"\n";
				nextLine = reader.readLine();
			}
			reader.close();
			
		//handle potential exceptions	
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while reading file!");;
			e.printStackTrace();
		}
		
		//contents should now have the file contents
		//split it on spaces and return it to the calling method
		return contents;
	}
	
}

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
 * 
 * @author Travis Dutko
 */
public class TextFileIngestor implements Ingestor {

	private File file;

	/**
	 * Constructor. Takes the file to ingest as a parameter
	 * 
	 * @param f
	 */
	public TextFileIngestor(File f) {
		file = f;
	}

	/**
	 * Constructor. Takes the filepath to the file to ingest as a parameter
	 * 
	 * @param s
	 */
	public TextFileIngestor(String s) {
		file = new File(s);
	}

	@Override
	public String getContent() {
		return getContentPieceByPiece(-1,-1);
	}

	@Override
	public String getContentPieceByPiece(int iteration, int numLines) {
		// this will store the file contents
		String contents = "";

		// Perform the read-in from the file
		try {
			BufferedReader reader = new BufferedReader(new FileReader(file));
			String nextLine = "";

			//if we're just grabbing a pice, grab it piece by piece
			if (numLines > 0 && iteration >= 0) {
				for (int i = 0; i<iteration*numLines; i++){
					reader.readLine();
				}
				nextLine = reader.readLine();
				
				//the double conditional of the j and while ensures that we stop at either end of file
				//or at the number of lines we're supposed to take, whatever occurs first.
				int j = 0;
				while (nextLine != null){
					if (j > numLines)
						break;
					contents += nextLine +"\n";
					nextLine = reader.readLine();
				}
				
			//if either one of the above is -1, grab everything
			} else {
				nextLine = reader.readLine();
				while (nextLine != null) {
					contents += nextLine + "\n";
					nextLine = reader.readLine();
				}
			}
			reader.close();

			// handle potential exceptions
		} catch (FileNotFoundException e) {
			System.out.println("File not found!");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("Error while reading file!");
			;
			e.printStackTrace();
		}
		
		return contents;
	}

}

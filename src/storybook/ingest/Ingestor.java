package storybook.ingest;

/**
 * An interface for a standard ingestor. This is should allow for the capability
 * to read new filetypes to be added with minimal modification to the rest of
 * the code.
 * 
 * @author Travis Dutko
 */
public interface Ingestor {

	/**
	 * Ingests text from the file passed in to the Ingestor's constructor
	 * 
	 * @return the string contents (potentially formatted) which were extracted
	 *         from the file.
	 */
	public String getContent();

}

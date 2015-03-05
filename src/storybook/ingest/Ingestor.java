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

	/**
	 * For use with larger files such as full books, this is used to digest them piece by piece
	 * so as to prevent there from being a large memory impact.
	 * @param div
	 * @param size
	 * @return
	 */
	public String getContentPieceByPiece(int iteration, int numLines);
}

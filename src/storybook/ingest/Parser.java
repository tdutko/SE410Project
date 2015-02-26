package storybook.ingest;

import java.util.List;

/**
 * An interface for a standard parser. This is should allow for the capability to read new content and formats to be added
 * with minimal modification to the rest of the code.
 * 
 * @author Travis Dutko
 */
public interface Parser {

	/**
	 * Parses the text passed in via the parser's constructor
	 * @return the list of relevant tokens
	 */
	public List<String> getTokens();
	
}

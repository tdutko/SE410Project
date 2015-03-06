package storybook.ingest;

import java.io.InputStream;
import java.util.List;

/**
 * An interface for a standard parser. This is should allow for the capability
 * to read new content and formats to be added with minimal modification to the
 * rest of the code.
 * 
 * @author Travis Dutko
 */
public abstract class Parser {

	protected InputStream is;
	
	protected Parser(InputStream in) {
		is = in;
	}
	
	/**
	 * Returns the list of relevant tokens to the caller. Parser class determines the token returned
	 * 
	 * @return the list of relevant tokens
	 */
	public abstract List<String> getTokens();

}

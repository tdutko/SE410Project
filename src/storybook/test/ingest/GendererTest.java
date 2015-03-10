package storybook.test.ingest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.*;

import storybook.ingest.Genderer;
import static org.junit.Assert.*;

/**
 * The class <code>GendererTest</code> contains tests for the class <code>{@link Genderer}</code>.
 *
 * @generatedBy CodePro at 3/10/15 4:11 PM
 * @author priyal patel
 * @version $Revision: 1.0 $
 */
public class GendererTest {
	/**
	 * Run the Map<String, String> genderCharacters(List<String>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 4:11 PM
	 */
	@Test
	public void testGenderCharacters_1()
		throws Exception {
		List<String> names = new ArrayList<String>();

		Map<String, String> result = Genderer.genderCharacters(names);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/C:/Users/priyal patel/Documents/GitHub/SE410Project/lib/javax.ws.rs-api-2.0.1.jar!/javax/ws/rs/client/ClientBuilder.class to jar:file:/C:/Users/priyal patel/Documents/GitHub/SE410Project/lib/javax.ws.rs-api-2.0.1.jar!/javax/ws/rs/client/ClientBuilder.class
		//       at javax.ws.rs.client.ClientBuilder.newBuilder(ClientBuilder.java:97)
		//       at com.github.irobson.jgenderize.client.DefaultGenderize.<init>(DefaultGenderize.java:15)
		//       at com.github.irobson.jgenderize.GenderizeIoAPI.create(GenderizeIoAPI.java:11)
		//       at storybook.ingest.Genderer.genderCharacters(Genderer.java:14)
		assertNotNull(result);
	}

	/**
	 * Run the Map<String, String> genderCharacters(List<String>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 4:11 PM
	 */
	@Test
	public void testGenderCharacters_2()
		throws Exception {
		List<String> names = new ArrayList<String>();

		Map<String, String> result = Genderer.genderCharacters(names);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/C:/Users/priyal patel/Documents/GitHub/SE410Project/lib/javax.ws.rs-api-2.0.1.jar!/javax/ws/rs/client/ClientBuilder.class to jar:file:/C:/Users/priyal patel/Documents/GitHub/SE410Project/lib/javax.ws.rs-api-2.0.1.jar!/javax/ws/rs/client/ClientBuilder.class
		//       at javax.ws.rs.client.ClientBuilder.newBuilder(ClientBuilder.java:97)
		//       at com.github.irobson.jgenderize.client.DefaultGenderize.<init>(DefaultGenderize.java:15)
		//       at com.github.irobson.jgenderize.GenderizeIoAPI.create(GenderizeIoAPI.java:11)
		//       at storybook.ingest.Genderer.genderCharacters(Genderer.java:14)
		assertNotNull(result);
	}

	/**
	 * Run the Map<String, String> genderCharacters(List<String>) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 4:11 PM
	 */
	@Test
	public void testGenderCharacters_3()
		throws Exception {
		List<String> names = new ArrayList<String>();

		Map<String, String> result = Genderer.genderCharacters(names);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.LinkageError: ClassCastException: attempting to castjar:file:/C:/Users/priyal patel/Documents/GitHub/SE410Project/lib/javax.ws.rs-api-2.0.1.jar!/javax/ws/rs/client/ClientBuilder.class to jar:file:/C:/Users/priyal patel/Documents/GitHub/SE410Project/lib/javax.ws.rs-api-2.0.1.jar!/javax/ws/rs/client/ClientBuilder.class
		//       at javax.ws.rs.client.ClientBuilder.newBuilder(ClientBuilder.java:97)
		//       at com.github.irobson.jgenderize.client.DefaultGenderize.<init>(DefaultGenderize.java:15)
		//       at com.github.irobson.jgenderize.GenderizeIoAPI.create(GenderizeIoAPI.java:11)
		//       at storybook.ingest.Genderer.genderCharacters(Genderer.java:14)
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 3/10/15 4:11 PM
	 */
	@Before
	public void setUp()
		throws Exception {
		// add additional set up code here
	}

	/**
	 * Perform post-test clean-up.
	 *
	 * @throws Exception
	 *         if the clean-up fails for some reason
	 *
	 * @generatedBy CodePro at 3/10/15 4:11 PM
	 */
	@After
	public void tearDown()
		throws Exception {
		// Add additional tear down code here
	}

	/**
	 * Launch the test.
	 *
	 * @param args the command line arguments
	 *
	 * @generatedBy CodePro at 3/10/15 4:11 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(GendererTest.class);
	}
}
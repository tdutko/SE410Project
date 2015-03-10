package storybook.test.importer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import storybook.ui.MainFrame;
import storybook.importer.NatLangImporter;
import storybook.model.hbn.entity.Person;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * The class <code>NatLangImporterTest</code> contains tests for the class <code>{@link NatLangImporter}</code>.
 *
 * @generatedBy CodePro at 3/10/15 12:18 PM
 * @author priyal patel
 * @version $Revision: 1.0 $
 */
public class NatLangImporterTest {
	/**
	 * Run the NatLangImporter(MainFrame,InputStream) constructor test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 12:18 PM
	 */
	@Test
	public void testNatLangImporter_1()
		throws Exception {
		MainFrame mf = new MainFrame();
		InputStream is = new ByteArrayInputStream("".getBytes());

		NatLangImporter result = new NatLangImporter(mf, is);

		// add additional test code here
		assertNotNull(result);
	}

	/**
	 * Run the Person[] extractPersons() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 12:18 PM
	 */
	@Test
	public void testExtractPersons_1()
		throws Exception {
		NatLangImporter fixture = new NatLangImporter(new MainFrame(), new ByteArrayInputStream("".getBytes()));

		Person[] result = fixture.extractPersons();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.util.NoSuchElementException: No line found
		//       at java.util.Scanner.nextLine(Unknown Source)
		//       at storybook.ingest.StanfordCharacterParser.getTokens(StanfordCharacterParser.java:79)
		//       at storybook.importer.NatLangImporter.extractPersons(NatLangImporter.java:26)
		assertNotNull(result);
	}

	/**
	 * Run the Person[] extractPersons() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 12:18 PM
	 */
	@Test
	public void testExtractPersons_2()
		throws Exception {
		NatLangImporter fixture = new NatLangImporter(new MainFrame(), new ByteArrayInputStream("".getBytes()));

		Person[] result = fixture.extractPersons();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.util.NoSuchElementException: No line found
		//       at java.util.Scanner.nextLine(Unknown Source)
		//       at storybook.ingest.StanfordCharacterParser.getTokens(StanfordCharacterParser.java:79)
		//       at storybook.importer.NatLangImporter.extractPersons(NatLangImporter.java:26)
		assertNotNull(result);
	}

	/**
	 * Perform pre-test initialization.
	 *
	 * @throws Exception
	 *         if the initialization fails for some reason
	 *
	 * @generatedBy CodePro at 3/10/15 12:18 PM
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
	 * @generatedBy CodePro at 3/10/15 12:18 PM
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
	 * @generatedBy CodePro at 3/10/15 12:18 PM
	 */
	public static void main(String[] args) {
		new org.junit.runner.JUnitCore().run(NatLangImporterTest.class);
	}
}
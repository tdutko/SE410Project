package storybook.test.importer;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import storybook.ui.MainFrame;
import storybook.importer.Importer;
import storybook.importer.NatLangImporter;
import storybook.model.hbn.entity.Gender;

import org.junit.*;

import static org.junit.Assert.*;

/**
 * The class <code>ImporterTest</code> contains tests for the class <code>{@link Importer}</code>.
 *
 * @generatedBy CodePro at 3/10/15 12:18 PM
 * @author priyal patel
 * @version $Revision: 1.0 $
 */
public class ImporterTest {
	/**
	 * Run the Gender getGender(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 12:18 PM
	 */
	@Test
	public void testGetGender_1()
		throws Exception {
		Importer fixture = new NatLangImporter(new MainFrame(), new ByteArrayInputStream("".getBytes()));
		String g = "";

		Gender result = fixture.getGender(g);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at storybook.importer.Importer.getGender(Importer.java:43)
		assertNotNull(result);
	}

	/**
	 * Run the Gender getGender(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 12:18 PM
	 */
	@Test
	public void testGetGender_2()
		throws Exception {
		Importer fixture = new NatLangImporter(new MainFrame(), new ByteArrayInputStream("".getBytes()));
		String g = "";

		Gender result = fixture.getGender(g);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at storybook.importer.Importer.getGender(Importer.java:43)
		assertNotNull(result);
	}

	/**
	 * Run the Gender getGender(String) method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 12:18 PM
	 */
	@Test
	public void testGetGender_3()
		throws Exception {
		Importer fixture = new NatLangImporter(new MainFrame(), new ByteArrayInputStream("".getBytes()));
		String g = "";

		Gender result = fixture.getGender(g);

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.lang.NullPointerException
		//       at storybook.importer.Importer.getGender(Importer.java:43)
		assertNotNull(result);
	}

	/**
	 * Run the InputStream getInputStream() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 12:18 PM
	 */
	@Test
	public void testGetInputStream_1()
		throws Exception {
		Importer fixture = new NatLangImporter(new MainFrame(), new ByteArrayInputStream("".getBytes()));

		InputStream result = fixture.getInputStream();

		// add additional test code here
		assertNotNull(result);
		assertEquals(-1, result.read());
		assertEquals(0, result.available());
		assertEquals(true, result.markSupported());
	}

	/**
	 * Run the void importData() method test.
	 *
	 * @throws Exception
	 *
	 * @generatedBy CodePro at 3/10/15 12:18 PM
	 */
	@Test
	public void testImportData_1()
		throws Exception {
		Importer fixture = new NatLangImporter(new MainFrame(), new ByteArrayInputStream("".getBytes()));

		fixture.importData();

		// add additional test code here
		// An unexpected exception was thrown in user code while executing this test:
		//    java.util.NoSuchElementException: No line found
		//       at java.util.Scanner.nextLine(Unknown Source)
		//       at storybook.ingest.StanfordCharacterParser.getTokens(StanfordCharacterParser.java:79)
		//       at storybook.importer.NatLangImporter.extractPersons(NatLangImporter.java:26)
		//       at storybook.importer.Importer.importData(Importer.java:52)
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
		new org.junit.runner.JUnitCore().run(ImporterTest.class);
	}
}
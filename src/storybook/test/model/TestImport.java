package storybook.test.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import storybook.SbApp;
import storybook.importer.Importer;
import storybook.importer.NatLangImporter;
import storybook.ui.MainFrame;

public class TestImport {
	
	// This is only an example of usage... might not actually work.
	// TODO: Juan and Priyal to do this correctly.
	
	public void main(String[] args) {
		SbApp sb = SbApp.getInstance();
		MainFrame mf = sb.getMainFrames().get(0);
		File f = new File("testfile.txt");
		InputStream fis;
		try {
			fis = new FileInputStream(f);
			Importer importer = new NatLangImporter(mf, fis);
			importer.importData();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}

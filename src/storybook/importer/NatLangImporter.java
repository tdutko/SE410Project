package storybook.importer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import storybook.ingest.StanfordCharacterParser;
import storybook.model.hbn.entity.Person;
import storybook.ui.MainFrame;

public class NatLangImporter extends Importer {

	public NatLangImporter(MainFrame mf, InputStream is) {
		super(mf, is);
	}

	@Override
	public Person[] extractPersons() {
		List<Person> persons = new ArrayList<Person>();
		
		StanfordCharacterParser parser = new StanfordCharacterParser(getInputStream());
		
		List<String> names = parser.getTokens();
		
		for (String n : names) {
			Person p = new Person();
			
			String firstName = n.split(" ")[0];
			String lastName = n.substring(firstName.length()).trim();
			
			p.setFirstname(firstName);
			p.setLastname(lastName);
			
			persons.add(p);
		}
		
		return persons.toArray(new Person[persons.size()]);
	}

}

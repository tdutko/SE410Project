package storybook.importer;

import java.util.ArrayList;
import java.util.List;

import storybook.ingest.Ingestor;
import storybook.ingest.StanfordCharacterParser;
import storybook.model.hbn.entity.Person;
import storybook.ui.MainFrame;

public class NatLangImporter extends Importer {

	public NatLangImporter(MainFrame mf, Ingestor i) {
		super(mf, i);
	}

	@Override
	public Person[] extractPersons() {
		List<Person> persons = new ArrayList<Person>();
		
		String text = ingestor.getContent();
		StanfordCharacterParser parser = new StanfordCharacterParser(text);
		
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

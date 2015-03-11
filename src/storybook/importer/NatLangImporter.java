package storybook.importer;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import storybook.model.hbn.entity.Gender;
import storybook.model.hbn.entity.Person;
import storybook.ui.MainFrame;

public class NatLangImporter extends Importer {

	public NatLangImporter(MainFrame mf, InputStream is) {
		super(mf, is);
	}

	// Extracts Persons from InputStream through natural language processing, 
	// extracting and setting first name, last name, abbreviation, and gender if available.
	@Override
	public Person[] extractPersons() {
		List<Person> persons = new ArrayList<Person>();
		
		StanfordCharacterParser parser = new StanfordCharacterParser(getInputStream());
		
		List<String> names = parser.getTokens();
		Map<String, String> genders = Genderer.genderCharacters(names);
		
		for (String n : names) {
			Person p = new Person();
			
			String firstName = n.split(" ")[0];
			String lastName = n.substring(firstName.length()).trim();
			String abbreviation = firstName.substring(0, firstName.length() >= 2 ? 2 : firstName.length())
					+ lastName.substring(0, lastName.length() >= 2 ? 2 : lastName.length());
			String g = genders.get(n);
			Gender gender = getGender(g);
			
			p.setFirstname(firstName);
			p.setLastname(lastName);
			p.setAbbreviation(abbreviation);
			if (gender != null)
				p.setGender(gender);
			
			persons.add(p);
		}
		
		return persons.toArray(new Person[persons.size()]);
	}

}

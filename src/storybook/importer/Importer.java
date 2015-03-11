package storybook.importer;

import java.io.InputStream;

import org.hibernate.Session;

import storybook.model.BookModel;
import storybook.model.hbn.dao.PersonDAOImpl;
import storybook.model.hbn.entity.Gender;
import storybook.model.hbn.entity.Person;
import storybook.ui.MainFrame;

public abstract class Importer {
	
	private MainFrame mainFrame;
	private InputStream inputStream;
	
	public Importer(MainFrame mf, InputStream is) {
		mainFrame = mf;
		inputStream = is;
	}
	
	// Formally inserts given persons into the given book model.
	private void importPersons(BookModel model, Person[] persons) {
		Session session = model.beginTransaction();
		PersonDAOImpl personDAO = new PersonDAOImpl(session);
		personDAO.save(persons);
		model.commit();
	}
	
	// @param g : String of gender to convert to Gender object ("male" or "female")
	// @return Male or Female Gender object, or null if neither
	public Gender getGender(String g) {
		Gender gender = null;
		long genderId = 0L;
		
		if (g == null)
			return null;

		if (g.equalsIgnoreCase("male"))
			genderId = 1L;
		else if (g.equalsIgnoreCase("female"))
			genderId = 2L;
		else
			return null;

		Session session = mainFrame.getBookModel().getSession();
		session.beginTransaction();
		gender = (Gender) session.get(Gender.class, genderId);
		session.getTransaction().commit();

		return gender;
	}
	
	// getter for ivar inputStream
	public InputStream getInputStream() {
		return inputStream;
	}

	// @return an array of Person model objects extracted from the InputStream
	protected abstract Person[] extractPersons();
	
	// Extracts and imports relevant plot objects into the current BookModel.
	public void importData() {
		Person[] persons = extractPersons();
		importPersons(mainFrame.getBookModel(), persons);
		
		// More extracts/imports can be added here, 
		// e.g. extractLocations() with importLocations()...
	}

}

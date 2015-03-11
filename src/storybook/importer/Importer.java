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
	
	private void importPersons(BookModel model, Person[] persons) {
		Session session = model.beginTransaction();
		PersonDAOImpl personDAO = new PersonDAOImpl(session);
		personDAO.save(persons);
		model.commit();
	}
	
	protected InputStream getInputStream() {
		return inputStream;
	}
	
	protected Gender getGender(String g) {
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

	protected abstract Person[] extractPersons();
	
	public void importData() {
		Person[] persons = extractPersons();
		importPersons(mainFrame.getBookModel(), persons);
	}

}

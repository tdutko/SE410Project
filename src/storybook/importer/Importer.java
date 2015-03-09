package storybook.importer;

import java.io.InputStream;

import org.hibernate.Session;

import storybook.model.BookModel;
import storybook.model.hbn.dao.PersonDAOImpl;
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
	
	protected abstract Person[] extractPersons();
	
	public void importData() {
		Person[] persons = extractPersons();
		importPersons(mainFrame.getBookModel(), persons);
	}

}

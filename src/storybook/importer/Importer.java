package storybook.importer;

import org.hibernate.Session;

import storybook.ingest.Ingestor;
import storybook.model.BookModel;
import storybook.model.hbn.dao.PersonDAOImpl;
import storybook.model.hbn.entity.Person;
import storybook.ui.MainFrame;

public abstract class Importer {
	
	private MainFrame mainFrame;
	protected Ingestor ingestor;
	
	public Importer(MainFrame mf, Ingestor i) {
		mainFrame = mf;
		ingestor = i;
	}
	
	public void importData() {
		Person[] persons = extractPersons();
		importPersons(mainFrame.getBookModel(), persons);
	}
	
	private void importPersons(BookModel model, Person[] persons) {
		Session session = model.beginTransaction();
		PersonDAOImpl personDAO = new PersonDAOImpl(session);
		personDAO.save(persons);
		model.commit();
	}
	
	protected abstract Person[] extractPersons();

}

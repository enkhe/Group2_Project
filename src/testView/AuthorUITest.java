package testView;
/**
 * TCSS360 
 * 
 * @author Enkhamgalan Baterdene
 * @version v1.0
 */
import static org.junit.Assert.assertEquals;
import model.Author;
import model.Conference;
import model.Manuscript;
import model.RegisteredUser;

import org.junit.Before;
import org.junit.Test;

import view.AuthorUI;

public class AuthorUITest {
	
	@Test
	public void testAuthorManuscriptSubmissionOnValidSubmission() {
		result1 = myAuthorUI.controllerAuthorSubmitManuscript(manuscript1);
		assertEquals("Sucessful submission results in 0.", expectedValid, result1);
	}
	
	@Test
	public void testAuthorManuscriptSubmissionOnInvalidSubmission() {
		myAuthorUI.controllerAuthorSubmitManuscript(manuscript1);
		result2 = myAuthorUI.controllerAuthorSubmitManuscript(manuscript1);
		assertEquals("Unsucessful submission results in -1.", expectedInvalid, result2);
	}
	
	@Test
	public void authorManuscriptRemovalTest() {
		// Arrange
		myAuthorUI.controllerAuthorSubmitManuscript(manuscript1);
		expectedValid = 0;
		expectedInvalid = -1;
		
		// Act
		result1 = myAuthorUI.controllerRemoveManuscript(manuscript1);
		result2 = myAuthorUI.controllerRemoveManuscript(manuscript1);
		
		// Assert
		assertEquals("Sucessful removal results in 0.", expectedValid, result1);
		assertEquals("Unsucessful removal results in -1.", expectedInvalid, result2);
	}
	
	@Before
	public void beforeAllTests() {
		theRegU = new RegisteredUser();
		theConf = new Conference();
		theConf.setConferenceName("The Con");
		myAuthorUI = new AuthorUI(theRegU, theConf);
		result1 = -1;
		result2 = -1;
		expectedValid = 0;
		expectedInvalid = -1;
		setUpAuthors();
		setUpManuscripts();
	}
	
	private void setUpAuthors() {
		author1 =  new Author(new RegisteredUser("fName1", "lName1", "uName1", 1));
		author2 =  new Author(new RegisteredUser("fName2", "lName2", "uName2", 2));
		author3 =  new Author(new RegisteredUser("fName3", "lName3", "uName3", 3));
		author4 =  new Author(new RegisteredUser("fName4", "lName4", "uName4", 4));
		theConf.addAuthor(author1);
		theConf.addAuthor(author2);
		theConf.addAuthor(author3);
		theConf.addAuthor(author4);
	}
	
	private void setUpManuscripts() {
		manuscript1 = new Manuscript(author1.getID(), "title1", ".\test.txt");
		manuscript2 = new Manuscript(author2.getID(), "title2", ".\test.txt");
		manuscript3 = new Manuscript(author3.getID(), "title3", "filePath3");
		manuscript4 = new Manuscript(author4.getID(), "title4", "filePath4");
	}

	private Conference theConf;
	private RegisteredUser theRegU;
	private AuthorUI myAuthorUI;
	private int result1, result2, expectedValid, expectedInvalid;
	private Manuscript manuscript1, manuscript2, manuscript3, manuscript4;
	private Author author1, author2, author3, author4;
	
}
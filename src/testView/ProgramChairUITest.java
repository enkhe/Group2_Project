package testView;

import static org.junit.Assert.*;
import model.Conference;
import model.Manuscript;
import model.RegisteredUser;
import model.SubProgramChair;

import org.junit.Before;
import org.junit.Test;

import view.ProgramChairUI;

public class ProgramChairUITest {
	SubProgramChair mySubPC;
	Conference myConference;
	Manuscript myManuscriptUserNotAuthor;
	Manuscript myManuscriptUserIsAuthor;
	ProgramChairUI myUI;
	
	@Before
	public void setUp() throws Exception {
		mySubPC = new SubProgramChair(new RegisteredUser("Test", "Test", "Test", 123));
		myManuscriptUserNotAuthor = new Manuscript();
		myManuscriptUserIsAuthor = new Manuscript(123);
		myConference = new Conference();
		myUI = new ProgramChairUI(new RegisteredUser("Test", "Test", "Test", 123),
				                  myConference);
	}

	@Test
	public void testBrCheck_SubprogamNotAuthorOnUserNotAuthor() {
		assertTrue(myUI.brCheck_SubprogamNotAuthor(myManuscriptUserNotAuthor, mySubPC));
	}
	
	@Test
	public void testBrCheck_SubprogamNotAuthorOnUserIsAuthor() {
		assertFalse(myUI.brCheck_SubprogamNotAuthor(myManuscriptUserIsAuthor, mySubPC));
	}

}

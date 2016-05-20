package testView;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Conference;
import model.Manuscript;
import model.RegisteredUser;
import model.Reviewer;
import model.SubProgramChair;

import org.junit.Before;
import org.junit.Test;

import view.SubProgramChairUI;

public class SubProgramChairUITest {

	SubProgramChair mySubPC;
	Reviewer myRev;
	RegisteredUser myUser;
	RegisteredUser myRevUser;
	Conference myCon;
	Manuscript myMan;
	SubProgramChairUI myUI;
	
	@Before
	public void setUp() throws Exception {
		myUser = new RegisteredUser("Test", "Test", "Test", 123); 
		myRevUser = new RegisteredUser("Test2", "Test2", "Test2", 345);
		mySubPC = new SubProgramChair(myUser);
		myRev = new Reviewer(myRevUser);
		myMan = new Manuscript();
		myCon = new Conference();
		myUI = new SubProgramChairUI(myUser, myCon);
	}
	
	@Test
	public void setSubProgramChairTest() {
		myCon.addSubprogramChair(myUser);
	    assertTrue(myUI.setSubProgramChair(123) == 1);
	    assertFalse(myUI.setSubProgramChair(345) == 1);
	}
	
	@Test
	public void brcheck_ReviewerNotManuscriptAuthor() {
		assertTrue(myUI.brcheck_ReviewerNotManuscriptAuthor(myRev, myMan));
		myMan = new Manuscript(345);
		assertFalse(myUI.brcheck_ReviewerNotManuscriptAuthor(myRev, myMan));
	}
	
	@Test
	public void brCheck_SubprogramChairNotOverAssignedTest() {
		assertTrue(myUI.brcheck_ReviewerNotOverAssigned(myRev));
		myRev.assignManuscript(new Manuscript());
		assertTrue(myUI.brcheck_ReviewerNotOverAssigned(myRev));
		myRev.assignManuscript(new Manuscript());
		assertTrue(myUI.brcheck_ReviewerNotOverAssigned(myRev));
		myRev.assignManuscript(new Manuscript());
		assertTrue(myUI.brcheck_ReviewerNotOverAssigned(myRev));
		myRev.assignManuscript(new Manuscript());
		assertFalse(myUI.brcheck_ReviewerNotOverAssigned(myRev));
	}

}

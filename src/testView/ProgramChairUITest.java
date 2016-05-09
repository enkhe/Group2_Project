package test;

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
	RegisteredUser myUser;
	Conference myCon;
	Manuscript myMan;
	ProgramChairUI myUI;
	
	@Before
	public void setUp() throws Exception {
		myUser = new RegisteredUser("Test", "Test", "Test", 123); 
		mySubPC = new SubProgramChair(myUser);
		myMan = new Manuscript();
		myCon = new Conference();
		myUI = new ProgramChairUI(myUser, myCon);
	}

	@Test
	public void brCheck_SubprogamNotAuthorTest() {
		assertTrue(myUI.brCheck_SubprogamNotAuthor(myMan, mySubPC));
		myMan = new Manuscript(123);
		assertFalse(myUI.brCheck_SubprogamNotAuthor(myMan, mySubPC));
	}
	
	@Test
	public void brCheck_SubprogramChairNotOverAssignedTest() {
		assertTrue(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPC));
		mySubPC.assignManuscript(new Manuscript());
		assertTrue(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPC));
		mySubPC.assignManuscript(new Manuscript());
		assertTrue(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPC));
		mySubPC.assignManuscript(new Manuscript());
		assertTrue(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPC));
		mySubPC.assignManuscript(new Manuscript());
		assertFalse(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPC));
	}

}

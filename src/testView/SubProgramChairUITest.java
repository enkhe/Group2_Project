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

	private SubProgramChair mySubPC;
	private Reviewer myRevWithOneManuscript;
	private Conference myCon;
	private SubProgramChairUI myUI;
	private SubProgramChair myInvalidSubPC;
	
	@Before
	public void setUp() throws Exception { 
		myInvalidSubPC = new SubProgramChair(new RegisteredUser("Test", "Test", "Test", 456));
		mySubPC = new SubProgramChair(new RegisteredUser("Test", "Test", "Test", 123));
		myCon = new Conference();
		myUI = new SubProgramChairUI(mySubPC, myCon);
		myCon.addSubprogramChair(new RegisteredUser("Test", "Test", "Test", 123));
	}
	
	@Test
	public void testSetSubProgramChairOnValidSubProgramChairID() {		
	    assertTrue(myUI.setSubProgramChair(mySubPC.getID()) == 1);
	}
	
	@Test
	public void testSetSubProgramChairOnInvalidSubProgramChairID() {
		assertFalse(myUI.setSubProgramChair(myInvalidSubPC.getID()) == 1);
	}
	

	
	
}


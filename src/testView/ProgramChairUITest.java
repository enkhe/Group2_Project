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
	SubProgramChair mySubPCAssignedOneManuscript;
	SubProgramChair mySubPCAssignedTwoManuscripts;
	SubProgramChair mySubPCAssignedThreeManuscripts;
	SubProgramChair mySubPCAssignedFourManuscripts;
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
		
		mySubPCAssignedOneManuscript = new SubProgramChair();
		mySubPCAssignedOneManuscript.assignManuscript(new Manuscript());
		
		mySubPCAssignedTwoManuscripts = new SubProgramChair();
		mySubPCAssignedTwoManuscripts.assignManuscript(new Manuscript());
		mySubPCAssignedTwoManuscripts.assignManuscript(new Manuscript());
		
		mySubPCAssignedThreeManuscripts = new SubProgramChair();
		mySubPCAssignedThreeManuscripts.assignManuscript(new Manuscript());
		mySubPCAssignedThreeManuscripts.assignManuscript(new Manuscript());
		mySubPCAssignedThreeManuscripts.assignManuscript(new Manuscript());
		
		mySubPCAssignedFourManuscripts = new SubProgramChair();
		mySubPCAssignedFourManuscripts.assignManuscript(new Manuscript());
		mySubPCAssignedFourManuscripts.assignManuscript(new Manuscript());
		mySubPCAssignedFourManuscripts.assignManuscript(new Manuscript());
		mySubPCAssignedFourManuscripts.assignManuscript(new Manuscript());
	}

	@Test
	public void testBrCheck_SubprogamNotAuthorOnUserNotAuthor() {
		assertTrue(myUI.brCheck_SubprogamNotAuthor(myManuscriptUserNotAuthor, mySubPC));
	}
	
	@Test
	public void testBrCheck_SubprogamNotAuthorOnUserIsAuthor() {
		assertFalse(myUI.brCheck_SubprogamNotAuthor(myManuscriptUserIsAuthor, mySubPC));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithNoAssignments() {
		assertTrue(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPC));
	}

	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithOneAssignments() {
		assertTrue(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPCAssignedOneManuscript));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithTwoAssignments() {
		assertTrue(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPCAssignedTwoManuscripts));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithThreeAssignments() {
		assertTrue(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPCAssignedThreeManuscripts));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithFourAssignments() {
		assertFalse(myUI.brCheck_SubprogramChairNotOverAssigned(mySubPCAssignedFourManuscripts));
	}

}

package testModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;


/**
 * @Author: Amrit Puri
 * Group 2 - TCSS 360A
 */


public class SubProgramChairTest {
	RegisteredUser user;
	SubProgramChair chair;
	SubProgramChair mySubPC;
	SubProgramChair mySubPCAssignedOneManuscript;
	SubProgramChair mySubPCAssignedTwoManuscripts;
	SubProgramChair mySubPCAssignedThreeManuscripts;
	SubProgramChair mySubPCAssignedFourManuscripts;
	Manuscript manuscript;
	Manuscript manuscript2;
	
	@Before
	public void beforeAllTests() {
		user = new RegisteredUser("Amrit", "Puri", "APuri", 27);
		chair = new SubProgramChair(user);
		manuscript = new Manuscript();
		manuscript2 = new Manuscript();
		chair.assignManuscript(manuscript);
		chair.assignManuscript(manuscript2);
		chair.unassignManuscript(manuscript);

		mySubPC = new SubProgramChair(new RegisteredUser("Test", "Test", "Test", 123));
		
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
	public void testAssignManuscript() {
		assertNotNull(chair.getMyAssignedManuscripts().get(0));
	}

	@Test
	public void testUnassignManuscript() {
		assertFalse(chair.getMyAssignedManuscripts().contains(manuscript));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithNoAssignments() {
		assertTrue(mySubPC.brCheck_SubprogramChairNotOverAssigned());
	}

	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithOneAssignments() {
		assertTrue(mySubPCAssignedOneManuscript.brCheck_SubprogramChairNotOverAssigned());
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithTwoAssignments() {
		assertTrue(mySubPCAssignedTwoManuscripts.brCheck_SubprogramChairNotOverAssigned());
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithThreeAssignments() {
		assertTrue(mySubPCAssignedThreeManuscripts.brCheck_SubprogramChairNotOverAssigned());
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnUserWithFourAssignments() {
		assertTrue(mySubPCAssignedFourManuscripts.brCheck_SubprogramChairNotOverAssigned());
	}
	
	@Test
	public void testBrCheck_SubprogramChairCannotAssignOnUserWithFourAssignments() {
		assertFalse(mySubPCAssignedFourManuscripts.assignManuscript(new Manuscript()) == 0);
	}
}
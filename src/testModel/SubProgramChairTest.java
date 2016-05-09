package testModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import model.*;
import view.*;


/**
 * @Author: Amrit Puri
 * Group 2 - TCSS 360A
 */


public class SubProgramChairTest {
	RegisteredUser user;
	SubProgramChair chair;
	Manuscript manuscript;
	Manuscript manuscript2;
	
	@Before
	public void beforeAllTests() {
		user = new RegisteredUser("Amrit", "Puri", "APuri", 27);
		chair = new SubProgramChair(user);
		manuscript = new Manuscript();
		manuscript2 = new Manuscript();
	}
	
	
	@Test
	public void testAssignManuscript() {
		chair.assignManuscript(manuscript);
		assertNotNull(chair.getMyAssignedManuscripts().get(0));
	}

	@Test
	public void testUnassignManuscript() {
		chair.assignManuscript(manuscript);
		chair.assignManuscript(manuscript2);
		chair.unassignManuscript(manuscript);
		
		assertFalse(chair.getMyAssignedManuscripts().contains(manuscript));
	}
}
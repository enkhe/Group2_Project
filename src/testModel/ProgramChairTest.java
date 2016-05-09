package testModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import model.*;

/**
 * @Author: Amrit Puri
 * Group 2 - TCSS 360A
 */


public class ProgramChairTest {
	RegisteredUser user;
	ProgramChair chair;
	Manuscript manuscript;
	
	
	@Before
	public void beforeAllTests() {
		user = new RegisteredUser("Amrit", "Puri", "APuri", 27);
		chair = new ProgramChair(user);
		manuscript = new Manuscript();
	}
	
	
	@Test
	public void testChangeManuscriptAccept() {
		chair.changeAcceptance(manuscript, true);
		assertTrue(manuscript.getAcceptStatus().equals("Accepted"));
	}
	
	@Test
	public void testChangeManuscriptReject() {
		chair.changeAcceptance(manuscript, false);
		assertTrue(manuscript.getAcceptStatus().equals("Rejected"));
	}
	
	@Test
	public void testSubProgramChairAssignments() {
		for (int i = 0; i < 10; i++) {
			chair.assignSubProgramChair(i);
		}
		assertTrue(chair.getSubPCAssignments().contains(9));
	}

}
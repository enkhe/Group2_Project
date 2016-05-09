package testModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

/**
 * @Author: Amrit Puri
 * Group 2 - TCSS 360A
 */
 
public class ReviewerTest {
	RegisteredUser user;
	Reviewer reviewer;
	Manuscript manuscript;
	Manuscript manuscript2;
	
	@Before
	public void beforeAllTests() {
		user = new RegisteredUser("Amrit", "Puri", "APuri", 27);
		reviewer = new Reviewer(user);
		manuscript = new Manuscript();
		manuscript2 = new Manuscript();
	}
	
	
	@Test
	public void testAssignManuscript() {
		reviewer.assignManuscript(manuscript);
		assertNotNull(reviewer.getMyAssignedManuscripts().get(0));
	}

	@Test
	public void testUnassignManuscript() {
		reviewer.assignManuscript(manuscript);
		reviewer.assignManuscript(manuscript2);
		reviewer.unassignManuscript(manuscript);
		
		assertFalse(reviewer.getMyAssignedManuscripts().contains(manuscript));
	}
}
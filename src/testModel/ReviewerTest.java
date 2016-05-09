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
	Reviewer reviewer2;
	
	@Before
	public void beforeAllTests() {
		reviewer = new Reviewer();
		user = new RegisteredUser("Amrit", "Puri", "APuri", 27);
		reviewer2 = new Reviewer(user);
	}
//	
//	@Test
//	public void testDefaultConstructorGetManuscripts() {
//		assertNotNull(reviewer.getMyAssignedManuscripts());
//	}
//	
//	@Test
//	public void testOverloadedConstructorGetManuscripts() {
//		assertNotNull(reviewer2.getMyAssignedManuscripts());
//	}

}
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
	
	@Before
	public void beforeAllTests() {
		user = new RegisteredUser("Amrit", "Puri", "APuri", 27);
		chair = new SubProgramChair(user);
		manuscript = new Manuscript();
	}
	
	
	@Test
	public void testSentenceGoesHere() {
		
	}

}
package testView;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import model.*;
import view.*;

/**
 * TCSS360
 * 
 */

public class ManagementSystemTest {
	
	@Test
	public void selectRoles() {
		// Arrange
		bExpected1 = false;
		bExpected2 = true;
		bExpected3 = false;
		bExpected4 = false;
		
		// Act
		bActual1 = conference1.isAuthor(myCurrentUser.getID());
		bActual2 = conference1.isProgramChair(myCurrentUser.getID());
		bActual3 = conference1.isSubprogramChair(myCurrentUser.getID());
		bActual4 = conference1.isReviewer(myCurrentUser.getID());
		
		// Assert
		assertEquals("Is Author false.", bExpected1, bActual1);	
		assertEquals("Is ProgramChair true.", bExpected1, bActual1);
		assertEquals("Is SubprogramChair false.", bExpected1, bActual1);
		assertEquals("Is Reviewer true.", bExpected1, bActual1);
		
	}
	

	// Login means the username exists in the system and authenticates it
	
	// main menu.
	
	// submitManuscript
	
	
	
	private void setUpManagementSystem() {
		SetUp setUp = new SetUp();
		managementSystem = setUp.generateManagementSystem();
		myUserList = managementSystem.getMyUserList();
		myConferences = managementSystem.myMyConferences();
		conference1 = myConferences.get(0);
		conference2 = myConferences.get(1);
		myCurrentUser = myUserList.get(10); // jrobinson
	}
	
	@Before
	public void beforeAllTests() {
		managementSystem = new ManagementSystem();
		myUserList = new LinkedList<>();
		myConferences = new LinkedList<>();
		loggedIn = false;
		setUpManagementSystem();
		bActual1 = false;
		bActual2 = false;
		bActual3 = false;
		bActual4 = false;
		bExpected1 = false;
		bExpected2 = true;
		bExpected3 = false;
		bExpected4 = false;
	}

	ManagementSystem managementSystem;
	private List<RegisteredUser> myUserList;
	private List<Conference> myConferences;
	RegisteredUser myCurrentUser;
	Conference conference1, conference2;
	boolean loggedIn;
	boolean bExpected1, bExpected2, bExpected3, bExpected4;
	boolean bActual1, bActual2, bActual3, bActual4;

}
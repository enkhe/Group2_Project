package testView;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import model.Conference;
import model.RegisteredUser;

import org.junit.Before;
import org.junit.Test;

import view.ManagementSystem;

public class ManagementSystemTest {
	
	
	private ManagementSystem managementSystem;
	private String validUserName;
	private String invalidUserName;
	private List<RegisteredUser> myUserList;
	private List<Conference> myConferences;
	private RegisteredUser userShaun;
	private RegisteredUser userTyler;
	
	@Before
	public void setUp() {
		validUserName = "scoleman";
		invalidUserName = "invalid";
		userShaun = new RegisteredUser("Shaun", "Coleman", validUserName, 1);
		userTyler = new RegisteredUser("Tyler", "Brent", "tbrent", 2);
		Conference conference = new Conference();
		myUserList = new ArrayList<>();
		myUserList.add(userShaun);
		myUserList.add(userTyler);
		myConferences = new ArrayList<>();
		myConferences.add(conference);
		managementSystem = new ManagementSystem(myConferences, myUserList);
	}
	
	@Test
	public void testGetUserOnValidUserName() {
		assertEquals(userShaun, managementSystem.getUser(validUserName));
	}
	
	@Test
	public void testGetUserOnInvalidUserName() {
		assertFalse(userShaun.equals(managementSystem.getUser(invalidUserName)));
	}

}
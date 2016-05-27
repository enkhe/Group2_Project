package model;
/*
 * Author: Tyler Brent
 * Group 2 - TCSS 360A
 */

import java.io.Serializable;

/**
 * 
 * This class represents a Registered User. It contains all identifying properties
 * of anyone who has been registered into the system.
 *
 */

public class RegisteredUser implements Serializable {
	
	private static final long serialVersionUID = 7108419094297515234L;
	private String myFirstName;
	private String myLastName;
	private String myUserName;
	private int myID;
	
	public RegisteredUser() {
		myFirstName = null;
		myLastName = null;
		myUserName = null;
		myID = -1;
	}
	
	public RegisteredUser(String theFirstName, String theLastName, String theUserName, int theID) {
		myFirstName = theFirstName;
		myLastName = theLastName;
		myUserName = theUserName;
		myID = theID;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the an int that represents the ID of the user.
	 */
	public int getID() {
		return myID;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the first name of the user.
	 */
	public String getFirstName() {
		return myFirstName;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the last name of the user.
	 */
	public String getLastName() {
		return myLastName;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the user name of the user.
	 */
	public String getUserName() {
		return myUserName;
	}
}

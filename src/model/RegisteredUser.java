package model;
/*
 * Author: Tyler Brent
 * Group 2 - TCSS 360A
 */

public class RegisteredUser {
	
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
	
	public int getID() {
		return myID;
	}
	
	public String getFirstName() {
		return myFirstName;
	}
	
	public String getLastName() {
		return myLastName;
	}
	
	public String getUserName() {
		return myUserName;
	}
}

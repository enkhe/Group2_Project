package model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Tyler Brent
 * Group 2 - TCSS 360A
 */

public class Author extends RegisteredUser implements Serializable {

	private static final long serialVersionUID = -1558785409225599245L;
	/** The list of Manuscripts this Author has submitted. */
	List<Manuscript> myManuscripts;

	/**
	 * Default constructor.
	 */
	public Author() {
		myManuscripts = new ArrayList<>();
	}
	
	/**
	 * Overloaded constructor.
	 */
	public Author(RegisteredUser theUser){
		super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		myManuscripts = new ArrayList<>();
	}
	
	/**
	 * Takes a Manuscript and adds it to the Authors list of Manuscripts
	 * if the following conditions are met.
	 * 1) The Manuscript must not already exist.
	 * 2) the Author must not exceed 4 submissions.
	 * 
	 * 0 is returned for a successful add.
	 * -1 is returned for an unsuccessful add.
	 */
	public int submitManuscript(Manuscript theManuscript){
		
		if(!exists(theManuscript)) {
			myManuscripts.add(theManuscript);
		} else {
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Takes a Manuscript object, checks if it exists on the list. If it does the 
	 * Manuscript is removed from the list.
	 * 
	 * 	0 is returned for a successful remove.
	 * -1 is returned for an unsuccessful remove.
	 */
	public int removeManuscript(Manuscript theManuscript){
		if(exists(theManuscript)) {
			myManuscripts.remove(theManuscript);
		} else {
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Takes a Manuscript and a String filePath for the Manuscript, then checks if it
	 * exists on the list of Manuscript the Author has submitted. If it does, only the Manuscripts
	 * filePath is updated.
	 * 
	 *  0 is returned for a successful replacement.
	 * -1 is returned for an unsuccessful replacement.
	 */
	public int replaceManuscript(Manuscript theManuscript, Manuscript theReplacement) {
		int pass = removeManuscript(theManuscript);
		pass = submitManuscript(theReplacement);
		
		return pass;
	}
	
	/**
	 * Returns a list of all Manuscripts the Author has submitted.
	 */
	public List<Manuscript> getMyManuscripts() {
		return myManuscripts;
	}
	
	/**
	 * Private method that checks if a Manuscript exists on the list
	 * of Manuscripts the Author has already submitted. Returns true if it exists
	 * and false if it doesn't.
	 */
	private boolean exists(Manuscript theManuscript) {
		boolean exists = false;
		
		for(Manuscript m : myManuscripts) {
			if(m.getFile() == theManuscript.getFile() && 
					m.getAuthorID() == theManuscript.getAuthorID()) {
				exists = true;
			}
		}
		
		return exists;
	}
}

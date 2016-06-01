package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Amrit Puri
 * Group 2 - TCSS 360A
 * 
 * This class represents a Reviewer in in the system. It can be used to 
 * manage conferences.
 */

public class Reviewer extends RegisteredUser implements Serializable {

	
    /* This is a unique serial ID for this class */
	private static final long serialVersionUID = 1L;
	
    /**
     * Used to represent the maximum manuscripts a reviewer can be assigned.
     */
    private final int MAX_REVIEWER_ASSIGNED_MANUSCRIPTS = 4;
	
	private List<Manuscript> myManuscripts;
	
    /**
     * Precondition: None
     * Postcondition: A Reviewer has been created.
     * 
     * Default constructor
	*/
    public Reviewer () {
		myManuscripts = new ArrayList<Manuscript>();
    }

    /**
     * Precondition: A User for which to create a Reviewer.
     * Postcondition: A Reviewer has been created.
     * 
     * Overloaded Constructor
     * 
     * @param a RegisteredUser from which a Reviewer will be instantiated with the
		same attributes
     */
    public Reviewer(RegisteredUser theUser) {
    	super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		myManuscripts = new ArrayList<Manuscript>();
    }

    /**
     * Precondition: None
     * Postcondition: The list of assigned manuscripts is returned.
     * 
     * Gets the Manuscripts assigned to this Reviewer.
     * 
	 * @return a list of all Manuscripts the Reviewer is assigned
     */
    public List<Manuscript> getMyAssignedManuscripts() {
        return myManuscripts;
    }
    
    /**
     * Precondition: A Manuscript to be assigned.
     * Postcondition: An integer representing success(0) or failure (-1).
     * 
     * Assigns a Manuscript to this Reviewer.
     * 
     * @param the Manuscript to be assigned
     * @return an integer representing success(0) or failure (-1)
     */
    public int assignManuscript(Manuscript theManuscript) {
    	if(!(brcheck_ReviewerNotManuscriptAuthor(theManuscript))
    	   || !(brcheck_ReviewerNotOverAssigned())) {
    		return -1;
    	}
    	
    	if (!myManuscripts.contains(theManuscript)) {
        	myManuscripts.add(theManuscript);
    	}
    	
    	return 0;
    }
    
    /**
     * Precondition: A Manuscript to be unassigned.
     * Postcondition: An integer representing success(0) or failure (-1).
     *
     * Unassigns a Manuscript from this Reviewer.
     *
     * @param the Manuscript to be unassigned
     * @return an integer representing success(0) or failure (-1)
     */
    public int unassignManuscript(Manuscript theManuscript) {
    	if (myManuscripts.contains(theManuscript)) {
    		myManuscripts.remove(theManuscript);
    		return 1;
    	}
    	
    	return 0;
    }
    
    /**
     * Business Rule check to insure the Reviewer is not at the maximum assigned manuscripts.
     * @param theReviewer the Reviewer in question.
     * 
     * @return true if the check is passed, false if the business rule would be broken.
     */
    public boolean brcheck_ReviewerNotOverAssigned() {
    	return myManuscripts.size() < MAX_REVIEWER_ASSIGNED_MANUSCRIPTS;
    }
    
    /**
     * Business Rule check to insure the Reviewer is not assigned a manuscript they authored
     * @param theManuscript the Manuscript in question.
     * @param theReviewer the Reviewer in question.
     * 
     * @return true if the check is passed, false if the business rule would be broken.
     */
    public boolean brcheck_ReviewerNotManuscriptAuthor(Manuscript theManuscript) {
    	return myID != theManuscript.getAuthorID();
    }
}
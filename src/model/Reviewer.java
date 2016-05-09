package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Amrit Puri
 * Group 2 - TCSS 360A
 */

public class Reviewer extends RegisteredUser implements Serializable {

    /** This is a unique serial ID for this class */
	private static final long serialVersionUID = 1L;
	private List<Manuscript> myManuscripts;
	
    /**
		Default constructor
     */
    public Reviewer () {
		myManuscripts = new ArrayList<Manuscript>();
    }

    /**
		Overloaded Constructor
		
		@param a RegisteredUser from which a Reviewer will be instantiated with the
		same attributes
     */
    public Reviewer(RegisteredUser theUser) {
    	super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		myManuscripts = new ArrayList<Manuscript>();
    }

    /**
     * Gets the Manuscripts assigned to this Reviewer.
     * 
	 * @return a list of all Manuscripts the Reviewer is assigned
     */
    public List<Manuscript> getMyAssignedManuscripts() {
        return myManuscripts;
    }
    
    /**
		Assigns a Manuscript to this Reviewer.
		
		@param the Manuscript to be assigned
     */
    public void assignManuscript(Manuscript theManuscript) {
    	if (!myManuscripts.contains(theManuscript)) {
        	myManuscripts.add(theManuscript);
    	}
    }
    
    /**
		Unassigns a Manuscript from this Reviewer.
		
		@param the Manuscript to be unassigned
     */
    public int unassignManuscript(Manuscript theManuscript) {
    	if (myManuscripts.contains(theManuscript)) {
    		myManuscripts.remove(theManuscript);
    		return 1;
    	}
    	
    	return 0;
    }
}
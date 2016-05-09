package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Amrit Puri
 * Group 2 - TCSS 360A
 */

public class SubProgramChair  extends RegisteredUser implements Serializable {

    /** This is a unique serial ID for this class */
	private static final long serialVersionUID = 1L;
	private List<Manuscript> myManuscripts;
    
    /**
		Default Constructor
     */
    public SubProgramChair() {
		myManuscripts = new ArrayList<Manuscript>();
    }

    /**
		Overloaded Constructor
     */
    public SubProgramChair(RegisteredUser theUser) {
    	super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		myManuscripts = new ArrayList<Manuscript>();
    }

    /**
	 * Returns a list of all Manuscripts the SPC is assigned.
     */
    public List<Manuscript> getMyAssignedManuscripts() {
    	return myManuscripts;
    }
    
    /**

     */
    public void assignManuscript(Manuscript theManuscript) {
    	if (!myManuscripts.contains(theManuscript)) {
        	myManuscripts.add(theManuscript);
    	}
    }
    
    /**

     */
    public void unassignManuscript(Manuscript theManuscript) {
    	if (myManuscripts.contains(theManuscript)) {
    		myManuscripts.remove(theManuscript);
    	}
    }

    /**

     */
    public void assignReviewer(int theID) {
    	
    }

    /**

     */
    public void submitRecommendation(boolean theRecommendation) {
    	
    }
}
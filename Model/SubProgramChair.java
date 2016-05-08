package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Amrit Puri
 * Group 2 - TCSS 360A
 */

public class SubProgramChair  extends RegisteredUser implements Serializable {

    /**
	 * 
	 */
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
    public void assignReviewer(int theID) {

    }

    /**

     */
    public void submitRecommendation(boolean theRecommendation) {
    	
    }
}
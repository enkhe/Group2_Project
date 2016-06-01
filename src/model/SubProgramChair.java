package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Amrit Puri
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
		
		@param a RegisteredUser from which a SubProgramChair will be instantiated with the
		same attributes
     */
    public SubProgramChair(RegisteredUser theUser) {
    	super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		myManuscripts = new ArrayList<Manuscript>();
    }

    /**
	 * Gets the Manuscripts assigned to this SubProgramChair.
	 * 
	 * @return a list of all Manuscripts the SPC is assigned
     */
    public List<Manuscript> getMyAssignedManuscripts() {
    	return myManuscripts;
    }
    
    /**
		Assigns a Manuscript to this SubProgramChair.
		
		@param the Manuscript to be assigned
     */
    public int assignManuscript(Manuscript theManuscript) {
    	if (!myManuscripts.contains(theManuscript) && myManuscripts.size() != 4 
    			&& theManuscript.getAuthorID() != getID()) {
        	myManuscripts.add(theManuscript);
        	return 0;
    	}
    	return -1;
    }
    
    /**
		Unassigns a Manuscript from this SubPrograChair.
		
		@param the Manuscript to be unassigned
     */
    public int unassignManuscript(Manuscript theManuscript) {
    	if (myManuscripts.contains(theManuscript)) {
    		myManuscripts.remove(theManuscript);
    		return 0;
    	}
    	
    	return -1;
    }
}
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Amrit Puri
 * Group 2 - TCSS 360A
 */

public class ProgramChair extends RegisteredUser implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private List<Manuscript> mySubPCAssignments;
	
	
   /**
		Default Constructor
    */	
	public ProgramChair() {
		mySubPCAssignments = new ArrayList<Manuscript>();
	}

    /**
		Overloaded Constructor
    */
    public ProgramChair(RegisteredUser theUser) {
    	super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		mySubPCAssignments = new ArrayList<Manuscript>();
    }

    /**

     */
    public void changeAcceptance(Manuscript theManuscript, boolean theAcceptance) {
    	if (theAcceptance) {
    		theManuscript.setAcceptStatus(1);
    	} else {
    		theManuscript.setAcceptStatus(0);
    	}
    }

    /**

     */
    public List<Manuscript> getSubPCAssignments() {
    	return mySubPCAssignments;
    }

    /**

     */
    public void assignSubProgramChair(int theID) {
    	
    }
}
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: Amrit Puri
 * Group 2 - TCSS 360A
 */

public class ProgramChair extends RegisteredUser implements Serializable {
	
    /** This is a unique serial ID for this class */
	private static final long serialVersionUID = 1L;
	private List<Integer> mySubPCAssignments;
	
	
   /**
		Default Constructor
    */	
	public ProgramChair() {
		mySubPCAssignments = new ArrayList<Integer>();
	}

    /**
		Overloaded Constructor
    */
    public ProgramChair(RegisteredUser theUser) {
    	super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		mySubPCAssignments = new ArrayList<Integer>();
    }

    /**
		This method changes the acceptance of a given Manuscript given the Manuscript
		and a boolean.
     */
    public void changeAcceptance(Manuscript theManuscript, boolean theAcceptance) {
    	if (theAcceptance) {
    		theManuscript.setAcceptStatus(1);
    	} else {
    		theManuscript.setAcceptStatus(0);
    	}
    }

    /**
		This method returns a List of Integers containing the IDs of the SubProgram
		Chairs assigned to this Program Chair.
     */
    public List<Integer> getSubPCAssignments() {
    	return mySubPCAssignments;
    }

    /**
		This method assigns a SubProgram Chair given that user's ID.
     */
    public void assignSubProgramChair(int theID) {
    	mySubPCAssignments.add(theID);
    }
}
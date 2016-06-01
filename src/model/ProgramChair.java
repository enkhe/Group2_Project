package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Amrit Puri
 * Group 2 - TCSS 360A
 */

public class ProgramChair extends RegisteredUser implements Serializable {
	
    /** This is a unique serial ID for this class */
	private static final long serialVersionUID = 1L;
	
    /**
     * Used to assign an accepted status.
     */
    public static final int ACCEPT = 1;
    
    /**
     * Used to assign a rejected status.
     */
    public static final int REJECT = 0;
	
    /*
     * A list of all SubProgramChair's assigned by this program chair.
     */
	private List<Integer> mySubPCAssignments;
	
   /**
     * Precondition: None
     * Postcondition: A ProgramChair has been created.
     * 
     * Default Constructor	
    */	
	public ProgramChair() {
		mySubPCAssignments = new ArrayList<Integer>();
	}
	
    /**
     * Precondition: A User for which to create a ProgramChair.
     * Postcondition: A ProgramChair has been created.
     * 
     * Overloaded Constructor
     * 
     * @param a RegisteredUser from which a ProgramChair will be instantiated with the
     * same attributes
     */
    public ProgramChair(RegisteredUser theUser) {
    	super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		mySubPCAssignments = new ArrayList<Integer>();
    }

    /**
     * Precondition: The manuscript to change and the value to change it to.
     * Postcondition: None
     * 
     * This method changes the acceptance of a given Manuscript given the Manuscript
     * and a boolean.
     * 
     * @param theManuscript is the manuscript whose acceptance to change
     * @param theAcceptance is the value to change the acceptance to
     */
    public void changeAcceptance(Manuscript theManuscript, boolean theAcceptance) {
    	if (theAcceptance) {
    		theManuscript.setAcceptStatus(ACCEPT);
    	} else {
    		theManuscript.setAcceptStatus(REJECT);
    	}
    }
    
    /**
		This method assigns a SubProgram Chair given that user's ID.
     */
    public void assignSubProgramChair(int theID) {
    	mySubPCAssignments.add(theID);
    }

    /**
     * Precondition: None
     * Postcondition: The list of assigned SubProgram Chairs is returned.
     * 
     * This method returns a List of Integers containing the IDs of the SubProgram
     * Chairs assigned to this Program Chair.
     * 
     * @return List<Integer> is the list of SubProgram Chairs that have been assigned
     */
    public List<Integer> getSubPCAssignments() {
    	return mySubPCAssignments;
    }
}
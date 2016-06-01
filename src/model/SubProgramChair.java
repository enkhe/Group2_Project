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
	
	public static final int MAX_SUBPC_ASSIGNED_MANUSCRIPTS = 4;
	
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
    	System.err.println("here!");
    	System.err.println(theManuscript + " " + brCheck_SubprogamNotAuthor(theManuscript)
    			           + " " + brCheck_SubprogramChairNotOverAssigned());
    	if (myManuscripts.contains(theManuscript)
    	    || !brCheck_SubprogamNotAuthor(theManuscript)
    	    || !brCheck_SubprogramChairNotOverAssigned()) {
    		return -1;
    	}
    	
    	myManuscripts.add(theManuscript);
    	return 0;
    	
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
    
    /**
     * Business Rule check to insure the Subprogram Chair is not assigned a manuscript they authored
     * @param theManuscript the Manuscript in question.
     * 
     * @return true if the check is passed, false if the business rule would be broken.
     */
    public boolean brCheck_SubprogamNotAuthor(Manuscript theManuscript) {
        return myID != theManuscript.getAuthorID();    
    }

    /**
     * Business Rule check to insure the Subprogram Chair is not at the maximum assigned manuscripts.
     * 
     * @return true if the check is passed, false if the business rule would be broken.
     */
    public boolean brCheck_SubprogramChairNotOverAssigned() {
        return myManuscripts.size() <= MAX_SUBPC_ASSIGNED_MANUSCRIPTS;
    }
}
package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Amrit Puri
 * Group 2 - TCSS 360A
 * 
 * This class represents a Sub Program Chair in the system. It can be used to manage
 * conferences.
 */

public class SubProgramChair  extends RegisteredUser implements Serializable {

    /** This is a unique serial ID for this class */
	private static final long serialVersionUID = 1L;
	
	public static final int MAX_SUBPC_ASSIGNED_MANUSCRIPTS = 4;
	
	private List<Manuscript> myManuscripts;

    /**
     * Precondition: None
     * Postcondition: A SubProgramChair has been created.
     * 
     * Default constructor
	*/
    public SubProgramChair() {
		myManuscripts = new ArrayList<Manuscript>();
    }
	
    /**
     * Precondition: A User for which to create a SubProgramChair.
     * Postcondition: A SubProgramChair has been created.
     * 
     * Overloaded Constructor
     * 
     * @param a RegisteredUser from which a SubProgramChair will be instantiated with the
		same attributes
     */
    public SubProgramChair(RegisteredUser theUser) {
    	super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		myManuscripts = new ArrayList<Manuscript>();
    }

    /**
     * Precondition: None
     * Postcondition: The list of assigned manuscripts is returned.
     * 
     * Gets the Manuscripts assigned to this SubProgramChair.
     * 
	 * @return a list of all Manuscripts the SubProgramChair is assigned
     */
    public List<Manuscript> getMyAssignedManuscripts() {
    	return myManuscripts;
    }
    
    /**
     * Precondition: A Manuscript to be assigned.
     * Postcondition: An integer representing success(0) or failure (-1).
     * 
     * Assigns a Manuscript to this SubProgramChair.
     * 
     * @param the Manuscript to be assigned
     * @return an integer representing success(0) or failure (-1)
     */
    public int assignManuscript(Manuscript theManuscript) {
    	if (myManuscripts.contains(theManuscript)
    	    || !brCheck_SubprogamNotAuthor(theManuscript)
    	    || !brCheck_SubprogramChairNotOverAssigned()) {
    		return -1;
    	}
    	
    	myManuscripts.add(theManuscript);
    	return 0;
    	
    }
    
    /**
     * Precondition: A Manuscript to be unassigned.
     * Postcondition: An integer representing success(0) or failure (-1).
     *
     * Unassigns a Manuscript from this SubProgramChair.
     *
     * @param the Manuscript to be unassigned
     * @return an integer representing success(0) or failure (-1)
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
        System.err.println(myManuscripts.size());
    	return myManuscripts.size() < MAX_SUBPC_ASSIGNED_MANUSCRIPTS;
    }
}
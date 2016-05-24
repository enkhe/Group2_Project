package view;
import java.util.List;
import java.util.Objects;

import model.Conference;
import model.Manuscript;
import model.RegisteredUser;
import model.SubProgramChair;

/**
 * A class containing the UI elements for the Program Chair.
 * 
 * @author Shaun Coleman
 * @version MAY 20 2016
 */
public class ProgramChairUI {
    /**
     * A constant int used to represent the maximum manuscripts a subprogram chair can be
     * assigned.
     */
    private final int MAX_SUBPC_ASSIGNED_MANUSCRIPTS = 4;
    
    /**
     * A constant used to assign an accepted status.
     */
    private final int ACCEPT = 1;
    
    /**
     * A constant used to assign a rejected status.
     */
    private final int REJECT = 0;
   
    /**
     * A constant to represent the menu selection for changing the acceptance of a manuscript.
     */
    private final int CHANGE_ACCEPTANCE = 1;
    
    /**
     * A constant to represent the menu selection for designating a subprogram chair.
     */
    private final int DESIGNATE_SUBPROGRAM_CHAIR = 2;
    
	
	/**
     * The currently logged in user.  Null if no user is logged in.
     */
    private RegisteredUser myPC;
    
    /**
     * The currently selected conference.  Null if not logged into a conference.
     */
    private Conference myCurrentConference;
    
    /**
     * Constructs a new ProgramChairUI object with the given RegisteredUser and Conference.
     * @param theUser the RegisteredUser to act as the current ProgramChair.
     * @param theConference the currently selected conference.
     */
    public ProgramChairUI(RegisteredUser theUser, Conference theConference) {
    	myCurrentConference = theConference;
    	// needs to change later once proper ProgramChair getProgramChair(int) method is added.
    	myPC = theUser;
    }
    
    /**
     * Provides menu options for all Program Chair Actions.
     */
    public void programChairMenu() {
        int choice = -1;
        
        do {
            displayScreenHeader("Program Chair Menu");
        
            System.out.println("\nPlease enter a command below.");
            System.out.println("1) Accept or Reject a Manuscript.");
            System.out.println("2) Assign a Manuscript to a Subprogram Chair.");
            System.out.println("0) Return to main menu.");
            
            choice = SystemHelper.promptUserInt();
            
            switch (choice) {
                case CHANGE_ACCEPTANCE:
                    changeManuscriptAcceptance();
                    break;
                case DESIGNATE_SUBPROGRAM_CHAIR:
                    assignSubProgramChair();
                    break;
                case 0:
                    // empty; leaving method.
                    break;
                default:
                    break;
            }
        
        } while (choice != 0); 
    }
    
    /**
     * Displays a menu to accept or reject a manuscript.
     */
    private void changeManuscriptAcceptance() {
        int choice = -1;
        Manuscript selectedManuscript = programChairSelectManuscript();
        
        displayScreenHeader("Manuscript Acceptance");
        if (Objects.nonNull(selectedManuscript)) {
            System.out.println("\nAccept or Reject " + selectedManuscript.getTitle());
            System.out.println("1) Accept");
            System.out.println("2) Reject");
            System.out.println("0) Cancel");
            choice = SystemHelper.promptUserInt();
            
            switch(choice) {
	            case 1:
	            	selectedManuscript.setAcceptStatus(ACCEPT);
	                System.out.println("\n" + selectedManuscript.getTitle() + " accepted!");
	            	break;
	            case 2:
	            	selectedManuscript.setAcceptStatus(REJECT);
	                System.out.println("\n" + selectedManuscript.getTitle() + " rejected!");
	            	break;
	            case 0:
	            	break;
	            default:
	            	System.out.println("Invalid input; returning to Program Chair menu.");
	            	break;
            }
        }
    }
    
    /**
     * Designates a Subprogram Chair to a selected Manuscript.
     */
    private void assignSubProgramChair() {
        Manuscript selectedManuscript = programChairSelectManuscript();
        SubProgramChair selectedSubPC = null;
        
        if(Objects.isNull(selectedManuscript) || selectedManuscript.getSPC() > -1)  {
        	System.out.println("\nSelected Manuscript already has a Subprogram Chair.");
            return;
        }
        
        selectedSubPC = selectSubPCToAssign();
        
        if(Objects.nonNull(selectedSubPC)) {
            finalizeSubPCAssignment(selectedManuscript, selectedSubPC);
        }
    }
    
    /**
     * Displays a menu to select a manuscript to be assigned a Subprogram Chair.
     *
     * @return the selected manuscript.
     */
    private Manuscript programChairSelectManuscript() {
        List<Manuscript> manuscripts = myCurrentConference.getAllAuthorsManuscript(1);
        Manuscript selectedManuscript = null;
        int choice = -1;
        
        displayScreenHeader("Manuscript Selection");
        
        System.out.println("\nPlease choose the manuscript from the options below.");
        displayPCManuscriptOptionList();
        choice = SystemHelper.promptUserInt();
        
        if(choice == 0) return null;
        
        try {
            selectedManuscript = manuscripts.get(choice - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input");
        }
        
        return selectedManuscript;
    }
    
    /**
     * Displays a menu to select a Subprogram Chair to assign to a Manuscript.
     * 
     * @return the selected SubprogramChair.
     */
    private SubProgramChair selectSubPCToAssign() {
        int choice = -1;
        int option = 1;
        List<SubProgramChair> subprogramChairs = myCurrentConference.getAllSubProgramChairs();
        SubProgramChair selectedSubPC = null;
        
        displayScreenHeader("Subprogram Chair Selection");
        
        System.out.println("\nPlease choose the Subprogram Chair to designate to this paper.");
        for (SubProgramChair sub : subprogramChairs) {
            System.out.println(option++ + ") " + sub.getLastName());
        }
        System.out.println("0) Cancel");
        
        choice = SystemHelper.promptUserInt();
        
        if(choice == 0) return null;
        
        try {
            selectedSubPC = subprogramChairs.get(choice - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nInvalid Command Entered.");
        }
        
        return selectedSubPC;
    }
    
    /**
     * Displays a simple numbered list of Manuscript titles for a Program Chair menu.
     */
    private void displayPCManuscriptOptionList() {
        List<Manuscript> manuscripts = myCurrentConference.getAllAuthorsManuscript(0);
        int option = 1;
        for(Manuscript manuscript : manuscripts) {
            System.out.println(option++ + ") " + manuscript.getTitle());
        }
        System.out.println("0) Cancel");
    }
    
    /**
     * Displays the current status header.
     * 
     * @param menuTitle the title of the current menu.
     */
    private void displayScreenHeader(String menuTitle) {
        System.out.println(SystemHelper.SYS_TITLE);
        System.out.println(myCurrentConference.getConferenceName());
        System.out.println("Program Chair: " + myPC.getUserName());
        System.out.println(menuTitle);
        displayManuscriptsForProgramChair();
    }
    
    /**
	 * Displays a detailed list of manuscripts including title, assigned subprogram chair,
	 * recommendation, and acceptance.
	 */
	private void displayManuscriptsForProgramChair() {
	    List<Manuscript> manuscripts = myCurrentConference.getAllAuthorsManuscript(1);
	    
	    //Headers
	    System.out.printf(SystemHelper.PC_MAN_DISPLAY_FORMAT, "Title", 
	                      "Subprogram Chair", "Recommendation", "Accepted");
	    SystemHelper.displayDashedLine();
	     
	    for (Manuscript manuscript : manuscripts) {
	        String title = manuscript.getTitle();
	        String subPCName = searchSubProgramChairForManuscript(manuscript);
	        String recommendation = manuscript.getRecommendation();
	        String acceptance = manuscript.getAcceptStatus();
	        
	        System.out.printf(SystemHelper.PC_MAN_DISPLAY_FORMAT, title, 
	        		           subPCName, recommendation, acceptance);
	    }
	}

	// Possible methods to move down to the model.
	
	/**
	 * Finalizes the SubProgramChair Assignment after checking business rules.  Will instead display
	 * an error message if any business rule fails.
	 * @param theManuscript the manuscript being assigned a subprogram chair.
	 * @param theSPC the Subprogram Chair to be assigned.
	 */
	private void finalizeSubPCAssignment(Manuscript theManuscript, SubProgramChair theSPC) {
	    if(!brCheck_SubprogamNotAuthor(theManuscript, theSPC)) {
	        System.out.println("Subprogram chair cannot be assigned to a Manuscript they authored.");
	    } else if (!brCheck_SubprogramChairNotOverAssigned(theSPC)) {
	        System.out.println("Subprogram chair cannont be assigned more than for Manuscripts.");
	    } else {
	        //Need to add this method.
	        theManuscript.setSPC(theSPC.getID());
	        theSPC.assignManuscript(theManuscript);
	        System.out.println(theSPC.getLastName() + " assinged to " + theManuscript.getTitle() + "!");
	    }
	}

	/**
	 * A linear search to find the last name of the Subprogram Chair of the specified Manuscript.
	 * @param theManuscript the Manuscript in question.
	 * @return the last name of the Subprogram Chair of the specified Manuscript.
	 */
	private String searchSubProgramChairForManuscript(Manuscript theManuscript) {

    	for(SubProgramChair sub : myCurrentConference.getAllSubProgramChairs()) {
        	if(sub.getID() == theManuscript.getSPC()) {
        		return sub.getLastName();
        	}
        }
    	
    	return SystemHelper.NOTHING_TO_DISPLAY;
    }
    
    /**
     * Business Rule check to insure the Subprogram Chair is not assigned a manuscript they authored
     * @param theManuscript the Manuscript in question.
     * @param theSPC the SubProgramChair in question.
     * 
     * @return true if the check is passed, false if the business rule would be broken.
     */
    public boolean brCheck_SubprogamNotAuthor(Manuscript theManuscript, SubProgramChair theSPC) {
        return theSPC.getID() != theManuscript.getAuthorID();    
    }

    /**
     * Business Rule check to insure the Subprogram Chair is not at the maximum assigned manuscripts.
     * @param theSPC the SubProgramChair in question.
     * 
     * @return true if the check is passed, false if the business rule would be broken.
     */
    public boolean brCheck_SubprogramChairNotOverAssigned(SubProgramChair theSPC) {
        return theSPC.getMyAssignedManuscripts().size() < MAX_SUBPC_ASSIGNED_MANUSCRIPTS;
    }
    
}

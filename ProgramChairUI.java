import java.util.List;
import java.util.Objects;

/**
 * A class containing teh UI elements for the Program Chair.
 * 
 * @author Shaun Coleman
 * @version 1.0
 */
public class ProgramChairUI {
    /**
     * A constant int used to represent the maximum manuscripts a subprogram chair can be
     * assigned.
     */
    private final int MAX_SUBPC_ASSIGNED_MANUSCRIPTS = 4;
    
	
	/**
     * The currently logged in user.  Null if no user is logged in.
     */
    private ProgramChair myPC;
    
    /**
     * The currently selected conference.  Null if not logged into a conference.
     */
    private Conference myCurrentConference;
    
    public ProgramChairUI(RegisteredUser theUser, Conference theConference) {
    	myCurrentConference = theConference;
    	myPC = theConference.getProgramChair(theUser.getId());
    }
    
    /**
     * Provides menu options for all Program Chair Actions.
     */
    public void programChairMenu() {
        int choice = -1;
        
        do {
            displayScreenHeader("Program Chair:", "Program Chair Menu");
        
            System.out.println("\nPlease enter a command below.");
            System.out.println("1) Veiw all Manuscript status.");
            System.out.println("2) Accept or Reject a Manuscript.");
            System.out.println("4) Assign a Manuscript to a Subprogram Chair.");
            System.out.println("0) Return to main menu.");
            
            choice = SystemHelper.promptUserInt();
            
            switch (choice) {
                case 1:
                    displayManuscriptsForProgramChair();
                    break;
                case 2:
                    changeManuscriptAcceptance();
                case 4:
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
     * Displays a detailed list of manuscripts including title, assigned subprogram chair,
     * recommendation, and acceptance.
     */
    private void displayManuscriptsForProgramChair() {
        // Note: need this method
        List<Manuscript> manuscripts = myCurrentConference.getManuscripts();
        
        //Headers
        System.out.printf(SystemHelper.PC_MAN_DISPLAY_FORMAT, "Title", 
                          "Subprogram Chair", "Recommendation", "Accepted");
        for(int i = 0; i < 10; i++) {
            System.out.print("------");
        }
        
        for (Manuscript manuscript : manuscripts) {
            String title = manuscript.getTitle();
            String subPCName = "-----------";
            
            // Possible code; using unimplemented methods from manuscript
            if (manuscript.hasSubPC()) {
                subPCName = manuscript.getSPC().getLastName();
            }
            
            String recommendation = manuscript.getRecommendation();
            String acceptance = manuscript.getAcceptance();
            
            System.out.printf(SystemHelper.PC_MAN_DISPLAY_FORMAT, title, 
            		           subPCName, recommendation, acceptance);
        }
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
            choice = SystemHelper.promptUserInt();
            
            // Note need these methods
            if (choice == 1) {
                selectedManuscript.setAcceptance("Accepted");
                System.out.println("\n" + selectedManuscript.getTitle() + " accepted!");
            } else if (choice == 2) {
                selectedManuscript.setAcceptance("Rejected");
                System.out.println("\n" + selectedManuscript.getTitle() + " rejected!");
            } else { 
                System.out.println("Invalid input; returning to Program Chair menu.");
            }
        }
    }
    
    /**
     * Designates a Subprogram Chair to a selected Manuscript.
     */
    private void assignSubProgramChair() {
        Manuscript selectedManuscript = programChairSelectManuscript();
        SubprogramChair selectedSubPC;
        
        if(Objects.nonNull(selectedManuscript)) {
            selectedSubPC = selectSubPCToAssign();
        }
        
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
        List<Manuscript> manuscripts = myCurrentConference.getManuscripts();
        Manuscript selectedManuscript = null;
        int choice = -1;
        
        displayScreenHeader("Manuscript Selection");
        
        System.out.println("\nPlease choose the manuscript from the options below.");
        displayPCManuscriptOptionList();
        choice = SystemHelper.promptUserInt();
        
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
    private SubprogramChair selectSubPCToAssign() {
        int choice = -1;
        int option = 1;
        List<SubprogramChair> subprogramChairs = myCurrentConference.getAllSubProgramChairs();
        SubprogramChair selectedSubPC = null;
        
        displayScreenHeader("Subprogram Chair Selection");
        
        System.out.println("\nPlease choose the Subprogram Chair to designate to this paper.");
        for (SubprogramChair sub : subprogramChairs) {
            System.out.println(option++ + ") " + sub.getLastName());
        }
        
        choice = SystemHelper.promptUserInt();
        
        try {
            selectedSubPC = subprogramChairs.get(choice - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input");
        }
        
        return selectedSubPC;
    }
    
    /**
     * Displays a simple numbered list of Manuscript titles for a Program Chair menu.
     */
    private void displayPCManuscriptOptionList() {
        List<Manuscript> manuscripts = myCurrentConference.getManuscripts();
        int option = 1;
        for(Manuscript manuscript : manuscripts) {
            System.out.println(option++ + ") " + manuscript.getTitle);
        }
    }
    
    private void finalizeSubPCAssignment(Manuscript theManuscript, SubprogramChair theSPC) {
        if(!brCheck_SubprogamNotAuthor(theManuscript, theSPC) {
            System.out.println("Subprogram chair cannot be assigned to a Manuscript they authored.");
        } else if (!brCheck_SubprogramChairNotOverAssigned(theSPC)) {
            System.out.println("Subprogram chair cannont be assigned more than for Manuscripts.");
        } else {
            //Need to add this method.
            theManuscript.setSPC(theSPC);
            theSPC.assignManuscript(theManuscript);
        }
    }
    
    private void displayScreenHeader(String menuTitle) {
        System.out.println(SystemHelper.SYS_TITLE);
        System.out.println(myCurrentConference.getConferenceName());
        System.out.println("Program Chair: " + myPC.getUserName());
        System.out.println(menuTitle);
    }
    
    /**
     * Business Rule check to insure the Subprogram Chair is not assigned a manuscript they authored
     * @param theManuscript the Manuscript in question.
     * @param theSPC the SubprogramChair in question.
     * 
     * @return true if the check is passed, false if the business rule would be broken.
     */
    private boolean brCheck_SubprogamNotAuthor(Manuscript theManuscript, SubprogramChair theSPC) {
        return theSPC.getId() != theManuscript.getAuthor().getId;    
    }

    /**
     * Business Rule check to insure the Subprogram Chair is at the maximum assigned manuscripts.
     * @param theSPC the SubprogramChair in question.
     * 
     * @return true if the check is passed, false if the business rule would be broken.
     */
    private boolean brCheck_SubprogramChairNotOverAssigned(SubprogramChair theSPC) {
        return theSPC.getMyAssignedManuscripts().size() < MAX_SUBPC_ASSIGNED_MANUSCRIPTS;
    }
    
}

import java.util.List;
import java.util.Objects;

/**
 * A class containing the UI elements of a Subprogram Chair.
 * 
 * @author Shaun Coleman
 * @author
 * @version 1.0
 * 
 */
public class SubProgramChairUI {

    /**
     * A constant int used to represent the maximum manuscripts a reviewer can be assigned.
     */
    private final int MAX_REVIEWER_ASSIGNED_MANUSCRIPTS = 4;
	
	/**
     * The currently logged in user.  Null if no user is logged in.
     */
    private SubprogramChair mySPC;
    
    /**
     * The currently selected conference.  Null if not logged into a conference.
     */
    private Conference myCurrentConference;
    
    /**
     * 
     * @param theUser
     * @param theConference
     */
    public SubprogramChairUI(RegisteredUser theUser, Conference theConference) {
    	myCurrentConference = theConference;
    	mySPC = theConference.getSubprogramChair(theUser.getId());
    }

    /**
     * Provides menu options for all Subprogram Chair Actions.
     */
    private void subProgramChairMenu() {
        int choice = -1;
        
        do {
            displayScreenHeader("Subprogram Chair Menu");
        
            System.out.println("\nPlease enter a command below.");
            System.out.println("1) Assign a Reviewer to a Manuscript.");
            System.out.println("2) Submit a Recommendation.");
            System.out.println("0) Return to main menu.");
            
            choice = SystemHelper.promptUserInt();
            
            switch (choice) {
                case 1:
                    assignReviewer();
                    break;
                case 2:
                    //Submit a recommendation
                    break;
                case 0:
                    //Empty; exiting menu.
                    break;
                default:
                    break;
            }
        
        } while (choice != 0);   
    }
    
    /**
     * Assigns a Reviewer to a manuscript based on the the results of 
     * selecting a manuscript menu, and selecting a reviewer menu.
     * 
     * @param mySPC the subprogram chair making the assignment.
     */
    private void assignReviewer() {
        Manuscript selectedManuscript = subprogramChairSelectManuscript();
        Reviewer selectedReviewer;
        
        if(Objects.nonNull(selectedManuscript)) {
            selectedReviewer = selectReviewerToAssign();
        }
        
        if(Objects.nonNull(selectedReviewer)) {
            /* if conditions need to be separated into brcheck_ methods and a similar
               finalize method that could be moved outside this class */
            if(selectedReviewer.getMyAssignedManuscripts().size() < 4
               && selectedReviewer.getId != selectedManuscript.getAuthor().getId()) {
                mySPC.assignReviewer(selectedReviewer.getId());
                selectedManuscript.setReview(selectedReviewer.getId(), null);
            }
        }
    }
    
    /**
     * 
     * @param mySPC
     * @return
     */
    private Manuscript subprogramChairSelectManuscript() {
        int choice = -1;
        Manuscript selectedManuscript = null;
        
        displayScreenHeader("Manuscript Selection");
        
        System.out.println("\nPlease select a manuscript below to assign a reviewer.");
        displaySubPCManuscriptOptionList(mySPC);
       
        choice = SystemHelper.promptUserInt();
        
        try {
            selectedManuscript = mySPC.getManuscripts().get(choice -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Manuscript option");
        }
        
        return selectedManuscript;
    }
    
    /**
     * Displays a simple numbered list of Manuscript titles for a Subprogram Chair menu.
     */
    private void displaySubPCManuscriptOptionList() {
        List<Manuscript> manuscripts = mySPC.getManuscripts();
        int option = 1;
        for(Manuscript manuscript : manuscripts) {
            System.out.println(option++ + ") " + manuscript.getTitle);
        }
    }
    
    /**
     * Displays a menu to select a Subprogram Chair to assign to a Manuscript.
     * 
     * @return the selected SubprogramChair.
     */
    private Reviewer selectReviewerToAssign() {
        int choice = -1;
        int option = 1;
        List<Review> reviewers = myCurrentConference.getAllReviewers();
        Reviewer selectedReviewer = null;
        
        displayScreenHeader("Reviewer Selection");
        System.out.println("\nPlease choose the Reviewer to assign to this paper.");
        
        for (Reviewer reviewer : reveiwers) {
            System.out.println(option++ + ") " + reviewer.getLastName());
        }
        
        choice = SystemHelper.promptUserInt();
        
        try {
            selectedReviewer = reviewers.get(choice - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input");
        }
        
        return selectedReviewer;
    }

    private void displayScreenHeader(String menuTitle) {
        System.out.println(SystemHelper.SYS_TITLE);
        System.out.println(myCurrentConference.getConferenceName());
        System.out.println("Subprogram Chair: " + mySPC.getUserName());
        System.out.println(menuTitle);
    }
}

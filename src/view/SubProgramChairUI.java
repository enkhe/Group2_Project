package view;
import java.util.List;
import java.util.Objects;

import model.Conference;
import model.Manuscript;
import model.RegisteredUser;
import model.Review;
import model.Reviewer;
import model.SubProgramChair;

/**
 * A class containing the UI elements of a Subprogram Chair.
 * 
 * @author Shaun Coleman
 * @version MAY 8 2015
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
    private SubProgramChair mySPC;
    
    /**
     * The currently selected conference.  Null if not logged into a conference.
     */
    private Conference myCurrentConference;
    
    /**
     * 
     * @param theUser
     * @param theConference
     */
    public SubProgramChairUI(RegisteredUser theUser, Conference theConference) {
    	myCurrentConference = theConference;
    	setSubProgramChair(theUser.getID());
    }

    /**
     * Provides menu options for all Subprogram Chair Actions.
     */
    public void subProgramChairMenu() {
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
                    assignRecommendation();
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
        Reviewer selectedReviewer = null;
        
        if(Objects.nonNull(selectedManuscript)) {
            selectedReviewer = selectReviewerToAssign();
        }
        
        finalizeReviewerAssignment(selectedManuscript, selectedReviewer);
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
        
        System.out.println("\nPlease select a manuscript below");
        displaySubPCManuscriptOptionList();
       
        choice = SystemHelper.promptUserInt();
        
        try {
            selectedManuscript = mySPC.getMyAssignedManuscripts().get(choice -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Manuscript option");
        }
        
        return selectedManuscript;
    }
    
    /**
     * Displays a simple numbered list of Manuscript titles for a Subprogram Chair menu.
     */
    private void displaySubPCManuscriptOptionList() {
        List<Manuscript> manuscripts = mySPC.getMyAssignedManuscripts();
        int option = 1;
        for(Manuscript manuscript : manuscripts) {
            System.out.println(option++ + ") " + manuscript.getTitle());
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
        List<Reviewer> reviewers = myCurrentConference.getAllReviewers();
        Reviewer selectedReviewer = null;
        
        displayScreenHeader("Reviewer Selection");
        System.out.println("\nPlease choose the Reviewer to assign to this paper.");
        
        for (Reviewer reviewer : reviewers) {
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

    private void assignRecommendation() {
    	
    	if(mySPC.getMyAssignedManuscripts().size() <= 0) {
    		System.out.println("\nNo papers assigned");
    		return;
    	}
    	
    	Manuscript manuscript = subprogramChairSelectManuscript();
    	int recommendation = -1;

    	if(Objects.nonNull(manuscript)) {
    		recommendation = displayRecommendationSelect(manuscript.getScale());
    	}
    	
        finalizeRecommendation(manuscript, recommendation);
    }
    
    private int displayRecommendationSelect(List<String> theScale) {
    	int choice = -1;
    	int option = 1;
    	
    	displayScreenHeader("Select Recommendation Score");
    	
    	System.out.println("Please enter a recommendation score below.");
    	
    	for (String rec : theScale) {
    		System.out.println(option++ + ") "+ rec);
    	}
    	
    	choice = SystemHelper.promptUserInt() - 1;
    	   	
    	return choice;
    }
    
    private void finalizeRecommendation(Manuscript theManuscript, int theRecommendation) {
        try {
        	theManuscript.getScale().get(theRecommendation);
        	theManuscript.setRecommendation(theRecommendation);
        	System.out.println(theManuscript.getTitle() + "'s recommendation set to" 
        	                  + theManuscript.getScale().get(theRecommendation) + "!");
        } catch (IndexOutOfBoundsException e) {
        	System.out.println("Invalid recommendation selection.");
        }
    	
    }
    
    private void finalizeReviewerAssignment(Manuscript theManuscript, Reviewer theReviewer) {
        if(Objects.nonNull(theReviewer)) {
            if(brcheck_ReviewerNotOverAssigned(theReviewer)
               && brcheck_ReviewerNotManuscriptAuthor(theReviewer, theManuscript)) {
                theManuscript.setReview(theReviewer.getID(), null);
                theReviewer.assignManuscript(theManuscript);
                System.out.println(theReviewer.getLastName() + " is assigned " 
                                   + theManuscript.getTitle() + "!");
            } else {
            	System.out.println("Unable to assign reviewer.");
            }
        } else {
        	System.out.println("Invalid reviewer selected.");
        }
    }
    
    public boolean brcheck_ReviewerNotOverAssigned(Reviewer theReviewer) {
    	return theReviewer.getMyAssignedManuscripts().size() < 4;
    }
    
    public boolean brcheck_ReviewerNotManuscriptAuthor(Reviewer theReviewer, Manuscript theManuscript) {
    	return theReviewer.getID() != theManuscript.getAuthorID();
    }
    
    /**
     * Linear search for the correct SubProgramChair object based on the passed
     * user id.  Sets the current SubProgramChair to the found object, or null if
     * the object is not found.
     * 
     * @param theID the ID of the desired SubProgramChair object.
     */
    public int setSubProgramChair(int theID) {
    	mySPC = null;
    	for (SubProgramChair sub : myCurrentConference.getAllSubProgramChairs()) {
    		if (sub.getID() == theID) {
    			mySPC = sub;
    			return 1;
    		}
    	}
    	
    	return 0;
    }
    
    /**
     * Displays the header information for the current screen.
     * 
     * @param menuTitle the title of the current menu screen.
     */
    private void displayScreenHeader(String menuTitle) {
        System.out.println(SystemHelper.SYS_TITLE);
        System.out.println(myCurrentConference.getConferenceName());
        System.out.println("Subprogram Chair: " + mySPC.getUserName());
        System.out.println(menuTitle);
    }
}
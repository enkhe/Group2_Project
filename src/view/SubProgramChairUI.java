package view;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

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
 * @version MAY 20 2015
 * 
 */
public class SubProgramChairUI {
    
    /*
     * Used to represent the menu selection for assigning a reviewer.
     */
    private final int ASSIGN_REVIEWER = 1;
    
    /*
     * Used to represent the menu selection for making a recommendation.
     */
    private final int MAKE_RECOMMENDATION = 2;
    
    /*
     * Used to represent the menu selection to exit to the login menu.
     */
    private final int EXIT = 9;
    
    /*
     * Used to represent the menu selection for exiting the menu.
     */
    private final int BACK = 0;
	
	/*
     * The currently logged in user.  Null if no user is logged in.
     */
    private SubProgramChair mySPC;
    
    /*
     * The currently selected conference.  Null if not logged into a conference.
     */
    private Conference myCurrentConference;
    
    /**
     * Provides a new SubProgramChairUI for the given RegisteredUser on the given
     * Conference.  Assumes both the user and conference are valid for this system
     * and that the user is a SubprogramChair for the specified conference.
     * 
     * @param theUser the RegisteredUser to act as the current SubProgramChair.
     * @param theConference the currently selected conference.
     */
    public SubProgramChairUI(RegisteredUser theUser, Conference theConference) {
    	myCurrentConference = theConference;
    	mySPC = myCurrentConference.searchSubProgramChairByID(theUser.getID());
    }

    /**
     * Provides menu options for all Subprogram Chair Actions.
     */
    public int subProgramChairMenu() {
        int choice = -1;
        
        do {
            displayScreenHeader("Subprogram Chair Menu");
    	    displayManuscriptTable();
    	    
            System.out.println("\nPlease enter a command below.");
            System.out.println("1) Assign a Reviewer to a Manuscript.");
            System.out.println("2) Submit a Recommendation.");
            System.out.println("9) Exit to Login Menu.");
            System.out.println("0) Return to Role Selection.");
            
            choice = SystemHelper.promptUserInt();
            
            switch (choice) {
                case ASSIGN_REVIEWER:
                    assignReviewer();
                    break;
                case MAKE_RECOMMENDATION:
                    assignRecommendation();
                    break;
                case EXIT:
                	choice = SystemHelper.EXIT_TO_LOGIN;
                	return choice;
                case BACK:
                    //Empty; exiting menu.
                    break;
                default:
                    break;
            }
        
        } while (choice != 0);   
        
        return choice;
    }
    
    /*
     * Assigns a Reviewer to a manuscript based on the the results of 
     * selecting a manuscript menu, and selecting a reviewer menu.
     * 
     * @param mySPC the subprogram chair making the assignment.
     */
    private void assignReviewer() {
        Manuscript selectedManuscript = selectManuscriptPrompt();
        Reviewer selectedReviewer = null;
        
        if(Objects.nonNull(selectedManuscript)) {
            selectedReviewer = selectReviewerToAssign();
        }
        
        finalizeReviewerAssignment(selectedManuscript, selectedReviewer);
    }
    
    /*
     * Displays a menu to select a manuscript from the SubProgramChair's 
     * currently assigned manuscripts.
     * @return the selected Manuscript.
     */
    private Manuscript selectManuscriptPrompt() {
        int choice = -1;
        Manuscript selectedManuscript = null;
        
        displayScreenHeader("Manuscript Selection");
        
        System.out.println("\nPlease select a manuscript below, or 0 to go back.");
        
        displaySubPCManuscriptOptionList();
        choice = SystemHelper.promptUserInt();
        
        if(choice == 0) return null;
        
        try {
            selectedManuscript = mySPC.getMyAssignedManuscripts().get(choice -1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid Manuscript option");
        }
        
        return selectedManuscript;
    }
    
    /*
     * Displays a simple numbered list of Manuscript titles for a Subprogram Chair menu.
     */
    private void displaySubPCManuscriptOptionList() {
        List<Manuscript> manuscripts = mySPC.getMyAssignedManuscripts();
        int option = 1;
        for(Manuscript manuscript : manuscripts) {
            System.out.println(option++ + ") " + manuscript.getTitle());
        }
        System.out.println("0) Back");
    }
    
    /*
     * Displays a menu to select a Subprogram Chair to assign to a Manuscript.
     * 
     * @return the selected SubprogramChair.
     */
    private Reviewer selectReviewerToAssign() {
        int choice = -1;

        List<Reviewer> reviewers = myCurrentConference.getAllReviewers();
        Reviewer selectedReviewer = null;
        
        displayScreenHeader("Reviewer Selection");
        displayReviewerSelectMenu(reviewers);
        
        choice = SystemHelper.promptUserInt();
        
        try {
            selectedReviewer = reviewers.get(choice - 1);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Invalid input");
        }
        
        return selectedReviewer;
    }
    
    /*
     * Displays the possible users to be selected as reviewers.
     */
    private void displayReviewerSelectMenu(List<Reviewer> theReviewers) {
        int option = 1;
    	System.out.println("\nSelect the Reviewer to assign to this paper, or 0 to go back.");
        
        for (Reviewer reviewer : theReviewers) {
            System.out.println(option++ + ") " + reviewer.getLastName());
        }
        System.out.println("0) Back");
    }

    /*
     * Initiates the menus required to assign a recommendation.
     */
    private void assignRecommendation() {
    	int recommendation = -1;
    	
    	if (mySPC.getMyAssignedManuscripts().size() <= 0) {
    		System.out.println("\nNo papers assigned");
    		return;
    	}
    	
    	Manuscript manuscript = selectManuscriptPrompt();
    	
    	if (Objects.isNull(manuscript)) { 
    		System.out.println("Invalid Manuscript Selected.");
    		return;
    	}
    	
    	if (!manuscript.isAllReviewsSubmitted()) {
    		System.out.println("Unable to submit a recommendation until all reviews are submitted.");
    		return;
    	}

    	displayRecommendationSelect(manuscript.getScale());
    	recommendation = SystemHelper.promptUserInt();
    	
    	if(recommendation == 0) return;
    	
        finalizeRecommendation(manuscript, recommendation - 1);
    }
    
    /*
     * Displays an option list to select the from the Recommendation Scale.
     * @param theScale the List of scale values to select from.
     * @return the selected scale value.
     */
    private void displayRecommendationSelect(List<String> theScale) {
    	int option = 1;
    	
    	displayScreenHeader("Select Recommendation Score");
    	System.out.println("\nPlease select the recommendation score, or 0 to go back.");
    	
    	for (String rec : theScale) {
    		System.out.println(option++ + ") "+ rec);
    	}
    	System.out.println("0) Back");
    }
    
    /*
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
	
	/*
	 * Displays a status table for the manuscripts assigned to the current SubProgramChair
	 */
	private void displayManuscriptTable() {
	    //Headers
	    System.out.printf("\n" + SystemHelper.SPC_MAN_DISPLAY_FORMAT, "Title", 
	                      "Reviewer", "Review Score", "Recommendation");
	    SystemHelper.displayDashedLine();
	     
	    if(mySPC.getMyAssignedManuscripts().isEmpty()) {
	    	System.out.println("\nNo manuscripts to display.\n");
	    }
	    
	    for (Manuscript manuscript : mySPC.getMyAssignedManuscripts()) {
	        String title = SystemHelper.shorten(30, manuscript.getTitle());
	        String recommendation = manuscript.getRecommendation();
	        
	        Map<Integer, Review> reviews = manuscript.getReviews();
	        Set<Integer> reviewers = reviews.keySet();
	        
	        if(reviewers.isEmpty()) {
	        	System.out.printf(SystemHelper.SPC_MAN_DISPLAY_FORMAT, title, 
	        			SystemHelper.NOTHING_TO_DISPLAY, SystemHelper.NOTHING_TO_DISPLAY,
	        			recommendation);
	        } else {
		        displayDetailLineForEachReviewer(reviews, reviewers, title, recommendation);
		    }
	    }   
	}
	
	/*
	 * Displays a table detail line for each reviewer of the current manuscript.
	 * 
	 * @param reviews - A Map of reviews for the current manuscript.
	 * @param reviewers - A Set of reviewer IDs for each review given to the current manuscript.
	 * @param title - The title of the current manuscript.
	 * @param recommendation - The recommendation given to the current manuscript.
	 */
	private void displayDetailLineForEachReviewer(Map<Integer, Review> reviews, Set<Integer> reviewers, 
			                                      String title, String recommendation) {
		for(Integer reviewID : reviewers) {
			Reviewer reviewer = myCurrentConference.getReviewer(reviewID);
			String reviewerLastName = reviewer.getLastName();
        	Review review = reviews.get(reviewID);
        	// Need a get max scale for review scores.
        	String reviewScore = review == null ? "--/10" : review.getScore() + "/10";
        	
        	System.out.printf(SystemHelper.SPC_MAN_DISPLAY_FORMAT, title, 
     		           reviewerLastName, reviewScore, recommendation);
        	
        	title = "";
        	recommendation = "";
		}
		System.out.println("");
	}

	/*
	 * Finalizes the "Make Recommendation" option by assigning a selected recommendation to
	 * the selected manuscript if the recommendation was valid.  Displays an error message if
	 * the recommendation was not valid.
	 * 
	 * @param theManuscript - The Manuscript to be assigned the specified recommendation.
	 * @param theRecommendation - The recommendation score to assign.
	 */
	private void finalizeRecommendation(Manuscript theManuscript, int theRecommendation) {
		try {
        	theManuscript.getScale().get(theRecommendation);
        	theManuscript.setRecommendation(theRecommendation);
        	System.out.println(theManuscript.getTitle() + "'s recommendation set to " 
        	                  + theManuscript.getScale().get(theRecommendation) + "!");
        } catch (IndexOutOfBoundsException e) {
        	System.out.println("Invalid recommendation selection.");
        }
    	
    }
    
	/*
	 * Finalizes the "Assign Reviewer" option by assigning a selected Reviewer to
	 * the selected manuscript if the recommendation was valid.  Displays an error message if
	 * the Reviewer is unable to be assigned.
	 * 
	 * @param theManuscript - The Manuscript to be assigned to specified Reviewer.
	 * @param theReviewer - The Reviewer to be assigned to the specified Manuscript.
	 */
    private void finalizeReviewerAssignment(Manuscript theManuscript, Reviewer theReviewer) {
        if(Objects.nonNull(theReviewer)) {
            if(theReviewer.assignManuscript(theManuscript) != -1) {
                theManuscript.setReview(theReviewer.getID());
                System.out.println(theReviewer.getLastName() + " is assigned " 
                                   + theManuscript.getTitle() + "!");
            } else {
            	System.out.println("Unable to assign reviewer.");
            }
        } else {
        	System.out.println("Invalid reviewer selected.");
        }
    }
}

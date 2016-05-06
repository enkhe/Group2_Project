package view;
/*
 * TCSS360 Group 2 Project
 */

/**
 * 
 * @author
 * @version
 *
 */
public class ReviewerUI {
	
	Author myAuthor;
	Conference myCurrentConference;
	
	public ReviewerUI () {
		Author myAuthor = new Author();
		Conference myCurrentConference = new Conference();
	}
	
	/**
     * Provides menu options for all Reviewer Actions.
     */
    private void reviewerMenu() {
        //Submit a review
    	int choice = -1;

    	do {
    		displayScreenHeader(String menuTitle)
    		
    		writeln("1) Download all Manuscripts");
    		writeln("2) Update/Remove a Review");
    		writeln("3) Upload Review");
    		writeln("4) Back");
    		writeln("5) Exit");
    		writeln("\n > ");

    		choice = myScanner.nextInt();
            myScanner.nextLine();
            
    		switch(choice) {
    			case 1:
    				reviewerDownloadAllManuscripts();
    				break;
    			case 2:
    				reviewerUpdateRemoveReview();
    				break;
    			case 3:
    				reviewerUploadReview();
    				break;
    			case 4:
    				// Call previous menu.
    				break;
    			default:
    				// Exit from the menu.
    				break;
    		}

    	} while(choice != 0);
    }

    
	public boolean reviewerDownloadAllManuscripts() {
		
	}
	
	public boolean reviewerUpdateRemoveReview() {
		
	}
	
	public boolean reviewerUploadReview() {
		
	}
	
	
	private void displayScreenHeader(String menuTitle) {
        System.out.println(SystemHelper.SYS_TITLE);
        System.out.println(myCurrentConference.getConferenceName());
        System.out.println("Reviewer: " + myReviewer.getUserName());
        System.out.println(menuTitle);
    }
	
}


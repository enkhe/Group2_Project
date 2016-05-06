package view;
/*
 * TCSS360 Group 2 Project
 */

/**
 * 
 * @author Enkhamgalan Baterdene
 * @version v1.0
 *
 */
public class AuthorUI {

	Author myAuthor;
	Conference myCurrentConference;
	public AuthorUI () {
		
	}
	
	public void authorMenu() {
    	int choice = -1;

    	do {
    		displayScreenHeader(String menuTitle)
    		
    		writeln("1) Submit a Manuscript");
    		writeln("2) Modify/Remove a Manuscript");
    		writeln("3) View a manuscript review");
    		writeln("4) Back");
    		writeln("5) Exit");
    		writeln("\n > ");

    		choice = myScanner.nextInt();
            myScanner.nextLine();
            
    		switch(choice) {
    			case 1:
    				submitManuscriptMenu();
    				break;
    			case 2:
    				modifyRemoveManuscript();
    				break;
    			case 3:
    				viewManuscriptReview();
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
	
	public void submitManuscriptMenu() {
		
	}
	
	public void modifyRemoveManuscript(){
		
	}
	
	public void viewManuscriptReview() {
		
	}
	
	
}

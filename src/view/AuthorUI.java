package view;

/*
 * TCSS360 Group 2 Project
 */
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.RegisteredUser;

/**
 * Author's console based user interface.
 * 
 * @author Enkhamgalan Baterdene
 * @version v1.0
 */
public class AuthorUI {
    private Author myAuthor;
    private Conference myCurrentConference;
    public final String MENU_TITLE = "Author Menu";

    /**
     * Author console based user interface that takes user and conference information.
     * @param theRegisteredUser - An registered user.
     * @param theConference - Current conference that Author has selected.
     */
    public AuthorUI(RegisteredUser theRegisteredUser, Conference theConference) {
        myCurrentConference = theConference;
        myAuthor = theConference.getAuthor(theRegisteredUser.getID());
        if(Objects.isNull(myAuthor)) {
            myAuthor = new Author(theRegisteredUser);
        }
    }

    /**
     * Author's main menu.
     * @return - Value for returning to main menu.
     */
    public int authorMenu() {
        int choice = -1;

        do {
            displayScreenHeader(MENU_TITLE);
            displayManuscriptsForAuthor();

            writeln("1) Submit a Manuscript");
            writeln("2) Remove a Manuscript");
            writeln("3) Change a Manuscript");
            writeln("9) Exit to Login Menu");
            writeln("0) Return to Role Selection");
            choice = SystemHelper.promptUserInt();

            switch (choice) {
	            case 1:
	                submitManuscriptMenu();
	                break;
	            case 2:
	                unSubmitManuscript();
	                break;
	            case 3:
	                makeChangesToMySubmission();
	                break;
	            case 9:
	            	choice = SystemHelper.EXIT_TO_LOGIN;
	            	return choice;
	            case 0:
	            	break;
	            default:
	            	writeln("Invalid Input.");
            }

        } while (choice != 0);
        
        return choice;
    }

    /**
     * Menu item for Author's submit manuscript menu.
     */
    public void submitManuscriptMenu() {
        if(myCurrentConference.deadlinePassed(Calendar.getInstance())) {
            System.out.println("Submittion deadline passed, no further submissions are allowed.");
            return;
        }
        
        displayScreenHeader("Submit a Manuscript");
        String strFilePath, strManuscriptTitle = "";

        writeln("Please enter the file path and name, or 0 to cancel:");
        writeln("Sample path: C:\\users\\author\\documents\\paper.docx");
        strFilePath = SystemHelper.promptUserString();
        
        if (strFilePath.equals("0")) return;
        youHaveEntered(strFilePath);

        writeln("Please enter the manuscript title, or 0 to cancel:");
        strManuscriptTitle = SystemHelper.promptUserString();

        if (strManuscriptTitle.equals("0")) return;
        
        youHaveEntered(strManuscriptTitle);
        Manuscript manuscript = new Manuscript(myAuthor.getID(), strFilePath, strManuscriptTitle);
        int result = controllerAuthorSubmitManuscript(manuscript);
            
        if (result == 0) {
            // result check for conference submit too?
            myCurrentConference.submitManuscript(manuscript, myAuthor);
            writeln(myAuthor.getUserName() + " has successfully submitted the manuscript "
                        + strManuscriptTitle + "!");
        } else {
            writeln("There was some error in adding the manuscript.");
        }

    }
    
    
    /**
     * Menu item for Author's unsubmit manuscript.
     */
    public void unSubmitManuscript() {
        displayScreenHeader("Remove a Manuscript");

        // View All manuscripts
        List<Manuscript> manuscripts = myAuthor.getMyManuscripts();
        int intConsoleInput = promptUserForManuscript(manuscripts);
        
        if (intConsoleInput == 0) {
            return;
        }
        
        Manuscript selectedManuscript = myAuthor.getMyManuscripts().get(intConsoleInput - 1);
        
        int result = controllerRemoveManuscript(selectedManuscript);
        
        if (result == 0) {
            writeln(unsubmitManuscriptSuccessfullMessage(myAuthor.getUserName() , selectedManuscript.getTitle()));
        } else {
            writeln(unsubmitManuscriptUnSuccessfullMessage(myAuthor.getUserName() , selectedManuscript.getTitle()));
        }
    }

    /**
     * Menu item for Author manuscript submission.
     */
    public void makeChangesToMySubmission() {
        if(myCurrentConference.deadlinePassed(Calendar.getInstance())) {
            System.out.println("Submittion deadline passed, no further modifications are allowed.");
            return;
        }
        
    	displayScreenHeader("Change a Manuscript");
        
        // View All manuscripts
        List<Manuscript> theManuscripts = myAuthor.getMyManuscripts();
        
        if (theManuscripts.size() == 0) {
            writeln("Currently, there were no manuscripts found under the username, " + myAuthor.getUserName());
            writeln("Press 0 to return.");
            SystemHelper.promptUserString();
            return;
        }
       
        int intConsoleInput = promptUserForManuscript(theManuscripts);
        
        if (intConsoleInput == 0) {
            return;
        }
        
        Manuscript selectedManuscript = theManuscripts.get(intConsoleInput -1 );

        // You've selected ( this manuscript.)
        writeln("You've selected the title, \"" + selectedManuscript.getTitle() + "\"");
        
        String strManuscriptTitle = "";

        // Get Manuscript Title.
        writeln("Please enter the new manuscript title or 0 to cancel:");
        writeln("Sample title: \" Scikit-learn: Machine learning in Python \"");

        strManuscriptTitle = SystemHelper.promptUserString();
        
        if(strManuscriptTitle.equalsIgnoreCase("0")) {
            writeln("\n Returned to main menu.");
            return;
        }
        
        youHaveEntered(strManuscriptTitle);
        String previousTitle = selectedManuscript.getTitle();

        int result = controllerMakeChangesToSubmition(selectedManuscript, strManuscriptTitle);
        
        if (result == 0) {
            writeln(successfullManuscriptReplacementMessage(myAuthor.getUserName(), previousTitle, selectedManuscript.getTitle()));
        } else {
            writeln(unSuccessfullManuscriptReplacementMessage(myAuthor.getUserName(), previousTitle, selectedManuscript.getTitle()));
        }
    }
    
    /**
     * 
     * @param manuscript
     * @return
     */
    public int controllerAuthorSubmitManuscript (Manuscript manuscript) {
        return myAuthor.submitManuscript(myCurrentConference.getConferenceName(), manuscript);
    }
    
    /**
     * 
     * @param theManuscriptToBeRemoved
     * @return
     */
    public int controllerRemoveManuscript(Manuscript theManuscriptToBeRemoved) {
        int result = myAuthor.removeManuscript(theManuscriptToBeRemoved);
        
        if(result != -1) {
            myCurrentConference.removeManuscript(theManuscriptToBeRemoved);
        }
        
        return result;
    }
    
    /**
     * 
     * @param previous
     * @param theReplacementManuscript
     * @return
     */
    public int controllerMakeChangesToSubmition(Manuscript selectedManuscript, String theTitle) {
        selectedManuscript.setTitle(theTitle);
        int result = myCurrentConference.modifyManuscript(selectedManuscript, theTitle);
        
        return result;
    }
    
    /**
     * 
     * @param userName - Author's username.
     * @param origManuscriptTitle - Author's manuscript original title.
     * @param replacementManuscriptTitle - Author's manuscripts replacement title.
     * @return
     */
    public String successfullManuscriptReplacementMessage(String userName, String origManuscriptTitle, String replacementManuscriptTitle) {
        return userName + " has succesfully replaced " + origManuscriptTitle + " with " + replacementManuscriptTitle + "!";
    }
    
    /**
     * 
     * @param userName - Author's user name.
     * @param origManuscriptTitle - Author's manuscript original title.
     * @param replacementManuscriptTitle - Author's manuscripts replacement title.
     * @return
     */
    public String unSuccessfullManuscriptReplacementMessage(String userName, String origManuscriptTitle, String replacementManuscriptTitle) {
        return userName + ", there was some error in replacing " + origManuscriptTitle + " with "
                + replacementManuscriptTitle + ".";
    }
    
    /**
     * 
     * @param theUserName - Author's user name.
     * @param theManuscriptTitle - Author's manuscript title.
     * @return - System message that states un submit manuscript has successfully submitted.
     */
    public String unsubmitManuscriptSuccessfullMessage (String theUserName, String theManuscriptTitle) {
        return theUserName + " has successfully removed the manuscript "
                + theManuscriptTitle + "!";
    }
    
    /**
     * 
     * @param theUserName - Author's user name.
     * @param theManuscriptTitle - Author's manuscript title.
     * @return - System message that states manuscript has error submitting.
     */
    public String unsubmitManuscriptUnSuccessfullMessage (String theUserName, String theManuscriptTitle) {
        return theUserName + ", there was some error in removing the manuscript: " + theManuscriptTitle + ".";
    }

    private int promptUserForManuscript(List<Manuscript> theManuscripts) {
		writeln("Please select a manuscript below or 0 to cancel.\n");
	    int counter = 0;
	    for (Manuscript manuscript : theManuscripts) {
	        counter ++;
	        writeln(counter + ") " + manuscript.getTitle());
	    }
	    
	    writeln("0) Cancel");
	    
	    return SystemHelper.promptUserInt();
	    
	}
    
    private void youHaveEntered(String theInput) {
        writeln("You have entered: " + theInput);
    }

    private void writeln(String theInput) {
        System.out.println(theInput);
    }
    
    private void displayScreenHeader(String menuTitle) {
        writeln(SystemHelper.SYS_TITLE);
        writeln(myCurrentConference.getConferenceName());
        writeln("Author: " + myAuthor.getUserName());
        writeln(menuTitle + "\n");
    }
    
	private void displayManuscriptsForAuthor() {
		List<Manuscript> manuscripts = myCurrentConference.getAllAuthorsManuscript(myAuthor.getID());
		
		System.out.printf(SystemHelper.AUT_MAN_DISPLAY_FORMAT, "Manuscript Titles", "Submission Deadline", "Acceptance Status");
		SystemHelper.displayDashedLinesShort();
		
		if (manuscripts.size() < 1) {
			System.out.println("Not Available.\n");
		}
	    
		for ( Manuscript man : manuscripts ) {
			if (man.getAuthorID() == myAuthor.getID()) {
				String title = SystemHelper.shorten(30, man.getTitle());
				String deadline = SystemHelper.shorten(30, myCurrentConference.getDeadlineString());
				String acceptance = SystemHelper.shorten(15, man.getAcceptStatus());
				System.out.printf(SystemHelper.AUT_MAN_DISPLAY_FORMAT, title, deadline, acceptance);
			}
		}
		System.out.println("");
	}
}

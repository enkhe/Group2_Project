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
 * 
 * @author Enkhamgalan Baterdene
 * @version v1.0
 *
 */
public class AuthorUI {

    private Author myAuthor;
    private Conference myCurrentConference;
    public final String MENU_TITLE = "Author Menu";

    public AuthorUI(RegisteredUser theRegisteredUser, Conference theConference) {
        myCurrentConference = theConference;
        myAuthor = theConference.getAuthor(theRegisteredUser.getID());
        if(Objects.isNull(myAuthor)) {
            myAuthor = new Author(theRegisteredUser);
        }
    }

    public void authorMenu() {
        int choice = -1;

        do {
            displayScreenHeader(MENU_TITLE);

            writeln("1) Submit a Manuscript");
            writeln("2) Remove a Manuscript");
            writeln("3) Change a Manuscript");
            writeln("0) Return to Main Menu");
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
            }

        } while (choice != 0);
    }

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
     * 
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

    public void makeChangesToMySubmission() {
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
        return myAuthor.submitManuscript(manuscript);
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
     * @param userName
     * @param origManuscriptTitle
     * @param replacementManuscriptTitle
     * @return
     */
    public String successfullManuscriptReplacementMessage(String userName, String origManuscriptTitle, String replacementManuscriptTitle) {
        return userName + " has succesfully replaced " + origManuscriptTitle + " with " + replacementManuscriptTitle + "!";
    }
    
    /**
     * 
     * @param userName
     * @param origManuscriptTitle
     * @param replacementManuscriptTitle
     * @return
     */
    public String unSuccessfullManuscriptReplacementMessage(String userName, String origManuscriptTitle, String replacementManuscriptTitle) {
        return userName + ", there was some error in replacing " + origManuscriptTitle + " with "
                + replacementManuscriptTitle + ".";
    }
    
    /**
     * 
     * @param theUserName
     * @param theManuscriptTitle
     * @return
     */
    public String unsubmitManuscriptSuccessfullMessage (String theUserName, String theManuscriptTitle) {
        return theUserName + " has successfully removed the manuscript "
                + theManuscriptTitle + "!";
    }
    
    /**
     * 
     * @param theUserName
     * @param theManuscriptTitle
     * @return
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

	/**
     * 
     * @param theInput
     */
    private void youHaveEntered(String theInput) {
        writeln("You have entered: " + theInput);
    }

    /**
     * 
     * @param theInput
     */
    private void writeln(String theInput) {
        System.out.println(theInput);
    }

    /**
     * 
     * @param menuTitle
     */
    private void displayScreenHeader(String menuTitle) {
        writeln(SystemHelper.SYS_TITLE);
        writeln(myCurrentConference.getConferenceName());
        writeln("Author: " + myAuthor.getUserName());
        writeln(menuTitle + "\n");
    }

}

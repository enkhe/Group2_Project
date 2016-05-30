package view;

import java.util.Calendar;
import java.util.List;
<<<<<<< HEAD
import java.util.Objects;

=======
>>>>>>> almost-master
import model.Author;
import model.Conference;
import model.Manuscript;
import model.RegisteredUser;

/**
<<<<<<< HEAD
 * Author's console based user interface.
=======
 * TCSS360 Group 2 Project
>>>>>>> almost-master
 * 
 * @author Enkhamgalan Baterdene
 * @version v1.0
 */
public class AuthorUI {
<<<<<<< HEAD
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
        displayManuscriptsForAuthor();
    }
    
	private void displayManuscriptsForAuthor() {
		List<Manuscript> manuscripts = myCurrentConference.getAllAuthorsManuscript(myAuthor.getID());
=======

	private Author myAuthor;
	private Conference myCurrentConference;
	public final String MENU_TITLE = "Author Menu";

	/**
	 * Constructs Author UI.
	 * @param theRegisteredUser
	 * @param theConference
	 */
	public AuthorUI(RegisteredUser theRegisteredUser, Conference theConference) {
		myCurrentConference = theConference;
		myAuthor = theConference.getAuthor(theRegisteredUser.getID());
	}

	/**
	 * Author main menu.
	 */
	public void authorMenu() {
		int choice = -1;

		do {
			displayScreenHeader(MENU_TITLE);

			viewAllAuthorManuscripts();
			
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

	public void viewAllAuthorManuscripts() {
		writeln("Author Manuscripts:");
		
		List<Manuscript> manuscripts = myAuthor.getMyManuscripts();
		writeln("Title");
		for (Manuscript manuscript : manuscripts) {
			writeln("" + manuscript.getTitle());
		}
		writeln("");
	}
	/**
	 * Author submit manuscript menu.
	 */
	public void submitManuscriptMenu() {
		displayScreenHeader("Submit a Manuscript");
		String strFilePath, strManuscriptTitle = "";

		// Get Manuscript FilePath.
		writeln("Please enter the file path and name, or 0 to return.");
		writeln("Sample path: C:\\users\\author\\documents\\paper.docx\n");
		strFilePath = SystemHelper.promptUserString();
		
		if (strFilePath.equals("0")) {
			return;
		}
		
		SystemHelper.youHaveEntered(strFilePath);

		// Get Manuscript Title.
		writeln("Please enter the manuscript title:");
		writeln("Sample title: ");
		strManuscriptTitle = SystemHelper.promptUserString();

		SystemHelper.youHaveEntered(strManuscriptTitle);

		Calendar currentDate = Calendar.getInstance();
		
		if (!myCurrentConference.deadlinePassed(currentDate)) { 
			
			int result = controllerAuthorSubmitManuscript(new Manuscript(myAuthor.getID(), strFilePath, strManuscriptTitle));
			
			if (result == 0) {
				writeln(myAuthor.getUserName() + " have successfull submitted a manuscript with a title of "
						+ strManuscriptTitle);
			} else {
				writeln("There was some error in adding the manuscript.");
			}
		} else {
			writeln("The submission deadline has reached.");
		}

	}
	
	/**
	 * Author unsubmit manuscript menu option.
	 */
	public void unSubmitManuscript() {
		displayScreenHeader("Remove a Manuscript");
		writeln("Please select the manuscript that you'd like to remove.\n");

		// View All manuscripts
		List<Manuscript> manuscripts = myAuthor.getMyManuscripts();
		int counter = 0;
		for (Manuscript manuscript : manuscripts) {
			counter++;
			writeln(counter + ") " + manuscript.getTitle());
		}

		int intConsoleInput = SystemHelper.promptUserInt();
		
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
	 * Author make changes to my submission menu option.
	 */
	public void makeChangesToMySubmission() {
		displayScreenHeader("Change a Manuscript");
		// View All manuscripts
		writeln("Please, select the manuscript that you'd like to make changes to.");

		List<Manuscript> theManuscripts = myAuthor.getMyManuscripts();
		if (theManuscripts.size() == 0) {
			writeln("Currently, there were no manuscripts found under the username, " + myAuthor.getUserName());
			writeln("Press 0 to return.\n");
			write(" > ");
			return;
		}
		
		writeln("Please, enter 0 to go Back.\n");
		int counter = 0;
		for (Manuscript manuscript : theManuscripts) {
			counter ++;
			writeln(counter + ") " + manuscript.getTitle());
		}

		int intConsoleInput = SystemHelper.promptUserInt();
		
		if (intConsoleInput == 0) {
			return;
		}
>>>>>>> almost-master
		
		System.out.printf(SystemHelper.AUT_MAN_DISPLAY_FORMAT, "Manuscript Titles", "Submission Deadline", "Acceptance Status");
		SystemHelper.displayDashedLinesFor(myAuthor);
		
<<<<<<< HEAD
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
=======
		writeln("1) Please, enter the file path and name, or 0 to return.");
		writeln("Sample path: C:\\users\\author\\documents\\paper.docx\n");
		
		strFilePath = SystemHelper.promptUserString();
		
		if(strFilePath.equalsIgnoreCase("0")) {
			writeln("\n Returned to main menu.");
			return;
		}

		SystemHelper.youHaveEntered(strFilePath);

		// Get Manuscript Title.
		writeln("2) Please enter the manuscript title:");
		writeln("Sample title: \" Scikit-learn: Machine learning in Python \"");

		strManuscriptTitle = SystemHelper.promptUserString();
		
		if(strManuscriptTitle.equalsIgnoreCase("0")) {
			writeln("\n Returned to main menu.");
			return;
		}
		
		SystemHelper.youHaveEntered(strManuscriptTitle);

		Manuscript manuscript = new Manuscript(myAuthor.getID(), strFilePath, strManuscriptTitle);
		
		int result = controllerMakeChangesToSubmition(selectedManuscript, manuscript);
		
		if (result == 0) {
			writeln(successfullManuscriptReplacementMessage(myAuthor.getUserName(), selectedManuscript.getTitle(), manuscript.getTitle()));
		} else {
			writeln(unSuccessfullManuscriptReplacementMessage(myAuthor.getUserName(), selectedManuscript.getTitle(), manuscript.getTitle()));
		}
	}
	

	/**
	 * Controller: Author submit manuscript.
	 * @param manuscript
	 * @return
	 */
	public int controllerAuthorSubmitManuscript (Manuscript manuscript) {
		return myAuthor.submitManuscript(manuscript);
	}
	
	/**
	 * Controller: Author remove manuscript.
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
	 * Controller: Author make changes to submission.
	 * 
	 * @param previous
	 * @param theReplacementManuscript
	 * @return
	 */
	public int controllerMakeChangesToSubmition(Manuscript previous, Manuscript theReplacementManuscript) {
		int result = myAuthor.replaceManuscript(previous, theReplacementManuscript);
		
		if(result == 0) {
			myCurrentConference.modifyManuscript(previous, theReplacementManuscript);
		}
		
		return result;
	}
	
	/**
	 * Message: Successful manuscript replacement.
	 * 
	 * @param userName
	 * @param origManuscriptTitle
	 * @param replacementManuscriptTitle
	 * @return
	 */
	public String successfullManuscriptReplacementMessage(String userName, String origManuscriptTitle, String replacementManuscriptTitle) {
		return userName + " have succesfully replaced " + origManuscriptTitle + " with " + replacementManuscriptTitle;
	}
	
	/**
	 * Message: Unsuccessful manuscript replacement.
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
	 * Message: Unsubmit manuscript successful.
	 * 
	 * @param theUserName
	 * @param theManuscriptTitle
	 * @return
	 */
	public String unsubmitManuscriptSuccessfullMessage (String theUserName, String theManuscriptTitle) {
		return theUserName + " have successfully removed a manuscript with a title of "
				+ theManuscriptTitle;
	}
	
	/**
	 * Message: Unsubmit manuscript unsuccessful.
	 * 
	 * @param theUserName
	 * @param theManuscriptTitle
	 * @return
	 */
	public String unsubmitManuscriptUnSuccessfullMessage (String theUserName, String theManuscriptTitle) {
		return theUserName + ", there was some error in removing the manuscript: " + theManuscriptTitle + ".";
	}
	

	/**
	 * Write line out to console.
	 * @param theInput
	 */
	private void write(String theInput) {
		System.out.println(theInput);
	}

	/**
	 * Write new line out to console.
	 * @param theInput
	 */
	private void writeln(String theInput) {
		System.out.println(theInput);
	}

	/**
	 * Display screen header.
	 * @param menuTitle
	 */
	private void displayScreenHeader(String menuTitle) {
		writeln(SystemHelper.SYS_TITLE);
		writeln(myCurrentConference.getConferenceName());
		writeln("Author: " + myAuthor.getUserName());
		writeln(menuTitle + "\n");
	}

>>>>>>> almost-master
}

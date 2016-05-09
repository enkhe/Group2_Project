package view;

/*
 * TCSS360 Group 2 Project
 */
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

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
	private Scanner myScanner;
	public final String MENU_TITLE = "Author Menu";

	public AuthorUI(RegisteredUser theRegisteredUser, Conference theConference) {
		myCurrentConference = theConference;
		myAuthor = theConference.getAuthor(theRegisteredUser.getID());
		myScanner = new Scanner(System.in);
	}

	public void authorMenu() {
		int choice = -1;

		do {
			displayScreenHeader(MENU_TITLE);

			writeln("1) Submit a Manuscript");
			writeln("2) Remove a Manuscript");
			writeln("3) Change a Manuscript");
			writeln("0) Return to Main Menu");
			write(" > ");

			choice = myScanner.nextInt();
			myScanner.nextLine();

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
		displayScreenHeader("Submit a Manuscript");
		String strFilePath, strManuscriptTitle = "";

		// Get Manuscript FilePath.
		writeln("Please enter the file path and name, or 0 to return.");
		writeln("Sample path: C:\\users\\author\\documents\\paper.docx\n");
		write("\n > ");
		strFilePath = getConsoleLine();
		
		if (Integer.parseInt(strFilePath) == 0) {
			return;
		}
		
		youHaveEntered(strFilePath);

		// Get Manuscript Title.
		writeln("Please enter the manuscript title:");
		writeln("Sample title: ");
		write("\n > ");
		strManuscriptTitle = getConsoleLine();

		youHaveEntered(strManuscriptTitle);

		//Date theDateOne = myCurrentConference.getCreationDate();
		//Date theDateTwo = new Date();

		// Check for Deadline.
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
		write("\n > ");

		int intConsoleInput = getConsoleInt();
		
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
		writeln("Please, select the manuscript that you'd like to make changes to.");

		List<Manuscript> theManuscripts = myAuthor.getMyManuscripts();
		if (theManuscripts.size() == 0) {
			writeln("Currently, there were no manuscripts found under the username, " + myAuthor.getUserName());
			writeln("Press 0 to return.\n");
			write(" > ");
			getConsoleLine();
			return;
		}
		

		writeln("Please, enter 0 to go Back.\n");
		int counter = 0;
		for (Manuscript manuscript : theManuscripts) {
			counter ++;
			writeln(counter + ") " + manuscript.getTitle());
		}
		write("\n > ");

		int intConsoleInput = getConsoleInt();
		
		if (intConsoleInput == 0) {
			return;
		}
		
		Manuscript selectedManuscript = theManuscripts.get(intConsoleInput -1 );

		// You've selected ( this manuscript.)
		writeln("You've selected the title, \"" + selectedManuscript.getTitle() + "\"");
		
		String strFilePath, strManuscriptTitle = "";

		// Get Manuscript FilePath.
		writeln("\nSelecting the replacement Manuscript.");
		writeln("Requires: ");
		writeln("\t - 1) Selecting Manuscript File Path.");
		writeln("\t - 2) Selecting Manuscript Title.\n");
		
		writeln("1) Please, enter the file path and name, or 0 to return.");
		writeln("Sample path: C:\\users\\author\\documents\\paper.docx\n");
		write("\n > ");
		strFilePath = getConsoleLine();
		
		if(strFilePath.equalsIgnoreCase("0")) {
			writeln("\n Returned to main menu.");
			return;
		}

		youHaveEntered(strFilePath);

		// Get Manuscript Title.
		writeln("2) Please enter the manuscript title:");
		writeln("Sample title: \" Scikit-learn: Machine learning in Python \"");
		write(" > ");

		strManuscriptTitle = getConsoleLine();
		
		if(strManuscriptTitle.equalsIgnoreCase("0")) {
			writeln("\n Returned to main menu.");
			return;
		}
		

		youHaveEntered(strManuscriptTitle);

		Manuscript manuscript = new Manuscript(myAuthor.getID(), strFilePath, strManuscriptTitle);
		
		int result = controllerMakeChangesToSubmition(selectedManuscript, manuscript);
		
		if (result == 0) {
			writeln(successfullManuscriptReplacementMessage(myAuthor.getUserName(), selectedManuscript.getTitle(), manuscript.getTitle()));
		} else {
			writeln(unSuccessfullManuscriptReplacementMessage(myAuthor.getUserName(), selectedManuscript.getTitle(), manuscript.getTitle()));
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
		return myAuthor.removeManuscript(theManuscriptToBeRemoved);
	}
	
	/**
	 * 
	 * @param previous
	 * @param theReplacementManuscript
	 * @return
	 */
	public int controllerMakeChangesToSubmition(Manuscript previous, Manuscript theReplacementManuscript) {
		return myAuthor.replaceManuscript(previous, theReplacementManuscript);
	}
	
	/**
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
		return theUserName + " have successfully removed a manuscript with a title of "
				+ theManuscriptTitle;
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
	
	
	private String getConsoleLine() {
		return new Scanner(System.in).nextLine();
	}

	private int getConsoleInt() {
		return new Scanner(System.in).nextInt();
	}

	private void youHaveEntered(String theInput) {
		writeln("You have entered: " + theInput);
	}

	private void write(String theInput) {
		System.out.print(theInput);
	}

	private void writeln(String theInput) {
		System.out.println(theInput);
	}

	private void displayScreenHeader(String menuTitle) {
		for(int i = 0; i < 50; i++) {
			writeln("");
		}
		writeln(SystemHelper.SYS_TITLE);
		writeln(myCurrentConference.getConferenceName());
		writeln("Author: " + myAuthor.getUserName());
		writeln(menuTitle + "\n");
	}

}

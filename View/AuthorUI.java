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
			writeln("2) Modify/Remove a Manuscript");
			writeln("3) View a manuscript review");
			writeln("4) Back");
			writeln("5) Exit");
			writeln("\n > ");

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
			case 4:
				// Call previous menu.
				break;
			default:
				// Exit from the menu.
				break;
			}

		} while (choice != 0);
	}

	public void submitManuscriptMenu() {
		displayScreenHeader("");
		String strFilePath, strManuscriptTitle = "";

		// Get Manuscript FilePath.
		writeln("Please enter the file path and name, or 0 to return.");
		writeln("Sample path: C:\\users\\author\\documents\\paper.docx");
		write(" > ");
		strFilePath = getConsoleLine();

		youHaveEntered(strFilePath);

		// Get Manuscript Title.
		writeln("Please enter the manuscript title:");
		writeln("Sample title: ");
		write(" > ");
		strManuscriptTitle = getConsoleLine();

		youHaveEntered(strManuscriptTitle);

		//Date theDateOne = myCurrentConference.getCreationDate();
		//Date theDateTwo = new Date();

		// Check for Deadline.
		Calendar currentDate = Calendar.getInstance();
		
		if (!myCurrentConference.deadlinePassed(currentDate)) { 
			// +++++++++++ Action happens here +++++++++++ 
			int result = myAuthor.submitManuscript(new Manuscript(myAuthor.getID(), strFilePath, strManuscriptTitle)); 
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
		writeln("Please select the manuscript that you'd like to remove.");

		// View All manuscripts
		List<Manuscript> manuscripts = myAuthor.getMyManuscripts();
		int counter = 0;
		for (Manuscript manuscript : manuscripts) {
			writeln(counter++ + ") " + manuscript.getTitle());
		}
		writeln(" > ");

		Manuscript selectedManuscript = myAuthor.getMyManuscripts().get(getConsoleInt() - 1);

		int result = myAuthor.removeManuscript(selectedManuscript);
		if (result == 0) {
			writeln(myAuthor.getUserName() + " have successfully removed a manuscript with a title of "
					+ selectedManuscript.getTitle());
		} else {
			writeln("There was some error in removing the manuscript.");
		}
	}

	public void makeChangesToMySubmission() {
		// View All manuscripts
		writeln("Please, select the manuscript that you'd like to make changes to.");

		List<Manuscript> theManuscripts = myAuthor.getMyManuscripts();
		int counter = 0;
		for (Manuscript manuscript : theManuscripts) {
			writeln(counter ++ + ") " + manuscript.getTitle());
		}
		
		
		Manuscript selectedManuscript = theManuscripts.get(getConsoleInt() + 1);

		// You've selected ( this manuscript.)
		writeln("You've selected " + selectedManuscript.getTitle());
		
		String strFilePath, strManuscriptTitle = "";

		// Get Manuscript FilePath.
		writeln("Please enter the file path and name, or 0 to return.");
		writeln("Sample path: C:\\users\\author\\documents\\paper.docx");
		write(" > ");
		strFilePath = getConsoleLine();

		youHaveEntered(strFilePath);

		// Get Manuscript Title.
		writeln("Please enter the manuscript title:");
		writeln("Sample title: ");
		write(" > ");
		strManuscriptTitle = getConsoleLine();

		youHaveEntered(strManuscriptTitle);

		Manuscript manuscript = new Manuscript(myAuthor.getID(), strFilePath, strManuscriptTitle);
		
		int result = myAuthor.replaceManuscript(selectedManuscript, manuscript);
		
		if (result == 0) {
			writeln(myAuthor.getUserName() + " have succesfully replaced " + selectedManuscript.getTitle() + " with "
					+ manuscript.getTitle());
		} else {
			writeln("There was some error in replacing " + selectedManuscript.getTitle() + " with "
					+ manuscript.getTitle() + ".");
		}
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
		System.out.println(SystemHelper.SYS_TITLE);
		System.out.println(myCurrentConference.getConferenceName());
		System.out.println("Author: " + myAuthor.getUserName());
		System.out.println(menuTitle);
	}

}

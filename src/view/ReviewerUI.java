package view;
/*
 * TCSS360 Group 2 Project
 */

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.RegisteredUser;
import model.Review;
import model.Reviewer;

/**
 * Reviewer console based interface.
 * 
 * @author Enkhamgalan Baterdene
 * @version 1.0
 */
public class ReviewerUI {

	Reviewer myReviewer;
	Conference myCurrentConference;
	Scanner myScanner;

	/**
	 * Reviewer console based interface that takes registered user and conference.
	 * 
	 * @param theUser - An registered user who has been assigned as an reviewer.
	 * @param theConference - The current conference that user has selected.
	 */
	public ReviewerUI(RegisteredUser theUser, Conference theConference) {
		myReviewer = theConference.getReviewer(theUser.getID());
		myCurrentConference = theConference;
		myScanner = new Scanner(System.in);
	}

	/**
	 * Reviewer's main menu.
	 */
	public int reviewerMenu() {
		// Submit a review
		int choice = -1;

		do {
			displayScreenHeader("Reviewer Menu");
			displayManuscriptsForReviewer();

		    writeln("\nPlease enter a command below.");
			writeln("1) Upload Review");
			writeln("9) Exit to Login Menu");
			writeln("0) Back to Role Selection");
			choice = SystemHelper.promptUserInt();

			switch (choice) {
				case 1:
					reviewerUploadReview();
					break;
				case 9:
					choice = SystemHelper.EXIT_TO_LOGIN;
					return choice;
				case 0:
					// return to previous menu
					break;
				default:
					System.out.println("\n Invalid menu command \n");
					break;
			}

		} while (choice != 0);
		
		return choice;
	}
	
	/**
	 * Reviewer menu for downloading all manuscripts.
	 */
	public void reviewerDownloadAllManuscripts() {
		displayScreenHeader("Download all Manuscripts");

		List<Manuscript> myManuscripts = myReviewer.getMyAssignedManuscripts();

		// Assigned Manuscripts:
		int counter = 0;
		for (Manuscript manuscript : myManuscripts) {
			// display with index
			counter++;
			writeln(counter + ") " + manuscript.getFile());
		}
	}

	/**
	 * Reviewer menu for updating a current review.
	 */
	public void reviewerUpdateRemoveReview() {
		displayScreenHeader("Update/Remove a Review");

		List<Manuscript> myManuscripts = myReviewer.getMyAssignedManuscripts();

		// Assigned Manuscripts:
		// Please select the manuscript to update the review
		//
		int counter = 0;
		for (Manuscript manuscript : myManuscripts) {
			// display with index
			writeln(counter++ + ") " + manuscript.getFile());
		}
		
		int intSelection = SystemHelper.promptUserInt();

		StringBuilder sb = new StringBuilder();
		sb.append(myScanner.nextLine());
		sb.delete(0, 3);
		
		// You've selected
		Manuscript selectedManuscript = myManuscripts.get(intSelection - 1);
		// Reviewer for the manuscript
		Review rev = selectedManuscript.getReviews().get(myReviewer.getID());

		// Update or remove review.
		int intUpdateRemoveSelection = -1;

		writeln("1) Update Review for " + selectedManuscript.getTitle());
		writeln("2) Remove Review for " + selectedManuscript.getTitle());

		switch (intUpdateRemoveSelection) {
		case 1:
			// Update Review given manuscript
			updateGivenManuscript(selectedManuscript);
			break;
		case 2:
			// remove review for manuscript
			selectedManuscript.getReviews().remove(myReviewer.getID());
			writeln("You've successfully removed the review for " + selectedManuscript.getTitle());
			break;
		default:
			break;
		}
	}

	/**
	 * Reivewer menu for uploading reviews.
	 */
	public void reviewerUploadReview() {
		displayScreenHeader("Upload Review");
	
		// Firstly, view all assigned manuscripts & see if any of them is not
		// reviewed
		List<Manuscript> myManuscripts = myReviewer.getMyAssignedManuscripts();
		System.out.println("\nSelect a manuscript below to upload a review for.");
		int counter = 1;
		for (Manuscript manuscript : myManuscripts) {
			
			write(counter++ + ") " + manuscript.getTitle());
		}
		writeln("0) Cancel");
	
		int choice = SystemHelper.promptUserInt();
		
		if(choice == 0) return;
		
		// selected manuscript that needs an review update.
		Manuscript theManuscript = myReviewer.getMyAssignedManuscripts().get(choice - 1);
		
		int intScore = -1;
	
		writeln("Please enter a review score or 0 to cancel.");
		writeln("Scale 1 - 10 (Worst to Best).");
		intScore = SystemHelper.promptUserInt();
		
		if(intScore == 0) return;
		
		writeln("Please, enter the file path for the full review or 0 to cancel.");
		writeln("Sample path: C:\\users\\author\\documents\\paper.docx");
		String reviewPath = SystemHelper.promptUserString();
		
		if(reviewPath.equals("0")) return;
		
		Review revNewReview = new Review(intScore, reviewPath);
		theManuscript.setReview(myReviewer.getID(), revNewReview);
		
		writeln("You've successfully saved a review for " + theManuscript.getTitle() + " with a score of " + intScore);
	}

	private void updateGivenManuscript(Manuscript selectedManuscript) {
		int intScore = -1;
		String strFilePath = selectedManuscript.getFile();

		writeln("Updating review for " + selectedManuscript.getTitle());
		writeln("Please enter a new score: ");
		writeln("1 being weakest to 10 being the best.\n");
		
		intScore = SystemHelper.promptUserInt();
		
		Review revNewReview = new Review(intScore, strFilePath);
		selectedManuscript.setReview(myReviewer.getID(), revNewReview);

		writeln("You've successfully updated " + selectedManuscript.getTitle());
	}

	private void write(String theInput) {
		System.out.println(theInput);
	}

	private void writeln(String theInput) {
		System.out.println(theInput);
	}

	private void displayScreenHeader(String menuTitle) {
		System.out.println(SystemHelper.SYS_TITLE);
		System.out.println(myCurrentConference.getConferenceName());
		System.out.println("Reviewer: " + myReviewer.getUserName());
		System.out.println(menuTitle);
	}

	private void displayManuscriptsForReviewer() {
		List<Manuscript> manuscripts = myReviewer.getMyAssignedManuscripts();
		
		System.out.printf("\n" + SystemHelper.REV_MAN_DISPLAY_FORMAT, 
				          "Manuscript Titles", "Score");
		SystemHelper.displayDashedLinesShort();
		
		if (manuscripts.size() < 1) {
			System.out.println("Not Available.\n");
		}
		
		for(Manuscript man : manuscripts ) {
			Review review = man.getReviews().get(myReviewer.getID());
			String strScore;
			
			if(Objects.nonNull(review)) {
				strScore = SystemHelper.shorten(15, Integer.toString(review.getScore())) + "/10";
			} else {
				strScore = "--/10";
			}
				
			String title = SystemHelper.shorten(30, man.getTitle());
			System.out.printf(SystemHelper.REV_MAN_DISPLAY_FORMAT, title, strScore);
		}
	}
}

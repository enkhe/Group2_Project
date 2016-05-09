package view;
/*
 * TCSS360 Group 2 Project
 */

import java.util.List;
import java.util.Scanner;

import model.*;

/**
 * 
 * @author Enkhamgalan Baterdene
 * @version 1.0
 *
 */
public class ReviewerUI {

	Reviewer myReviewer;
	Conference myCurrentConference;
	Scanner myScanner;

	public ReviewerUI(RegisteredUser theUser, Conference theConference) {
		myReviewer = theConference.getReviewer(theUser.getID());
		myCurrentConference = theConference;
		myScanner = new Scanner(System.in);
	}

	/**
	 * Provides menu options for all Reviewer Actions.
	 */
	private void reviewerMenu() {
		// Submit a review
		int choice = -1;

		do {
			displayScreenHeader("Reviewer Menu");

			writeln("1) Download all Manuscripts");
			writeln("2) Update/Remove a Review");
			writeln("3) Upload Review");
			writeln("4) Back");
			writeln("5) Exit");
			writeln("\n > ");

			choice = myScanner.nextInt();
			myScanner.nextLine();

			switch (choice) {
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

		} while (choice != 0);
	}

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

		write(" > ");
		int intSelection = myScanner.nextInt();

		StringBuilder sb = new StringBuilder();
		sb.append(myScanner.nextLine());
		sb.delete(0, 3);

		// In case myScanner.nextInt(); doesn't work.
		// intSelection = Integer.parseInt(sb.toString());

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

	private void updateGivenManuscript(Manuscript selectedManuscript) {
		int intScore = -1;
		String strFilePath = selectedManuscript.getFile();

		writeln("Updating review for " + selectedManuscript.getTitle());
		writeln("Please enter a new score: ");
		writeln("1 being weakest to 10 being the best.\n");
		writeln(" > ");

		myScanner = new Scanner(System.in);
		intScore = myScanner.nextInt();

		Review revNewReview = new Review(intScore, strFilePath);
		selectedManuscript.setReview(myReviewer.getID(), revNewReview);

		writeln("You've successfully updated " + selectedManuscript.getTitle());
	}

	public void reviewerUploadReview() {
		displayScreenHeader("Upload Review");

		// Firstly, view all assigned manuscripts & see if any of them is not
		// reviewed
		List<Manuscript> myManuscripts = myReviewer.getMyAssignedManuscripts();

		int counter = 0;
		for (Manuscript manuscript : myManuscripts) {
			// display with index
			String rev = manuscript.getReviews().get(myReviewer.getID()).getReviewFile();

			writeln("Index\tManuscript File Path \t hasReview");

			write(counter++ + ") " + manuscript.getFile());

			if (rev.isEmpty()) {
				writeln("\t\t\t\t No");
			} else {
				writeln("\t\t\t\t Yes");
			}

		}

		writeln(" > ");

		// select index
		myScanner = new Scanner(System.in);
		int intSelectedManuscriptIndex = myScanner.nextInt();
		
		// selected manuscript that needs an review update.
		Manuscript theManuscript = myReviewer.getMyAssignedManuscripts().get(intSelectedManuscriptIndex - 1);
		
		String strReview = theManuscript.getReviews().get(myReviewer.getID()).getReviewFile();
		Review revReview = new Review();
		revReview.setReviewFile(theManuscript.getFile());
		int intScore = -1;
		
		if(strReview.isEmpty()){
			writeln("Review for " + theManuscript.getTitle() + " is empty.");
		} else {
			writeln("Review for " + theManuscript.getTitle() + " and it has a score of " 
					+ theManuscript.getReviews().get(myReviewer.getID()).getScore());
		}
		
		writeln("Please enter a review score: ");
		writeln("1 being weakest to 10 being the best.\n");
		writeln(" > ");
		myScanner = new Scanner(System.in);
		intScore = myScanner.nextInt();
		
		Review revNewReview = new Review(intScore, theManuscript.getFile());
		theManuscript.setReview(myReviewer.getID(), revNewReview);
		
		writeln("You've successfully update a review for " + theManuscript.getTitle() + " with a score of " + intScore);
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

}

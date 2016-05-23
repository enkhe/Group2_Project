package view;

import java.util.List;
import java.util.Objects;
import java.util.Scanner;

import model.Conference;
import model.Manuscript;
import model.RegisteredUser;
import model.Review;
import model.Reviewer;

/**
 * TCSS360 Group 2 Project
 * 
 * @author Enkhamgalan Baterdene
 * @version 1.0
 *
 */
public class ReviewerUI {

	Reviewer myReviewer;
	Conference myCurrentConference;
	Scanner myScanner;

	/**
	 * Constructs Reviewer UI.
	 * @param theUser
	 * @param theConference
	 */
	public ReviewerUI(RegisteredUser theUser, Conference theConference) {
		myReviewer = theConference.getReviewer(theUser.getID());
		myCurrentConference = theConference;
		myScanner = new Scanner(System.in);
	}

	/**
	 * Provides menu options for all Reviewer Actions.
	 */
	public void reviewerMenu() {
		// Submit a review
		int choice = -1;

		do {
			displayScreenHeader("Reviewer Menu");
			
			writeln("All Assigned Manuscripts:");
			viewAllAssignedManuscripts();
			
		    writeln("\nPlease enter a command below.");
			writeln("1) View all assigned manuscripts");
			writeln("2) Update/Remove a Review");
			writeln("3) Upload Review");
			writeln("0) Back");
			choice = SystemHelper.promptUserInt();

			switch (choice) {
			case 1:
				viewAllAssignedManuscripts();
				break;
			case 2:
				reviewerUpdateRemoveReview();
				break;
			case 3:
				reviewerUploadReview();
				break;
			case 0:
				// return to previous menu
				break;
			default:
				System.out.println("\n Invalid menu command \n");
				break;
			}

		} while (choice != 0);
	}

	/**
	 * View all assigned manuscripts menu option.
	 */
	public void viewAllAssignedManuscripts() {
		for(Manuscript manuscript : myReviewer.getMyAssignedManuscripts()) {
			Review review = manuscript.getReviews().get(myReviewer.getID());
			
			System.out.print(manuscript.getTitle());
			if(Objects.nonNull(review)) {
				System.out.println("   Review Score: " + review.getScore() 
						+ " Review Path: " + review.getReviewFile());
			} else {
				System.out.println("   No review submitted.");
			}
		}
	}
	
	/**
	 * Reviewer download all manuscript.
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
	 * Reveiwer update remove review.
	 */
	public void reviewerUpdateRemoveReview() {
		displayScreenHeader("Update/Remove a Review");

		List<Manuscript> myManuscripts = myReviewer.getMyAssignedManuscripts();

		// Assigned Manuscripts:
		// Please select the manuscript to update the review
		int counter = 0;
		for (Manuscript manuscript : myManuscripts) {
			// display with index
			writeln(counter++ + ") " + manuscript.getFile());
		}
		
		int intSelection = SystemHelper.promptUserInt();

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

	/**
	 * Update given manuscript menu option.
	 * @param selectedManuscript
	 */
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

	/**
	 * Reviewer upload review menu option.
	 */
	public void reviewerUploadReview() {
		displayScreenHeader("Upload Review");

		// Firstly, view all assigned manuscripts & see if any of them is not
		// reviewed
		List<Manuscript> myManuscripts = myReviewer.getMyAssignedManuscripts();

		int counter = 1;
		for (Manuscript manuscript : myManuscripts) {
			
			write(counter++ + ") " + manuscript.getTitle());

		}

		int choice = SystemHelper.promptUserInt();
		
		// selected manuscript that needs an review update.
		Manuscript theManuscript = myReviewer.getMyAssignedManuscripts().get(choice - 1);
		
		int intScore = -1;

		writeln("Please enter a review score.");
		writeln("1 being weakest to 10 being the best.\n");
		intScore = SystemHelper.promptUserInt();
		
		writeln("Please, enter the file path for the full review.");
		writeln("Sample path: C:\\users\\author\\documents\\paper.docx\n");
		String reviewPath = SystemHelper.promptUserString();
		
		Review revNewReview = new Review(intScore, reviewPath);
		theManuscript.setReview(myReviewer.getID(), revNewReview);
		
		writeln("You've successfully saved a review for " + theManuscript.getTitle() + " with a score of " + intScore);
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
		System.out.println(SystemHelper.SYS_TITLE);
		System.out.println(myCurrentConference.getConferenceName());
		System.out.println("Reviewer: " + myReviewer.getUserName());
		System.out.println(menuTitle);
	}

}

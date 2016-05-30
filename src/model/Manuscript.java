/*
 * Author: Tyler Brent
 * Group 2 - TCSS 360A
 */

package model;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 
 * This class represents a Manuscript that Authors of a Conference can submit.
 * All information about a Manuscript that has been submitted is held here.
 *
 */

public class Manuscript implements Serializable {

	private static final long serialVersionUID = 5722370405900009754L;
	/** Author ID*/
	private int myAuthor;
	/** Subprogram Chair ID. */
	private int mySPC;
	/** SPC Recommendation Score*/
	private int myRecommendation;
	/** PC acceptance/rejection/pending flag */
	private int myAcceptStatus;
	/** File Path to the Manuscript.*/
	private String myFilePath;
	/** The title of the paper.*/
	private String myTitle;
	/** List of reviews. Key: ID, Value: Review Object */
	private Map<Integer, Review> myReviews;
	/** Scale for SPC recommendations */
	private List<String> myScale;
	
	public Manuscript() {
		myAuthor = -1;
		mySPC = -1;
		myRecommendation = -1;
		myAcceptStatus = -1;
		myTitle = "No Title Given";
		
		myReviews = new HashMap<>();
		myScale = new ArrayList<>();
		myScale.add("Strongly Not Recommended");
		myScale.add("Not Recommended");
		myScale.add("Weakly Recommended");
		myScale.add("Recommended");
		myScale.add("Strongly Recommended");
	}
	
	public Manuscript(int theAuthor) {
		this();
		myAuthor = theAuthor;
	}
	
	public Manuscript(int theAuthor, String theFilePath, String theTitle){
		this();
		myAuthor = theAuthor;
		myFilePath = theFilePath;
		myTitle = theTitle;
	}
	
	/**
	 * Precondition: Takes an int that is the ID of the Subprogram Chair (SPC).
	 * Postcondition: Sets the Subprogram chair of this Manuscript to the one provided.
	 */
	public void setSPC(int theSPC) {
		mySPC = theSPC;
	}
	
	/**
	 * Precondition: Takes a String that represents the file path of the Manuscript.
	 * Postcondition: Sets the file path of this Manuscript to the one provided.
	 */
	public void setFilePath(String theFilePath) {
		myFilePath = theFilePath;
	}
	
	/**
	 * Precondition: Takes an int as the ID of the Reviewer and a Review object as the review
	 * of the Manuscript.
	 * Postcondition: Adds the review to the Manuscript and creates a copy of the review file.
	 */
	public void setReview(int theID, Review theReview) {
		myReviews.put(theID, theReview);
		
		if(Objects.nonNull(theReview)) {
			//get file name and extension of review
			int fileIndexOfNameAndExtension = theReview.getReviewFile().lastIndexOf(File.separator);
			String fileNameAndExtension = theReview.getReviewFile().substring(fileIndexOfNameAndExtension, 
					theReview.getReviewFile().length());
			
			//parse filepath of manuscript to get destination
			String path = getFile().substring(0, getFile().lastIndexOf(File.separator));
			
			//get the full paths to the files
			Path from = Paths.get(theReview.getReviewFile());
			Path to = Paths.get(path+ File.separator + fileNameAndExtension); 
			
			//copy the files over
			try {
				Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.println("Huston, there seems to be a problem!");
				System.err.println("I advise you to check out Authors submitManuscript method immediatly.");
				System.err.println("Also, be sure to check that you entered your file path correctly!");
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Precondition: Takes an int ranging from 0 to 4 that represents the recommendation.
	 * 0 being Strongly Not Recommended, 4 being Strong Recommended.
	 * Postcondition: Sets the recommendation of this Manuscript to the one provided. Throws
	 * an exception if an incorrect number is passed.
	 */
	public void setRecommendation(int theRecommendation) {
		if(theRecommendation >= 0 && theRecommendation <= 4) {
			myRecommendation = theRecommendation;
		} else {
			throw new IllegalArgumentException("Invalid number used for the Scale.");
		}
	}
	
	/**
	 * Precondition: Takes an int that represents the acceptance status of the Manuscript.
	 * 0 for rejected, 1 for accepted.
	 * Postcondition: Sets the acceptance status of this Manuscript to the one provide.
	 */
	public void setAcceptStatus(int theAcceptStatus) {
		if(theAcceptStatus == 0 || theAcceptStatus == 1) {
			myAcceptStatus = theAcceptStatus;
		} else {
			throw new IllegalArgumentException("Invalid number used for Accept Status");
		}
	}
	
	/**
	 * Precondition: Takes a String as the title of the Manuscript.
	 * Postcondition: Sets the title of the Manuscript to the one provided.
	 */
	public void setTitle(String theTitle) {
		myTitle = theTitle;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the Author of this paper as an int ID.
	 */
	public int getAuthorID() {
		return myAuthor;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the Subprogram Chair of this Manuscript as an int ID.
	 */
	public int getSPC() {
		return mySPC;
	}
	/**
	 * Precondition: None.
	 * Postcondition: Returns the file path of this Manuscript as a String.
	 */
	public String getFile() {
		return myFilePath;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns a map holding the reviews and reviewers of the Manuscript.
	 */
	public Map<Integer, Review> getReviews() {
		return myReviews;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the recommendation for the Manuscript as a String.
	 */
	public String getRecommendation() {
		if(myRecommendation != -1) {
			return myScale.get(myRecommendation);
		}
		
		return "No Recommendation";
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns a List representing the scale used for recommending papers.
	 */
	public List<String> getScale() {
		return myScale;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the current acceptance status of the Manuscript as a String.
	 */
	public String getAcceptStatus() {
		if(myAcceptStatus == 0) {
			return "Rejected";
		}
		else if(myAcceptStatus == 1) {
			return "Accepted";
		} else {
			return "Pending";
		}
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the title of the Manuscript.
	 */
	public String getTitle() {
		return myTitle;
	}
}

/*
 * Author: Tyler Brent
 * Group 2 - TCSS 360A
 */
package model;
import java.util.Map;
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
	
	/**
	 * Default constructor.
	 * ID's are set to -1 by default symbolizing that no Author
	 * or no Subprogram Chair was designated.
	 */
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
	
	/**
	 * Sets myAuthor to theAuthor ID being passed.
	 * 
	 * @param theAuthor of the paper in ID form.
	 */
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
	 * Sets mySubprogramChair to theSubprogramChair ID being
	 * passed.
	 * 
	 * @param theSPC of the paper in ID form.
	 */
	public void setSPC(int theSPC) {
		mySPC = theSPC;
	}
	
	/**
	 * Sets myFilePath to theFilePath String being passed.
	 * 
	 * @param theFilePath theFilePath in String form.
	 */
	public void setFilePath(String theFilePath) {
		myFilePath = theFilePath;
	}
	
	/**
	 * Adds a review of the manuscript to a HashMap. The
	 * key is an Integer (ID), and the Value is a Review 
	 * Object.
	 * 
	 * @param theID of the person making the review.
	 * @param theReview the Review itself as an Object.
	 */
	public void setReview(int theID, Review theReview) {
		myReviews.put(theID, theReview);
		
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
	
	/**
	 * Sets the recommendation given by the SPC as an int that will be
	 * later used to index myScale for the String representation of that
	 * recommendation.
	 */
	public void setRecommendation(int theRecommendation) {
		if(theRecommendation >= 0 && theRecommendation <= 4) {
			myRecommendation = theRecommendation;
		} else {
			throw new IllegalArgumentException("Invalid number used for the Scale.");
		}
	}
	
	/**
	 * My status given as an int. Pass 0 for reject or 1
	 * for accept.
	 */
	public void setAcceptStatus(int theAcceptStatus) {
		if(theAcceptStatus == 0 || theAcceptStatus == 1) {
			myAcceptStatus = theAcceptStatus;
		} else {
			throw new IllegalArgumentException("Invalid number used for Accept Status");
		}
	}
	
	/**
	 * Set the title of the paper.
	 */
	public void setTitle(String theTitle) {
		myTitle = theTitle;
	}
	
	/**
	 * Gets the ID of the Author.
	 * 
	 * @return myAuthor ID number.
	 */
	public int getAuthorID() {
		return myAuthor;
	}
	
	/**
	 * Gets the ID of the Subprogram Chair.
	 * 
	 * @return the ID of the Subprogram Chair.
	 */
	public int getSPC() {
		return mySPC;
	}
	/**
	 * Gets the FilePath of the Manuscript.
	 * 
	 * @return a String of the FilePath.
	 */
	public String getFile() {
		return myFilePath;
	}
	
	/**
	 * Gets all the reviews made for this paper.
	 * 
	 * @return Map of all reviews.
	 */
	public Map<Integer, Review> getReviews() {
		return myReviews;
	}
	
	/**
	 * Uses the recommendation from the SPC that has been passed and stored as an
	 * int as the index for myScale and returns the corresponding String acceptance message.
	 * If no recommendation has been made it will return "No Recommendation".
	 */
	public String getRecommendation() {
		if(myRecommendation != -1) {
			return myScale.get(myRecommendation);
		}
		
		return "No Recommendation";
	}
	
	/**
	 * Returns a list of the SPC recommendation scale.
	 */
	public List<String> getScale() {
		return myScale;
	}
	
	/**
	 * Returns acceptance status of the paper in String form. 
	 * Accepted returned if status is 1.
	 * Rejected returned if status is 0.
	 * Pending returned if status is -1. (default value)
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
	 * Get the title of the paper.
	 */
	public String getTitle() {
		return myTitle;
	}
}

/*
 * Group 2 - TCSS 360A
 */

import java.util.Map;
import java.util.HashMap;

public class Manuscript {
	
	/** Author ID*/
	private int myAuthor;
	/** Subprogram Chair ID. */
	private int mySPC;
	/** File Path to the Manuscript.*/
	private String myFilePath;
	/** List of reviews. Key: ID, Value: Review Object */
	private Map<Integer, Review> myReviews = new HashMap<>();
	
	/**
	 * Default constructor.
	 * ID's are set to -1 by default symbolizing that no Author
	 * or no Subprogram Chair was designated.
	 */
	public Manuscript() {
		myAuthor = -1;
		mySPC = -1;
	}
	
	/**
	 * Sets myAuthor to theAuthor ID being passed.
	 * 
	 * @param theAuthor of the paper in ID form.
	 */
	public Manuscript(int theAuthor) {
		myAuthor = theAuthor;
	}
	
	public Manuscript(int theAuthor, String theFilePath){
		myAuthor = theAuthor;
		myFilePath = theFilePath;
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
}

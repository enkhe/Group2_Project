package model;
import java.io.Serializable;

/*
 * Group 2 - TCSS260A
 */

public class Review implements Serializable{
	
	private static final long serialVersionUID = 6477667439086991275L;
	/** Review score */
	private int myScore;
	/** File path for the review */
	private String myFilePath;
	
	/**
	 * Default constructor.
	 * myScore set to -1 to represent no score given.
	 * myFilePath set to null to represent no review given.
	 */
	public Review() {
		myScore = -1;
		myFilePath = null;
	}
	
	/**
	 * Overloaded constructor.
	 * 
	 * @param theScore given by the reviewer.
	 * @param theReview file path.
	 */
	public Review(int theScore, String theReview) {
		myScore = theScore;
		myFilePath = theReview;
	}
	
	/**
	 * Gets the score given by the reviewer.
	 * 
	 * @return the score.
	 */
	public int getScore() {
		return myScore;
	}
	
	/**
	 * Gets the file path of the review file.
	 * 
	 * @return the file path.
	 */
	public String getReviewFile() {
		return myFilePath;
	}
	
	/**
	 * Sets the review score.
	 * 
	 * @param theScore given by the reviewer.
	 */
	public void setScore(int theScore) {
		myScore = theScore;
	}
	
	/**
	 * Sets the file path to the review.
	 * 
	 * @param theFilePath of the review.
	 */
	public void setReviewFile(String theFilePath) {
		myFilePath = theFilePath;
	}
}
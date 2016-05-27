package model;
import java.io.Serializable;

/*
 * Author: Tyler Brent
 * Group 2 - TCSS260A
 */

/**
 * 
 * This class represents a Review for a Manuscript. It contains the score and filepath
 * to the review file.
 *
 */

public class Review implements Serializable{
	
	private static final long serialVersionUID = 6477667439086991275L;
	/** Review score. */
	private int myScore;
	/** File path for the review file. */
	private String myFilePath;
	
	public Review() {
		myScore = -1;
		myFilePath = null;
	}
	
	public Review(int theScore, String theReview) {
		myScore = theScore;
		myFilePath = theReview;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the review score.
	 */
	public int getScore() {
		return myScore;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns the file path to the review file.
	 */
	public String getReviewFile() {
		return myFilePath;
	}
	
	/**
	 * Precondition: Takes an int as a review score.
	 * Postcondition: Sets the review score to the one provided.
	 */
	public void setScore(int theScore) {
		myScore = theScore;
	}
	
	/**
	 * Precondition: Takes a String as a file path the the review file.
	 * Postcondition: Sets the file path of the review file to the one provided.
	 */
	public void setReviewFile(String theFilePath) {
		myFilePath = theFilePath;
	}
}
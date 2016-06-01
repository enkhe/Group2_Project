
package testModel;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import model.Author;
import model.Manuscript;
import model.RegisteredUser;
import model.Review;

import org.junit.Before;
import org.junit.Test;

public class ManuscriptTest {
	
	Author author;
	RegisteredUser user;
	Review review;
	Manuscript manuscript1;
	Manuscript manuscript2;
	Manuscript manuscript3;
	File f;
	File f2;
	Manuscript manuscriptWithOneCompleteReview;
	Manuscript manuscriptWithOneIncompleteReview;
	Manuscript manuscriptWithMultipleIncompleteReviews;
	Manuscript manuscriptWithMultipleCompleteReviews;
	Manuscript manuscriptWithMultipleMixedReviews;
	private Manuscript manuscriptWithNoReviews;


	@Before
	public void setUp() throws Exception {
		//creating the user and author to submit from
		user = new RegisteredUser("TEST_Tyler", "TEST_Brent", "TEST_tylerkb2", 1234);
		author = new Author(user);	
		
		f = new File("TEST_manuscript.txt");
		f.createNewFile();
		f2 = new File("TEST_review.txt");
		f2.createNewFile();
		
		//creating the manuscript from all constructors
		manuscript1 = new Manuscript();
		manuscript2 = new Manuscript(author.getID());
		manuscript3 = new Manuscript(author.getID(), f.getAbsolutePath(), "TEST_title");
		
		//submitting a manuscript to be reviewed
		author.submitManuscript("TEST_Conference", manuscript3);
		review = new Review(1111, manuscript3.getFile());
		
		Map<Integer, Review> oneCompleteReveiw = new HashMap<>();
		oneCompleteReveiw.put(1, review);
		
		Map<Integer, Review> oneIncompleteReveiw = new HashMap<>();
		oneCompleteReveiw.put(1, new Review(1111, null));
		
		Map<Integer, Review> multipleCompleteReveiw = new HashMap<>();
		multipleCompleteReveiw.put(1, review);
		multipleCompleteReveiw.put(2, review);
		multipleCompleteReveiw.put(3, review);
		
		Map<Integer, Review> multipleInCompleteReveiw = new HashMap<>();
		multipleInCompleteReveiw.put(1, null);
		multipleInCompleteReveiw.put(2, null);
		multipleInCompleteReveiw.put(3, null);
		
		Map<Integer, Review> multipleMixedReveiw = new HashMap<>();
		multipleMixedReveiw.put(1, review);
		multipleMixedReveiw.put(2, null);
		multipleMixedReveiw.put(3, null);
		
		manuscriptWithNoReviews = new Manuscript();
		manuscriptWithOneCompleteReview = new Manuscript(1234, oneCompleteReveiw);
		manuscriptWithOneIncompleteReview = new Manuscript(1234, oneIncompleteReveiw);
		manuscriptWithMultipleIncompleteReviews = new Manuscript(1234, multipleInCompleteReveiw);
		manuscriptWithMultipleCompleteReviews = new Manuscript(1234, multipleCompleteReveiw);
		manuscriptWithMultipleMixedReviews = new Manuscript(1234, multipleMixedReveiw);
		
		
	}
	
	@Test
	public void testSetReviewSuccess() {
		//0 is returned for a successfully added and created review.
		assertEquals(manuscript3.setReview(1111, review), 0);
	}
	
	@Test
	public void testSetReviewWithNullObject() {
		//-1 is returned if the review is null and fails to set.
		assertEquals(manuscript1.setReview(author.getID(), null), -1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetRecommendationLessThanZero() {
		//A value less than one should not be accepted and should throw an exception. 
		manuscript1.setRecommendation(-1);
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetRecommendationGreaterThanFour() {
		//A value greater than four should not be accepted and should throw an exception.
		manuscript1.setRecommendation(5);
	}
	
	@Test
	public void testSetRecommendationValueBetweenZeroAndFour() {
		//A value between 0 and 4 should be accepted and properly set and return an
		//appropriate string according to the scale in Manuscript. 3 = Recommended.
		manuscript2.setRecommendation(3);
		assertEquals(manuscript2.getRecommendation(), "Recommended");
	}
	
	@Test
	public void testSetAcceptStatusZero() {
		manuscript2.setAcceptStatus(0);
		assertEquals(manuscript2.getAcceptStatus(), "Rejected");
	}
	
	@Test
	public void testSetAcceptStatusOne() {
		manuscript2.setAcceptStatus(1);
		assertEquals(manuscript2.getAcceptStatus(), "Accepted");
	}
	
	@Test (expected = IllegalArgumentException.class)
	public void testSetAcceptStatusToNotZeroOrOne() {
		//A value other than 1 or 0 should throw an exception if you try
		//to set it.
		manuscript2.setAcceptStatus(4);
	}
	
	@Test
	public void testIsAllReviewsSubmittedOnNoReviews() {
		assertEquals(manuscript1.isAllReviewsSubmitted(), false);
	}
	
	@Test
	public void testIsAllReviewsSubmittedOnOneIncompleteReview() {
		assertEquals(manuscriptWithOneIncompleteReview.isAllReviewsSubmitted(), false);
	}
	
	@Test
	public void testIsAllReviewsSubmittedOnMultipleCompleteReviews() {
		assertEquals(manuscriptWithMultipleCompleteReviews.isAllReviewsSubmitted(), true);
	}
	
	@Test
	public void testIsAllReviewsSubmittedOnMultipleIncompleteReviews() {
		assertEquals(manuscriptWithMultipleIncompleteReviews.isAllReviewsSubmitted(), false);
	}
	
	@Test
	public void testIsAllReviewsSubmittedOnMultipleMixedReviews() {
		assertEquals(manuscriptWithMultipleMixedReviews.isAllReviewsSubmitted(), false);
	}
}

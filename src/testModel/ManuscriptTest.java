
package testModel;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.Before;
import org.junit.Test;
import model.*;

public class ManuscriptTest {
	
	Author author;
	RegisteredUser user;
	Review review;
	Manuscript manuscript1;
	Manuscript manuscript2;
	Manuscript manuscript3;
	File f;
	File f2;


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
	
	@Test (expected = IOException.class)
	public void testSetReviewWithIncorrectFilePath() {
		//An incorrect path should throw an IOException and be caught
		review.setReviewFile(review.getReviewFile() + "\\incorrectFilePath");
		manuscript3.setReview(1111, review);
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
}

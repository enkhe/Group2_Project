/**
 * Author: Tyler Brent
 * Group2 - TCSS360A
 */
package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * Test class for Manuscript.
 * Authored by: Tyler Brent
 */
public class ManuscriptTest {
	
	Author author;
	RegisteredUser user;
	Manuscript manuscript1;
	Manuscript manuscript2;
	Manuscript manuscript3;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		user = new RegisteredUser("Tyler", "Brent", "tylerkb2", 1234);
		author = new Author(user);
		manuscript1 = new Manuscript();
		manuscript2 = new Manuscript(author.getID());
		manuscript3 = new Manuscript(author.getID(), "filepath", "title");
	}

	@Test(expected=IllegalArgumentException.class)
	public void testSetRecommendation() throws Exception {
		//test the bounds of recommendation input 1-5
		manuscript1.setRecommendation(-1);
		manuscript1.setRecommendation(0);
		manuscript1.setRecommendation(1);
		manuscript1.setAcceptStatus(2);
		manuscript1.setRecommendation(5);
		manuscript1.setRecommendation(6);
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void testSetAcceptStatus() throws Exception {
		//test the bounds of accept status input 0-1
		manuscript1.setAcceptStatus(-1);
		manuscript1.setAcceptStatus(0);
		manuscript1.setAcceptStatus(1);
		manuscript1.setAcceptStatus(2);
	}
	
	@Test
	public void testGetRecommendation() {
		//if no recommendation has been made then the default value should be -1
		//which should return no recommendation.
		assertEquals(manuscript2.getRecommendation(), "No Recommendation");
		manuscript2.setRecommendation(1);
		assertEquals(manuscript2.getRecommendation(), "Not Recommended");
		manuscript2.setRecommendation(4);
		assertEquals(manuscript2.getRecommendation(), "Strongly Recommended");
	}
	
	@Test
	public void testGetAcceptStatus() {
		//if no accept/reject decision has been made then the default value should
		//be -1 which should return Pending.
		assertEquals(manuscript3.getAcceptStatus(), "Pending");
		manuscript3.setAcceptStatus(0);
		assertEquals(manuscript3.getAcceptStatus(), "Rejected");
		manuscript3.setAcceptStatus(1);
		assertEquals(manuscript3.getAcceptStatus(), "Accepted");
	}

}

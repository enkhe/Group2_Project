package testModel;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import model.*;

/**
 * @Author: Amrit Puri
 * Group 2 - TCSS 360A
 */
 
public class ReviewerTest {
	private Reviewer myRevWithNoManuscripts;
	private Reviewer myRevWithOneManuscript;
	private Reviewer myRevWithTwoManuscripts;
	private Reviewer myRevWithThreeManuscripts;
	private Reviewer myRevWithFourManuscripts;
	private Manuscript myMan;
	
	@Before
	public void setUp() {
		myRevWithOneManuscript = new Reviewer(new RegisteredUser("Test", "Test", "Test", 345));
		myRevWithTwoManuscripts = new Reviewer(new RegisteredUser("Test", "Test", "Test", 567));
		myRevWithThreeManuscripts = new Reviewer(new RegisteredUser("Test", "Test", "Test", 678));
		myRevWithFourManuscripts = new Reviewer(new RegisteredUser("Test", "Test", "Test", 789));
		myRevWithNoManuscripts = new Reviewer(new RegisteredUser("Test", "Test", "Test", 890));
		myMan = new Manuscript(myRevWithOneManuscript.getID());

		
		myRevWithOneManuscript.assignManuscript(myMan);
		
		
		myRevWithTwoManuscripts.assignManuscript(new Manuscript());
		myRevWithTwoManuscripts.assignManuscript(new Manuscript());
		
		myRevWithThreeManuscripts.assignManuscript(new Manuscript());
		myRevWithThreeManuscripts.assignManuscript(new Manuscript());
		myRevWithThreeManuscripts.assignManuscript(new Manuscript());
		
		myRevWithFourManuscripts.assignManuscript(new Manuscript());
		myRevWithFourManuscripts.assignManuscript(new Manuscript());
		myRevWithFourManuscripts.assignManuscript(new Manuscript());
		myRevWithFourManuscripts.assignManuscript(new Manuscript());
	}
	
	@Test
	public void testBrcheck_ReviewerNotManuscriptAuthorOnReviewerThatIsAuthor() {
		assertFalse(myRevWithOneManuscript.brcheck_ReviewerNotManuscriptAuthor(myMan));
		
	}
	
	@Test
	public void testBrchack_ReviewerNotManuscriptAuthorOnReviwerThatIsNotAuthor() {
		assertTrue(myRevWithNoManuscripts.brcheck_ReviewerNotManuscriptAuthor(myMan));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithNoAssignedManuscripts() {
		assertTrue(myRevWithNoManuscripts.brcheck_ReviewerNotOverAssigned());
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithOneAssignedManuscript() {
		assertTrue(myRevWithOneManuscript.brcheck_ReviewerNotOverAssigned());
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithTwoAssignedManuscripts() {
		assertTrue(myRevWithTwoManuscripts.brcheck_ReviewerNotOverAssigned());
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithTreeAssignedManuscripts() {
		assertTrue(myRevWithTwoManuscripts.brcheck_ReviewerNotOverAssigned());
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithFourAssignedManuscripts() {
		assertFalse(myRevWithFourManuscripts.brcheck_ReviewerNotOverAssigned());
	}
	
	@Test
	public void testAssignManuscriptOnReviewerWithNoManuscripts() {
		myRevWithNoManuscripts.assignManuscript(myMan);
		assertNotNull(myRevWithNoManuscripts.getMyAssignedManuscripts().get(0));
		assertTrue(myRevWithNoManuscripts.getMyAssignedManuscripts().contains(myMan));
	}

	@Test
	public void testUnassignManuscriptOnReviewerWithOneManuscript() {
		myRevWithOneManuscript.unassignManuscript(myMan);
		assertFalse(myRevWithOneManuscript.getMyAssignedManuscripts().contains(myMan));
	}
}
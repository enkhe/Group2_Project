package testView;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import model.Conference;
import model.Manuscript;
import model.RegisteredUser;
import model.Reviewer;
import model.SubProgramChair;

import org.junit.Before;
import org.junit.Test;

import view.SubProgramChairUI;

public class SubProgramChairUITest {

	private SubProgramChair mySubPC;
	private Reviewer myRevWithNoManuscripts;
	private Reviewer myRevWithOneManuscript;
	private Reviewer myRevWithTwoManuscripts;
	private Reviewer myRevWithThreeManuscripts;
	private Reviewer myRevWithFourManuscripts;
	private Conference myCon;
	private Manuscript myMan;
	private SubProgramChairUI myUI;
	private SubProgramChair myInvalidSubPC;
	
	@Before
	public void setUp() throws Exception { 
		myInvalidSubPC = new SubProgramChair(new RegisteredUser("Test", "Test", "Test", 456));
		mySubPC = new SubProgramChair(new RegisteredUser("Test", "Test", "Test", 123));
		myRevWithOneManuscript = new Reviewer(new RegisteredUser("Test", "Test", "Test", 345));
		myRevWithTwoManuscripts = new Reviewer(new RegisteredUser("Test", "Test", "Test", 567));
		myRevWithThreeManuscripts = new Reviewer(new RegisteredUser("Test", "Test", "Test", 678));
		myRevWithFourManuscripts = new Reviewer(new RegisteredUser("Test", "Test", "Test", 789));
		myRevWithNoManuscripts = new Reviewer(new RegisteredUser("Test", "Test", "Test", 890));
		myMan = new Manuscript();
		myCon = new Conference();
		myUI = new SubProgramChairUI(mySubPC, myCon);
		myCon.addSubprogramChair(new RegisteredUser("Test", "Test", "Test", 123));
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
	public void testSetSubProgramChairOnValidSubProgramChairID() {		
	    assertTrue(myUI.setSubProgramChair(mySubPC.getID()) == 1);
	}
	
	@Test
	public void testSetSubProgramChairOnInvalidSubProgramChairID() {
		assertFalse(myUI.setSubProgramChair(myInvalidSubPC.getID()) == 1);
	}
	
	@Test
	public void testBrcheck_ReviewerNotManuscriptAuthorOnReviewerThatIsAuthor() {
		assertFalse(myUI.brcheck_ReviewerNotManuscriptAuthor(myRevWithOneManuscript, myMan));
		
	}
	
	@Test
	public void testBrchack_ReviewerNotManuscriptAuthorOnReviwerThatIsNotAuthor() {
		assertTrue(myUI.brcheck_ReviewerNotManuscriptAuthor(myRevWithNoManuscripts, myMan));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithNoAssignedManuscripts() {
		assertTrue(myUI.brcheck_ReviewerNotOverAssigned(myRevWithNoManuscripts));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithOneAssignedManuscript() {
		assertTrue(myUI.brcheck_ReviewerNotOverAssigned(myRevWithOneManuscript));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithTwoAssignedManuscripts() {
		assertTrue(myUI.brcheck_ReviewerNotOverAssigned(myRevWithTwoManuscripts));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithTreeAssignedManuscripts() {
		assertTrue(myUI.brcheck_ReviewerNotOverAssigned(myRevWithThreeManuscripts));
	}
	
	@Test
	public void testBrCheck_SubprogramChairNotOverAssignedOnReviewerWithFourAssignedManuscripts() {
		assertFalse(myUI.brcheck_ReviewerNotOverAssigned(myRevWithFourManuscripts));
	}
	
	
}


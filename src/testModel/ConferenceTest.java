/*
 * TCSS360 Group2 Project 
 */
package testModel;

import static org.junit.Assert.*;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import model.*;

/**
 * A set of unit tests to help prove proper functionality of Conference.java.
 * @author Tyler
 * @author Shaun Coleman
 * @version MAY 31 2016
 */
public class ConferenceTest {
	
	Conference myConference;
	RegisteredUser userInConference;
	RegisteredUser userNotInConference;
	Author authorInConference;
	Author authorNotInConference;
	Reviewer reviewerInConference;
	Reviewer reviewerNotInConference;
	SubProgramChair subPCInConference;
	SubProgramChair subPCNotInConference;
	ProgramChair programChairInConference;
	ProgramChair programChairNotInConference;
	Calendar preDeadlineDate;
	Calendar postDeadlineDate;

	@Before
	public void setUp() throws Exception {
		myConference = new Conference();
		userInConference = new RegisteredUser("Tyler", "Brent", "tylerkb2", 1234);
		userNotInConference = new RegisteredUser("Shaun", "Coleman", "scoleman", 4566);
		authorInConference = new Author(userInConference);
		authorNotInConference = new Author(userNotInConference);		
		reviewerInConference = new Reviewer(userInConference);
		reviewerNotInConference = new Reviewer(userNotInConference);
		subPCInConference = new SubProgramChair(userInConference);
		subPCNotInConference = new SubProgramChair(userNotInConference);
		programChairInConference = new ProgramChair(userInConference);
		programChairNotInConference = new ProgramChair(userNotInConference);
		myConference.addAuthor(authorInConference);
		myConference.addReviewer(reviewerInConference);
		myConference.addSubprogramChair(reviewerInConference);
		myConference.setProgramChair(programChairInConference);
		preDeadlineDate = Calendar.getInstance();
		postDeadlineDate = Calendar.getInstance();
		postDeadlineDate.add(Calendar.DAY_OF_WEEK, 6);
		
	}
	
	@Test
	public void testGetAuthorOnAuthorInConference() {
		assertEquals(myConference.getAuthor(authorInConference.getID()), authorInConference);
	}
	
	@Test
	public void testGetAuthorOnAuthorNotInConference() {
		assertNull(myConference.getAuthor(authorNotInConference.getID()));
	}
	
	@Test
	public void testIsAuthorOnAuthorInConference() {
		assertEquals(myConference.isAuthor(authorInConference.getID()), true);
	}
	
	@Test
	public void testIsAuthorOnAuthorNotInConference() {
		assertEquals(myConference.isAuthor(authorNotInConference.getID()), false);
	}
	
	@Test
	public void testAddAuthorOnValidNewAuthor() {
		assertEquals(myConference.isAuthor(authorNotInConference.getID()), false);
		myConference.addAuthor(authorNotInConference);
		assertEquals(myConference.isAuthor(authorNotInConference.getID()), true);
	}
	
	@Test
	public void testRemoveAuthorOnAuthorInConference() {
		assertEquals(myConference.isAuthor(authorInConference.getID()), true);
		myConference.removeAuthor(authorInConference.getID());
		assertEquals(myConference.isAuthor(authorInConference.getID()), false);
	}
	
	@Test
	public void testDeadlineOnPreDeadlineDate() {
		assertEquals(myConference.deadlinePassed(preDeadlineDate), false);
	}
	
	@Test
	public void testDeadlineOnPostDeadlineDate() {
		assertEquals(myConference.deadlinePassed(postDeadlineDate), true);
	}
	
	@Test
	public void testGetReviewerOnReviewerInConference() {
		assertEquals(myConference.getReviewer(reviewerInConference.getID()), 
				     reviewerInConference);
	}
	
	@Test
	public void testGetReviewerOnReviewerNotInConference() {
		assertEquals(myConference.getReviewer(reviewerNotInConference.getID()), 
			     null);
	}
	
	@Test
	public void testIsReviewerOnReviewerInConference() {
		assertEquals(myConference.isReviewer(reviewerInConference.getID()), true);
	}
	
	@Test
	public void testIsReviewerOnReviewerNotInConference() {
		assertEquals(myConference.isReviewer(reviewerNotInConference.getID()), false);
	}
	
	@Test
	public void testAddReviewerOnValidReviewer() {
		assertEquals(myConference.isReviewer(reviewerNotInConference.getID()), false);
		myConference.addReviewer(reviewerNotInConference);
		assertEquals(myConference.isReviewer(reviewerNotInConference.getID()), true);
	}
	
	@Test
	public void testRemoveReviewerOnValidReviewer() {
		assertEquals(myConference.isReviewer(reviewerInConference.getID()), true);
		myConference.removeReviewer(reviewerInConference.getID());
		assertEquals(myConference.isReviewer(reviewerInConference.getID()), false);
	}
	
	@Test
	public void testIsProgramChairOnProgramChairInConference() {
		assertEquals(myConference.isProgramChair(programChairInConference.getID()), true);
	}
	
	@Test
	public void testIsProgramChairOnProgramChairNotInConference() {
		assertEquals(myConference.isProgramChair(programChairNotInConference.getID()), false);
	}
	
	@Test
	public void testSetProgramChairOnValidProgramChair() {
		assertEquals(myConference.isProgramChair(programChairNotInConference.getID()), false);
		myConference.setProgramChair(programChairNotInConference);
		assertEquals(myConference.isProgramChair(programChairNotInConference.getID()), true);
	}
	
	@Test
	public void testIsSubprogramChairOnSubprogramChairInConference() {
		assertEquals(myConference.isSubprogramChair(subPCInConference.getID()), true);
	}
	
	@Test
	public void testIsSubprogramChairOnSubprogramChairNotInConference() {
		assertEquals(myConference.isSubprogramChair(subPCNotInConference.getID()), false);
	}
	
	@Test
	public void testAddSubprogramChairOnValidSubprogramChair() {
		assertEquals(myConference.isSubprogramChair(subPCNotInConference.getID()), false);
		myConference.addSubprogramChair(subPCNotInConference);
		assertEquals(myConference.isSubprogramChair(subPCNotInConference.getID()), true);
	}
	
	@Test
	public void testRemoveSubprogramChairOnValidSubprogramChair() {
		assertEquals(myConference.isSubprogramChair(subPCInConference.getID()), true);
		myConference.removeSubProgramChair(subPCInConference.getID());
		assertEquals(myConference.isSubprogramChair(subPCInConference.getID()), false);
	}


}

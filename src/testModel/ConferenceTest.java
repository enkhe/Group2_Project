/**
 * 
 */
package testModel;

import static org.junit.Assert.*;
import java.util.Calendar;
import org.junit.Before;
import org.junit.Test;
import model.*;

/**
 * @author Tyler
 *
 */
public class ConferenceTest {
	
	Conference myConference;
	Author author;
	RegisteredUser user;
	Reviewer reviewer;

	@Before
	public void setUp() throws Exception {
		myConference = new Conference();
		user = new RegisteredUser("Tyler", "Brent", "tylerkb2", 1234);
		author = new Author(user);
		reviewer = new Reviewer(user);
	}

	@Test
	public void testAuthor() {
		//test that when author is added, we can retrieve the same object.
		myConference.addAuthor(author);
		assertEquals(myConference.getAuthor(author.getID()), author);
		// Author has been added and should show up as an author on Conference.
		assertEquals(myConference.isAuthor(author.getID()), true);
		//test that when we remove the author, it's actually being removed from
		//the list.
		myConference.removeAuthor(author.getID());
		assertEquals( myConference.getAuthor(author.getID()).getID(), -1);
		// 0000 is a made up ID and shouldn't show up as an Author.
		assertEquals(myConference.isAuthor(0000), false);
	}
	
	@Test
	public void testDeadlinePassed() {
		//For testing, the conference deadline is preset at 5 days past its creation.
		//To test if this is working I made a Calendar date 6 days past its creation...
		//and so on.
		Calendar test = Calendar.getInstance();
		assertEquals("Deadline has not passed.", myConference.deadlinePassed(test), false);
		//Add 6 days to check.
		test.add(Calendar.DAY_OF_YEAR, 6);
		assertEquals("Deadline has passed.", myConference.deadlinePassed(test), true);
	}
	
	@Test
	public void testReviewer() {
		//test that when reviewer is added, we can retrieve the same object.
		myConference.addReviewer(reviewer);
		assertEquals(myConference.getReviewer(reviewer.getID()), reviewer);
		//isReviewer should return true since we just added it.
		assertEquals(myConference.isReviewer(reviewer.getID()), true);
		//0000 is a made up ID and shouldn't match a reviewer.
		assertEquals(myConference.isReviewer(0000), false);
		//test that when we remove the reviewer, it's actually being removed from
		//the list.
		myConference.removeReviewer(reviewer.getID());			
		assertEquals( myConference.getReviewer(reviewer.getID()).getID(), -1);
	}
	
	@Test
	public void testPC() {
		//Test that we can check ID for Program Chair
		ProgramChair pc = new ProgramChair(user);
		myConference.setProgramChair(pc);
		assertEquals(myConference.isProgramChair(pc.getID()), true);
		//0000 is a made up ID and shouldn't match a PC.
		assertEquals(myConference.isProgramChair(0000), false);
	}
	
	@Test
	public void testSPC() {
		//Test that we can check ID for SPC
		SubProgramChair spc = new SubProgramChair(user);
		myConference.addSubprogramChair(spc);
		assertEquals(myConference.isSubprogramChair(spc.getID()), true);
		// 0000 is a made up ID and shouldn't match an SPC.
		assertEquals(myConference.isSubprogramChair(0000), false);
		myConference.removeSubProgramChair(spc.getID());
		//size of SPC list should be 0 since we removed the only one.
		assertEquals(myConference.getAllSubProgramChairs().size(), 0);
	}
	
	@Test
	public void testManuscript() {
		Manuscript m = new Manuscript();
		//Should be zero right now since none have been added
		assertEquals(myConference.getAllAuthorsManuscript(author.getID()).size(), 0);
		myConference.submitManuscript(m, new Author());
		//Should be one now that we added a manuscript
		assertEquals(myConference.getAllAuthorsManuscript(author.getID()).size(), 1);
		myConference.removeManuscript(m);
		//Should be zero again since we removed the only paper.
		assertEquals(myConference.getAllAuthorsManuscript(author.getID()).size(), 0);
		
	}

}

/*
 * Author: Tyler Brent
 * Group2 - TCSS 360A
 */

package testModel;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import model.*;

/**
 * 
 * This is a test of Author.java
 * Authored by: Tyler Brent
 *
 */

public class AuthorTest {
	RegisteredUser user;
	Author author;
	Author author2;
	Manuscript manuscript1;
	Manuscript manuscript2;

	@Before
	public void setUp() throws Exception {
		user = new RegisteredUser("Tyler", "Brent", "tylerkb2", 1234);
		author = new Author(user);
		author2 = new Author();
		manuscript1 = new Manuscript(author.getID(), "filepath", "title");
		manuscript2 = new Manuscript(author.getID(), "filepath2", "title2");
	}

	@Test
	public void testSumbitManuscript() {
		//tests should fail if a duplicate Manuscript is being added.
		//it isn't so this should pass on the first run.
		assertEquals("Success = 0", author.submitManuscript("",manuscript1), 0);
		//this test should fail since this Manuscript has already been submitted.
		assertEquals("Fail = -1", author.submitManuscript("",manuscript1), -1);
	}

	@Test
	public void testRemoveManuscript() {
		//this should fail since no manuscript has been submitted and
		//therefore this Manuscript doesn't exist on the list of Manuscripts
		//in author.
		assertEquals("Fail = -1", author.removeManuscript(manuscript1), -1);
		//this should pass now that we've submitted the manuscript and therefore
		//it exists on the list to be deleted.
		author.submitManuscript("",manuscript1);
		assertEquals("Success = 0", author.removeManuscript(manuscript1), 0);
	}
	
	@Test
	public void testReplaceManuscript() {
		//Should fail since no such manuscript1 exists in Author yet.
		assertEquals("Fail = -1", author.replaceManuscript(manuscript1, manuscript2), -1);
		//Should succeed in replacing manuscript1 with manuscript2 since manuscript1
		//has been submitted.
		author.submitManuscript("",manuscript1);
		assertEquals("Success = 0", author.replaceManuscript(manuscript1, manuscript2), 0);
	}
	
	@Test
	public void testGetManuscripts() {
		//test that myManuscripts is updating properly.
		//after adding a manuscript size should be 1.
		author.submitManuscript("",manuscript1);
		assertEquals(author.getMyManuscripts().size(), 1);
	}
	
	@Test
	public void testExistsBranches() {
		//test both branches of Exists to make sure the comparisons are
		//being done correctly. The follow test Manuscript will test ID branch.
		Manuscript test = new Manuscript(9999, "filepath", "title");
		author.submitManuscript("",manuscript1);
		assertEquals(author.submitManuscript("",test), 0);
		//the following will test the filepath branch.
		Manuscript test2 = new Manuscript(1234, "filepath2", "title");
		assertEquals(author.submitManuscript("",test2), 0);
	}
}

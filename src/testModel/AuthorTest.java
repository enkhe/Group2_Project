package testModel;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.IOException;

import model.Author;
import model.Manuscript;
import model.RegisteredUser;

import org.junit.Before;
import org.junit.Test;

public class AuthorTest {
	String conferenceName;
	RegisteredUser user;
	RegisteredUser user2;
	Author author;
	Author author2;
	Author author3;
	Manuscript manuscript1;
	Manuscript manuscript2;
	Manuscript manuscript3;
	File f;
	File f2;
	File f3;

	@Before
	public void setUp() throws Exception {
		user = new RegisteredUser("TEST_Tyler", "TEST_Brent", "TEST_tylerkb2", 1234);
		user2 = new RegisteredUser("TEST_James", "TEST_Bond", "TEST_007", 007);
		
		author = new Author(user);
		author2 = new Author(user2);
		author3 = new Author();
		
		f = new File("test.txt");
		f.createNewFile();
		f2 = new File("test2.txt");
		f2.createNewFile();
		f3 = new File("test3.txt");
		manuscript1 = new Manuscript(author.getID(), f.getAbsolutePath(), "TEST_title");
		manuscript1.setFilePath(f.getAbsolutePath());
		manuscript2 = new Manuscript(author2.getID(), f2.getAbsolutePath(), "TEST_title2");
		manuscript3 = new Manuscript(0, f3.getAbsolutePath(), "TEST_title3");
		
		conferenceName = "TEST_Conference";
	}
	
	@Test
	public void testSubmitManuscriptSuccessfully() {
		//0 is returned if a paper is successfully submitted.
		assertEquals(author.submitManuscript(conferenceName, manuscript1), 0);
	}
	
	@Test
	public void testSumbitManuscriptAlreadyExists() {
		//-1 is returned if a paper is unsuccessfully submitted.
		author.submitManuscript(conferenceName, manuscript1);
		assertEquals(author.submitManuscript(conferenceName, manuscript1), -1);
	}
	
	@Test (expected = IOException.class)
	public void testSubmitManuscriptIncorrectFilePath() {
		//-1 is returned if a paper is unsuccessfully submitted.
		assertEquals(author.submitManuscript(conferenceName, manuscript3), -1);
	}
	
	@Test
	public void testRemoveManuscriptExists() {
		//0 is returned if a paper is removed successfully.
		author2.submitManuscript(conferenceName, manuscript2);
		assertEquals(author2.removeManuscript(manuscript2), 0);
	}
	
	@Test
	public void testRemoveManuscriptDoesNotExists() {
		//-1 is returned if a paper is not removed.
		assertEquals(author3.removeManuscript(manuscript3), -1);
	}
	
	@Test
	public void testReplaceManuscriptSuccessfully() {
		//0 is returned if a paper is successfully replaced.
		author2.submitManuscript(conferenceName, manuscript1);
		assertEquals(author2.replaceManuscript(manuscript1, manuscript2), 0);
	}
	
	@Test
	public void testReplaceManuscriptDoesNotExist() {
		//-1 is returned if a paper is not replaced.
		assertEquals(author2.replaceManuscript(manuscript1, manuscript3), -1);
	}
	
	@Test
	public void testManuscriptExistsFilePathBranch() {
		author2.submitManuscript(conferenceName, manuscript1);
		manuscript1.setFilePath("incorrectFilePath");
		assertEquals(author2.removeManuscript(manuscript1), -1);
	}
}

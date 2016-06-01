package testView;

import static org.junit.Assert.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import model.*;

/**
 * TCSS360 
 * 
 * @author Enkhamgalan Baterdene
 * @version v2.0
 */
public class ReviewerUITest {
	
	@Test
	public void testBrCheck_ReviewerCanNotReviewAManuscriptThatHeOrSheAuthored() {
		// Reviwer is ssharp
		// Manuscript's author is ssharp.
		
		// Arrange
		int expected = -1;
		
		// Act
		int actual = myRevWithOneManuscript.assignManuscript(myManuscripts.get(1));
		
		// Assert
		assertTrue("A Reviewer cannot review a manuscript that he or she authored.", 
				expected==actual);
	}
	
	@Test
	public void testBrCheck_ReviewerCanOnlyAccessTheReviewsThatHeOrSheSubmits() {
		// Arrange
		int expected = 0;
		Set<Integer> myRevIDs = myRevWithOneManuscript.getMyAssignedManuscripts().get(0).getReviews().keySet();
		myRevIDs.remove(myRevWithOneManuscript.getID());
		
		// Act
		int actual = myRevIDs.size();
		
		// Assert
		assertTrue("A Reviewer can only access the reviews that he or she submits.", 
				expected==actual);
	}
	
	
	@Test
	public void testBrCheck_ReviewerCanAssignMaximumFourManuscriptsAndCantExceed() {
		// Arrange
		int expected = -1;
		
		// Act
		int actual = myRevWithFourManuscripts.assignManuscript(myManuscripts.get(3));
		
		// Assert
		assertTrue("A Reviewer can be assigned to review a maximum of 4 manuscript s to review for any conference", 
				expected==actual);
	}
	
	@Test
	public void testBrCheck_ReviewerCanAssignMaximumFourManuscripts() {
		// Arrange
		int expected = 0;
		
		// Act
		int actual = myRevWithThreeManuscript.assignManuscript(myManuscripts.get(4));
		
		// Assert
		assertTrue("A Reviewer can be assigned to review a maximum of 4 manuscript s to review for any conference", 
				expected==actual);
	}
	
	
	private void populateManuscripts(List<Manuscript> manuscripts) {
		if (manuscripts.size() > 0) manuscripts.clear();
		manuscripts.add(new Manuscript(4, 
				"Dynamic_Locomotion.doc", 
				"Dynamic Locomotion in Industrial Robots")); // tjimenez
		manuscripts.add(new Manuscript(5, 
				"c:/Robust Monte Carlo localization for mobile robots.docx", 
				"Robust Monte Carlo localization for mobile robots"));
		manuscripts.add(new Manuscript(6, 
				"c:/exploration and mapping strategy on semantic.docx", 
				"A robot exploration and mapping strategy based on a " 
				+ "+semantic hierarchy of spatial representations"));
		manuscripts.add(new Manuscript(7, 
				"C:/Coordinated path planning for multiple robots.docx", 
				"Coordinated path planning for multiple robots"));
		manuscripts.add(new Manuscript(8, 
				"c:/papers/underactuated mechanical hands.docx", 
				"Simulation and Design of Underactuated Mechanical Hands"));
		manuscripts.add(new Manuscript(9, 
				"c:/Users/Documents/robot teacher.tex", 
				"An affective mobile robot educator with a full-time job"));
		manuscripts.add(new Manuscript(10, 
				"c:/Users/Documents/Papers/wrappers for feature subset selection.pdf", 
				"Wrappers For Feature Subset Selection"));
		manuscripts.add(new Manuscript(11, 
				"Machine_Learning.docx ", 
				"Consistency-based Search in Feature Selection in Machine Learning"));
	}
	
	private void populateUserList(List<RegisteredUser> registeredUsers) {
		if (registeredUsers.size() > 0) registeredUsers.clear();
		int count = 0;
		registeredUsers.add(new RegisteredUser("Bat", 		"Enk", 			"benk", 		count++));
		registeredUsers.add(new RegisteredUser("Tyler", 	"Brent", 		"tbrent", 		count++));
		registeredUsers.add(new RegisteredUser("Shaun", 	"Coleman", 		"scoleman", 	count++));
		registeredUsers.add(new RegisteredUser("Amrit", 	"Puti", 		"aputi", 		count++));
		registeredUsers.add(new RegisteredUser("Tiffancy", 	"Jimenez", 		"tjimenez", 	count++));
		registeredUsers.add(new RegisteredUser("Stephen", 	"Sharp", 		"ssharp", 		count++));
		registeredUsers.add(new RegisteredUser("Carolyn", 	"Summers", 		"csummers", 	count++));
		registeredUsers.add(new RegisteredUser("Rodney", 	"Lindsey", 		"rlindsey", 	count++));
		registeredUsers.add(new RegisteredUser("Javier", 	"Parsons", 		"jparsons", 	count++));
		registeredUsers.add(new RegisteredUser("Janice", 	"Glover", 		"jglover", 		count++));
		registeredUsers.add(new RegisteredUser("Jan", 		"Robinson", 	"jrobinson", 	count++));
		registeredUsers.add(new RegisteredUser("Victor", 	"Simon", 		"vsimon", 		count++));
		registeredUsers.add(new RegisteredUser("Ethel", 	"Wilson", 		"ewilson", 		count++));
		registeredUsers.add(new RegisteredUser("Gilberto", 	"Weaver", 		"gweaver", 		count++));
		registeredUsers.add(new RegisteredUser("Gwendolyn", "Watts", 		"gwatts", 		count++));
		registeredUsers.add(new RegisteredUser("Jeanne", 	"Burton", 		"jburton", 		count++));
		registeredUsers.add(new RegisteredUser("Jamie", 	"Dennis", 		"jdennis", 		count++));
		registeredUsers.add(new RegisteredUser("Clay", 		"Mcguire", 		"cmcguire", 	count++));
		registeredUsers.add(new RegisteredUser("Salvador", 	"Holloway", 	"sholloway", 	count++));
		registeredUsers.add(new RegisteredUser("Jeanette", 	"Brown", 		"jbrown", 		count++));
		registeredUsers.add(new RegisteredUser("Ivan", 		"Richards", 	"irichards", 	count++));
		registeredUsers.add(new RegisteredUser("Melinda", 	"Gonzalez", 	"mgonzalez", 	count++));
		registeredUsers.add(new RegisteredUser("Tami", 		"Christensen", 	"tchristensen", count++));
		registeredUsers.add(new RegisteredUser("Lorena", 	"Norris", 		"lnorris", 		count++));
		registeredUsers.add(new RegisteredUser("Flora", 	"Fletcher", 	"ffletcher", 	count++));
		registeredUsers.add(new RegisteredUser("Nadine", 	"Ross", 		"nross", 		count++));
		registeredUsers.add(new RegisteredUser("Gilbert", 	"Maldonado", 	"gmaldona", 	count++));
		registeredUsers.add(new RegisteredUser("Pam", 		"Mccarthy", 	"pmccarthy", 	count++));
		registeredUsers.add(new RegisteredUser("Roderick", 	"Nichols", 		"rnichols", 	count++));
		registeredUsers.add(new RegisteredUser("Brett", 	"Diaz", 		"bdiaz", 		count++));
		registeredUsers.add(new RegisteredUser("Ken", 		"Briggs", 		"kbriggs", 		count++));
		registeredUsers.add(new RegisteredUser("Byron", 	"Lambert", 		"blambert", 	count++));
		registeredUsers.add(new RegisteredUser("Brandi", 	"Ruiz", 		"bruiz", 		count++));
		registeredUsers.add(new RegisteredUser("Maureen", 	"Farmer", 		"mfarmer", 		count++));
		registeredUsers.add(new RegisteredUser("Lola", 		"Smith", 		"lsmith", 		count++));
		registeredUsers.add(new RegisteredUser("Leon", 		"Lane", 		"llane", 		count++));
		registeredUsers.add(new RegisteredUser("Blake", 	"Hansen", 		"bhansen", 		count++));
	}
	
	@Before
	public void beforeAllTests() {
		myManuscripts = new LinkedList<>();
		myRegUs = new LinkedList<>();
		populateManuscripts(myManuscripts);
		populateUserList(myRegUs);
		myRevWithNoManuscript = new Reviewer(myRegUs.get(4));
		myRevWithOneManuscript = new Reviewer(myRegUs.get(5));
		myRevWithTwoManuscript = new Reviewer(myRegUs.get(6));
		myRevWithThreeManuscript = new Reviewer(myRegUs.get(7));
		myRevWithFourManuscripts = new Reviewer(myRegUs.get(2));
		
		myRevWithOneManuscript.assignManuscript(myManuscripts.get(0));
		
		myRevWithTwoManuscript.assignManuscript(myManuscripts.get(1));
		myRevWithTwoManuscript.assignManuscript(myManuscripts.get(2));
		
		myRevWithThreeManuscript.assignManuscript(myManuscripts.get(1));
		myRevWithThreeManuscript.assignManuscript(myManuscripts.get(2));
		myRevWithThreeManuscript.assignManuscript(myManuscripts.get(3));
		

		myRevWithFourManuscripts.assignManuscript(myManuscripts.get(4));
		myRevWithFourManuscripts.assignManuscript(myManuscripts.get(5));
		myRevWithFourManuscripts.assignManuscript(myManuscripts.get(6));
		myRevWithFourManuscripts.assignManuscript(myManuscripts.get(0));
	}
	
	Reviewer myRevWithNoManuscript;
	Reviewer myRevWithOneManuscript;
	Reviewer myRevWithTwoManuscript;
	Reviewer myRevWithThreeManuscript;
	Reviewer myRevWithFourManuscripts;
	
	
	List<Manuscript> myManuscripts;
	List<RegisteredUser> myRegUs;
	
}
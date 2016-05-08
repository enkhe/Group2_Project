package View;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import Model.*;

public class SetUp {

	List<RegisteredUser> myUserList;
	List<Conference> myConferences;
	List<Manuscript> myManuscripts;
	
	// Deals with serializing.
	public SetUp() {
		myManuscripts = new LinkedList<>();
		myConferences = new LinkedList<>();
		myUserList = new LinkedList<>();
		myUserList = populateUserList();
		myManuscripts = populateManuscripts();
		
	}
	
	public boolean fileExists(String strFilePath) {
		// Check to see if the /save.ser exists.
		File f = new File(strFilePath);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		return false;
	}
	
	public void serialize() {
		SystemHelper.serialize(generateManagementSystem());
	}
	
	private ManagementSystem generateManagementSystem() {
		myConferences = populateConferences();
		
		ManagementSystem managementSystem = 
				new ManagementSystem(myUserList, myConferences);
		
		
		
		managementSystem.setMyCurrentUser(myUserList.get(10)); // jrobinson
		managementSystem.setMyCurrentConference(myConferences.get(0));
		
		return managementSystem;
	}

	private List<Conference> populateConferences() {
		List<Conference> conferences = new LinkedList<>();
		
		conferences.add(getConferenceOne());
		conferences.add(getConferenceTwo());
		conferences.add(getConferenceThree());
		conferences.add(getConferenceFour());
		conferences.add(getConferenceFive());
		
		return conferences;
	}

	

	private Conference getConferenceOne() {
		Conference theConference = new Conference();
		
		ProgramChair programChair = new ProgramChair(myUserList.get(10));
		

		programChair.assignSubProgramChair(2);
		
		/// Stuck here for now. 
		
		
		List<Author> authors = new LinkedList<>();
		List<Reviewer> reviewers = new LinkedList<>();
		List<SubProgramChair> subprogramChairs = new LinkedList<>();
		
		authors = populateAuthorsForConference1();
		reviewers = populateReviewersForConference1();
		subprogramChairs = populateSubProgramChairsForConference1();
		
		
		
		theConference.setProgramChair(programChair);
		
		
		theConference.addAuthor((Author)myUserList.get(4));
		theConference.addAuthor((Author)myUserList.get(5));
		theConference.addAuthor((Author)myUserList.get(6));
		theConference.addAuthor((Author)myUserList.get(7));
		theConference.addAuthor((Author)myUserList.get(8));
		theConference.addAuthor((Author)myUserList.get(9));
		theConference.addAuthor((Author)myUserList.get(10));
		theConference.addAuthor((Author)myUserList.get(11));
		
		theConference.addSubprogramChair(myUserList.get(12));
		theConference.addSubprogramChair(myUserList.get(13));
		theConference.addSubprogramChair(myUserList.get(14));
		
		
		return theConference;
	}
	
	private List<Author> populateAuthorsForConference1 () {
		List<Author>  authors = new LinkedList<>();
		
		return authors;
	}
	private List<Reviewer> populateReviewersForConference1 () {
		List<Reviewer> reviewers = new LinkedList<>();
		
		return reviewers;
	}

	private List<SubProgramChair> populateSubProgramChairsForConference1 () {
		List<SubProgramChair> subProgramChairs = new LinkedList<>();
		
		return subProgramChairs;
	}

	private Conference getConferenceTwo() {
		Conference theConference = new Conference();
		
		
		return theConference;
	}

	private Conference getConferenceThree() {
		Conference theConference = new Conference();
		
		
		return theConference;
	}

	private Conference getConferenceFour() {
		Conference theConference = new Conference();
		
		
		return theConference;
	}

	private Conference getConferenceFive() {
		Conference theConference = new Conference();
		
		return theConference;
	}


	private List<Manuscript> populateManuscripts() {
		List<Manuscript> manuscripts = new LinkedList<>();
		manuscripts.add(new Manuscript(4, 
				"Dynamic_Locomotion.doc", 
				"Dynamic Locomotion in Industrial Robots"));
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
		return manuscripts;
	}
	

	private List<RegisteredUser> populateUserList() {
		List<RegisteredUser> registeredUsers = new LinkedList<>();
		int count = 0;
		registeredUsers.add(new RegisteredUser("Bat", "Enk", "benk", count++));
		registeredUsers.add(new RegisteredUser("Tyler", "Brent", "tbrent", count++));
		registeredUsers.add(new RegisteredUser("Shaun", "Coleman", "scoleman", count++));
		registeredUsers.add(new RegisteredUser("Amrit", "Puti", "aputi", count++));
		registeredUsers.add(new RegisteredUser("Tiffancy", "Jimenez", "tjimenez", count++));
		registeredUsers.add(new RegisteredUser("Stephen", "Sharp", "ssharp", count++));
		registeredUsers.add(new RegisteredUser("Carolyn", "Summers", "csummers", count++));
		registeredUsers.add(new RegisteredUser("Rodney", "Lindsey", "rlindsey", count++));
		registeredUsers.add(new RegisteredUser("Javier", "Parsons", "jparsons", count++));
		registeredUsers.add(new RegisteredUser("Janice", "Glover", "jglover", count++));
		registeredUsers.add(new RegisteredUser("Jan", "Robinson", "jrobinson", count++));
		registeredUsers.add(new RegisteredUser("Victor", "Simon", "vsimon", count++));
		registeredUsers.add(new RegisteredUser("Ethel", "Wilson", "ewilson", count++));
		registeredUsers.add(new RegisteredUser("Gilberto", "Weaver", "gweaver", count++));
		registeredUsers.add(new RegisteredUser("Gwendolyn", "Watts", "gwatts", count++));
		registeredUsers.add(new RegisteredUser("Jeanne", "Burton", "jburton", count++));
		registeredUsers.add(new RegisteredUser("Jamie", "Dennis", "jdennis", count++));
		registeredUsers.add(new RegisteredUser("Clay", "Mcguire", "cmcguire", count++));
		registeredUsers.add(new RegisteredUser("Salvador", "Holloway", "sholloway", count++));
		registeredUsers.add(new RegisteredUser("Jeanette", "Brown", "jbrown", count++));
		registeredUsers.add(new RegisteredUser("Ivan", "Richards", "irichards", count++));
		registeredUsers.add(new RegisteredUser("Melinda", "Gonzalez", "mgonzalez", count++));
		registeredUsers.add(new RegisteredUser("Tami", "Christensen", "tchristensen", count++));
		registeredUsers.add(new RegisteredUser("Lorena", "Norris", "lnorris", count++));
		registeredUsers.add(new RegisteredUser("Flora", "Fletcher", "ffletcher", count++));
		registeredUsers.add(new RegisteredUser("Nadine", "Ross", "nross", count++));
		registeredUsers.add(new RegisteredUser("Gilbert", "Maldonado", "gmaldona", count++));
		registeredUsers.add(new RegisteredUser("Pam", "Mccarthy", "pmccarthy", count++));
		registeredUsers.add(new RegisteredUser("Roderick", "Nichols", "rnichols", count++));
		registeredUsers.add(new RegisteredUser("Brett", "Diaz", "bdiaz", count++));
		registeredUsers.add(new RegisteredUser("Ken", "Briggs", "kbriggs", count++));
		registeredUsers.add(new RegisteredUser("Byron", "Lambert", "blambert", count++));
		registeredUsers.add(new RegisteredUser("Brandi", "Ruiz", "bruiz", count++));
		registeredUsers.add(new RegisteredUser("Maureen", "Farmer", "mfarmer", count++));
		registeredUsers.add(new RegisteredUser("Lola", "Smith", "lsmith", count++));
		registeredUsers.add(new RegisteredUser("Leon", "Lane", "llane", count++));
		registeredUsers.add(new RegisteredUser("Blake", "Hansen", "bhansen", count++));
		
		return registeredUsers;
	}
	
	
}

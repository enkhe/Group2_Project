package view;

import java.io.File;
import java.util.LinkedList;
import java.util.List;

import model.*;

public class SetUp {

	private List<RegisteredUser> myRegisteredUsers;
	private List<Conference> myConferences;
	private List<Manuscript> myManuscripts;
	private Conference myCurrentConference;
	private ProgramChair myCurrentProgramChair;
	private List<Author> myAuthors;
	private List<Reviewer> myReviewers;
	private List<SubProgramChair> mySubProgramChairs;

	// Deals with serializing.
	public SetUp() {
		myManuscripts = new LinkedList<>();
		myConferences = new LinkedList<>();
		myRegisteredUsers = new LinkedList<>();
		myCurrentConference = new Conference();
		myCurrentProgramChair = new ProgramChair();
		myAuthors = new LinkedList<>();
		myReviewers = new LinkedList<>();
		mySubProgramChairs = new LinkedList<>();
	}
	
	public void serialize() {
		ManagementSystem theManagementSystem = new ManagementSystem();
		generateManagementSystem(theManagementSystem);
		SystemHelper.serialize(theManagementSystem);
	}
	
	private void generateManagementSystem(ManagementSystem managementSystem) {
		populateConferences(myConferences);
		populateUserList(myRegisteredUsers);
		populateManuscripts(myManuscripts);
		
		managementSystem = new ManagementSystem(myConferences, myRegisteredUsers);
		
	}

	private void populateConferences(List<Conference> conferences) {
		applyMyCurrentConference1();
		conferences.add(myCurrentConference);
	}

	private void applyMyCurrentConference1() {
		myCurrentConference = new Conference();
		
		myCurrentProgramChair = new ProgramChair(myRegisteredUsers.get(10));
		
		myCurrentProgramChair.assignSubProgramChair(2);
		 
		
		myAuthors = new LinkedList<>();
		myReviewers = new LinkedList<>();
		mySubProgramChairs = new LinkedList<>();
		
		populateAuthorsForConference1(myCurrentConference, myAuthors);
		populateReviewersForConference1(myCurrentConference, myReviewers);
		populateSubProgramChairsForConference1(myCurrentConference, mySubProgramChairs);
		
		myCurrentConference.setProgramChair(myCurrentProgramChair);
		myCurrentConference.setMyManuscripts(myManuscripts);
		
	}
	
	private void populateAuthorsForConference1 (Conference theConference, List<Author> theAuthors) {
		
		theConference.addAuthor((Author)myRegisteredUsers.get(4));
		theConference.addAuthor((Author)myRegisteredUsers.get(5));
		theConference.addAuthor((Author)myRegisteredUsers.get(6));
		theConference.addAuthor((Author)myRegisteredUsers.get(7));
		theConference.addAuthor((Author)myRegisteredUsers.get(8));
		theConference.addAuthor((Author)myRegisteredUsers.get(9));
		theConference.addAuthor((Author)myRegisteredUsers.get(10));
		theConference.addAuthor((Author)myRegisteredUsers.get(11));
		
	}
	
	private void populateReviewersForConference1 (Conference theConference, List<Reviewer> theReviewers) {
		
		
	}

	private void populateSubProgramChairsForConference1 (Conference theConference, List<SubProgramChair> theSubProgramChairs) {
		theConference.addSubprogramChair(myRegisteredUsers.get(12));
		theConference.addSubprogramChair(myRegisteredUsers.get(13));
		theConference.addSubprogramChair(myRegisteredUsers.get(14));
		
	}
	

	private void populateManuscripts(List<Manuscript> manuscripts) {
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
	}
	

	private void populateUserList(List<RegisteredUser> registeredUsers) {
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
		
	}

	public boolean fileExists(String strFilePath) {
		// Check to see if the /save.ser exists.
		File f = new File(strFilePath);
		if(f.exists() && !f.isDirectory()) { 
		    return true;
		}
		return false;
	}
	
	
}

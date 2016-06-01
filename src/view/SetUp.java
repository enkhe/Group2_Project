package view;
/**
 * TCSS360 Group 2 Project
 */
import java.io.File;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import model.Conference;
import model.Manuscript;
import model.ProgramChair;
import model.RegisteredUser;
import model.Reviewer;
import model.SubProgramChair;

/**
 * @author Enkhamgalan Baterdene
 * @author Shaun Coleman
 *
 */
public class SetUp {

	List<RegisteredUser> myUserList;
	List<Conference> myConferences;
	List<Manuscript> myManuscripts;
	
	// Deals with serializing.
	public SetUp() {
		myManuscripts = new LinkedList<>();
		myUserList = new LinkedList<>();
		myConferences = new LinkedList<>();
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
	
	public ManagementSystem generateManagementSystem() {
		populateUserList(myUserList);
		populateManuscripts(myManuscripts);
		populateConferences(myConferences);
		
		ManagementSystem managementSystem = 
				new ManagementSystem(myConferences, myUserList);
		
		return managementSystem;
	}

	private void populateConferences(List<Conference> conferences) {
		Conference theConference1 = new Conference();
		Conference theConference2 = new Conference();
		
		Calendar deadline = Calendar.getInstance();
		deadline.set(2015, 4, 8);
		Conference theConference3 = new Conference(deadline);
		
		doConferenceOne(theConference1);
		doConferenceTwo(theConference2);
		doConferenceThree(theConference3);
		
		conferences.add(theConference1);
		conferences.add(theConference2);
		conferences.add(theConference3);
	}

	private void doConferenceOne(Conference theConference) {
		
		ProgramChair programChair = new ProgramChair(myUserList.get(10));
		theConference.setProgramChair(programChair);
		
		SubProgramChair spc1 = new SubProgramChair(myUserList.get(12)); // ewilson 
		SubProgramChair spc2 = new SubProgramChair(myUserList.get(13)); // gweaver
		SubProgramChair spc3 = new SubProgramChair(myUserList.get(14)); // gwatts
		programChair.assignSubProgramChair(spc1.getID());
		programChair.assignSubProgramChair(spc2.getID());
		programChair.assignSubProgramChair(spc3.getID());
		
		
		// all of the registered users are possible Reviewers except ProgramChair
		
		// ssharp, csummers, rlindsey, jparsons, jglover
		Reviewer rev1 = new Reviewer(myUserList.get(5));
		Reviewer rev2 = new Reviewer(myUserList.get(6));
		Reviewer rev3 = new Reviewer(myUserList.get(7));
		Reviewer rev4 = new Reviewer(myUserList.get(8));
		Reviewer rev5 = new Reviewer(myUserList.get(9));
		
		theConference.addReviewer(rev1);
		theConference.addReviewer(rev2);
		theConference.addReviewer(rev3);
		theConference.addReviewer(rev4);
		theConference.addReviewer(rev5);
		
		Manuscript man1, man2, man3, man4, man5, man6 = new Manuscript();
		
		theConference.setMyManuscripts(myManuscripts.subList(0, 5));
		
		
		//for(int i = 5; i < 10; i++)
		//	theConference.addReviewer(new Reviewer(myUserList.get(i)));
		man1 = myManuscripts.get(0); // Author is benk
		man2 = myManuscripts.get(1);
		man3 = myManuscripts.get(2);
		man4 = myManuscripts.get(3);
		man5 = myManuscripts.get(4);
		man6 = myManuscripts.get(5);
		
		theConference.addSubprogramChair(spc1);
		theConference.addSubprogramChair(spc2);
		theConference.addSubprogramChair(spc3);
		
		theConference.getAllSubProgramChairs().get(2).assignManuscript(man1);//tjimenez
		theConference.getAllSubProgramChairs().get(0).assignManuscript(man2);//ssharp
		theConference.getAllSubProgramChairs().get(1).assignManuscript(man3);//csummers
		theConference.getAllSubProgramChairs().get(2).assignManuscript(man4);//rlindsey
		theConference.getAllSubProgramChairs().get(0).assignManuscript(man5);//jparsons
		theConference.getAllSubProgramChairs().get(2).assignManuscript(man6);//jglover
		
		
		theConference.setConferenceName("2016 IEEE International Cyber Security Conference");
	}
	
	private void doConferenceTwo(Conference theConference) {
		//Conference theConference = new Conference();
		
		ProgramChair programChair = new ProgramChair(myUserList.get(0)); //benk
		theConference.setProgramChair(programChair);
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(0)));
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(1)));
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(2)));
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(3)));
		
		for(int i = 4; i < 9; i++)
			theConference.addReviewer(new Reviewer(myUserList.get(i)));
		
		theConference.addReviewer(new Reviewer(myUserList.get(0)));
		
		//theConference.getAllSubProgramChairs().get(0).assignManuscript(myManuscripts.get(0));
		
		
		theConference.setConferenceName("2016 IEEE NetSoft Conference");
		
	}
	
	private void doConferenceThree(Conference theConference) {
		ProgramChair programChair = new ProgramChair(myUserList.get(1)); //tbrent
		theConference.setProgramChair(programChair);
		
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(12)));
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(13)));
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(14)));
		
		
		
		for(int i = 5; i < 10; i++)
			theConference.addReviewer(new Reviewer(myUserList.get(i)));
		
		theConference.setConferenceName("2015 IEEE International Cyber Security Conference");
		
	}

	/**
	 * 
	 * 
	 * @param manuscripts 
	 */
	private void populateManuscripts(List<Manuscript> manuscripts) {
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
	
	
}

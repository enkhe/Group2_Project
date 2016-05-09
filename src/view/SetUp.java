package view;
/**
 * TCSS360 Group 2 Project
 */
import java.io.File;
import java.util.LinkedList;
import java.util.List;

import model.Conference;
import model.Manuscript;
import model.ProgramChair;
import model.RegisteredUser;
import model.Reviewer;
import model.SubProgramChair;

/**
 * 
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
		myUserList = populateUserList();
		myConferences = populateConferences();
		
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
		myConferences = populateConferences();
		
		ManagementSystem managementSystem = 
				new ManagementSystem(myConferences, myUserList);
		
		return managementSystem;
	}

	private List<Conference> populateConferences() {
		List<Conference> conferences = new LinkedList<>();
		
		conferences.add(getConferenceOne());
		conferences.add(getConferenceTwo());
		
		return conferences;
	}

	

	private Conference getConferenceOne() {
		Conference theConference = new Conference();
		
		ProgramChair programChair = new ProgramChair(myUserList.get(10));
		theConference.setProgramChair(programChair);
		
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(12)));
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(13)));
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(14)));
		
		for(int i = 5; i < 10; i++)
			theConference.addReviewer(new Reviewer(myUserList.get(i)));
		
		theConference.setConferenceName("2016 IEEE International Cyber Security Conference");
		
		return theConference;
	}
	
	private Conference getConferenceTwo() {
		Conference theConference = new Conference();
		
		ProgramChair programChair = new ProgramChair(myUserList.get(0));
		theConference.setProgramChair(programChair);
		
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(1)));
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(2)));
		theConference.addSubprogramChair(new SubProgramChair(myUserList.get(3)));
		
		for(int i = 4; i < 9; i++)
			theConference.addReviewer(new Reviewer(myUserList.get(i)));
		
		theConference.setConferenceName("2016 IEEE NetSoft Conference");
		
		return theConference;
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

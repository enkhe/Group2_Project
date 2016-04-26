package MainPackage;

import java.util.HashMap;
import java.util.LinkedList;

import UI.*;

public class Manuscripts {
	private String filePath;
	
	private String review;
	
	private LinkedList<Author> authors;
	private LinkedList<Reviewer> reviewers;
	private LinkedList<ProgramChair> programChairs;
	private LinkedList<SubProgramChair> subProgramChairs;
	private LinkedList<RegisteredUser> registeredUsers;
	private HashMap<Integer, LinkedList<User>> userRoles;
	
	public Manuscripts() {
		authors = new LinkedList<>();
		reviewers = new LinkedList<>();
		programChairs = new LinkedList<>();
		subProgramChairs = new LinkedList<>();
		registeredUsers = new LinkedList<>();
	}
	
	public void addUserRole(Integer userID, User userRole) {
		// A single registered user can be Subprogram chair on multiple conferences.
		LinkedList<User> currentUserRoles = userRoles.get(userID);
		currentUserRoles.add(userRole);
		userRoles.put(userID, currentUserRoles);
	}
	
	public LinkedList<User> getUserRoles(Integer userID) {
		return userRoles.get(userID);
	}
	
	public void addAuthor(Integer userID, String filePathManuscript) {
		Author author = new Author(userID, filePathManuscript);
		authors.add(author);
		addUserRole(userID, author);
	}
	
	public void addReviewer(Integer userID) {
		Reviewer reviewer = new Reviewer();
		reviewers.add(reviewer);
		addUserRole(userID, reviewer);
	}
	
	public void addProgramChair(Integer userID) {
		ProgramChair programChair = new ProgramChair();
		programChairs.add(programChair);
		addUserRole(userID, programChair);
	}
	
	public void addSubProgramChair(Integer userID) {
		SubProgramChair subProgramChair = new SubProgramChair();
		subProgramChairs.add(subProgramChair);
		addUserRole(userID, subProgramChair);
	}
	
	public void addRegisteredUser(String userName) {
		RegisteredUser registeredUser = new RegisteredUser(userName);
		registeredUsers.add(registeredUser);
	}
	
	public void AddManuscript(String _filePath) {
		this.setFilePath(_filePath);
	}
	
	public void RemoveManuscript() {
		this.setFilePath("");
	}
	
	public String getReview() {
		return review;
	}
	
	public void setReview(int userID, String review) {
		this.review = review;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
}

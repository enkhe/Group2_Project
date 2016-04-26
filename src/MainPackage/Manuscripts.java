package MainPackage;

import java.util.LinkedList;

import UI.*;

public class Manuscripts {
	private String filePath;
	
	private String review;
	LinkedList<Author> authors;
	LinkedList<Reviewer> reviewers;
	LinkedList<ProgramChair> programChairs;
	LinkedList<SubProgramChair> subProgramChairs;
	LinkedList<RegisteredUser> registeredUsers;
	
	public Manuscripts() {
		authors = new LinkedList<>();
		reviewers = new LinkedList<>();
		programChairs = new LinkedList<>();
		subProgramChairs = new LinkedList<>();
		registeredUsers = new LinkedList<>();
	}
	
	public void addAuthor(Integer userID, String filePathManuscript) {
		Author author = new Author(userID, filePathManuscript);
		authors.add(author);
	}
	
	public void addReviewer(Integer userID) {
		Reviewer reviewer = new Reviewer();
		reviewers.add(reviewer);
	}
	
	public void addProgramChair(Integer userID) {
		ProgramChair programChair = new ProgramChair();
		programChairs.add(programChair);
	}
	
	public void addSubProgramChair(Integer userID) {
		SubProgramChair subProgramChair = new SubProgramChair();
		subProgramChairs.add(subProgramChair);
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

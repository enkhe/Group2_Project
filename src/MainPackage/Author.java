package MainPackage;

import UI.*;

public class Author extends RegisteredUser {

	private int userID;
	private String filePathManuscript;
	
	public Author() {
		this(0, "");
	}
	
	public Author(int _userID, String _filePathManuscript) {
		setUserID(_userID);
		setFilePathManuscript(_filePathManuscript);
	}
	
	
	public void Submit() {
		
		
	}
	
	public void UnSubmit() {
		
	}
	
	public void Edit() {
		
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFilePathManuscript() {
		return filePathManuscript;
	}

	public void setFilePathManuscript(String filePathManuscript) {
		this.filePathManuscript = filePathManuscript;
	}


}

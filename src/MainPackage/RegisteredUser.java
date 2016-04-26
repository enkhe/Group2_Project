package MainPackage;

import UI.*;

public class RegisteredUser implements User {

	private String userName;
	
	public RegisteredUser() {
		this("");
	}
	
	public RegisteredUser(String _userName) {
		setUserName(_userName);
	}
	
	@Override
	public void Login() {
		
		
	}

	@Override
	public void Register() {
		
		
	}
	
	public int getID() {
		
		return 0;
	}
	
	public void viewManuscripts() {
		// LinkedList<Manuscripts> manuscripts = new LinkedList<>();
		
		
	}

	public void viewManuscript(int ID) {
		// LinkedList<Manuscripts> manuscripts = new LinkedList<>();
		
		
	}
	
	public void setUserName(String _userName) {
		this.userName = _userName;
	}
	public String getUserName() {
		return this.userName;
	}
}

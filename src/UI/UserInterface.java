package UI;


import MainPackage.*;

public class UserInterface {

	private RegisteredUser regUser;
	private Manuscripts manuscripts;
	
	private static String HEADER1 = "MSEE Conference Management System";
	private static String HEADER2 = "Conference: 2016 IEEE International Conference on Robotics and Automation";
	
	public void Register() {
		printHeader();
		wCn("");
		wC("Please enter your userName: ");
		String userName = rC();
		regUser = new RegisteredUser(userName);
		
		wC("Thank you for registering, " + userName + "!");
	}
	
	public void Login () {
		printHeader();
		wCn("");
		wC("Please enter your user name to login: ");
		String userName = rC();
		
		wC("Thank you for login in, " + userName + "!");
		
		// Let the user to select from the user Roles.
		
		
	}
	
	
	
	private void printHeader() {
		wCn(HEADER1);
		wCn(HEADER2);
	}
	
	
	/**
	 * Read console.
	 * 
	 * @return String of console input. 
	 */
	public String rC () {
		return System.console().readLine().toLowerCase();
	}
	/**
	 * Write to Console.
	 */
	public void wC(String _message) {
		System.out.print(_message);
	}
	
	/**
	 * Write to Console with newline.
	 */
	public void wCn(String _message) {
		System.out.println(_message);
	}
	
}

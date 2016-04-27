package UI;


import java.util.Scanner;

import MainPackage.*;

public class UserInterface {

	private RegisteredUser regUser;
	private Manuscripts manuscripts;
	
	private static String HEADER1 = "MSEE Conference Management System";
	private static String HEADER2 = "Conference: 2016 IEEE International Conference on Robotics and Automation";
	
	public UserInterface() {
		manuscripts = new Manuscripts();
		regUser = new RegisteredUser();
	}
	
	public void mainMenuUnRegisteredUser() {
		printHeader();
		wCn(	"\nMain Menu \n\n"+
				"Please select a number from the options to continue.\n");
		wCn("1) Login");
		wCn("2) Register");
		wCn("3) Exit\n");
		
		Integer num = rI();
		switch(num) {
			case 1:
				Login();
				break;
			case 2:
				Register();
				break;
			default:
				break;
		}
		
	}
	
	public void mainMenuAuthor (Author author) {
		printHeader();
		String authorName = author.getUserName();
		wCn("Author: "+ authorName + "\n");
		
		wCn("Please enter a command: ");
		wCn("1) Submit a Manuscript");
		wCn("2) Modify/Remove a Manuscript");
		wCn("3) View a manuscript review");
		wCn("4) Back");
		wCn("5) Exit");
		Integer num = rI();
		switch(num) {
			case 1:
				mainMenuAuthor_SubmitManuscript(author);
				break;
			case 2:
				mainMenuAuthor_UnSubmitManuscript(author);
				break;
			default:
				break;
		}
	}
	
	/**
	 * 
	 * @param author
	 */
	public void mainMenuAuthor_SubmitManuscript(Author author) {
		printHeader();
		String authorName = author.getUserName();
		wCn("Author: "+ authorName + "\n");
		wCn("Please enter the file path and name, or 0 to return.");
		wCn("Sample path: C:\\users\\author\\documents\\paper.docx\\n");
		
		String filePath = rS();
		
		wCn(filePath + " has submitted!");
	}
	
	/**
	 * 
	 * @param author
	 */
	public void mainMenuAuthor_UnSubmitManuscript(Author author) {
		printHeader();
		String authorName = author.getUserName();
		wCn("Author: "+ authorName + "\n");
		wCn("Please select the file to remove.");
		
		// Loop through the manuscripts by an author.
		
		wCn("Sample path: C:\\users\\author\\documents\\paper.docx\\n");
		
		String filePath = rS();
		
		wCn(filePath + " has submitted!");
	}
	
	public void Register() {
		printHeader();
		wC("\nPlease enter your userName: ");
		String userName = rS();
		regUser = new RegisteredUser(userName);
		
		wCn("\nThank you for registering, " + userName + "!");
		manuscripts.addRegisteredUser(userName);
		
	}
	
	public void Login () {
		printHeader();
		wC("\nPlease enter your user name to login: ");
		String userName = rS();
		
		wC("\nThank you for login in, " + userName + "!");
		
		// Let the user to select from the user Roles.
		
		
	}
	
	
	
	/**
	 * Conference header information. 
	 */
	private void printHeader() {
		clearScreen();
		wCn(HEADER1);
		wCn(HEADER2);
	}
	
	
	/**
	 * Read console String.
	 * 
	 * @return String of console input. 
	 */
	public String rS () {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		return in.nextLine();
		//return System.console().readLine().toLowerCase();
	}
	
	/**
	 * Read console Integer.
	 * 
	 * @return String of console input. 
	 */
	public Integer rI () {
		@SuppressWarnings("resource")
		Scanner in = new Scanner(System.in);
		return in.nextInt();
		//return Integer.parseInt(System.console().readLine().toLowerCase());
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
	
	/**
	 * Clears console screen.
	 */
	public static void clearScreen() {  
	    System.out.print("\033[H\033[2J");  
	    System.out.flush();  
	} 

	/**
	 * Exits the console.
	 */
	public void Exit () {
		printHeader();
		wCn("\n Exiting the application.");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.exit(0);
	}
	
}

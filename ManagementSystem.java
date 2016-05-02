import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ManagementSystem implements Serializable {
	/**
	 * Eclipse Generated UID for serialization.
	 */
	private static final long serialVersionUID = -4051485962926076869L;

	private final static String SAVE_FILE = "/save.ser";
	private final static String SYS_TITLE = "\nMSEE Conference Management System";
	
	private enum Role {
		AUTHOR, REVIEWER, PROGRAM_CHAIR, SUBPROGRAM_CHAIR
	}
	
//	private List<String> testNames;
//    private String testUser;
	private List<RegisteredUser> myUserList;
	private List<Conference> myConferences;
	private transient RegisteredUser currentUser;
	private transient Role currentRole;
	private transient Conference currentConference;
	
	public ManagementSystem() {
		myUserList = new ArrayList<>();
		myConferences = new ArrayList<>();
	}
	
	private void registerUser(Scanner theScanner) {
		String enteredName;
		RegisteredUser newUser;
		
		do {
			System.out.println(SYS_TITLE);
			System.out.println("\nPlease enter a desired username or 0 to return.\n");
			System.out.print("> ");
			enteredName = theScanner.nextLine();
			
			if(enteredName.equals("0")) {
				System.out.println("Returning to the login menu.\n");
				return;
			}
			
			newUser = getUser(enteredName);
			
			if(Objects.isNull(newUser)) {
				System.out.println("Username is available!");
				System.out.print("Enter your First Name > ");
				String firstName = theScanner.nextLine();
				System.out.print("Enter your Last Name > ");
				String lastName = theScanner.nextLine();
				int id = myUserList.size();
				myUserList.add(new RegisteredUser(enteredName, firstName, lastName, id));
				enteredName = "0";
				System.out.println("Registration complete!\n");
			} else {
				System.out.println("Username is already in use.");
			}
		} while (!enteredName.equals("0"));
		
	}
	
	private void login(Scanner theScanner) {
		String enteredName;
		boolean logInSuccessful = false;
		
		System.out.println(SYS_TITLE);
		System.out.println("\nPlease enter your username or 0 to return.");
		System.out.print("> ");
		enteredName = theScanner.nextLine();
		
		if (enteredName.equals("0")) return;
		
		currentUser = getUser(enteredName);
		
		if (Objects.nonNull(currentUser)) {
			currentConference = selectConference(theScanner);
			if(Objects.nonNull(currentConference)) {
				logInSuccessful = true;
			}
		} else {
			System.out.println("User name not found.\n");
		}
		
		if(logInSuccessful) {
			mainMenu();
		}
		
		//log out
		currentUser = null;
		currentConference = null;
		
		System.out.println("Returning to Login Menu");
	}
	
	private Conference selectConference(Scanner theScanner) {
		Conference selectedConference;
		int input = -1;
		do {
			System.out.println(SYS_TITLE);
			System.out.println("User: " + currentUser.getUserName);
			System.out.println("\nPlease select a conference below, or 0 to return.");
			
			for (int i = 0; i < myConferences.size(); i++) {
				System.out.println((i+1) + ") " 
			                       + myConferences.get(i).getConferenceName());
			}
			
			System.out.println("0) Return");
			System.out.print("\n> ");
			input = theScanner.nextInt();
			theScanner.nextLine(); // flush input
			
			if (input == 0) {
				System.out.println()
				return null;
			}
			
			try {
				selectedConference = myConferences.get(input-1);
			} catch (IndexOutOfBoundsException e) {
				System.out.println("Invalid command.");
			}
		} while (input != 0);
	}
	
	private void loginMenu() {
		int input = -1;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Welcome to the MSEE Conference Management System!");
			System.out.println("Please enter a command:");
			System.out.println("1) Login");
			System.out.println("2) Register");
			System.out.println("0) Exit");
			System.out.print("\n> ");
			input = scanner.nextInt();
			scanner.nextLine(); // flush input
					
			switch (input) {
				case 1:
					login(scanner);
					break;
				case 2:
					registerUser(scanner);
					break;
				case 0:
					return;
				default:
					System.out.println("Invalid command.\n");
			}
		} while (input != 0);
	}
	
	private void mainMenu() {
		System.out.println("\nComing Soon!\n");
	}
	
	private void programChairMenu() {
		
	}
	
	private void subProgramChairMenu() {
		
	}
	
	private void authorMenu() {
		
	}
	
	private void reviewerMenu() {
		
	}
	
	private RegisteredUser getUser(String theUserName) {
		for(RegisteredUser user : myUserList) {
			if (user.getUserName().equals(theUserName)) {
				return user;
			}
		}
		
		return null;
	}
	
	private static void serialize(ManagementSystem theData) {
		try {
			FileOutputStream outFile = new FileOutputStream(SAVE_FILE);
			ObjectOutputStream outStream = new ObjectOutputStream(outFile);
			outStream.writeObject(theData);
			outStream.close();
			outFile.close();
		} catch(IOException e) {
			System.err.println("Error: Unable to save to save.ser");
		}
	}
	
	private static ManagementSystem deserialize() {
		ManagementSystem openedMS = null;
		
		try{
			FileInputStream inFile = new FileInputStream(SAVE_FILE);
			ObjectInputStream inStream = new ObjectInputStream(inFile);
			openedMS = (ManagementSystem) inStream.readObject();
			inStream.close();
			inFile.close();
		} catch(IOException e) {
			System.err.println("Error: Unable to load from save.ser");
		} catch (ClassNotFoundException e) {
			System.err.println("Error: save.ser does not contain a ManagementSystem.");
		}
		
		return openedMS;
	}
	
	/**
	 * An initialization method to be used once to create the initial list of
	 * conferences.
	 * 
	 * @return An initialized ManagementSystem object.
	 */
	private static ManagementSystem init() {

		
		return null;
	}
	
	public static void main(String[] args) {
		ManagementSystem ms = new ManagementSystem();
		ms.loginMenu();
		System.out.println("\nThanks for using the MSEE Conference Management System!");
		System.out.println("Exiting program.");
	}
	
	// To be made into a proper JUnit Test
//	private void testSerialization() {
//		ManagementSystem ms = new ManagementSystem();
//		ms.testNames.add("user1");
//		ms.testNames.add("user2");
//		ms.testNames.add("user3");
//		ManagementSystem.serialize(ms);
//		ManagementSystem loaded = ManagementSystem.deserialize();
//		
//		for(String uid : loaded.testNames) {
//			System.out.println(uid);
//		}
//	}
}

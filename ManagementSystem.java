import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManagementSystem implements Serializable {
	/**
	 * Eclipse Generated UID for serialization.
	 */
	private static final long serialVersionUID = -4051485962926076869L;

	private final static String SAVE_FILE = "/save.ser";
	
	private List<String> testNames;
//	private List<RegisteredUser> myUserList;
//	private List<Conference> myConferences;
//	private transient RegisteredUser currentUser;
//	private transient RegisteredUser currentRole;
//	private transient Conference currentConference;
	
	public ManagementSystem() {
		testNames = new ArrayList<>();
	}
	
	private void registerUser() {
		System.out.println("Please enter the username to use: \n");
	}
	
	private void login() {
		System.out.println("Please enter your username: \n");
	}
	
	private void loginMenu() {
		int input = -1;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("Welcome to the MSEE Conference Management System!");
			System.out.println("Please enter a command:");
			System.out.println("1. Login");
			System.out.println("2. Register");
			System.out.println("0. Exit");
			System.out.print("\n> ");
			input = scanner.nextInt();
					
			switch(input) {
				case 1:
					login();
					break;
				case 2:
					registerUser();
					break;
				case 0:
					return;
				default:
					System.out.println("Invalid command.\n");
			}
		} while(input != 0);
	}
	
	private void mainMenu() {
		
	}
	
	private void programChairMenu() {
		
	}
	
	private void subProgramChairMenu() {
		
	}
	
	private void authorMenu() {
		
	}
	
	private void reviewerMenu() {
		
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
	}
	
	// To be made into a proper JUnit Test
	private void testSerialization() {
		ManagementSystem ms = new ManagementSystem();
		ms.testNames.add("user1");
		ms.testNames.add("user2");
		ms.testNames.add("user3");
		ManagementSystem.serialize(ms);
		ManagementSystem loaded = ManagementSystem.deserialize();
		
		for(String uid : loaded.testNames) {
			System.out.println(uid);
		}
	}
}

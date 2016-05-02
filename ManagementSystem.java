/*
 * TCSS360 Group 2 Project
 */
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;

/**
 * Manages the user interface for a Conference Management System,
 * 
 * @author Shaun Coleman
 * @version 1.3
 */
public class ManagementSystem implements Serializable {
    /**
     * Eclipse Generated UID for serialization.
     */
    private static final long serialVersionUID = -4051485962926076869L;

    /**
     * A constant to represent the path to save persistent serializable data
     */
    private final static String SAVE_FILE = "/save.ser";
    
    /**
     * A constant to represent the title of the system for menu output.
     */
    private final static String SYS_TITLE = "\nMSEE Conference Management System";
    
//    private List<String> testNames;
//    private String testUser;
    /**
     * A list containing all known Registered Users.
     */
    private List<RegisteredUser> myUserList;
    
    /**
     * A list containing all known Conferences.
     */
    private List<Conference> myConferences;
    
    /**
     * The currenly logged in user.  Null if no user is logged in.
     */
    private transient RegisteredUser myCurrentUser;
    
    /**
     * The currently selected conference.  Null if not logged into a conference.
     */
    private transient Conference myCurrentConference;
    
    /**
     * Creates a new instance of a management system with no users or conferences.
     */
    public ManagementSystem() {
        myUserList = new ArrayList<>();
        myConferences = new ArrayList<>();
    }
    
    /**
     * Displays a menu providing the user with the ability to login, register or exit.
     */
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

    /**
     * Displays a menu to allow the user to register by entering a Username, First Name
     * and Last Name. 
     * 
     * @param theScanner the currently open input scanner for console IO.
     */
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
    
    /**
     * Prompts the user for their Username and logs the user in if the provided name is
     * valid.
     * 
     * @param theScanner the currently open input scanner for console IO.
     */
    private void login(Scanner theScanner) {
        String enteredName;
        boolean logInSuccessful = false;
        
        System.out.println(SYS_TITLE);
        System.out.println("\nPlease enter your username or 0 to return.");
        System.out.print("> ");
        enteredName = theScanner.nextLine();
        
        if (enteredName.equals("0")) return;
        
        myCurrentUser = getUser(enteredName);
        
        if (Objects.nonNull(myCurrentUser)) {
            myCurrentConference = selectConference(theScanner);
            if(Objects.nonNull(myCurrentConference)) {
                logInSuccessful = true;
            }
        } else {
            System.out.println("User name not found.\n");
        }
        
        if(logInSuccessful) {
            mainMenu();
        }
        
        //log out
        myCurrentUser = null;
        myCurrentConference = null;
        
        System.out.println("Returning to Login Menu");
    }
    
    /**
     * Prompts the user for the conference they wish to work in.
     * 
     * @param theScanner the currently open input scanner for console IO.
     * @return the Conference to be used during the user's session.
     */
    private Conference selectConference(Scanner theScanner) {
        Conference selectedConference;
        int input = -1;
        do {
            System.out.println(SYS_TITLE);
            System.out.println("User: " + myCurrentUser.getUserName);
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
    
    /**
     * Prompts the user for which personal role they will manage for the conference
     * they have currently selected.
     */
    private void selectRole() {
        
        
    }
    
    /**
     * Prompts the user to either select a role, submit a paper to become and Author
     * of the current Conference, or return to the login menu. 
     */
    private void mainMenu() {
        // select a specific role
        // submit a paper
        // log out
        System.out.println("\nComing Soon!\n");
    }
    
    /**
     * Provides menu options for all Program Chair Actions.
     */
    private void programChairMenu() {
        //view all submitted manuscripts
        //change acceptance of a paper
        //view Subprogram Chair assignments
        //designate a Subprogram Chair
    }
    
    /**
     * Provides menu options for all Subprogram Chair Actions.
     */
    private void subProgramChairMenu() {
        //Assign a reviewer
        //Submit a recommendation
    }
    
    /**
     * Provides menu options for all Author Actions.
     */
    private void authorMenu() {
        //Submit a paper
        //Unsub a paper
        //Change a submission
    }
    
    /**
     * Provides menu options for all Reviewer Actions.
     */
    private void reviewerMenu() {
        //Submit a review
    }
    
    /**
     * Searches and returns a RegisteredUser based on the provide unique UserName.
     * 
     * @param theUserName the unique user name of the requested RegisteredUser.
     * @return the requested RegisteredUser, or null if no user name matches the
     *         provided user name.
     */
    private RegisteredUser getUser(String theUserName) {
        for(RegisteredUser user : myUserList) {
            if (user.getUserName().equals(theUserName)) {
                return user;
            }
        }
        
        return null;
    }
    
    /**
     * Writes the current state of the provided ManagementSystem to the
     * file save.ser.
     *  
     * @param theData the ManagementSystem object to be written to file.
     */
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
    
    /**
     * Reads and returns the saved ManagementSystem object from save.ser
     * 
     * @return the saved ManagementSystem object, or null if the save file 
     *         could not be used or accessed.
     */
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
    
    /**
     * Starts the Conference Management System's user interface.
     * 
     * @param args not currently used.
     */
    public static void main(String[] args) {
        //ManagementSystem ms = ManagementSystem.deserialize();
        ManagementSystem ms = new ManagementSystem();
        ms.loginMenu();
        //ManagementSystem.serialize(ms);
        System.out.println("\nThanks for using the MSEE Conference Management System!");
        System.out.println("Exiting program.");
    }
    
// To be made into a proper JUnit Test
//    private void testSerialization() {
//        ManagementSystem ms = new ManagementSystem();
//        ms.testNames.add("user1");
//        ms.testNames.add("user2");
//        ms.testNames.add("user3");
//        ManagementSystem.serialize(ms);
//        ManagementSystem loaded = ManagementSystem.deserialize();
//        
//        for(String uid : loaded.testNames) {
//            System.out.println(uid);
//        }
//    }
}

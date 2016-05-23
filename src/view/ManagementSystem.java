package view;
/*
 * TCSS360 Group 2 Project
 */
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

import model.Author;
import model.Conference;
import model.Manuscript;
import model.RegisteredUser;

/**
 * Manages the user interface for a Conference Management System,
 * 
 * @author Shaun Coleman
 * @version 1.7
 */
public class ManagementSystem implements Serializable {
    /**
     * Eclipse Generated UID for serialization.
     */
    private static final long serialVersionUID = -4051485962926076869L;
    
    /**
     * A list containing all known Registered Users.
     */
    private List<RegisteredUser> myUserList;
    
    /**
     * A list containing all known Conferences.
     */
    private List<Conference> myConferences;
    
    /**
     * The currently logged in user.  Null if no user is logged in.
     */
    private transient RegisteredUser myCurrentUser;
    
    /**
     * The currently selected conference.  Null if not logged into a conference.
     */
    private transient Conference myCurrentConference;
    
    /**
     * A boolean used to identify if a user is logged in.
     */
    private transient boolean loggedIn;
    
    /**
     * Creates a new instance of a management system with no users or conferences.
     */
    public ManagementSystem() {
        myUserList = new ArrayList<>();
        myConferences = new ArrayList<>();
        loggedIn = false;
    }
    
    /**
     * Creates a new instance of a management system populated with the passed user and 
     * conference lists.
     * 
     * @param theConferences the conferences in this system will manage.
     * @param theUsers the registered users this system will manage.
     */
    public ManagementSystem(List<Conference> theConferences, List<RegisteredUser> theUsers) {
    	myUserList = theUsers;
    	myConferences = theConferences;
    }
    
    /**
     * Displays a menu providing the user with the ability to login, register or exit.
     */
    private void loginMenu() {
        int menuChoice = -1;

        do {
            System.out.println("Welcome to the MSEE Conference Management System!");
            System.out.println("Please enter a command:");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("0) Exit");
            menuChoice = SystemHelper.promptUserInt();
                    
            switch (menuChoice) {
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
        } while (menuChoice != 0);
    }

    /**
     * Displays a menu to allow the user to register by entering a Username, First Name
     * and Last Name. 
     */
    private void registerUser() {
        String enteredName;
        RegisteredUser newUser;
        
        do {
            displayScreenHeader("", "Register");
            System.out.println("\nPlease enter a desired username or 0 to return.\n");
            enteredName = SystemHelper.promptUserString();
            
            if(enteredName.equals("0")) {
                System.out.println("Returning to the login menu.\n");
                return;
            }
            
            newUser = getUser(enteredName);
            
            if(Objects.isNull(newUser)) {
            	createNewUser(enteredName);
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
     */
    private void login() {
        String enteredName;
        
        displayScreenHeader("", "Login");
        System.out.println("\nPlease enter your username or 0 to return.");
        enteredName = SystemHelper.promptUserString();
        
        if (enteredName.equals("0")) return;
        
        myCurrentUser = getUser(enteredName);
        
        if (Objects.nonNull(myCurrentUser)) {
            myCurrentConference = selectConference();
            if (Objects.nonNull(myCurrentConference)) {
                loggedIn = true;
                mainMenu();
            }
        } else {
        	System.out.println("Invalid User Name.");
        }
        
        setAsLoggedOff();
        System.out.println("\nReturning to Login Menu");
    }
    
    /**
     * Prompts the user for the conference they wish to work in.
     * 
     * @return the Conference to be used during the user's session.
     */
    private Conference selectConference() {
        Conference selectedConference = null;
        int input = -1;
        do {
            displayScreenHeader("User", "Conference Selection");
            System.out.println("\nPlease select a conference below, or 0 to return.");
            
            displayConferenceSelections();
            input = SystemHelper.promptUserInt();
            
            if (input != 0) {
                try {
                    selectedConference = myConferences.get(input-1);
                    input = 0;
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid command.");
                }
            }
            
        } while (input != 0);
        
        return selectedConference;
    }
    
    
    /**
     * Prompts the user for which personal role they will manage for the conference
     * they have currently selected.
     */
    private void selectRole() {
        int choice = -1;
        displayScreenHeader("User","Role Selection");
        System.out.println("\nPlease enter a command below.");

        displayRoleSelections();
        
        choice = SystemHelper.promptUserInt();
        
        if(choice == 1 && myCurrentConference.isAuthor(myCurrentUser.getID())) {
            new AuthorUI(myCurrentUser, myCurrentConference).authorMenu();
        } else if (choice == 2 && myCurrentConference.isReviewer(myCurrentUser.getID())) {
        	new ReviewerUI(myCurrentUser, myCurrentConference).reviewerMenu();
        } else if (choice == 3 && myCurrentConference.isSubprogramChair(myCurrentUser.getID())) {
        	new SubProgramChairUI(myCurrentUser, myCurrentConference).subProgramChairMenu();
        } else if (choice == 4 && myCurrentConference.isProgramChair(myCurrentUser.getID())) {
            new ProgramChairUI(myCurrentUser, myCurrentConference).programChairMenu();
        } else {
            System.out.println("\nInvalid input, please select a valid role.");
        }
    }
    
    /**
     * Prompts the user to either select a role, submit a paper to become and Author
     * of the current Conference, or return to the login menu. 
     */
    private void mainMenu() {
        int choice = -1;
        do {
            displayScreenHeader("User", "Main Menu");
        
            System.out.println("\nPlease enter a command below:");
            System.out.println("1) Select a Role");
            System.out.println("2) Submit a Manuscript");
            System.out.println("0) Log out");
            
            choice = SystemHelper.promptUserInt();
            
            switch (choice) {
                case 1:
                    selectRole();
                    break;
                case 2:
                    submitManuscript(getAuthorforSubmit());
                    break;
                case 0:
                    //empty, logout happens upon returning from this method.
                    break;
                default:
                    break;
            }
        } while (choice !=0);
    }
    
    /**
     * Set the current user as logged off.
     */
    private void setAsLoggedOff() {
        myCurrentUser = null;
        myCurrentConference = null;
        loggedIn = false;
    }
    
    /**
     * Submits a Manuscript from the currently selected user to the currently selected
     * conference.
     * 
     * @param theAuthor the Author object for the current user.
     */
    private void submitManuscript(Author theAuthor) {
        if(myCurrentConference.deadlinePassed(Calendar.getInstance())) {
        	System.out.println("Cannot submit manuscript past deadline.");
        	return;
        }
        
    	String title;
        String manuscriptPath;
        
        displayScreenHeader("Author", "Submit Manuscript");
        
        System.out.println("\nPlease enter the file path for your Manuscript or type 0 to go back.");
        System.out.println("Sample path: C:\\users\\author\\documents\\paper.docx\n");
        manuscriptPath = SystemHelper.promptUserString();

		if (manuscriptPath.equals("0")) {
			return;
		}
		
        System.out.println("Please enter the title of your Manuscript.");
       
        title = SystemHelper.promptUserString();
        
        Manuscript newManuscript = new Manuscript(theAuthor.getID(), manuscriptPath, title);
        
        //Break out or move to model
        if (theAuthor.submitManuscript(newManuscript) != -1) {
        	myCurrentConference.submitManuscript(newManuscript);
        }
        System.out.println("\n" + manuscriptPath + " submitted!");
    }
    
    /**
     * Prompts the user for their first and last name to finalize new user creation. 
     */
    private void createNewUser(String theUserName) {
        System.out.println("Username is available!");
        System.out.print("Enter your First Name: ");
        String firstName = SystemHelper.promptUserString();
        if(firstName.equals("0")) return;
        
        System.out.print("Enter your Last Name: ");
        String lastName = SystemHelper.promptUserString();
        if(lastName.equals("0")) return;
        
        int id = myUserList.size();
        myUserList.add(new RegisteredUser(firstName, lastName, theUserName, id));
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
     * Displays the header for the current menu screen.
     * 
     * @param role the role of the current user.
     * @param menuTitle the title of the current menu screen.
     */
    private void displayScreenHeader(String role, String menuTitle) {
        System.out.println(SystemHelper.SYS_TITLE);
        if(loggedIn) {
            System.out.println(myCurrentConference.getConferenceName());
            System.out.println(role + ": " + myCurrentUser.getUserName());
        }
        System.out.println(menuTitle);
    }
    
    /**
     * Displays the possible conferences and their submission deadlines.
     */
    private void displayConferenceSelections() {
        System.out.printf("\n%s  %-50s %s\n", "#", "Conference Name", "Submission Deadline");
        SystemHelper.displayDashedLine();
    	for (int i = 0; i < myConferences.size(); i++) {
            String conferenceName = myConferences.get(i).getConferenceName();
        	String deadlineString = myConferences.get(i).getDeadlineString();
        	
        	System.out.printf(SystemHelper.CONFERENCE_MENU_FORMAT, i+1, conferenceName, 
        			                                               deadlineString);
        }
        System.out.println("\n\n0) Return");
    }
    
    /**
     * Displays the role selection menu based on the available roles of the currently
     * logged in user.
     */
    private void displayRoleSelections() {
        boolean isAuthor = myCurrentConference.isAuthor(myCurrentUser.getID());
        boolean isReviewer = myCurrentConference.isReviewer(myCurrentUser.getID());
        boolean isSub = myCurrentConference.isSubprogramChair(myCurrentUser.getID());
        boolean isPC = myCurrentConference.isProgramChair(myCurrentUser.getID());
        
        
        if(isAuthor) System.out.println("1) Author ");
        if(isReviewer) System.out.println("2) Reviewer ");
        if(isSub) System.out.println("3) Subprogram Chair ");
        if(isPC) System.out.println("4) Program Chair ");

        if(!isAuthor && !isReviewer && !isSub && !isPC) {
        	System.out.println("No roles to select. Enter any number to return.");
        }
    }
    
    /**
     * Starts the Conference Management System's user interface.
     * 
     * @param args not currently used.
     */
    public static void main(String[] args) {
    	ManagementSystem ms = SystemHelper.deserialize();
    	//ManagementSystem ms = new SetUp().generateManagementSystem();
        ms.loginMenu();
        SystemHelper.serialize(ms);
        
        System.out.println("\nThanks for using the MSEE Conference Management System!");
        System.out.println("Exiting program.");
    }
    
    /**
    * (Business Rule - Become an Author by submitting a manuscript).
    * Returns the Author object used to submit a manuscript by the current user, or if
    * the user is not an author for the current conference yet a new Author object 
    * will be created, added to the conference, then returned.
    * 
    * @return the Author object to be used to submit a Manuscript.
    */
    private Author getAuthorforSubmit() {
        Author author;
        if(myCurrentConference.isAuthor(myCurrentUser.getID())) {
           author = myCurrentConference.getAuthor(myCurrentUser.getID());
        } else {
           author = new Author(myCurrentUser);
           myCurrentConference.addAuthor(author);
        }
           
        return author;
    }
    
    // Need to check if these are needed.
    
    /**
     * For Testing purposes the registered users are exposed.
     * @author Enkh
     * @return List<RegisteredUser>()
     */
    public List<RegisteredUser> getMyUserList() {
    	return myUserList;
    }
    
    /**
     * For Testing purposes the Conferences are exposed.
     * @author Enkh
     * @return List<Conference>()
     */
    public List<Conference> myMyConferences() {
    	return myConferences;
    }

}

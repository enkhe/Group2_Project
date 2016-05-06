/*
 * TCSS360 Group 2 Project
 */
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

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
     * Displays a menu providing the user with the ability to login, register or exit.
     */
    private void loginMenu() {
        int input = -1;

        do {
            System.out.println("Welcome to the MSEE Conference Management System!");
            System.out.println("Please enter a command:");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("0) Exit");
            input = SystemHelper.promptUserInt();
                    
            switch (input) {
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
        } while (input != 0);
    }

    // Note: Not a User Story; may want to remove after initalizng some users.
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
            	createNewUser();
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
        }
        
        setAsLoggedOff();
        System.out.println("Returning to Login Menu");
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
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("Invalid command.");
                }
            }
            
        } while (input != 0);
        
        return selectedConference;
    }
    
    //Very rough here, looking to make dynamic later if there is extra time.
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
        
        if(choice == 1 && isAuthor) {
            System.err.println("Comming Soon!");
        } else if (choice == 2 && isReviewer) {
        	System.err.println("Comming Soon!");
        } else if (choice == 3 && isSub) {
        	new SubProgramChairUI(myCurrentUser, myCurrentConference).subProgramChairMenu();
        } else if (choice == 4 && isPC) {
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
     * 
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
        String title;
        String manuscriptPath;
        
        displayScreenHeader("Author", "Submit Manuscript");
        
        System.out.println("\nPlease enter the file path for your Manuscript");
        manuscriptPath = SystemHelper.promptUserString();
        
        System.out.println("Please enter the title of your Manuscript.");
        title = SystemHelper.promptUserString();
        
        theAuthor.submitManuscript(manuscriptPath, title);
        System.out.println("\n" + manuscriptPath + " submitted!");
    }
    
    /**
     * 
     */
    private void createNewUser() {
        System.out.println("Username is available!");
        System.out.print("Enter your First Name: ");
        String firstName = SystemHelper.promptUserString();
        System.out.print("Enter your Last Name: ");
        String lastName = SystemHelper.promptUserString();
        int id = myUserList.size();
        myUserList.add(new RegisteredUser(enteredName, firstName, lastName, id));
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
    
    private void displayScreenHeader(String role, String menuTitle) {
        System.out.println(SystemHelper.SYS_TITLE);
        if(loggedIn) {
            System.out.println(myCurrentConference.getConferenceName());
            System.out.println(role + ": " + myCurrentUser.getUserName());
        }
        System.out.println(menuTitle);
    }
    
    private void displayConferenceSelections() {
        for (int i = 0; i < myConferences.size(); i++) {
            System.out.println((i+1) + ") " 
                               + myConferences.get(i).getConferenceName());
        }
        System.out.println("0) Return");
    }
    
    private void displayRoleSelections() {
        boolean isAuthor = myCurrentConference.isAuthor(myCurrentUser.getId);
        boolean isReviewer = myCurrentConference.isReviewer(myCurrentUser.getId);
        boolean isSub = myCurrentConference.isSubprogramChair(myCurrentUser.getId);
        boolean isPC = myCurrentConference.isProgramChair(myCurrentUser.getId);
        
        System.out.print("1) Author ");
        if(!isAuthor) System.out.println("(Unavailable)");
        System.out.print("2) Reviewer ");
        if(!isReviewer) System.out.println("(Unavailable)");
        System.out.print("3) Subprogram Chair ");
        if(!isSub) System.out.println("(Unavailable)");
        System.out.print("4) Program Chair ");
        if(!isPC) System.out.println("(Unavailable)");
    }
    
    /**
     * Starts the Conference Management System's user interface.
     * 
     * @param args not currently used.
     */
    public static void main(String[] args) {
        ManagementSystem ms = SystemHelper.deserialize();
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
        if(myCurrentConference.isAuthor(myCurrentUser.getUserName())) {
           author = myCurrentConference.getAuthor(myCurrentUser.getId());
        } else {
           author = new Author(myCurrentUser);
           myCurrentConference.addAuthor(author);
        }
           
        return author;
    }
}

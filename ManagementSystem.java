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
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

/**
 * Manages the user interface for a Conference Management System,
 * 
 * @author Shaun Coleman
 * @version 1.5
 */
public class ManagementSystem implements Serializable {
    /**
     * Eclipse Generated UID for serialization.
     */
    private static final long serialVersionUID = -4051485962926076869L;

    /**
     * A constant to represent the prompt display for the UI.
     */
    private final static String PROMPT = "\n > ";
    
    /**
     * A constant to represent the path to save persistent serializable data
     */
    private final static String SAVE_FILE = "/save.ser";
    
    /**
     * A constant to represent the title of the system for menu output.
     */
    private final static String SYS_TITLE = "\nMSEE Conference Management System";
    
    /**
     * A constant used to store the format string for detailed manuscript display.
     */
    private final static String PC_MAN_DISPLAY_FORMAT = "\n%20s %15s %10s %s";
    
    /**
     * A constant int used to represent the maximum manuscripts a subprogram chair can be
     * assigned.
     */
    private final int MAX_SUBPC_ASSIGNED_MANUSCRIPTS = 4;
    
    /**
     * A constant int used to represent the maximum manuscripts a reviewer can be assigned.
     */
    private final int MAX_REVIEWER_ASSIGNED_MANUSCRIPTS = 4;
    
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
     * A boolean used to identify if a user is logged in.
     */
    private transient boolean loggedIn;
    
    /**
     * The scanner being used for console i/o.
     */
    private transient Scanner myScanner;
    
    /**
     * Creates a new instance of a management system with no users or conferences.
     */
    public ManagementSystem() {
        myUserList = new ArrayList<>();
        myConferences = new ArrayList<>();
        loggedIn = false;
        myScanner = null;
    }
    
    /**
     * Displays a menu providing the user with the ability to login, register or exit.
     */
    private void loginMenu() {
        int input = -1;
        myScanner = new Scanner(System.in);
        do {
            System.out.println("Welcome to the MSEE Conference Management System!");
            System.out.println("Please enter a command:");
            System.out.println("1) Login");
            System.out.println("2) Register");
            System.out.println("0) Exit");
            input = promptUserInt();
                    
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
            
            if(enteredName.equals("0")) {
                System.out.println("Returning to the login menu.\n");
                return;
            }
            
            newUser = getUser(enteredName);
            
            if(Objects.isNull(newUser)) {
                System.out.println("Username is available!");
                System.out.print("Enter your First Name > ");
                String firstName = myScanner.nextLine();
                System.out.print("Enter your Last Name > ");
                String lastName = myScanner.nextLine();
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
     */
    private void login() {
        String enteredName;
        
        displayScreenHeader("", "Login");
        System.out.println("\nPlease enter your username or 0 to return.");
        enteredName = promptUserString();
        
        if (enteredName.equals("0")) return;
        
        myCurrentUser = getUser(enteredName);
        
        if (Objects.nonNull(myCurrentUser)) {
        	myCurrentConference = selectConference();
        	if (Objects.nonNull(myCurrentConference)) {
            	loggedIn = true;
        		mainMenu();
            }
        }
        
        /*log out; really small and only used once, so I left this in line instead of
          as a separate method */
        myCurrentUser = null;
        myCurrentConference = null;
        loggedIn = false;
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
            System.out.println(SYS_TITLE);
            System.out.println("User: " + myCurrentUser.getUserName);
            System.out.println("\nPlease select a conference below, or 0 to return.");
            
            for (int i = 0; i < myConferences.size(); i++) {
                System.out.println((i+1) + ") " 
                                   + myConferences.get(i).getConferenceName());
            }
            System.out.println("0) Return");
            
            input = promptUserInt();
            
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
    
    //Very rough here, looking to improve later if there is extra time.
    /**
     * Prompts the user for which personal role they will manage for the conference
     * they have currently selected.
     */
    private void selectRole() {
    	
    	boolean isAuthor = myCurrentConference.isAuthor(myCurrentUser.getId);
    	boolean isReviewer = myCurrentConference.isReviewer(myCurrentUser.getId);
    	boolean isSub = myCurrentConference.isSubprogramChair(myCurrentUser.getId);
    	boolean isPC = myCurrentConference.isProgramChair(myCurrentUser.getId);
    	int choice = -1;
    	
    	displayScreenHeader("User","Role Selection");
        System.out.println("\nPlease enter a command below.");
        
        System.out.print("1) Author ");
        if(!isAuthor) System.out.println("(Unavailable)");
        System.out.print("2) Reviewer ");
        if(!isReviewer) System.out.println("(Unavailable)");
        System.out.print("3) Subprogram Chair ");
        if(!isSub) System.out.println("(Unavailable)");
        System.out.print("4) Program Chair ");
        if(!isPC) System.out.println("(Unavailable)");
        
        choice = promptUserInt();
        
        if(choice == 1 && isAuthor) {
        	authorMenu();
        } else if (choice == 2 && isReviewer) {
        	reviewerMenu();
        } else if (choice == 3 && isSub) {
        	subProgramChairMenu();
        } else if (choice == 4 && isPC) {
        	programChairMenu();
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
		manuscriptPath = promptUserString();
		
		System.out.println("Please enter the title of your Manuscript.");
		title = promptUserString();
		
		theAuthor.submitManuscript(manuscriptPath, title);
		System.out.println("\n" + manuscriptPath + " submitted!");
    }
    
    /**
     * Provides menu options for all Program Chair Actions.
     */
    private void programChairMenu() {
    	int choice = -1;
    	
    	do {
    		displayScreenHeader("Program Chair:", "Program Chair Menu");
		
	        System.out.println("\nPlease enter a command below.");
	        System.out.println("1) Veiw all Manuscript status.");
	        System.out.println("2) Accept or Reject a Manuscript.");
	        System.out.println("4) Assign a Manuscript to a Subprogram Chair.");
	        System.out.println("0) Return to main menu.");
	        
	        choice = promptUserInt();
	        
	        switch (choice) {
	        	case 1:
	        		displayManuscriptsForProgramChair();
	        		break;
	        	case 2:
	        		changeManuscriptAcceptance();
	        	case 4:
	        		assignSubProgramChair();
	        		break;
	        	case 0:
	        		// empty; leaving method.
	        		break;
	        	default:
	        		break;
	        }
        
    	} while (choice != 0); 
    }
    
    /**
     * Displays a detailed list of manuscripts including title, assigned subprogram chair,
     * recommendation, and acceptance.
     */
    private void displayManuscriptsForProgramChair() {
		// Note: need this method
		List<Manuscript> manuscripts = myCurrentConference.getManuscripts();
		
		//Headers
		System.out.printf(PC_MAN_DISPLAY_FORMAT, "Title", 
				          "Subprogram Chair", "Recommendation", "Accepted");
		for(int i = 0; i < 10; i++) {
			System.out.print("------");
		}
		
		for (Manuscript manuscript : manuscripts) {
			String title = manuscript.getTitle();
			String subPCName = "-----------";
			// Possible code; using unimplemented methods from manuscript
			if (manuscript.hasSubPC()) {
				subPCName = manuscript.getSPC().getLastName();
			}
			String recommendation = manuscript.getRecommendation();
			String acceptance = manuscript.getAcceptance();
			
			System.out.printf(PC_MAN_DISPLAY_FORMAT, title, subPCName, recommendation, acceptance);
		}
    }
    
    /**
     * Displays a menu to accept or reject a manuscript.
     */
    private void changeManuscriptAcceptance() {
    	int choice = -1;
    	Manuscript selectedManuscript = programChairSelectManuscript();
    	
    	displayScreenHeader("Program Chair", "Manuscript Acceptance");
    	if (Objects.nonNull(selectedManuscript)) {
    		System.out.println("\nAccept or Reject " + selectedManuscript.getTitle());
    		System.out.println("1) Accept");
    		System.out.println("2) Reject");
    		choice = promptUserInt();
    		
    		// Note need these methods
    		if (choice == 1) {
    			selectedManuscript.setAcceptance("Accepted");
    			System.out.println("\n" + selectedManuscript.getTitle() + " accepted!");
    		} else if (choice == 2) {
    			selectedManuscript.setAcceptance("Rejected");
    			System.out.println("\n" + selectedManuscript.getTitle() + " rejected!");
    		} else { 
    			System.out.println("Invalid input; returning to Program Chair menu.");
    		}
    	}
    }
    
    /**
     * Designates a Subprogram Chair to a selected Manuscript.
     */
    private void assignSubProgramChair() {
    	Manuscript selectedManuscript = programChairSelectManuscript();
    	SubprogramChair selectedSubPC;
    	
    	if(Objects.nonNull(selectedManuscript)) {
    		selectedSubPC = selectSubPCToAssign();
    	}
    	
    	if(Objects.nonNull(selectedSubPC)) {
    		finalizeSubPCAssignment(selectedManuscript, selectedSubPC);
    	}
    }
    
    /**
     * Displays a menu to select a manuscript to be assigned a Subprogram Chair.
     *
     * @return the selected manuscript.
     */
    private Manuscript programChairSelectManuscript() {
    	List<Manuscript> manuscripts = myCurrentConference.getManuscripts();
    	Manuscript selectedManuscript = null;
    	int choice = -1;
    	
    	displayScreenHeader("Program Chair", "Manuscript Selection");
        
        System.out.println("\nPlease choose the manuscript from the options below.");
        displayPCManuscriptOptionList();
        choice = promptUserInt();
        
        try {
			selectedManuscript = manuscripts.get(choice - 1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid input");
		}
        
        return selectedManuscript;
    }
    
    /**
     * Displays a menu to select a Subprogram Chair to assign to a Manuscript.
     * 
     * @return the selected SubprogramChair.
     */
    private SubprogramChair selectSubPCToAssign() {
    	int choice = -1;
        int option = 1;
    	List<SubprogramChair> subprogramChairs = myCurrentConference.getAllSubProgramChairs();
    	SubprogramChair selectedSubPC = null;
    	
    	displayScreenHeader("Program Chair", "Subprogram Chair Selection");
    	
        System.out.println("\nPlease choose the SubprogramvChair to designate to this paper.");
        for (SubprogramChair sub : subprogramChairs) {
        	System.out.println(option++ + ") " + sub.getLastName());
        }
        
        choice = promptUserInt();
        
        try {
			selectedSubPC = subprogramChairs.get(choice - 1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid input");
		}
        
        return selectedSubPC;
    }
    
    /**
     * Displays a simple numbered list of Manuscript titles for a Program Chair menu.
     */
    private void displayPCManuscriptOptionList() {
    	List<Manuscript> manuscripts = myCurrentConference.getManuscripts();
    	int option = 1;
    	for(Manuscript manuscript : manuscripts) {
    		System.out.println(option++ + ") " + manuscript.getTitle);
    	}
    }
    
    /**
     * Provides menu options for all Subprogram Chair Actions.
     */
    private void subProgramChairMenu() {
        SubprogramChair currentSubPC = currentConference.getSubProgramChair(
        		                                            currentUser.getId());
    	int choice = -1;
    	
    	do {
	    	displayScreenHeader("Subprogram Chair", "Subprogram Chair Menu");
		
	        System.out.println("\nPlease enter a command below.");
	        System.out.println("1) Assign a Reviewer to a Manuscript.");
	        System.out.println("2) Submit a Recommendation.");
	        System.out.println("0) Return to main menu.");
	        
	        choice = promptUserInt();
	        
	        switch (choice) {
	        	case 1:
	        		assignReviewer(currentSubPC);
	        		break;
	        	case 2:
	        		//Submit a recommendation
	        		break;
	        	case 0:
	        		//Empty; exiting menu.
	        		break;
	        	default:
	        		break;
	        }
        
    	} while (choice != 0); 
    	
        
    }
    
    /**
     * Assigns a Reviewer to a manuscript based on the the results of 
     * selecting a manuscript menu, and selecting a reviewer menu.
     * 
     * @param theSPC the subprogram chair making the assignment.
     */
    private void assignReviewer(SubprogramChair theSPC) {
    	Manuscript selectedManuscript = subprogramChairSelectManuscript();
    	Reviewer selectedReviewer;
    	
    	if(Objects.nonNull(selectedManuscript)) {
    		selectedReviewer = selectReviewerToAssign();
    	}
    	
    	if(Objects.nonNull(selectedReviewer)) {
    		/* if conditions need to be separated into brcheck_ methods and a similar
    		   finalize method that could be moved outside this class */
    		if(selectedReviewer.getMyAssignedManuscripts().size() < 4
    		   && selectedReviewer.getId != selectedManuscript.getAuthor().getId()) {
    			theSPC.assignReviewer(selectedReviewer.getId());
    			selectedManuscript.setReview(selectedReviewer.getId(), null);
    		}
    	}
        
    }
    
    /**
     * 
     * @param theSPC
     * @return
     */
    private Manuscript subprogramChairSelectManuscript(SubprogramChair theSPC) {
    	int choice = -1;
    	Manuscript selectedManuscript = null;
    	
    	displayScreenHeader("Subprogram Chair", "Manuscript Selection");
        
        System.out.println("\nPlease select a manuscript below to assign a reviewer.");
        displaySubPCManuscriptOptionList(theSPC);
       
        choice = promptUserInt();
        
        try {
        	selectedManuscript = theSPC.getManuscripts().get(choice -1);
        } catch (IndexOutOfBoundsException e) {
        	System.out.println("Invalid Manuscript option");
        }
        
        return selectedManuscript;
    }
    
    /**
     * Displays a simple numbered list of Manuscript titles for a Subprogram Chair menu.
     */
    private void displaySubPCManuscriptOptionList(SubprogramChair theSPC) {
    	List<Manuscript> manuscripts = theSPC.getManuscripts();
    	int option = 1;
    	for(Manuscript manuscript : manuscripts) {
    		System.out.println(option++ + ") " + manuscript.getTitle);
    	}
    }
    
    /**
     * Displays a menu to select a Subprogram Chair to assign to a Manuscript.
     * 
     * @return the selected SubprogramChair.
     */
    private Reviewer selectReviewerToAssign() {
    	int choice = -1;
        int option = 1;
    	List<Review> reviewers = myCurrentConference.getAllReviewers();
    	Reviewer selectedReviewer = null;
    	
    	displayScreenHeader("Subprogram Chair", "Reviewer Selection");
    	
        System.out.println("\nPlease choose the Reviewer to assign to this paper.");
        for (Reviewer reviewer : reveiwers) {
        	System.out.println(option++ + ") " + reviewer.getLastName());
        }
        
        choice = promptUserInt();
        
        try {
			selectedReviewer = reviewers.get(choice - 1);
		} catch (IndexOutOfBoundsException e) {
			System.out.println("Invalid input");
		}
        
        return selectedReviewer;
    }
    
    // Incomplete
    private void submitRecommendation(SubprogramChair theSPC) {
    	
    }
    
    private int displayRecommendationOptionList(theSPC) {

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
     * An initialization method to be used once to create the initial list of
     * conferences.
     * 
     * @return An initialized ManagementSystem object.
     */
    private static ManagementSystem init() {

        
        return null;
    }
    
    /**
     * 
     * @return
     */
    private int promptUserInt() {
        System.out.print(PROMPT);
        int choice = myScanner.nextInt();
        // flushes the rest of the line/
        myScanner.nextLine();
        
        return choice;
    }
    
    /**
     * 
     * @return
     */
    private String promptUserString() {
    	System.out.print(PROMPT);
        return myScanner.nextLine();
    }
    
    private void displayScreenHeader(String role, String menuTitle) {
        System.out.println(SYS_TITLE);
        if(loggedIn) {
	        System.out.println(myCurrentConference.getConferenceName());
	        System.out.println(Role + ": " + myCurrentUser.getUserName());
        }
        System.out.println(menuTitle);
    }
    
	/**
     * Starts the Conference Management System's user interface.
     * 
     * @param args not currently used.
     */
    public static void main(String[] args) {
        ManagementSystem ms = ManagementSystem.deserialize();
        ms.loginMenu();
        ManagementSystem.serialize(ms);
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

   	private void finalizeSubPCAssignment(Manuscript theManuscript, SubprogramChair theSPC) {
	    if(!brCheck_SubprogamNotAuthor(theManuscript, theSPC) {
	        System.out.println("Subprogram chair cannot be assigned to a Manuscript they authored.");
	     } else if (!brCheck_SubprogramChairNotOverAssigned(theSPC)) {
	     	System.out.println("Subprogram chair cannont be assigned more than for Manuscripts.");
	     } else {
	     	//Need to add this method.
	     	theManuscript.setSPC(theSPC);
	     	theSPC.assignManuscript(theManuscript);
	     }
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
   	 * Business Rule check to insure the Subprogram Chair is not assigned a manuscript they authored
   	 * @param theManuscript the Manuscript in question.
   	 * @param theSPC the SubprogramChair in question.
   	 * 
   	 * @return true if the check is passed, false if the business rule would be broken.
   	 */
   	private boolean brCheck_SubprogamNotAuthor(Manuscript theManuscript, SubprogramChair theSPC) {
   		return theSPC.getId() != theManuscript.getAuthor().getId;	
   	}

   	/**
   	 * Business Rule check to insure the Subprogram Chair is at the maximum assigned manuscripts.
   	 * @param theSPC the SubprogramChair in question.
   	 * 
   	 * @return true if the check is passed, false if the business rule would be broken.
   	 */
   	private boolean brCheck_SubprogramChairNotOverAssigned(SubprogramChair theSPC) {
   		return theSPC.getMyAssignedManuscripts().size() < MAX_SUBPC_ASSIGNED_MANUSCRIPTS;
   	}

    

}

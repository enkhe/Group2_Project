package view;
/*
 * TCSS360 Group 2 Project
 */

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

import model.Author;
import model.ProgramChair;
import model.RegisteredUser;

/**
 * A class containing static helper methods and constants for ManagementSystem.
 * @author Shaun Coleman
 * @version 1.0
 *
 */
public class SystemHelper {
	 /**
     * A constant to represent the prompt display for the UI.
     */
    public final static String PROMPT = "\n > ";
    
    /**
     * A constant to represent the path to save persistent serializable data
     */
    public final static String SAVE_FILE = "save.ser";
    
    /**
     * A constant to represent the title of the system for menu output.
     */
    public final static String SYS_TITLE = "\nMSEE Conference Management System";
    
    /**
     * A constant used to store the format string for conference menu selection.
     */
    public final static String CONFERENCE_MENU_FORMAT = "\n%1d) %-70s %10s";
    
    /**
     * A constant used to store the format string for detailed manuscript display.
     */
    public final static String PC_MAN_DISPLAY_FORMAT = "\n%-30s %-20s %-25s %s\n";
    
    
    public final static String AUT_MAN_DISPLAY_FORMAT = "%-30s %-30s %s\n";
    		
    /**
     * A constant used to store the format string for detailed manuscript display.
     */
    public final static String SPC_MAN_DISPLAY_FORMAT = "\n%-30s %-20s %-25s %s\n";
    
    /**
     * A constant string used in table output when there is no information to display.
     */
    public final static String NOTHING_TO_DISPLAY = "----------";
    
    /**
     * A constant used to represent the table width for output formatting.
     */
    public final static int TABLE_WIDTH = 100;
    
    /**
     * A constant used to represent the table width for output formatting with less contents.
     */
    public final static int TABLE_WIDTH_1 = 80;
    
    /**
     * 
     */
    public final static int EXIT_TO_LOGIN = -25;
    
    /**
     * SystemHelper cannot be instantiated.
     */
    private SystemHelper() {}
    
    /**
     * A helper method used to prompt the user for integer input.
     * This method assumes perfect user input.
     * 
     * @return the integer input from the user.
     */
    public static int promptUserInt() {
        Scanner scanner = new Scanner(System.in);
    	System.out.print(SystemHelper.PROMPT);
        int choice = scanner.nextInt();
        // flushes the rest of the line/
        scanner.nextLine();
        return choice;
    }
    
    /**
     * A helper method used to prompt the user for String input.
     * Assumes perfect user input.
     * 
     * @return the String input from the user.
     */
    public static String promptUserString() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print(SystemHelper.PROMPT);
    	String input = scanner.nextLine();
        return input;
    }
    
    /**
     * Displays a horizontal dashed line for Table formatting.
     */
    public static void displayDashedLine() {
    	for(int i = 0; i < TABLE_WIDTH; i++) {
	        System.out.print("-");
	    }
    }
    
    /**
     * Writes the current state of the provided ManagementSystem to the
     * file save.ser.
     *  
     * @param theData the ManagementSystem object to be written to file.
     */
    public static void serialize(ManagementSystem theData) {
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
    public static ManagementSystem deserialize() {
    	ManagementSystem openedMS = null;
     
	     try{
	         FileInputStream inFile = new FileInputStream(SAVE_FILE);
	         ObjectInputStream inStream = new ObjectInputStream(inFile);
	         openedMS = (ManagementSystem) inStream.readObject();
	         inStream.close();
	         inFile.close();
	     } catch(IOException e) {
	         System.err.println("Error: Unable to load from save.ser");
	         e.printStackTrace();
	     } catch (ClassNotFoundException e) {
	         System.err.println("Error: save.ser does not contain a ManagementSystem.");
	     }
     
     return openedMS;
 }

    /**
     * Displays a horizontal dashed line for Table formatting for users.
     * @param user - A registered user. (i.e. Author, Program Chair, Sub Program Chair, Reviewer)
     */
	public static void displayDashedLinesFor(RegisteredUser user) {
		int numLines = 0;
		if (user instanceof Author) {
			numLines = TABLE_WIDTH_1;
		} else if (user instanceof ProgramChair) {
			numLines = TABLE_WIDTH;
		}
		for(int i = 0; i < numLines; i++) {
	        System.out.print("-");
	    }
		System.out.println("");
	}
	
}

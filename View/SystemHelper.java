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
    public final static String SAVE_FILE = "/save.ser";
    
    /**
     * A constant to represent the title of the system for menu output.
     */
    public final static String SYS_TITLE = "\nMSEE Conference Management System";
    
    /**
     * A constant used to store the format string for detailed manuscript display.
     */
    public final static String PC_MAN_DISPLAY_FORMAT = "\n%20s %15s %10s %s";

    private SystemHelper() {
    	// Cannot be instantiated.
    }
    
    /**
     * 
     * @return
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
     * 
     * @return
     */
    public static String promptUserString() {
    	Scanner scanner = new Scanner(System.in);
    	System.out.print(SystemHelper.PROMPT);
    	String input = scanner.nextLine();
        return input;
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
	     } catch (ClassNotFoundException e) {
	         System.err.println("Error: save.ser does not contain a ManagementSystem.");
	     }
     
     return openedMS;
 }
	
}

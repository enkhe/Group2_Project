package model;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

/*
 * Author: -
 * Group 2 - TCSS 360A
 */

public class Author extends RegisteredUser implements Serializable {

	private static final long serialVersionUID = -1558785409225599245L;
	/** The list of Manuscripts this Author has submitted. */
	List<Manuscript> myManuscripts;

	/**
	 * Default constructor.
	 */
	public Author() {
		myManuscripts = new ArrayList<>();
	}
	
	/**
	 * Overloaded constructor.
	 */
	public Author(RegisteredUser theUser){
		
		super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		myManuscripts = new ArrayList<>();
		File file = new File("Authors\\" + theUser.getUserName());
		file.mkdirs();
	}
	
	/**
	 * Takes a Manuscript and adds it to the Authors list of Manuscripts
	 * if the manuscript exists. In addition now creates a copy
	 * of the file submitted. Any file type can be handled. File paths must be
	 * seperated by "\\".
	 * 
	 * 0 is returned for a successful add.
	 * -1 is returned for an unsuccessful add.
	 */
	public int submitManuscript(Manuscript theManuscript){
		
		if(!exists(theManuscript)) {
			//get file name and extension
			int fileIndexOfNameAndExtension = theManuscript.getFile().lastIndexOf('\\');
			String fileNameAndExtension = theManuscript.getFile().substring(fileIndexOfNameAndExtension, 
					theManuscript.getFile().length());
			
			//get the full paths to the files
			Path from = Paths.get(theManuscript.getFile());
			Path to = Paths.get("Authors\\" + getUserName() + "\\" + fileNameAndExtension); 
			
			//copy the files over
			try {
				Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				System.err.println("Huston, there seems to be a problem!");
				System.err.println("I advise you to check out Authors submitManuscript method immediatly.");
				System.err.println("Also, be sure to check that you entered your file path correctly!");
				e.printStackTrace();
				return -1;
			}
			
			//if it worked, add the manuscript to the list.
			myManuscripts.add(theManuscript);
			
		} else {
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Takes a Manuscript object, checks if it exists on the list. If it does the 
	 * Manuscript is removed from the list.
	 * 
	 * 	0 is returned for a successful remove.
	 * -1 is returned for an unsuccessful remove.
	 */
	public int removeManuscript(Manuscript theManuscript){
		if(exists(theManuscript)) {
			myManuscripts.remove(theManuscript);
		} else {
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Takes a Manuscript and a String filePath for the Manuscript, then checks if it
	 * exists on the list of Manuscript the Author has submitted. If it does, only the Manuscripts
	 * filePath is updated.
	 * 
	 *  0 is returned for a successful replacement.
	 * -1 is returned for an unsuccessful replacement.
	 */
	public int replaceManuscript(Manuscript theManuscript, Manuscript theReplacement) {
		if(removeManuscript(theManuscript) == 0) {
			submitManuscript(theReplacement);
			return 0;
		}
		
		return -1;
	}
	
	/**
	 * Returns a list of all Manuscripts the Author has submitted.
	 */
	public List<Manuscript> getMyManuscripts() {
		return myManuscripts;
	}
	
	/**
	 * Private method that checks if a Manuscript exists on the list
	 * of Manuscripts the Author has already submitted. Returns true if it exists
	 * and false if it doesn't.
	 */
	private boolean exists(Manuscript theManuscript) {
		boolean exists = false;
		
		for(Manuscript m : myManuscripts) {
			if(m.getFile().equalsIgnoreCase(theManuscript.getFile()) && 
					m.getAuthorID() == theManuscript.getAuthorID()) {
				exists = true;
			}
		}
		
		return exists;
	}
}

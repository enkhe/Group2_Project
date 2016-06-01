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
 * Author: Tyler Brent
 * Group 2 - TCSS 360A
 */

/**
 * 
 * This class handles functions of controller Author in a Conference.
 * You can submit manuscripts, remove manuscripts, and replace manuscripts.
 * You can also get a list of all manuscripts associated with this Author.
 *
 */

public class Author extends RegisteredUser implements Serializable {

	private static final long serialVersionUID = -1558785409225599245L;
	/** The list of Manuscripts this Author has submitted. */
	private List<Manuscript> myManuscripts;

	public Author() {
		myManuscripts = new ArrayList<>();
	}
	
	/**
	 * Overloaded constructor accepts a RegisteredUser.
	 */
	public Author(RegisteredUser theUser){
		
		super(theUser.getFirstName(), theUser.getLastName(), theUser.getUserName(), theUser.getID());
		myManuscripts = new ArrayList<>();
	}
	
	/**
	 * Precondition: Takes a String ConferenceName and a Manuscript to be submitted.
	 * Postcondition: Adds a manuscript to the Authors list and creates a copy of it.
	 * Returns -1 if the submission fails. Returns 0 if the submission succeeds.
	 */
	public int submitManuscript(String theConferenceName, Manuscript theManuscript){
		
		if(!exists(theManuscript)) {
			//get file name and extension
			int fileIndexOfNameAndExtension = theManuscript.getFile().lastIndexOf(File.separator);
			String fileNameAndExtension = theManuscript.getFile().substring(fileIndexOfNameAndExtension, 
					theManuscript.getFile().length());
			
			//create the file system
			File file = new File(theConferenceName + File.separator + "Authors" + File.separator + getUserName() 
							+ File.separator + theManuscript.getTitle());
			file.mkdirs();
			
			//get the full paths to the files
			Path from = Paths.get(theManuscript.getFile());
			Path to = Paths.get(theConferenceName + File.separator + "Authors" + File.separator + getUserName() + File.separator 
					+ theManuscript.getTitle() + File.separator + fileNameAndExtension); 
			
			//copy the files over
			try {
				Files.copy(from, to, StandardCopyOption.REPLACE_EXISTING);
				theManuscript.setFilePath(to.toString());
			} catch (IOException e) {
				System.err.println("Huston, there seems to be a problem!");
				System.err.println("I advise you to check out Authors submitManuscript method immediatly.");
				System.err.println("Also, be sure to check that you entered your file path correctly!");
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
	 *	Precondition: Takes a manuscript to be removed.
	 *	Postcondition: Removes the manuscript from the Authors list of Manuscripts. 
	 *	Returns -1 for a failed removal. Returns 0 for success.
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
	 * Precondition: Takes two Manuscripts. A Manuscript to be changed and a Manuscript containing the changes.
	 * Postcondition: Changes the title of the Manuscript to be changed. Returns -1 if the change was
	 * unsuccessful. Returns 0 for success.
	 */
	public int replaceManuscript(Manuscript theManuscript, Manuscript theReplacement) {
		if(exists(theManuscript)) {
			theManuscript.setTitle(theReplacement.getTitle());
			return 0;
		}
		
		return -1;
	}
	
	/**
	 * Precondition: None.
	 * Postcondition: Returns a list of all Manuscripts the Author has submitted.
	 */
	public List<Manuscript> getMyManuscripts() {
		return myManuscripts;
	}
	
	/*
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

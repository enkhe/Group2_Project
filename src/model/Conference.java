package model;

/**
 * TCSS360 - SPRING2016
 * Group 2 Project - Conferences
 */
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * 
 * @author Enkh
 * @coauthor Tyler Brent
 * @version v1.00
 *
 */
public class Conference implements Serializable {

	private static final long serialVersionUID = -8029606779383723400L;
	private static final int DEADLINE_DAYS = 5;
	private String myName;
	private ProgramChair myPC;
	private List<Manuscript> myManuscripts;
	private List<Author> myAuthors;
	private List<Reviewer> myReviewers;
	private List<SubProgramChair> mySubProgramChairs;
	private Calendar myDate;
	private Calendar myDeadline;
	
	/**
	 * Conference Constructor
	 */
	public Conference() {
		myName = "N/A";
		myAuthors = new LinkedList<>();
		myReviewers = new LinkedList<>();
		mySubProgramChairs = new LinkedList<>();
		myManuscripts = new LinkedList<>();
		myPC = new ProgramChair();
		myDate = Calendar.getInstance();
		myDeadline = (Calendar)myDate.clone();
		myDeadline.add(Calendar.DAY_OF_YEAR, DEADLINE_DAYS);
	}
	
	public Conference(Calendar theDeadline) {
		myName = "N/A";
		myAuthors = new LinkedList<>();
		myReviewers = new LinkedList<>();
		mySubProgramChairs = new LinkedList<>();
		myManuscripts = new LinkedList<>();
		myPC = new ProgramChair();
		myDeadline= theDeadline;
		myDate = (Calendar)myDeadline.clone();
		myDate.add(Calendar.DAY_OF_YEAR, -5);
	}
	
	/**
	 * Sets Program Chair
	 */
	public void setProgramChair(ProgramChair thePC) {
		myPC = thePC;
	}

	/**
	 * 
	 */
	public void setConferenceName(String theName) {
		myName = theName;
	}
	
	/**
	 * Set myManuscripts for Serializable
	 */
	public void setMyManuscripts(List<Manuscript> theManuscripts) {
        myManuscripts = theManuscripts;
        
    }

	/**
	 * Adds an Author the the list.
	 */
	public void addAuthor(Author theAuthor) {
		myAuthors.add(theAuthor);
	}

	/**
	 * Adds an SubprogramChair the the list.
	 */
	public void addSubprogramChair(RegisteredUser theUser) {
		mySubProgramChairs.add(new SubProgramChair(theUser));
	}

	/**
	 * Adds an Reviewer the the list.
	 */
	public void addReviewer(Reviewer theReviewer) {
		myReviewers.add(theReviewer);
	}
	

	/**
	 * Removes an Author.
	 */
	public void removeAuthor(Integer theUserID) {
		for(Author aut : myAuthors) {
			if (aut.getID() == theUserID) {
				myAuthors.remove(aut);
				break;
			}
		}
	}

	/**
	 * Removes an SubProgramChair.
	 */
	public void removeSubProgramChair(Integer theUserID) {
		for(SubProgramChair spc : mySubProgramChairs) {
			if (spc.getID() == theUserID) {
				mySubProgramChairs.remove(spc);
				break;
			}
		}
	}


	/**
	 * Removes Reviewer.
	 */
	public void removeReviewer(Integer userID) {
		for(Reviewer rev : myReviewers) {
			if (rev.getID() == userID) {
				myReviewers.remove(rev);
				break;
			}
		}
	}

	/**
	 * Removes an AuthorsManuscript.
	 */
	public void removeAuthorsManuscript(Manuscript theManuscript) {
		myManuscripts.remove(theManuscript);
	}

	/**
	 * Checks if an given user is an Author.
	 */
	public boolean isAuthor(Integer theId) {
		boolean isAut = false;
		for(Author aut : myAuthors) {
			if (aut.getID() == theId) {
				isAut = true;
				break;
			}
		}
		return isAut;
	}

	/**
	 * Checks if an given user is an Reviewer.
	 */
	public boolean isReviewer(Integer theId) {
		boolean isRev = false;
		for(Reviewer rev : myReviewers) {
			if (rev.getID() == theId) {
				isRev = true;
				break;
			}
		}
		return isRev;
	}

	/**
	 * Checks if an given user is an SubprogramChair.
	 */
	public boolean isSubprogramChair(Integer theId) {
		boolean isSPC = false;
		for(SubProgramChair spc : mySubProgramChairs) {
			if (spc.getID() == theId) {
				isSPC = true;
				break;
			}
		}
		return isSPC;
	}

	/**
	 * Checks if an given user is an ProgramChair.
	 */
	public boolean isProgramChair(Integer theID) {
		if(theID == myPC.getID()) {
			return true;
		}
		return false;
	}

	/**
	 * Gets the Conference name.
	 */
	public String getConferenceName() {
		return myName;
	}

	/**
	 * Gets the current ProgramChair id.
	 */
	public int getProgramChairId() {
		return myPC.getID();
	}
	

	/**
	 * Get all Authors.
	 */
	public List<Author> getAllAuthors() {
		return myAuthors;
	}

	/**
	 * Get all Subprogram Chairs.
	 */
	public List<SubProgramChair> getAllSubProgramChairs() {
		return mySubProgramChairs;
	}

	/**
	 * Get all Reviewers.
	 */
	public List<Reviewer> getAllReviewers() {
		return myReviewers;
	}

	/**
	 * Get all Authors Manuscripts.
	 */
	public List<Manuscript> getAllAuthorsManuscript(Integer theAuthorID) {
		return myManuscripts;
	}
	
	/**
	 * Get an author from conferences list by the ID.
	 */
	public Author getAuthor(int theID) {
		Author author = new Author();
		
		for(Author theAuthor : myAuthors) {
			if(theAuthor.getID() == theID) {
				author = theAuthor;
			}
		}
		
		return author;
	}
	
	/**
	 * Returns this conferences manuscript submission deadline as a string in
	 * MM\DD\YYYY format.
	 * 
	 * @return the submission deadline as a string in MM\DD\YYYY format.
	 */
	public String getDeadlineString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM\\dd\\YYYY");
    	String deadlineString = dateFormat.format(myDeadline.getTime());
		return deadlineString;
	}
	
	/**
	 * Check if the deadline has been reached. Currently deadlines are set for 5 days out from the
	 * creation of the conference.
	 */
	public boolean deadlinePassed(Calendar theDate) {
		if ((theDate.compareTo(myDate) >= 0) && (theDate.compareTo(myDeadline)  < 0 )) 
			return false; 
		else 
			return true;
	}
	
	/**
	 * purely a method to test against the business rule of
	 * no papers past deadline. Here we essentially adjust the
	 * deadline to force a late paper.
	 */
	public void setMyDate(Calendar theDate) {
		myDate = theDate;
	}
	
	public Reviewer getReviewer(int theID) {
		Reviewer reviewer = new Reviewer();
		
		for(Reviewer theReviewer : myReviewers) {
			if(theReviewer.getID() == theID) {
				reviewer = theReviewer;
			}
		}
		
		return reviewer;
	}
	
	/**
	 * Takes a Manuscript and adds it to the Authors list of Manuscripts
	 * if the following conditions are met.
	 * 1) The Manuscript must not already exist.
	 * 2) the Author must not exceed 4 submissions.
	 * 
	 * 0 is returned for a successful add.
	 * -1 is returned for an unsuccessful add.
	 */
	public int submitManuscript(Manuscript theManuscript){
		
		if(!exists(theManuscript)) {
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
			
			for(Reviewer reviewer : myReviewers) {
				reviewer.unassignManuscript(theManuscript);
			}
			
			for(SubProgramChair sub : mySubProgramChairs) {
				sub.unassignManuscript(theManuscript);
			}
			
		} else {
			return -1;
		}
		
		return 0;
	}
	
	public int modifyManuscript(Manuscript theOriginal, Manuscript theNew) {
		if(exists(theOriginal)) {
			myManuscripts.remove(theOriginal);
			myManuscripts.add(theNew);
			
			for(Reviewer reviewer : myReviewers) {
				if(reviewer.unassignManuscript(theOriginal) == 1) {
					reviewer.assignManuscript(theNew);
				}
			}
			
			for(SubProgramChair sub : mySubProgramChairs) {
				if(sub.unassignManuscript(theOriginal) == 1) {
					sub.assignManuscript(theNew);
				}
			}
			
		} else {
			return -1;
		}
		
		return 0;
	}
	
	/**
	 * Private method that checks if a Manuscript exists on the list
	 * of Manuscripts the Author has already submitted. Returns true if it exists
	 * and false if it doesn't.
	 */
	private boolean exists(Manuscript theManuscript) {
		boolean exists = false;
		
		for(Manuscript m : myManuscripts) {
			if(m.getFile() == theManuscript.getFile() && 
					m.getAuthorID() == theManuscript.getAuthorID()) {
				exists = true;
			}
		}
		
		return exists;
	}
}

package model;

/**
 * TCSS360 - SPRING2016
 * Group 2 Project - Conferences
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Calendar;

/**
 * 
 * @author Enkh
 * @version v1.00
 *
 */
public class Conference {
	private String filePath;
	private String review;
	private Conference myCurrentConference;
	private ProgramChair myCurrentProgramChair;
	private List<Manuscript> myManuscripts;
	private List<Author> myAuthors;
	private List<Reviewer> myReviewers;
	private List<ProgramChair> myProgramChairs;
	private List<SubProgramChair> mySubProgramChairs;
	private List<RegisteredUser> myRegisteredUsers;
	private List<Manuscript> myAuthorManuscripts;
	private List<Manuscript> myReviewerManuscripts;
	private List<Manuscript> mySubProgramChairManscripts;
	private HashMap<Integer, List<RegisteredUser>> myRegisteredUserRoles;
	private Calendar myDate;
	
	/**
	 * Conference Constructor
	 */
	public Conference() {
		myCurrentConference = this;
		myAuthors = new LinkedList<>();
		myReviewers = new LinkedList<>();
		myProgramChairs = new LinkedList<>();
		mySubProgramChairs = new LinkedList<>();
		myRegisteredUsers = new LinkedList<>();
		myManuscripts = new LinkedList<>();
		myRegisteredUserRoles = new HashMap<>();
		myCurrentProgramChair = new ProgramChair();
		myDate = Calendar.getInstance();
	}
	
	/**
	 * Sets Program Chair
	 */
	public void setProgramChair(ProgramChair thePC) {
		ProgramChair programChair = new ProgramChair();
		myProgramChairs.add(programChair);
		Integer userID = programChair.getID();
		List<RegisteredUser> userRoles = myRegisteredUserRoles.get(userID);
		userRoles.add((RegisteredUser)programChair);
		myRegisteredUserRoles.put(userID, userRoles);
	}

	/**
	 * 
	 */
	public void setConferenceName(String theConferenceName) {
		myCurrentConference.setConferenceName(theConferenceName);
	}

	/**
	 * Adds an Author the the list.
	 */
	public void addAuthor(Author theAuthor) {
		// TODO Auto-generated method stub
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
	public boolean isProgramChair(Integer theId) {
		boolean isPC = false;
		for(ProgramChair pc : myProgramChairs) {
			if (pc.getID() == theId) {
				isPC = true;
				break;
			}
		}
		return isPC;
	}

	/**
	 * Gets the Conference name.
	 */
	public String getConferenceName() {
		return myCurrentConference.getConferenceName();
	}

	/**
	 * Gets the current ProgramChair id.
	 */
	public int getProgramChairId() {
		return myCurrentProgramChair.getID();
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
	 * Check if the deadline has been reached. Currently deadlines are set for 5 days out from the
	 * creation of the conference.
	 */
	public boolean deadlinePassed(Calendar theDate) {
		int deadlineDays = 5;
		if ((theDate.get(Calendar.DAY_OF_MONTH) > myDate.get(Calendar.DAY_OF_MONTH) + deadlineDays)) {
			return true;
		}
		return false;
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
}

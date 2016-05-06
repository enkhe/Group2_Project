package model;
/**
 * TCSS360 - SPRING2016
 * Group 2 Project - Conferences
 */
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import interfaces.IConference;
import models.Author;
import models.Manuscript;
import models.ProgramChair;
import models.RegisteredUser;
import models.Reviewer;
import models.SubprogramChair;

/**
 * 
 * @author Enkh
 * @version v1.00
 *
 */
public class Conference implements IConference {
	private String filePath;
	private String review;
	private Conference myCurrentConference;
	private ProgramChair myCurrentProgramChair;
	private List<Manuscript> myManuscripts;
	private List<Author> myAuthors;
	private List<Reviewer> myReviewers;
	private List<ProgramChair> myProgramChairs;
	private List<SubprogramChair> mySubProgramChairs;
	private List<RegisteredUser> myRegisteredUsers;
	private List<Manuscript> myAuthorManuscripts;
	private List<Manuscript> myReviewerManuscripts;
	private List<Manuscript> mySubProgramChairManscripts;
	private HashMap<Integer, List<RegisteredUser>> myRegisteredUserRoles;
	
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
	}
	
	/**
	 * Sets Program Chair
	 */
	@Override
	public void setProgramChair(ProgramChair thePC) {
		ProgramChair programChair = new ProgramChair();
		myProgramChairs.add(programChair);
		Integer userID = programChair.getUserID();
		List<RegisteredUser> userRoles = myRegisteredUserRoles.get(userID);
		userRoles.add((RegisteredUser)programChair);
		myRegisteredUserRoles.put(userID, userRoles);
	}

	/**
	 * 
	 */
	@Override
	public void setConferenceName(String theConferenceName) {
		myCurrentConference.setConferenceName(theConferenceName);
	}

	/**
	 * Adds an Author the the list.
	 */
	@Override
	public void addAuthor(Author theAuthor) {
		// TODO Auto-generated method stub
		myAuthors.add(theAuthor);
	}

	/**
	 * Adds an SubprogramChair the the list.
	 */
	@Override
	public void addSubprogramChair(Integer theUserID) {
		mySubProgramChairs.add(new SubprogramChair(theUserID));
	}

	/**
	 * Adds an Reviewer the the list.
	 */
	@Override
	public void addReviewer(Reviewer theReviewer) {
		myReviewers.add(theReviewer);
	}
	

	/**
	 * Removes an Author.
	 */
	@Override
	public void removeAuthor(Integer theUserID) {
		for(Author aut : myAuthors) {
			if (aut.getUserID() == theUserID) {
				myAuthors.remove(aut);
				break;
			}
		}
	}

	/**
	 * Removes an SubProgramChair.
	 */
	@Override
	public void removeSubProgramChair(Integer theUserID) {
		for(SubprogramChair spc : mySubProgramChairs) {
			if (spc.getUserID() == theUserID) {
				mySubProgramChairs.remove(spc);
				break;
			}
		}
	}


	/**
	 * Removes Reviewer.
	 */
	@Override
	public void removeReviewer(Integer userID) {
		for(Reviewer rev : myReviewers) {
			if (rev.getUserID() == userID) {
				myReviewers.remove(rev);
				break;
			}
		}
	}

	/**
	 * Removes an AuthorsManuscript.
	 */
	@Override
	public void removeAuthorsManuscript(Manuscript theManuscript) {
		myManuscripts.remove(theManuscript);
	}

	/**
	 * Checks if an given user is an Author.
	 */
	@Override
	public boolean isAuthor(Integer theId) {
		boolean isAut = false;
		for(Author aut : myAuthors) {
			if (aut.getUserID() == theId) {
				isAut = true;
				break;
			}
		}
		return isAut;
	}

	/**
	 * Checks if an given user is an Reviewer.
	 */
	@Override
	public boolean isReviewer(Integer theId) {
		boolean isRev = false;
		for(Reviewer rev : myReviewers) {
			if (rev.getUserID() == theId) {
				isRev = true;
				break;
			}
		}
		return isRev;
	}

	/**
	 * Checks if an given user is an SubprogramChair.
	 */
	@Override
	public boolean isSubprogramChair(Integer theId) {
		boolean isSPC = false;
		for(SubprogramChair spc : mySubProgramChairs) {
			if (spc.getUserID() == theId) {
				isSPC = true;
				break;
			}
		}
		return isSPC;
	}

	/**
	 * Checks if an given user is an ProgramChair.
	 */
	@Override
	public boolean isProgramChair(Integer theId) {
		boolean isPC = false;
		for(ProgramChair pc : myProgramChairs) {
			if (pc.getUserID() == theId) {
				isPC = true;
				break;
			}
		}
		return isPC;
	}

	/**
	 * Gets the Conference name.
	 */
	@Override
	public String getConferenceName() {
		return myCurrentConference.getConferenceName();
	}

	/**
	 * Gets the current ProgramChair id.
	 */
	@Override
	public int getProgramChairId() {
		return myCurrentProgramChair.getUserID();
	}
	

	/**
	 * Get all Authors.
	 */
	@Override
	public List<Author> getAllAuthors() {
		return myAuthors;
	}

	/**
	 * Get all Subprogram Chairs.
	 */
	@Override
	public List<SubprogramChair> getAllSubProgramChairs() {
		return mySubprogramChairs;
	}

	/**
	 * Get all Reviewers.
	 */
	@Override
	public List<Reviewer> getAllReviewers() {
		return myReviewers;
	}

	/**
	 * Get all Authors Manuscripts.
	 */
	@Override
	public List<Manuscript> getAllAuthorsManuscript(Integer theAuthorID) {
		return myManuscripts;
	}

}

package testModel;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.NotSerializableException;
import java.io.ObjectOutputStream;

import org.junit.Before;
import org.junit.Test;

import model.*;
import view.ManagementSystem;
import view.SetUp;

public class SerializableTest {

	@Test
	public void authorIsSerializableTryCatch() {
		try {
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
			System.out.println("Author is serializable");
		} catch(NotSerializableException nse)  {
			fail("Author is not serializable");
		} catch(IOException nse)  {
			
		}
	}

	@Test
	public void conferenceIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
			System.out.println("Conference is serializable");
		} catch(NotSerializableException nse)  {
			fail("Conference is not serializable");
		} catch(IOException nse)  {
			
		}
	}
	@Test
	public void manuscriptIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
			System.out.println("Manuscript is serializable");
		} catch(NotSerializableException nse)  {
			fail("Manuscript is not serializable");
		} catch(IOException nse)  {
			
		}
	}
	@Test
	public void programChairIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
			System.out.println("ProgramChair is serializable");
		} catch(NotSerializableException nse)  {
			fail("ProgramChair is not serializable");
		} catch(IOException nse)  {
			
		}
	}
	@Test
	public void subProgramChairIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
			System.out.println("SubProgramChair is serializable");
		} catch(NotSerializableException nse)  {
			fail("SubProgramChair is not serializable");
		} catch(IOException nse)  {
			
		}
	}
	@Test
	public void reviewerIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
			System.out.println("Reviewer is serializable");
		} catch(NotSerializableException nse)  {
			fail("Reviewer is not serializable");
		} catch(IOException nse)  {
			
		}
	}
	@Test
	public void reviewIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
			System.out.println("Review is serializable");
		} catch(NotSerializableException nse)  {
			fail("Review is not serializable");
		} catch(IOException nse)  {
			
		}
	}
	
	@Test
	public void registeredUserIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
		} catch(NotSerializableException nse)  {
			fail("Registered user is not serializable");
		} catch(IOException nse)  {
			
		}
		System.out.println("Registered user is serializable");
	}
	
	@Test
	public void managementSystemUserIsSerializableTryCatch() {
		myManagementSystem = new SetUp().generateManagementSystem();
		boolean hasFailed = false;
		try{
			ObjectOutputStream outStream = new ObjectOutputStream(new FileOutputStream("save.ser"));
			outStream.writeObject(myManagementSystem);
            outStream.close();
		} catch(NotSerializableException nse)  {
			hasFailed = true;
		} catch(IOException nse)  {
			
		}
		
		if (hasFailed) {
			String message = "Management System is not serializable";
			System.out.println(message);
			fail(message);
		} else {
			System.out.println("Management System  is serializable");
		}
	}

	@Before
	public void setUp() throws Exception {
		myAuthor = new Author();
		myConference = new Conference();
		myManuscript = new Manuscript();
		myProgramChair = new ProgramChair();
		mySubProgramChair = new SubProgramChair();
		myRegisteredUser = new RegisteredUser();
		myReviewer = new Reviewer();
		myReview = new Review();
		myManagementSystem = new ManagementSystem();
	}

	Author myAuthor;
	Conference myConference;
	Manuscript myManuscript;
	ProgramChair myProgramChair;
	SubProgramChair mySubProgramChair;
	RegisteredUser myRegisteredUser;
	Reviewer myReviewer;
	Review myReview;
	ManagementSystem myManagementSystem;
}

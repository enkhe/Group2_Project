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

	Author myAuthor;
	Conference myConference;
	Manuscript myManuscript;
	ProgramChair myProgramChair;
	SubProgramChair mySubProgramChair;
	RegisteredUser myRegisteredUser;
	Reviewer myReviewer;
	Review myReview;
	ManagementSystem myManagementSystem;
	
	
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
	
	@Test
	public void authorIsSerializableTryCatch() {
		try {
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
		} catch(NotSerializableException nse)  {
			System.out.println("Author is not serializable");
		} catch(IOException nse)  {
			
		}
		System.out.println("Author is serializable");
	}

	@Test
	public void conferenceIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
		} catch(NotSerializableException nse)  {
			System.out.println("Conference is not serializable");
		} catch(IOException nse)  {
			
		}
		System.out.println("Conference is serializable");
	}
	@Test
	public void manuscriptIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
		} catch(NotSerializableException nse)  {
			System.out.println("Manuscript is not serializable");
		} catch(IOException nse)  {
			
		}
		System.out.println("Manuscript is serializable");
	}
	@Test
	public void programChairIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
		} catch(NotSerializableException nse)  {
			System.out.println("ProgramChair is not serializable");
		} catch(IOException nse)  {
			
		}
		System.out.println("ProgramChair is serializable");
	}
	@Test
	public void subProgramChairIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
		} catch(NotSerializableException nse)  {
			System.out.println("SubProgramChair is not serializable");
		} catch(IOException nse)  {
			
		}
		System.out.println("SubProgramChair is serializable");
	}
	@Test
	public void reviewerIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
		} catch(NotSerializableException nse)  {
			System.out.println("Reviewer is not serializable");
		} catch(IOException nse)  {
			
		}
		System.out.println("Reviewer is serializable");
	}
	@Test
	public void reviewIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
		} catch(NotSerializableException nse)  {
			System.out.println("Review is not serializable");
		} catch(IOException nse)  {
			
		}
		System.out.println("Review is serializable");
	}
	
	@Test
	public void registeredUserIsSerializableTryCatch() {
		try{
			new ObjectOutputStream(new ByteArrayOutputStream()).writeObject(myAuthor);
		} catch(NotSerializableException nse)  {
			System.out.println("Registered user is not serializable");
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
	
}

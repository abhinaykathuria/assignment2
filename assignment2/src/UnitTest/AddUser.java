package UnitTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Exception.NoParentException;
import Exception.NoSuchAgeException;
import controller.AddNewUserController;
import controller.DeleteUserController;
import helper.CreateTableDAO;
import model.Adult;
import model.User;

public class AddUser {
	 
	AddNewUserController lAddNewUserController=new AddNewUserController();
	
	 @BeforeClass
	    public static void runOnceBeforeClass() throws NoParentException {
		 CreateTableDAO lCreateTableDAO=new CreateTableDAO();
		 lCreateTableDAO.createTable();
	    }
	 
	@Test(expected = NoSuchAgeException.class)
	public void testAge() throws NoSuchAgeException, IOException {
		User adult=new Adult();
		adult.setName("John");
		adult.setAge(-100);
		adult.setGender("M");
		adult.setState("VIC");
		boolean createUser=lAddNewUserController.createAdult(adult);
		
	}

	@Test
	public void createUser() throws NoSuchAgeException, IOException {
		User adult=new Adult();
		adult.setName("Abhinay Kathuria");
		adult.setAge(20);
		adult.setGender("M");
		adult.setState("VIC");
		boolean createUser=lAddNewUserController.createAdult(adult);
		assertEquals(createUser,true);
		
	}
	
	@Test
	public void createDuplicateUser() throws NoSuchAgeException, IOException {
		User adult=new Adult();
		adult.setName("Gitansh");
		adult.setAge(20);
		adult.setGender("M");
		adult.setState("VIC");
		boolean createUser=lAddNewUserController.createAdult(adult);
		User adult1=new Adult();
		adult1.setName("Gitansh");
		adult1.setAge(25);
		adult1.setGender("M");
		adult1.setState("ACT");
		createUser=lAddNewUserController.createAdult(adult);
		assertEquals(createUser,false);
		
	}
	 @AfterClass
	    public static void runOnceAfterClass() throws NoParentException, IOException {
		 DeleteUserController lDeleteUserController=new DeleteUserController();
		 lDeleteUserController.deleteUser("Gitansh");
		 lDeleteUserController.deleteUser("Abhinay Kathuria");
	    }
}

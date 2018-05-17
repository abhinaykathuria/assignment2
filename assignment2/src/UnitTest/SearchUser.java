package UnitTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import Exception.NoAvailableException;
import Exception.NoParentException;
import Exception.NoSuchAgeException;
import Exception.NotToBeCoupledException;
import controller.AddNewUserController;
import controller.DeleteUserController;
import controller.SearchUserController;
import helper.CreateTableDAO;
import model.Adult;
import model.Child;
import model.User;

public class SearchUser {

	AddNewUserController lAddNewUserController=new AddNewUserController();
	SearchUserController lSearchUserController=new SearchUserController();
	 @BeforeClass
	    public static void runOnceBeforeClass() throws NoParentException {
		 CreateTableDAO lCreateTableDAO=new CreateTableDAO();
		 lCreateTableDAO.createTable();
	    }
	 
	 @Test()
		public void searchUser() throws NoSuchAgeException, IOException, NoAvailableException, NotToBeCoupledException, NoParentException, InterruptedException {
			User adult=new Adult();
			adult.setName("John M");
			adult.setAge(35);
			adult.setGender("M");
			adult.setState("VIC");
			lAddNewUserController.createAdult(adult);
			User adult1=new Adult();
			adult1.setName("Natasha M");
			adult1.setAge(35);
			adult1.setGender("F");
			adult1.setState("VIC");
			lAddNewUserController.createAdult(adult);
		 	User child=new Child();
			child.setName("Ana M");
			child.setAge(15);
			child.setGender("F");
			child.setState("VIC");
			((Child) child).setParent_id1("John M");
			((Child) child).setParent_id2("Natasha M");
			boolean createUser=lAddNewUserController.createChild(child);
			User searchchild=lSearchUserController.searchUser("Ana M");
			lSearchUserController.searchUser("Ana M");
			assertTrue(searchchild.getName().equalsIgnoreCase(child.getName()));
			
		

		}
	 
	 

}

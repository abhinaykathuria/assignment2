package UnitTest;

import static org.junit.Assert.*;

import java.io.IOException;

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

public class DeleteUser {

	AddNewUserController lAddNewUserController=new AddNewUserController();
	SearchUserController lSearchUserController=new SearchUserController();
	@BeforeClass
	public static void runOnceBeforeClass() throws NoParentException {
		CreateTableDAO lCreateTableDAO=new CreateTableDAO();
		lCreateTableDAO.createTable();
	}

	@Test(expected = NoParentException.class)
	public void searchUser() throws NoSuchAgeException, IOException, NoAvailableException, NotToBeCoupledException, NoParentException, InterruptedException {
		User adult=new Adult();
		adult.setName("John");
		adult.setAge(35);
		adult.setGender("M");
		adult.setState("VIC");
		lAddNewUserController.createAdult(adult);
		User adult1=new Adult();
		adult1.setName("Natasha");
		adult1.setAge(35);
		adult1.setGender("F");
		adult1.setState("VIC");
		lAddNewUserController.createAdult(adult);
		User child=new Child();
		child.setName("Ana");
		child.setAge(15);
		child.setGender("F");
		child.setState("VIC");
		((Child) child).setParent_id1("John");
		((Child) child).setParent_id2("Natasha");
		boolean createUser=lAddNewUserController.createChild(child);
		User searchchild=lSearchUserController.searchUser("Ana");
		DeleteUserController lDeleteUserController=new DeleteUserController();
		lDeleteUserController.deleteUser("John");
	}

	
}

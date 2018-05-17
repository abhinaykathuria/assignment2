package UnitTest;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

import Exception.NoParentException;
import controller.DeleteUserController;

public class Delete {

	@Test
	public void test() throws NoParentException, IOException {
		DeleteUserController lDeleteUserController=new DeleteUserController();
		lDeleteUserController.deleteUser("Ana");
		lDeleteUserController.deleteUser("John");
		lDeleteUserController.deleteUser("Natasha");
		lDeleteUserController.deleteUser("Ana M");
		lDeleteUserController.deleteUser("John M");
		lDeleteUserController.deleteUser("Natasha M");
	}

}

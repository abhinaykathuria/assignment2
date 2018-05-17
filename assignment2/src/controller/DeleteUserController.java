/**
 * The DeleteUserController is a controller class. It acts as an interface between the user and the database.
 * The class allows you to delete Users from the database.
 *It communicates with the DAO retrieve data from the local db.
 * @author  Abhinay Kathuria Gitansh
 * @version 1.0
 * @since   2018-05-14
 */

package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import Exception.NoParentException;
import Exception.NotToBeFriendsException;
import helper.DeleteUserDAO;
import model.User;

public class DeleteUserController {

	private static Logger logger = Logger.getLogger(DeleteUserController.class);

	
	/**
	 * This method is to delete the user from the network.
	 * @param name The Name of user is passed.
	 * @author  Abhinay Kathuria
	 */
	public void deleteUser(String name) throws NoParentException, IOException {
		// TODO Auto-generated method stub
		logger.debug("In DeleteUserController-deleteUser function");
		
		SearchUserController lSearchUserController=new SearchUserController();
		User user=lSearchUserController.searchUser(name);
		if(user==null)
			return ;
		if(user.getAge()>16)
		{
		ArrayList<String> childList=lSearchUserController.getChildren(name);
		if(childList.size()>0)
		{
			logger.debug("NoParentException-Can't Delete parent having dependent");
			throw new NoParentException("Can't Delete parent having dependent");
		}
		}
		DeleteUserDAO lDeleteUserDAO=new DeleteUserDAO();
		lDeleteUserDAO.deleteUser(name);
	}

}

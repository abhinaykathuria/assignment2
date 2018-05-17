/**
 * The CheckChildController is a controller class. It acts as an interface between the user and the database.
 * The class allows you to verify the child records entered from the text file.
 * @author  Abhinay Kathuria
 * @version 1.0
 * @since   2018-05-14
 */

package controller;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import Exception.NoParentException;
import helper.SearchUserDAO;

public class CheckChildController {

	private static Logger logger = Logger.getLogger(CheckChildController.class);

	/**
	 * This method is used to check Child and Young child records whose parent information might not be available in the relation file.
	 * If exists, delete those record from the database
	 * @author  Abhinay Kathuria
	 */
	public void checkYoung() {
		logger.debug("In CheckChildController-checkYoung function");
		// TODO Auto-generated method stub
		SearchUserDAO lSearchUserDAO=new SearchUserDAO();
		ArrayList<String> deleteList=new ArrayList<>();
		deleteList=lSearchUserDAO.searchUser();
		if(deleteList!=null)
		{
			logger.debug("Found records with wrong details.Deleting them. Number of records are"+deleteList.size());
			for(String s:deleteList)
			{
				DeleteUserController lDeleteUserController=new DeleteUserController();
				try {
					lDeleteUserController.deleteUser(s);
				} catch (NoParentException e) {
					// TODO Auto-generated catch block
					logger.debug("NoParentException"+e.getMessage());
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}

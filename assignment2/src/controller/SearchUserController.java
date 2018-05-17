/**
 * The SearchUserController is a controller class. It acts as an interface between the view and the database.
 * The class allows you to search users from the database and pass it to the view to display it to the user.
 *It communicates with the DAO to retrieve data from the local db.
 * @author  Abhinay Kathuria Gitansh
 * @version 1.0
 * @since   2018-05-14
 */

package controller;

import java.io.IOException;
import java.util.*;

import org.apache.log4j.Logger;

import helper.SearchUserDAO;
import model.User;

public class SearchUserController {

	private static Logger logger = Logger.getLogger(SearchUserController.class);

	/**
	 * This method is to search a particular user.
	 * @param name The Name of user is passed.
	 * @author  Abhinay Kathuria
	 * @return User This returns the details of user if found otherwise returns null.
	 */
	public User searchUser(String name) throws IOException {
		logger.debug("In SearchUserController-searchUser() function with following values");
		logger.debug("name"+name);
		SearchUserDAO lSearchUserDAO=new SearchUserDAO();
		return lSearchUserDAO.searchUser(name);
	}

	/**
	 * This method is to find parents of a particular user.
	 * @param name The Name of user is passed.
	 * @author  Gitansh
	 * @return ArrayList<String> This returns the the list of parents or children.
	 */
	public ArrayList<String> getParents(String name) {
		// TODO Auto-generated method stub
		logger.debug("In SearchUserController-getParents() function with following values");
		logger.debug("name"+name);
		SearchUserDAO lSearchUserDAO=new SearchUserDAO();
		return lSearchUserDAO.getParents(name);
	}


	/**
	 * This method is to find children of a particular user.
	 * @param name The Name of user is passed.
	 * @author  Gitansh
	 * @return ArrayList<String> This returns the the list of parents or children.
	 */
	public ArrayList<String> getChildren(String name) {
		logger.debug("In SearchUserController-getChildren() function with following values");
		logger.debug("name"+name);
		// TODO Auto-generated method stub
		SearchUserDAO lSearchUserDAO=new SearchUserDAO();
		return lSearchUserDAO.getParents(name);
	}
}

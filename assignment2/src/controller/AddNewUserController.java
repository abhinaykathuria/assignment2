/**
 * The AddNewUserController is a controller class. It acts as an interface between the user and the database.
 * The class allows you to add Users to the database and also check if a particular user id belongs to the database 
 *It communicates with the DAO to store and retrieve data from the local db.
 * @author  Abhinay Kathuria Gitansh
 * @version 1.0
 * @since   2018-05-14
 */




package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.log4j.Logger;

import Exception.NoAvailableException;
import Exception.NoSuchAgeException;
import Exception.NotToBeCoupledException;
import helper.CreateNewUserDAO;
import helper.CreateTableDAO;
import model.Adult;
import model.Child;
import model.User;
import model.YoungChild;

public class AddNewUserController {
	private static Logger logger = Logger.getLogger(AddNewUserController.class);

	/**
	 * This method is to create a new user.
	 * @author  Abhinay Kathuria
	 * @param adult The user to be created is passed.
	 */
	public boolean createAdult(User adult) throws NoSuchAgeException, IOException {
		// TODO Auto-generated method stub

		logger.debug("In AddNewUserController-createAdult function");
		logger.debug("Creating the user with following details");
		logger.debug("Name"+adult.getName());
		logger.debug("Age"+adult.getAge());
		logger.debug("Image"+adult.getDisplay_picture());
		logger.debug("Gender"+adult.getGender());
		logger.debug("State"+adult.getState());
		logger.debug("Status"+adult.getStatus());
		if(adult.getAge()<0||adult.getAge()>150)
		{
			logger.debug("Throwing an NoSuchAgeException exception-Enter an age between 0-150! Age entered is "+adult.getAge());
			throw new NoSuchAgeException("Please Enter an age between 0-150!");

		}

		CreateNewUserDAO lCreateNewUserDAO=new CreateNewUserDAO();
		if(adult!=null)
		{
			if(lCreateNewUserDAO.checkUserId(adult.getName()))
				return lCreateNewUserDAO.createUser(adult);
			else
				return false;
		}
		return false;
	}

	/**
	 * This method is check if the user name is unique.
	 * @param user_id The Name to be checked is passed.
	 * @author  Abhinay Kathuria
	 * @return boolean This returns false if it is not unique otherwise returns true.
	 */
	public boolean checkUserId(String name) {
		logger.debug("In AddNewUserController-checkUserId function");
		logger.debug("Name recieved: "+name);
		logger.debug("Calling CreateNewUserDAO for function checkUserId");
		CreateNewUserDAO lCreateNewUserDAO=new CreateNewUserDAO();
		return lCreateNewUserDAO.checkUserId(name);
	}


	/**
	 * This method is to create a new  Child.
	 * @author  Abhinay Kathuria
	 * @param lChild The user to be created is passed.
	 */
	public boolean createChild(User lChild) throws NoAvailableException, NoSuchAgeException, NotToBeCoupledException, IOException {
		logger.debug("In AddNewUserController-createChild function");
		if(lChild.getAge()<0||lChild.getAge()>150)
			throw new NoSuchAgeException("Please Enter an age between 0-150!");
		CreateNewUserDAO lCreateNewUserDAO=new CreateNewUserDAO();
		if(lCreateNewUserDAO.checkParentFrDependent(((Child) lChild).getParent_id1().toLowerCase(),((Child) lChild).getParent_id2().toLowerCase())&&lCreateNewUserDAO.checkParentFrDependent(((Child) lChild).getParent_id2().toLowerCase(),((Child) lChild).getParent_id1().toLowerCase()))
		{
			logger.debug("The couple is mutually exclusive.Creating Child");

			try {
				return lCreateNewUserDAO.createUser(lChild);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else if(lCreateNewUserDAO.getAge(((Child) lChild).getParent_id1().toLowerCase())<16||lCreateNewUserDAO.getAge(((Child) lChild).getParent_id2().toLowerCase())<16)
		{
			logger.debug("The couple is not mutually exclusive");
			throw new NotToBeCoupledException("The couple is not mutually exclusive!");

		}
		else
			throw new NoAvailableException("The couple is not mutually exclusive!");
		return false;
	}

	/**
	 * This method is to create a new Young Child.
	 * @author  Abhinay Kathuria
	 * @param lChild The user to be created is passed.
	 */
	public boolean createYoungChild(User lChild) throws NoAvailableException, NoSuchAgeException, IOException {
		if(lChild.getAge()<0||lChild.getAge()>150)
			throw new NoSuchAgeException("Please Enter an age between 0-150!");
		CreateNewUserDAO lCreateNewUserDAO=new CreateNewUserDAO();
		if(lCreateNewUserDAO.checkParentFrDependent(((YoungChild) lChild).getParent_id1().toLowerCase(),((YoungChild) lChild).getParent_id2().toLowerCase())&&lCreateNewUserDAO.checkParentFrDependent(((YoungChild) lChild).getParent_id2().toLowerCase(),((YoungChild) lChild).getParent_id1().toLowerCase()))
		{
			logger.debug("The couple is mutually exclusive.Creating Child");
			try {
				return lCreateNewUserDAO.createUser(lChild);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else
		{
			logger.debug("The couple is not mutually exclusive");
			throw new NoAvailableException("The couple is not mutually exclusive!");
		}
		return false;
	}
}

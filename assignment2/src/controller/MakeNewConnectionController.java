/**
 * The MakeNewConnectionController is a controller class. It acts as an interface between the user and the database.
 * The class allows you to make connection between different users.
 * Also allows you if two users are connected and if yes by which relation
 *It communicates with the DAO to retrieve data from the local db.
 * @author  Abhinay Kathuria Gitansh
 * @version 1.0
 * @since   2018-05-14
 */

package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

import Exception.NotToBeColleaguesException;
import Exception.NotToBeFriendsException;
import Exception.TooYoungException;
import helper.MakeNewConnectionDAO;
import model.User;

public class MakeNewConnectionController {
	private static Logger logger = Logger.getLogger(MakeNewConnectionController.class);

	public boolean createNewConnection(String name, String name2, String relation) {
		logger.debug("In MakeNewConnectionController-createConnection() function with following values");
		logger.debug("User 1: "+name);
		logger.debug("User 2: "+name2);
		logger.debug("Relation: "+relation);	
		logger.debug("Creating Connection");
		MakeNewConnectionDAO lMakeNewConnectionDAO=new MakeNewConnectionDAO();

		try {
			return lMakeNewConnectionDAO.createNewConnection(name,name2,relation);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return false;

	}

	public boolean checkConnection(String name, String name2) {
		// TODO Auto-generated method stub
		logger.debug("In MakeNewConnectionController-checkConnection() function with following values");
		logger.debug("User 1: "+name);
		logger.debug("User 2: "+name2);
		MakeNewConnectionDAO lMakeNewConnectionDAO=new MakeNewConnectionDAO();

		if((lMakeNewConnectionDAO.checkConnection(name,name2))||lMakeNewConnectionDAO.checkConnection(name2,name))
		{
			return true;
		}
		else
			return false;
	}

	public String getConnection(String userName, String userName1) {
		// TODO Auto-generated method stub
		MakeNewConnectionDAO lMakeNewConnectionDAO=new MakeNewConnectionDAO();	
		return lMakeNewConnectionDAO.checkRelation(userName,userName1);
	}


	public boolean createConnection(String userName,String userName1,String relation) throws TooYoungException, NotToBeFriendsException, NotToBeColleaguesException, IOException
	{
		logger.debug("In MakeNewConnectionController-createConnection() function with following values");
		logger.debug("User 1: "+userName);
		logger.debug("User 2: "+userName1);
		logger.debug("Relation: "+relation);
		SearchUserController lSearchUserController=new SearchUserController();
		User user;
		User user1;
		user=lSearchUserController.searchUser(userName.toLowerCase());
		user1=lSearchUserController.searchUser(userName1.toLowerCase());
		if(user!=null&&user1!=null)
		{
			logger.debug("User 1 and User 2 found!");
			MakeNewConnectionController lMakeNewConnectionController=new MakeNewConnectionController();


			if(relation.equals("friends"))
			{
				if(user.getAge()>16&&user1.getAge()<16||user1.getAge()>16&&user.getAge()<16)
				{
					logger.debug("Throwing NotToBeFriendsException:Can't Connect an adult and a child friend");
					throw new NotToBeFriendsException("Can't Connect an adult and a child friend");


				}
				else if(user1.getAge()<3||(user.getAge()<3))
				{
					logger.debug("Throwing TooYoungException: Can't Connect to a Young Child");
					throw new TooYoungException("Can't Connect to a Young Child");

				}
				else if(user.getAge()<16&&user1.getAge()<16)
				{

					if(Math.abs(user.getAge()-user1.getAge())>3)
					{
						logger.debug("Throwing NotToBeFriendsException: Can't Connect connect two children with an age gap larger than 3");

						throw new NotToBeFriendsException("Can't Connect connect two\n" + 
								"children with an age gap larger than 3");
					}
					if(Math.abs(user.getAge()-user1.getAge())<=3)
					{
						ArrayList<String> parentsList=lSearchUserController.getParents(user.getName());
						ArrayList<String> parentsList1=lSearchUserController.getParents(user1.getName());
						Collections.sort(parentsList);
						Collections.sort(parentsList1);
						System.out.println(parentsList);
						System.out.println(parentsList1);

						if(parentsList.equals(parentsList1))
						{
							logger.debug("Throwing NotToBeFriendsException: Can't Connect 2 people from the same family!");
							logger.debug("Parent List 1"+parentsList);
							logger.debug("Parent List 2"+parentsList1);
							throw new NotToBeFriendsException("Can't Connect 2 people from the same family!");				
						}
						else
						{
							logger.debug("Making a connection");
							lMakeNewConnectionController.createNewConnection(user.getName(),user1.getName(),"friends");
							return true;
						}
					}
				}
				else
				{
					logger.debug("Making a connection");
					lMakeNewConnectionController.createNewConnection(user.getName(),user1.getName(),"friends");
					return true;
				}
			}
			else if(relation.equals("colleague"))
			{
				if(user.getAge()>16&&user1.getAge()>16)
				{
					logger.debug("Making a connection");
					lMakeNewConnectionController.createNewConnection(user.getName(),user1.getName(),"colleague");
					return true;
				}
				else
				{
					logger.debug("Throwing NotToBeColleaguesException: Can't Connect  connect a child in a colleague relation");
					throw new NotToBeColleaguesException("Can't Connect  connect a child in a colleague relation");
				}

			}
			else if(relation.equals("classmate"))
			{
				if(user.getAge()<3||user1.getAge()<3)
				{
					logger.debug("Throwing NotToBeColleaguesException: Can't Connect  connect a child in a colleague relation");
					throw new NotToBeColleaguesException("Can't Connect  connect a child in a colleague relation");
				}
				else
				{
					logger.debug("Making a connection");
					lMakeNewConnectionController.createNewConnection(user.getName(),user1.getName(),"classmate");
					return true;

				}
			}
			else if(relation.equals("parent")||(relation.equals("couple")))
			{
				logger.debug("Making a connection");
				lMakeNewConnectionController.createNewConnection(user.getName(),user1.getName(),relation);
			}
		}

		return false;

	}


}

/**
 * The CreateNewUserDAO is the Database Object class which provides commuincation with the database.
 * The class talks with the local db to fetch and store data for creating and fetching data related to new user.
 * @author  Abhinay Kathuria Gitansh
 * @version 1.0
 * @since   2018-05-10
 */

package helper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import Util.SQLiteJDBCDriverConnection;
import controller.SearchUserController;
import model.Adult;
import model.Child;
import model.User;
import model.YoungChild;

public class CreateNewUserDAO {

	private static Logger logger = Logger.getLogger(CreateNewUserDAO.class);


	/**
	 * This method is to create a new user.
	 * In case of Child or Young Child it adds the parents detail into the table storing those values.
	 * @author  Abhinay Kathuria
	 * @param adult The user to be created is passed.
	 */
	public boolean createUser(User adult) throws IOException {
		// TODO Auto-generated method stub
		logger.debug("In CreateNewUserDAO-createUser() function with following values");
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="insert into User_Profile values(?,?,?,?,?,?)";

			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, adult.getName().toLowerCase());
			System.out.println(adult.getDisplay_picture());
			if(adult.getDisplay_picture()!=null)
			{
				File f = new File(adult.getDisplay_picture());
				FileInputStream fis = new FileInputStream(f);
				byte[] buffer = new byte[1024];
				ByteArrayOutputStream bos = new ByteArrayOutputStream();
				for (int len; (len = fis.read(buffer)) != -1;) 
					bos.write(buffer, 0, len);
				pst.setBytes(2, bos.toByteArray());
			}

			// pst.setString(2, adult.getDisplay_picture());
			pst.setString(3, adult.getStatus());
			pst.setString(4, adult.getGender());
			pst.setInt(5, adult.getAge());
			pst.setString(6, adult.getState());

			int rs=pst.executeUpdate();
			if(rs==0)
				return false;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if(adult instanceof Child||adult instanceof YoungChild)
		{
			logger.debug("User is not adult!");

			try {
				String query = "insert into relations values(?,?,?)";
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setString(1, adult.getName().toLowerCase());
				if(adult instanceof Child)
				{
					logger.debug("User is a Child!");
					pst.setString(2, ((Child) adult).getParent_id1().toLowerCase());

				}
				else
				{
					pst.setString(2, ((YoungChild) adult).getParent_id1().toLowerCase());

				}
				pst.setString(3, "parent");
				int rs=pst.executeUpdate();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			try {
				String query = "insert into relations values(?,?,?)";
				PreparedStatement pst=conn.prepareStatement(query);
				pst.setString(1, adult.getName().toLowerCase());
				if(adult instanceof Child)
				{

					pst.setString(2, ((Child) adult).getParent_id2().toLowerCase());

				}
				else
				{
					logger.debug("User is a youngChild!");
					pst.setString(2, ((YoungChild) adult).getParent_id2().toLowerCase());
				}
				pst.setString(3, "parent");
				int rs=pst.executeUpdate();
			}
			catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}


			if(adult instanceof Child)
			{
				if(checkParentAdded(((Child) adult).getParent_id1(),((Child) adult).getParent_id2())&&checkParentAdded(((Child) adult).getParent_id2(),((Child) adult).getParent_id1()))
				{
					logger.debug("Relation between parents does not exist! Creating a relation for couple");
					try {
						String query = "insert into relations values(?,?,?)";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1, ((Child) adult).getParent_id1().toLowerCase());
						pst.setString(2, ((Child) adult).getParent_id2().toLowerCase());
						pst.setString(3, "couple");
						int rs=pst.executeUpdate();
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}  
			}
			else
			{
				if(checkParentAdded(((YoungChild) adult).getParent_id1(),((YoungChild) adult).getParent_id2())&&checkParentAdded(((YoungChild) adult).getParent_id2(),((YoungChild) adult).getParent_id1()))
				{
					logger.debug("User is a Young Child!");
					try {
						String query = "insert into relations values(?,?,?)";
						PreparedStatement pst=conn.prepareStatement(query);
						pst.setString(1, ((YoungChild) adult).getParent_id1().toLowerCase());
						pst.setString(2, ((YoungChild) adult).getParent_id2().toLowerCase());
						pst.setString(3, "couple");
						int rs=pst.executeUpdate();
					}
					catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}


				}

			}


		}
		return true;
	}

	/**
	 * This method is to check if parents are already added.
	 * @author  Abhinay Kathuria
	 * @param parent_id1 The Name of first parent is passed.
	 * @param parent_id2 The Name of second parent is passed.
	 * @return boolean This returns false if they are already added otherwise returns true.
	 */
	boolean checkParentAdded(String parent_id1, String parent_id2) {

		logger.debug("In CreateNewUserDAO-checkParentAdded() function with following values");
		logger.debug("parent_id1= "+parent_id1);
		logger.debug("parent_id2= "+parent_id2);
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select count(*) from relations where trim(lower(Name))=? and trim(lower(friend_name)) = ? and Relation=? ";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,parent_id1.toLowerCase());
			pst.setString(2,parent_id2.toLowerCase());
			pst.setString(3,"couple");
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)>0) {
					logger.debug("Parent already added");
					return false;
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.debug("Parent not added");
		return true; 

	}

	/**
	 * This method is check if the user name is unique.
	 * @param user_id The Name to be checked is passed.
	 * @author  Abhinay Kathuria
	 * @return boolean This returns false if it is not unique otherwise returns true.
	 */
	public boolean checkUserId(String user_id) {

		logger.debug("In CreateNewUserDAO-checkUserId() function with following values");
		logger.debug("user_id= "+user_id);

		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select count(*) from User_Profile where trim(lower(name))= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,user_id.toLowerCase());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)>0) {
					logger.debug("Username does not exist!");

					return false;
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.debug("Username does not exist!");
		return true;
	}

	/**
	 * This method is check if the combination of parent id's is mutually exclusive to create a dependent.
	 * @param userParent1 The Name of first parent is passed.
	 * @param userParent2 The Name of second parent is passed.
	 * @author  Gitansh
	 * @return boolean This returns false if they are not mutually exclusive otherwise returns true.
	 */
	public boolean checkParentFrDependent(String userParent1, String userParent2) {

		logger.debug("In CreateNewUserDAO-checkParentFrDependent() function with following values");
		logger.debug("parent1= "+userParent1);
		logger.debug("parent2= "+userParent2);
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select count(*) from relations where trim(lower(Name))=? and trim(lower(friend_name)) <> ? and Relation=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,userParent1.toLowerCase());
			pst.setString(2,userParent2.toLowerCase());  
			pst.setString(3,"couple");
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)>0) {
					logger.debug("Parent already exists as a couple");
					return false;
				}
			}


		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return true;
	}

	/*public boolean createRelation(String name,String name2,String relation)
	{
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query = "insert into relations values(?,?,?)";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, name.toLowerCase());
			pst.setString(2, name2.toLowerCase());
			pst.setString(3, relation);
			int rs=pst.executeUpdate();
			if(rs>0)
				return true;
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}*/

	/**
	 * This method is to find the age of a user.
	 * @param user1 The Name of user is passed.
	 * @author  Gitansh
	 * @return int This returns the age of the user.
	 */
	public int getAge(String user1) {
		// TODO Auto-generated method stub
		logger.debug("In CreateNewUserDAO-getAge() function with following values");
		logger.debug("user= " + user1);
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select age from User_Profile where lower(name)= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,user1.toLowerCase());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				logger.debug("age= " + rs.getInt(1));
				return rs.getInt(1);
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return 0;
	}


}

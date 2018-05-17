/**
 * The SearchUserDAO is the Database Object class which provides communication with the database.
 * This Class is used to make search and fetch users which are already in the database.
 * @author  Abhinay Kathuria Gitansh
 * @version 1.0
 * @since   2018-05-10
 */


package helper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.omg.CORBA.portable.InputStream;

import Exception.NoParentException;
import Util.SQLiteJDBCDriverConnection;
import controller.DeleteUserController;
import model.Adult;
import model.Child;
import model.User;
import model.YoungChild;

public class SearchUserDAO {

	private static Logger logger = Logger.getLogger(SearchUserDAO.class);

	/**
	 * This method is to search a particular user in the database.
	 * @param name The Name of user is passed.
	 * @author  Abhinay Kathuria
	 * @return User This returns the details of user if found otherwise returns null.
	 */
	public User searchUser(String name) throws IOException {

		logger.debug("In SearchUserDAO- searchUser() function for the following values");
		logger.debug("Name1 = "+name);
		Connection conn= SQLiteJDBCDriverConnection.connect();
		User user=null;
		try {
			String query="select * from User_Profile where trim(lower(name))= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,   name.toLowerCase());
			ResultSet rs=pst.executeQuery();

			while(rs.next())
			{
				logger.debug("User found");

				if( rs.getInt(5)>16)
				{
					logger.debug("User is an adult");
					user=new Adult();
					user.setName(rs.getString(1));
					if(rs.getBinaryStream(2)!=null)
					{
						logger.debug("Image found");
						File file = new File(user.getName()+".jpg");
						FileOutputStream fos = new FileOutputStream(file);

						java.io.InputStream input = rs.getBinaryStream(2);
						byte[] buffer = new byte[1024];
						while (input.read(buffer) > 0) {
							fos.write(buffer);
						}
						user.setDisplay_picture(file.getPath());
					}
					System.out.println(user.getDisplay_picture());
					user.setStatus(rs.getString(3));

					user.setGender(rs.getString(4));
					user.setAge(rs.getInt(5));
					user.setState(rs.getString(6));

				}
				else if(rs.getInt(5)>2)
				{
					logger.debug("User is a Child");
					user=new Child();
					user.setName(rs.getString(1));
					if(rs.getBinaryStream(2)!=null)
					{
						logger.debug("Image found");
						File file = new File(user.getName()+".jpg");
						FileOutputStream fos = new FileOutputStream(file);

						java.io.InputStream input = rs.getBinaryStream(2);
						byte[] buffer = new byte[1024];
						while (input.read(buffer) > 0) {
							fos.write(buffer);
						}
						user.setDisplay_picture(file.getPath());
					}
					user.setStatus(rs.getString(3));
					user.setGender(rs.getString(4));
					user.setAge(rs.getInt(5));
					user.setState(rs.getString(6));
				}
				else
				{
					logger.debug("User is a Young Child");
					user=new YoungChild();
					user.setName(rs.getString(1));
					if(rs.getBinaryStream(2)!=null)
					{
						logger.debug("Image found");
						File file = new File(user.getName()+".jpg");
						FileOutputStream fos = new FileOutputStream(file);

						java.io.InputStream input = rs.getBinaryStream(2);
						byte[] buffer = new byte[1024];
						while (input.read(buffer) > 0) {
							fos.write(buffer);
						}
						user.setDisplay_picture(file.getPath());
					}
					user.setStatus(rs.getString(3));
					user.setGender(rs.getString(4));
					user.setAge(rs.getInt(5));
					user.setState(rs.getString(6));
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
		return user;
	}

	/**
	 * This method is to search Child and Young Child Records which do not have parent information .
	 * @author  Abhinay Kathuria
	 * @return ArrayList<String> This returns the list of the Child and Young Child Records which do not have parent information.
	 */
	public ArrayList<String> searchUser()
	{
		logger.debug("In SearchUserDAO- searchUser() function");
		Connection conn= SQLiteJDBCDriverConnection.connect();
		User user=null;
		ArrayList<String> deleteList=new ArrayList<>();
		try {
			String query="select * from User_Profile";
			PreparedStatement pst=conn.prepareStatement(query);
			ResultSet rs=pst.executeQuery();

			while(rs.next())
			{
				logger.debug("User exists");
				if( rs.getInt(5)<16)
				{
					logger.debug("User is not an adult");
					ArrayList<String> parentsList=getParents(rs.getString(1));

					if(parentsList.size()!=2)
					{
						logger.debug("There are not 2 parents for this record. Hence putting in delete List for "+ rs.getString(1));
						deleteList.add(rs.getString(1));

					}
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
		return deleteList;


	}

	/**
	 * This method is to find parents or children of a particular user.
	 * @param name The Name of user is passed.
	 * @author  Gitansh
	 * @return ArrayList<String> This returns the the list of parents or children.
	 */
	public ArrayList<String> getParents(String name) {

		logger.debug("In SearchUserDAO- getParents function for the following values");
		logger.debug("Name1 = "+name);
		Connection conn= SQLiteJDBCDriverConnection.connect();
		ArrayList<String> parentList =new ArrayList<>();
		try {
			String query="select Name from relations d where lower(d.friend_name)=? and Relation=?" +
					" UNION\n" +
					"select friend_name from relations  where lower(Name)=? and Relation=?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,name.toLowerCase());
			pst.setString(2,"parent");
			pst.setString(3,name.toLowerCase());
			pst.setString(4,"parent");
			ResultSet rs=pst.executeQuery();
			while (rs.next())
			{
				logger.debug("Adding values to the list "+rs.getString(1));
				parentList.add(rs.getString(1));
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
		return parentList;
	}

}

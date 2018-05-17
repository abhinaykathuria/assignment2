/**
 * The MakeNewConnectionDAO is the Database Object class which provides commuincation with the database.
 * This Class is used to make new connection and fetch connections which are already created.
 * @author Abhinay Kathuria Gitansh
 * @version 1.0
 * @since   2018-05-10
 */


package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import Util.SQLiteJDBCDriverConnection;
import model.YoungChild;

public class MakeNewConnectionDAO {

	private static Logger logger = Logger.getLogger(MakeNewConnectionDAO.class);

	/**
	 * This method is to connect two users .
	 * @param name The Name of user1 is passed.
	 * @param name2 The Name of user2 is passed.
	 * @param relation The type of relation to connect users.
	 * @return boolean This returns true if they are connected successfully otherwise false.
	 * @author  Abhinay Kathuria
	 */

	public boolean createNewConnection(String name, String name2, String relation) throws SQLException {
		// TODO Auto-generated method stub
		logger.debug("In MakeNewConnectionDAO- createNewConnection() function for the following values");
		logger.debug("Name1 = "+name);
		logger.debug("Name2 = "+name2);
		logger.debug("realtion = "+relation);

		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query = "insert into relations values(?,?,?)";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1, name);
			pst.setString(2, name2);
			pst.setString(3, relation);
			int rs=pst.executeUpdate();
			if(rs>0)
			{
				logger.debug("Added Succesfully");
				return true;
			}
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
		return false; 
	}

	/**
	 * This method is to find if two users are friends.
	 * @param name The Name of user1 is passed.
	 * @param name2 The Name of user2 is passed.
	 * @author  Gitansh
	 * @return boolean This returns true if they are friends otherwise false.
	 */
	public boolean checkConnection(String name, String name2) {
		// TODO Auto-generated method stub
		logger.debug("In MakeNewConnectionDAO- checkConnection() function for the following values");
		logger.debug("Name1 = "+name);
		logger.debug("Name2 = "+name2);


		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select count(*) from relations where trim(lower(Name))=? and trim(lower(friend_name))= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,name.toLowerCase());
			pst.setString(2,name2.toLowerCase());  

			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				if(rs.getInt(1)>0) {
					logger.debug("Relation Exists");
					return true;
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
		return false;

	}

	/**
	 * This method is to find the relation between  two users are friends.
	 * @param userName The Name of user1 is passed.
	 * @param userName1 The Name of user2 is passed.
	 * @author  Abhinay Kathuria
	 * @return String This returns the type of relation.
	 */
	public String checkRelation(String userName, String userName1) {
		// TODO Auto-generated method stub
		logger.debug("In MakeNewConnectionDAO- createNewConnection() function for the following values");
		logger.debug("Name1 = "+userName);
		logger.debug("Name2 = "+userName1);
		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="select relation from relations where trim(lower(Name))=? and trim(lower(friend_name))= ? "
					+ "UNION"
					+ " select relation from relations where trim(lower(Name))=? and trim(lower(friend_name))= ?";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,userName.toLowerCase());
			pst.setString(2,userName1.toLowerCase());  
			pst.setString(3,userName1.toLowerCase());  
			pst.setString(4,userName.toLowerCase());
			ResultSet rs=pst.executeQuery();
			if(rs.next())
			{
				logger.debug("Relation found =" + rs.getString(1));
				return rs.getString(1);
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
		return null;
	}

}

/**
 * The DeleteUserDAO is the Database Object class which provides commuincation with the database.
 * The class is used to delete users from the database.
 * @author  Abhinay Kathuria
 * @version 1.0
 * @since   2018-05-10
 */

package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import Util.SQLiteJDBCDriverConnection;

public class DeleteUserDAO {

	private static Logger logger = Logger.getLogger(DeleteUserDAO.class);

	/**
	 * This method is to delete the user from the network.
	 * @param uName The Name of user is passed.
	 * @author  Abhinay Kathuria
	 */
	public void deleteUser(String name) {
		// TODO Auto-generated method stub
		logger.debug("In DeleteUserDAO-deleteUser() function for the following value");
		logger.debug("name= "+ name);

		Connection conn= SQLiteJDBCDriverConnection.connect();

		try {
			String query="delete from relations where lower(name)=? or lower(friend_name) = ? ";
			PreparedStatement pst=conn.prepareStatement(query);
			pst.setString(1,name.toLowerCase());
			pst.setString(2,name.toLowerCase());
			pst.executeUpdate();

			query="delete from User_Profile where lower(name)=? ";
			pst=conn.prepareStatement(query);
			pst.setString(1,name.toLowerCase());
			pst.executeUpdate();

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

	}

}

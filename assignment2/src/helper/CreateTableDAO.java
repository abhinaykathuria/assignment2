/**
 * The CreateTableDAO is the Database Object class which provides commuincation with the database.
 * The class is used to create tables in the database.
 * @author  Gitansh
 * @version 1.0
 * @since   2018-05-10
 */


package helper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import Util.SQLiteJDBCDriverConnection;

public class CreateTableDAO {

	private static Logger logger = Logger.getLogger(CreateTableDAO.class);

	/**
	 * This method is used to create the tables in the database.
	 * @author  Gitansh
	 */
	public void createTable() {
		logger.debug("In CreateTableDAO-createTable() function");

		Connection conn= SQLiteJDBCDriverConnection.connect();
		if(conn==null)
			return;
		logger.debug("Connection established");
		try {
			String query="Create table if not exists User_Profile\n" +
					"(\n" +
					"Name varchar2(200),\n" +
					"display_image blob,\n" +
					"status varchar2(200),\n" +
					"gender varchar2(10),\n" +   
					"age INT,\n" + 
					"state varchar(20),\n" +
					"primary key (Name))";
			logger.debug("Creating Table User_Profile");
			PreparedStatement pst=conn.prepareStatement(query);
			pst.execute();

			String query1=" create table if not exists relations\n" +
					"(\n" +
					"Name varchar2(200),\n" +
					"friend_name varchar2(200),\n" +
					"Relation varchar2(200),\n" +
					"foreign key (Name) references User_Profile(Name),\n" +
					"foreign key (friend_name) references User_Profile(Name),\n" +
					"primary key(Name,friend_name)\n" +
					")";
			logger.debug("Creating Table relations");

			PreparedStatement pst1=conn.prepareStatement(query1);
			pst1.execute();


		} catch (SQLException e) {
			logger.error("SQLException");
			e.printStackTrace();
		}
	}
}

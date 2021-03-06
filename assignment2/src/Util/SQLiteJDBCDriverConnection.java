/**
 * The SQLiteJDBCDriverConnection is an Util class.
 * This Class is used to make connection with the database.
 * @author  Abhinay Kathuria 
 * @version 1.0
 * @since   2018-05-10
 */

package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteJDBCDriverConnection {

    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = "jdbc:sqlite:sample.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);


            return conn;
        } catch (SQLException e) {
            System.out.println("Connection to SQLite has not been established! Please try again");
        }
        return conn;
    }

    public static void main(String[] args) {
        connect();
    }
}

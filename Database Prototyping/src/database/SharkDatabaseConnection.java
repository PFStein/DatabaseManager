/**
 * 
 */
package database;

import java.sql.ResultSet;
import java.sql.SQLException;

import database.connection.DatabaseGateway;

/**
 * @author Patrick Stein
 *
 */
public class SharkDatabaseConnection {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    
	    DatabaseGateway dbConnection = null;
		
		if(args.length == 5){
			//Open a new connection using the database gateway
		 dbConnection = new DatabaseGateway(args[0],
									        args[1],
										    args[2],
										    args[3],
										    args[4]);
		}else{
			System.err.println("Not enough arguments to create a database connection.");
			System.exit(1);
		}

		ResultSet rs = null;
		//execute a command
		try {
			rs = dbConnection.execute("SELECT * FROM account_information");
			System.out.println(rs);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			dbConnection.destroy();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}

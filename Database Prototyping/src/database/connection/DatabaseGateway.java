package database.connection;
/**
 * @author Jon Pearce
 * @author Patrick Stein
 * http://www.cs.sjsu.edu/~pearce/modules/patterns/enterprise/persistence/gateway.htm
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseGateway {
    private String driver;
    private String dbms;
    private String dbase;
    private String dbaseName;

    private String user;
    private String password;

    private Connection connection;
    private Statement statement;

    public DatabaseGateway(String driver, String dbms, String dbase, String user, String pswd) {
    	
        this.driver = driver;
        this.dbms = dbms;
        this.dbase = dbase;
        this.user = user;
        this.password = pswd;
        this.dbaseName = dbms + "/" + dbase;
        
        try{
            Class.forName(driver);
            connection = DriverManager.getConnection(dbaseName, user, password);
            statement = connection.createStatement();
            
        } catch(SQLException e){
            System.err.println("can't connect to " + dbaseName + " because " + e);
        } catch(ClassNotFoundException e){
            System.err.println("can't find the the driver: " + this.driver);
        }
    }
    
	public ResultSet execute(String sql) throws SQLException {
        ResultSet result = null;
        if (statement.execute(sql)) {
            result = statement.getResultSet();
        }
        return result;
	}
	
    public void finalize() throws SQLException {
        statement.close();
        connection.close();
    }
    public void destroy() throws SQLException{
        statement.close();
        connection.close();
    }

}

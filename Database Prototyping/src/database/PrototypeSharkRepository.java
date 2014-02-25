package database;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;

import org.postgresql.geometric.PGpath;

import channel.Sokgraph;
import database.connection.DatabaseGateway;
import database.types.SqlSpecification;

public class PrototypeSharkRepository implements SharkRepository{
	
	private DatabaseGateway dbConnection;
	
	public PrototypeSharkRepository(String driver, String dbms, String dbase, String user, String pswd){
		this.dbConnection = new DatabaseGateway(driver, dbms, dbase, user, pswd);
		
	}

	@Override
	public void addSokgraph(Sokgraph s) throws SQLException {
		// TODO Auto-generated method stub
	    
	    String path = s.toString();
	    String keyboardID = s.getKeyboardID();
	    int userID = this.getUserID(s.getUsername());
	    int wordID = this.getWordID(s.getWord());
	    
	    String sp = s.getStartPointX() + "," + s.getStartPointY();
	    String ep = s.getEndPointX() + "," + s.getEndPointY();
	    
	    String query = "INSERT INTO sokgraph (userid,keyboardid,wordid,graph,startpoint,endpoint) VALUES(%i,'%s',%i,path('%s'),point(%s),point(%s))";
	    query = String.format(query,userID,keyboardID,wordID,path,sp,ep);
	    this.dbConnection.execute(query);
		
	}

	@Override
	public void removeSokgraph(Sokgraph s) {
		// TODO Implement this
		
	}

	@Override
	public void updateSokgraph(Sokgraph s) {
		// TODO Not sure if we should implement this
		
	}

	@Override
	public LinkedList<Sokgraph> query(SqlSpecification specification) throws SQLException {
		// TODO Auto-generated method stub
				
		String cond = specification.toSqlClauses();
		
		String query = "SELECT username,word,graph " +
					   "FROM sokgraph,word_dictionary,account_information "  +
					   "WHERE userid = UID AND " +
					   "wordid = WID AND " +
					   cond +";";
		ResultSet rs = this.dbConnection.execute(query);
		LinkedList<Sokgraph> sokgraphs = this.organizeSokgraphQuery(rs);
		rs.close();
		return sokgraphs;
					   
	}
	private int getWordID(String word) throws SQLException{
	    
	    String query = String.format("SELECT wid FROM word_dictionary where '%s' = word;",word);
	    
	    ResultSet rs = this.dbConnection.execute(query);
	    int wordID = rs.getInt("wid");
	    rs.close();
	   
	    return wordID;
	}
   private int getUserID(String username) throws SQLException{
        
        String query = String.format("SELECT uid FROM account_information where '%s' = username;",username);
        ResultSet rs = this.dbConnection.execute(query);
        int uid = rs.getInt("uid");
        rs.close();
       
        return uid;
    }
	// TODO Finish this function, will not work in current version
	private String getKeyboardID(String keyboardLayout) throws SQLException{
	    String keyboardID = null;
	    
	    String query = String.format("SELECT kid FROM keyboard_layout where '%s' = boardlayout;",keyboardLayout);
	        
        ResultSet rs = this.dbConnection.execute(query);
        keyboardID = rs.getString("kid");
        rs.close();
        
	    return keyboardID;
	}
	
	private LinkedList<Sokgraph> organizeSokgraphQuery(ResultSet rs) throws SQLException {
	    
	    LinkedList<Sokgraph> slist = new LinkedList<Sokgraph>();
        while(rs.next()){
                PGpath path = new PGpath(rs.getString("graph"));
                slist.add(new Sokgraph(path,rs.getString("word"),null,null,null));
        }
	    
	    return slist;
		
		
	}

}

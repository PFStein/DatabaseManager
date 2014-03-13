package database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import org.postgresql.geometric.PGpath;

import channel.Sokgraph;
import channel.SokgraphImpl;
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
	    
	    String path = s.toStringPath();
	    String keyboardID = s.getKeyboardID();
	    int userID = this.getUserID(s.getUsername());
	    int wordID = this.getWordID(s.getWord());
	    
	    String sp = s.getStartPointX() + "," + s.getStartPointY();
	    String ep = s.getEndPointX() + "," + s.getEndPointY();
	    
	    String query = "INSERT INTO sokgraph (userid,keyboardid,wordid,graph,startpoint,endpoint) VALUES(%d,'%s',%d,path('%s'),point(%s),point(%s));";
	    query = String.format(query,userID,keyboardID,wordID,path,sp,ep);
	    this.dbConnection.execute(query);
		
	}

	@Override
	public void removeSokgraph(Sokgraph s) {
		// TODO Implement this
	    System.out.println("Feature currently not supported.");
		
	}

	@Override
	public void updateSokgraph(Sokgraph s) {
		// TODO Not sure if we should implement this
	    System.out.println("Feature currently not supported.");
		
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
	    int wordID = 0;
	    rs.next();
	    
	    if(rs.isLast()){
	        wordID = rs.getInt("wid");
	    }else{
	        //Word doesn't exist, then we add the word
	        rs.close();
	        wordID = insertNewWord(word);
	    } 
	    rs.close();
	   
	    return wordID;
	}
	
	private int insertNewWord(String word) throws SQLException{
	    
        String insertQuery = String.format("INSERT INTO word_dictionary (word) VALUES ('%s');",word);
        this.dbConnection.execute(insertQuery);
        
        //Verify word has been entered in the database
	    String query = String.format("SELECT wid FROM word_dictionary where '%s' = word;",word);
	    ResultSet rs = this.dbConnection.execute(query);
        int wordID = 0;
        rs.next();
        if(rs.isLast()){
            wordID = rs.getInt("wid");
        }else{
            //Still doesn't exist, well. We are kind of screwed.
            rs.close();
            throw new SQLException("Word was not sucessfully inserted. Sokgraph cannot be inserted without an associated word.");
        } 
        rs.close();
        return wordID;
	}
	
	private int getUserID(String username) throws SQLException{
        
        String query = String.format("SELECT uid FROM account_information where '%s' = username;",username);
        ResultSet rs = this.dbConnection.execute(query);
        int uid = 0;
        rs.next();
        if(rs.isLast()){
            uid = rs.getInt("uid");
        }else{
            rs.close();
            throw new SQLException("Non-unique username. Contact a DBA to investigate this issue.");
        } 
        rs.close();
       
        return uid;
    }
	// TODO Finish this function, will not work in current version
	@SuppressWarnings("unused")
    private String getKeyboardID(String keyboardLayout) throws SQLException{
	    
	    String keyboardID = null;
	    String query = String.format("SELECT kid FROM keyboard_layout where '%s' = boardlayout;",keyboardLayout);
	        
        ResultSet rs = this.dbConnection.execute(query);
        rs.next();
        if(rs.isLast()){
            keyboardID = rs.getString("kid");
        }else{
            rs.close();
            throw new SQLException("Non-unique keyboard layout. Contact a DBA to investigate this issue.");
        } 
        rs.close();
        
	    return keyboardID;
	}
	private LinkedList<Sokgraph> organizeSokgraphQuery(ResultSet rs) throws SQLException {
	    
	    LinkedList<Sokgraph> slist = new LinkedList<Sokgraph>();
        while(rs.next()){
                PGpath path = new PGpath(rs.getString("graph"));
                slist.add(new SokgraphImpl(path,rs.getString("word"),null,null,null));
        }
	    return slist;
	}

}

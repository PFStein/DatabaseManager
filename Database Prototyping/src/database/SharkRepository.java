/**
 * 
 */
package database;

import java.sql.SQLException;
import java.util.LinkedList;

import channel.Sokgraph;
import database.types.SqlSpecification;

/**
 * @author Patrick Stein
 *
 */
public interface SharkRepository{
	void addSokgraph(Sokgraph s) throws SQLException;
	void removeSokgraph(Sokgraph s)throws SQLException;
	void updateSokgraph(Sokgraph s)throws SQLException;
	
	LinkedList<Sokgraph> query(SqlSpecification specification) throws SQLException;
	

}
/**
 * 
 */
package database;

import java.awt.List;
import java.sql.SQLException;

import channel.Sokgraph;
import database.types.SokgraphSpecification;
import database.types.SqlSpecification;

/**
 * @author Patrick Stein
 *
 */
public interface SharkRepository{
	void addSokgraph(Sokgraph s);
	void removeSokgraph(Sokgraph s);
	void updateSokgraph(Sokgraph s);
	
	List query(SokgraphSpecification specification) throws SQLException;
	

}
package database;

import java.awt.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import channel.Sokgraph;
import database.connection.DatabaseGateway;
import database.types.SokgraphSpecification;
import database.types.SqlSpecification;

public class PrototypeSharkRepository implements SharkRepository{
	
	private DatabaseGateway dbConnection;
	
	public PrototypeSharkRepository(){
		dbConnection = new DatabaseGateway(null, null, null, null, null);
		
	}

	@Override
	public void addSokgraph(Sokgraph s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeSokgraph(Sokgraph s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateSokgraph(Sokgraph s) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List query(SokgraphSpecification specification) throws SQLException {
		// TODO Auto-generated method stub
				
		String cond = specification.toSqlClauses();
		
		String query = "SELECT * " +
					   "FROM sokgraph, word" +
					   "WHERE WID = wordID AND" +
					   cond;
		return organizeSokgraphQuery(this.dbConnection.execute(query));
					   
	}
	
	private List organizeSokgraphQuery(ResultSet rs){
		
		System.out.println(rs);
		return null;
		
		
	}

}

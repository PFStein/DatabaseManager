/**
 * 
 */
package database;

import java.awt.Point;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;

import channel.Sokgraph;
import channel.SokgraphPoint;

import database.connection.DatabaseGateway;
import database.types.SokgraphSpecificationByStartAndEndPoints;
import database.types.SqlSpecification;

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
	    
	    PrototypeSharkRepository dbRepo = null;
		
		if(args.length == 5){
			//Open a new connection using the database gateway
		    dbRepo = new PrototypeSharkRepository(args[0],
									        args[1],
										    args[2],
										    args[3],
										    args[4]);
		}else{
			System.err.println("Not enough arguments to create a database connection.");
			System.exit(1);
		}
		
		LinkedList<SokgraphPoint> path = new LinkedList<SokgraphPoint>();
		
		LinkedList<Sokgraph> sokgraphList = null;
		
		path.add(new SokgraphPoint(1.0,0.0));
		path.add(new SokgraphPoint(2.0,0.0));
		path.add(new SokgraphPoint(3.0,0.0));
		path.add(new SokgraphPoint(4.0,0.0));
		
		

		Sokgraph testSokgraph = new Sokgraph(path, null, "Patrick Stein", "qwerty",null);
		
		
		SqlSpecification ss = new SokgraphSpecificationByStartAndEndPoints(testSokgraph,10.0);
		
		try {
            sokgraphList =  dbRepo.query(ss);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
		System.out.println(sokgraphList);
		
	}

}

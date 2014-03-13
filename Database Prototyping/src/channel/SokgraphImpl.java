package channel;

import java.awt.Point;
import java.util.LinkedList;

import org.postgresql.geometric.PGpath;
import org.postgresql.geometric.PGpoint;
/**
 * @author Patrick Stein
 * Basic implementation of the Sokgraph interface.
 * {@link Sokgraph}
 */

public class SokgraphImpl implements Sokgraph{
    
    
	private LinkedList<SokgraphPoint> coordinates;
	private String[] keyboardLayout;
	private String username;
	private String keyboardID;
	private String word;
	private Point startPoint;
	private Point endPoint;
	
	/**
	 * Constructor that takes a LinkedList of SokgraphPoints, a word, a username, a keyboardID, and a keyboardLayout
	 * 
	 * @param coords LinkedList of SokgraphPoints representing the sokgraph path
	 * @param word String that corresponds to what word the sokgraph represents
	 * @param un String that indicates the user that input this sokgraph.
	 * @param keyboardID String that represents the database ID for the keyboard layout
	 * @param keyLayout Array of Strings that represent the order and characters used on the keyboard
	 */
	public SokgraphImpl(LinkedList<SokgraphPoint> coords,String word, String un,String keyboardID,String[] keyLayout){
	    
	    this.setCoordinates(coords);
		this.setUsername(un);
		this.setWord(word);
		this.setKeyboardLayout(keyLayout);
		this.setKeyboardID(keyboardID);
	}
	/**
	 * Specialized constructor designed to be used with the PGpath data type from PostgreSQL JDBC.
	 * For more information about PGPath, see: {@link http://jdbc.postgresql.org/documentation/publicapi/org/postgresql/geometric/PGpath.html}
	 * @param path A special data type from the PostgreSQL JDBC. This represents the sokgraph path.
     * @param word String that corresponds to what word the sokgraph represents
     * @param un String that indicates the user that input this sokgraph.
     * @param keyboardID String that represents the database ID for the keyboard layout
     * @param keyLayout Array of Strings that represent the order and characters used on the keyboard
	 */
    public SokgraphImpl(PGpath path,String word, String un,String keyboardID,String[] keyLayout){
        this.setCoordinates(path);
        this.setUsername(un);
        this.setWord(word);
        this.setKeyboardLayout(keyLayout);
        this.setKeyboardID(keyboardID);
    }
	
	//////////////////////////////////Private set methods\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\
    /**
     * Special set method for the datatype conversion from the PGPath type to a more general type.
     * This will convert the PGpath into the LinkedList of SokgraphPoints.
     * For more information about PGPath, see: {@link http://jdbc.postgresql.org/documentation/publicapi/org/postgresql/geometric/PGpath.html}
     * @param path A special data type from the PostgreSQL JDBC. This represents the sokgraph path.
     */
	private void setCoordinates(PGpath path) {
	    LinkedList<SokgraphPoint> newCoords = new LinkedList<SokgraphPoint>();
	    //Make sure the path isn't null
	    if(path != null){
    	    PGpoint[] pointsPath = path.points;
    	    
    	    for(PGpoint point: pointsPath){
    	        //create and add a new sokgraph point
    	        newCoords.add(new SokgraphPoint(point));
    	    }
	    }
	    this.setCoordinates(newCoords);
    }
	/**
	 * 
	 * @param coords
	 */
	private void setCoordinates(LinkedList<SokgraphPoint> coords){
	    this.coordinates = coords;
	}
    /**
     * @param username the username to set
     */
    private void setUsername(String username) {
        this.username = username;
    }
    /**
     * 
     * @param w
     */
    private void setWord(String w){
        this.word = w;
    }
    /**
     * @param keyboardLayout the keyboardLayout to set
     */
    private void setKeyboardLayout(String[] keyboardLayout) {
        this.keyboardLayout = keyboardLayout;
    }
    /**
     * @param keyboardID the keyboardID to set
     */
    private void setKeyboardID(String keyboardID) {
        this.keyboardID = keyboardID;
    }   

	//Public get methods
    
	public double getEndPointX() {
		return this.coordinates.getLast().getX();
	}
	public double getEndPointY(){
		return this.coordinates.getLast().getY();
	}
	
	public double getStartPointX() {
	    return this.coordinates.getFirst().getX();
	}
	public double getStartPointY(){
	    return this.coordinates.getFirst().getY();
	}
    /**
     * @return the keyboardID
     */
    public String getKeyboardID() {
        return keyboardID;
    }
	
	/**
     * @return the word
     */
    public String getWord() {
        return word;
    }
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
    /**
     * @return the keyboardLayout
     */
    public String[] getKeyboardLayout() {
        return keyboardLayout;
    }


    /**
     * @return returns a string represents the x and y coordinates of every point on the sokgraph path
     */
    public String toStringPath(){
	    
	    String start = "[";
	    String end = "]";
	    
	    StringBuilder path = new StringBuilder();
	    path.append(start);
	    
	    for(Point p: this.coordinates){
	        if(path.length() != start.length()){
	            path.append(",");
	        }
	        path.append(p.toString());
	    }
	    
	    path.append(end);
        return path.toString();
	    
	}

}

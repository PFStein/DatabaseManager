package channel;

import java.awt.Point;
import java.util.LinkedList;

import org.postgresql.geometric.PGpath;
import org.postgresql.geometric.PGpoint;
/**
 * @author Patrick Stein
 *
 */

public class Sokgraph {
    
	private LinkedList<SokgraphPoint> coordinates;
	private String[] keyboardLayout;
	private String username;
	private String keyboardID;
	private String word;
	private Point startPoint;
	private Point endPoint;
	
	public Sokgraph(LinkedList<SokgraphPoint> coords,String word, String un,String keyboardID,String[] keyLayout){
	    this.setCoordinates(coords);
		this.setStartPoint(this.coordinates.getFirst());
		this.setEndPoint(this.coordinates.getLast());
		this.setUsername(un);
		this.setWord(word);
		this.setKeyboardLayout(keyLayout);
		this.setKeyboardID(keyboardID);
	}
    public Sokgraph(PGpath path,String word, String un,String keyboardID,String[] keyLayout){
        this.setCoordinates(path);
        this.setStartPoint(this.coordinates.getFirst());
        this.setEndPoint(this.coordinates.getLast());
        this.setUsername(un);
        this.setWord(word);
        this.setKeyboardLayout(keyLayout);
        this.setKeyboardID(keyboardID);
    }
	
	//Private set methods
	private void setCoordinates(PGpath path) {
	    LinkedList<SokgraphPoint> newCoords = new LinkedList<SokgraphPoint>();
	    if(path != null){
    	    PGpoint[] pointsPath = path.points;
    	    
    	    for(PGpoint point: pointsPath){
    	        new SokgraphPoint(point);
    	        newCoords.add(new SokgraphPoint(point));
    	    }
	    }
	    this.setCoordinates(newCoords);
    }
    private void setStartPoint(Point sp){
	    this.startPoint = sp;
	}
	private void setEndPoint(Point ep){
	    this.endPoint = ep;
	}
	private void setCoordinates(LinkedList<SokgraphPoint> coords){
	    this.coordinates = coords;
	}
    /**
     * @param username the username to set
     */
    private void setUsername(String username) {
        this.username = username;
    }
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
		return this.endPoint.getX();
	}
	public double getEndPointY(){
		return this.endPoint.getY();
	}
	
	public double getStartPointX() {
		return this.startPoint.getX();
	}
	public double getStartPointY(){
		return this.startPoint.getY();
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
        // TODO unsafe, change to not return reference
        return keyboardLayout;
    }


    public String toString(){
	    
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

package channel;
/**
 * 
 * @author Patrick Stein
 * Interface that specifies the behaviors that a Sokgraph should accomplish.
 *
 *
 */

public interface Sokgraph {
    
    /**
     * Gets the x coordinate of the end point of the sokgraph
     * @return returns a double representing the x coordinate of the end point of the sokgraph
     */
    public double getEndPointX();
    /**
     * Gets the x coordinate of the end point of the sokgraph
     * @return returns a double representing the x coordinate of the end point of the sokgraph
     */
    public double getEndPointY();
    /**
     * Gets the x coordinate of the start point of the sokgraph
     * @return returns a double representing the x coordinate of the start point of the sokgraph
     */
    public double getStartPointX();
    /**
     * Gets the y coordinate of the start point of the sokgraph
     * @return returns a double representing the y coordinate of the start point of the sokgraph
     */
    public double getStartPointY();
    /**
     * Returns the keyboardID of the keyboard layout. For instance, the typical QWERTY keyboard layout has an ID of "QWERTY"
     * @return returns a string representing the keyboardID for a given layout.
     */
    public String getKeyboardID();
    
    /**
     * Returns the word that that is represented by this sokgraph. 
     * @return returns a string that represents the word that this sokgraph represents on the given keyboard layout.
     */
    public String getWord();
    
    /**
     * Returns the username of the person that input this sokgraph.
     * @return a string containing the username of the person who created the sokgraph
     */
    public String getUsername();
    
    /**
     * Provides an array of strings that correspond to the keyboard layout.
     * @return an array of strings that represent the order and what keys were used on the keyboard that this sokgraph was created on.
     */
    public String[] getKeyboardLayout();
    
    /**
     * Takes the Sokgraph points and creates a string representing each coordinate in the sokgraph.
     * @return a string representation of the sokgraph coordinates
     */
    public String toStringPath();

}

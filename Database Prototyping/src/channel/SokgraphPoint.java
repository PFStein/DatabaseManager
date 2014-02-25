/**
 * 
 */
package channel;

import java.awt.Point;

import org.postgresql.geometric.PGpoint;

/**
 * @author Patrick Stein
 *
 */
public class SokgraphPoint extends Point{
    
    public SokgraphPoint(){
        super();
        
    }
    public SokgraphPoint(int x, int y){
        super(x,y);
    }
    public SokgraphPoint(Point p){
        super(p);
    }

    public SokgraphPoint(double d, double e) {
        // TODO Auto-generated constructor stub
        this.setLocation(d, e);
    }
    public SokgraphPoint(PGpoint point) {
        // TODO Auto-generated constructor stub
        this.setLocation(point.x,point.y);
    }
    public String toString(){
        return String.format("(%f,%f)", this.getX(),this.getY());
    }
}

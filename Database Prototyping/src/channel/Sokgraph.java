package channel;

import java.awt.Point;
import java.util.LinkedList;

public class Sokgraph {
	private LinkedList<Point> coordinates;
	private Point startPoint;
	private Point endPoint;
	
	public Sokgraph(Point sp, Point ep){
		this.startPoint = sp;
		this.endPoint = ep;
		
	}

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
	
	

}

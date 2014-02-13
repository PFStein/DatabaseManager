package database.types;

import channel.Sokgraph;

public class SokgraphSpecificationByStartAndEndPoints implements SqlSpecification{

	private Sokgraph sokgraph;
	private double threshold;
	
	public SokgraphSpecificationByStartAndEndPoints(Sokgraph s, double t){
		super();
		this.sokgraph = s;
		this.threshold = t;
	}
	
	@Override
	public String toSqlClauses() {
		
		double sx = this.sokgraph.getEndPointX();
		double sy = this.sokgraph.getEndPointY();
		
		double ex = this.sokgraph.getEndPointX();
		double ey = this.sokgraph.getEndPointY();
		
		
		return String.format("((manhattan(%s,%s,startPoint) + (manhattan(%s,%s,endPoint)) <= %s",sx,sy,ex,ey,getThreshold());
		
		
	}
	
	private double getThreshold(){
		return threshold;
		
	}
	
	

}

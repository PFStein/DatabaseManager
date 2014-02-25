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
		
		double sx = this.sokgraph.getStartPointX();
		double sy = this.sokgraph.getStartPointY();
		
		double ex = this.sokgraph.getEndPointX();
		double ey = this.sokgraph.getEndPointY();
		
		String cond = String.format("keyboardid = '%s' AND ",this.getSokgraph().getKeyboardID());
		
		String secCond = String.format("manhattan(%f,%f,startPoint) <= %f AND manhattan(%f,%f,endPoint) <= %f",sx,sy,getThreshold(),ex,ey,getThreshold());
		
		return cond + secCond;
		
		
	}
	
	private double getThreshold(){
		return threshold;
		
	}
	private Sokgraph getSokgraph(){
	    return this.sokgraph;
	}

}

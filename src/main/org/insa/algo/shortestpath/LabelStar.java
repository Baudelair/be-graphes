package org.insa.algo.shortestpath;

import org.insa.graph.Node;
import org.insa.graph.Point;

public class LabelStar extends Label implements Comparable<Label> {
	
	private double coutsupp;
	
	
	public LabelStar (ShortestPathData data){
		super();
		if (data.getMode() == org.insa.algo.shortestpath.ShortestPathData.Mode.LENGTH){
			this.coutsupp = Point.distance(data.getDestination().getPoint(), this.getNode().getPoint());
		}
		else{
			this.coutsupp = Point.distance(data.getDestination().getPoint(), this.getNode().getPoint())/data.getGraph().getGraphInformation().getMaximumSpeed();
		}
	}
	public LabelStar(Node node, ShortestPathData data) {
		super(node);
		if (data.getMode() == org.insa.algo.shortestpath.ShortestPathData.Mode.LENGTH){
			this.coutsupp = Point.distance(data.getDestination().getPoint(), this.getNode().getPoint());
		}
		else{
			this.coutsupp = Point.distance(data.getDestination().getPoint(), this.getNode().getPoint())/data.getGraph().getGraphInformation().getMaximumSpeed();
		}
	}
	public LabelStar(Node node, double cost, ShortestPathData data) {
		super(node,cost);
		if (data.getMode() == org.insa.algo.shortestpath.ShortestPathData.Mode.LENGTH){
			this.coutsupp = Point.distance(data.getDestination().getPoint(), this.getNode().getPoint());
		}
		else{
			this.coutsupp = Point.distance(data.getDestination().getPoint(), this.getNode().getPoint())/data.getGraph().getGraphInformation().getMaximumSpeed();
		}
	}
	public double getCoutSupp(){
		return this.coutsupp;
	}
		
	public double getTotalCost() {
		return this.getCost() + coutsupp;
	}	
	public int compareTo(LabelStar o) {
		int a;
		if (this.getTotalCost()>o.getTotalCost()) {
			a=1;
		}
		else if (this.getTotalCost() == o.getTotalCost()) {
			if (this.getCoutSupp()>o.getCoutSupp()){
				a=1;
			}
			else if (this.getCoutSupp()==o.getCoutSupp()){
				a=0;
			}
			else{
				a=-1;
			}
		}
		else {
			a=-1;
		}
		// TODO Auto-generated method stub
		return a;
	}
}
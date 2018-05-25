package org.insa.algo.shortestpath;

import org.insa.graph.Node;
import org.insa.graph.Point;

public class LabelStar extends Label implements Comparable<Label> {
	
	private Node dest;
	
	
	public LabelStar (Node destination){
		super();
		this.dest = destination;
	}
	public LabelStar(Node node, Node destination) {
		super(node);
		this.dest = destination;
	}
	
	public LabelStar(Node node, double cost, Node destination) {
		super(node,cost);
		this.dest = destination;
	}
	
		
	public double getTotalCost() {
		return this.getCost() + Point.distance(this.getNode().getPoint(),this.dest.getPoint());
	}	
}
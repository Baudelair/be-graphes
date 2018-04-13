
package org.insa.algo.shortestpath;
import org.insa.algo.AbstractAlgorithm;
import org.insa.graph.*;


public class Label implements Comparable<Label> {
	private double cost;
	private Node father;
	private int mark;
	private Node node;
	
	public Label(Node node, Node father) {
		this.node = node;
		this.father = father;
		this.cost = Double.POSITIVE_INFINITY; //a changer
		this.mark = 0;
	}
	
	public getNode() {
		
	}
	
	public getFather() {
		
	}

	public getCost() {
		
	}
	public getMark() {
		
	}
	public setCost() {
		
	}
	public setMark() {
		
	}

}
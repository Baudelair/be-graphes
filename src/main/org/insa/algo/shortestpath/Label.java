
package org.insa.algo.shortestpath;
import org.insa.algo.AbstractAlgorithm;
import org.insa.graph.*;


public class Label implements Comparable<Label> {
	private double cost;
	private Node father;
	private int mark;
	private Node node;
	
	public Label(Node node, double cost) {
		this.node = node;
		this.father = null;
		this.cost = cost; //a changer
		this.mark = 0; // 0 => non marqué 	1 => grisé	 2 => complété
	}
	
	public Node getNode() {
		return this.node;
	}
	
	public Node getFather() {
		return this.father;
	}

	public double getCost() {
		return this.cost;
	}
	public int getMark() {
		return this. mark;
	}
	public void setCost(double cout) {
		this.cost = cout;
	}
	public void setMark(int mark) {
		this.mark = mark;
	}
	
	public void setFather(Node father) {
		this.father = father;
	}

}
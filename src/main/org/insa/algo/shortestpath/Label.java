
package org.insa.algo.shortestpath;
import org.insa.algo.AbstractAlgorithm;
import org.insa.algo.AbstractInputData;
import org.insa.graph.*;


public class Label implements Comparable<Label> {
	private double cost;
	private Label father;
	private int mark;
	private Node node;
	
	public Label (){
		this.node = null;
		this.father = null;
		this.cost = Double.POSITIVE_INFINITY; //a changer
		this.mark = 0; // 0 => non marqué 	1 => grisé
	}
	public Label(Node node) {
		this.node = node;
		this.father = null;
		this.cost = Double.POSITIVE_INFINITY; //a changer
		this.mark = 0; // 0 => non marqué 	1 => grisé	 2 => complété
	}
	
	public Label(Node node, double cost) {
		this.node = node;
		this.father = null;
		this.cost = cost; //a changer
		this.mark = 0; // 0 => non marqué 	1 => grisé	 2 => complété
	}
	
	public Node getNode() {
		return this.node;
	}
	
	public Label getFather() {
		return this.father;
	}

	public double getCost() {
		return this.cost;
	}
	public double getTotalCost() {
		return this.cost ;
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
	
	public void setFather(Label father) {
		this.father = father;
	}
	@Override
	public int compareTo(Label o) {
		int a;
		if (this.getTotalCost()>o.getTotalCost()) {
			a=1;
		}
		else if (this.getTotalCost() == o.getTotalCost()) {
			a=0;
		}
		else {
			a=-1;
		}
		// TODO Auto-generated method stub
		return a;
	}

}
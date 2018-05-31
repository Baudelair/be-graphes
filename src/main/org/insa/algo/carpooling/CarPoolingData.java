package org.insa.algo.carpooling;

import org.insa.algo.AbstractInputData;
import org.insa.algo.ArcInspector;
import org.insa.graph.Graph;
import org.insa.graph.Node;

public class CarPoolingData extends AbstractInputData {

	
	private Node A;
	private Node B;
	private Node D;
    protected CarPoolingData(Graph graph, ArcInspector arcFilter) {
        super(graph, arcFilter);
    }
    public CarPoolingData(Graph graph, Node n1, Node n2, Node n3, ArcInspector arcFilter) {
        super(graph, arcFilter);
        this.A = n1;
        this.B = n2;
        this.D = n3;
    }

    public Node getA(){
    	return this.A;
    }
    public Node getB(){
    	return this.B;
    }
    public Node getD(){
    	return this.D;
    }
}

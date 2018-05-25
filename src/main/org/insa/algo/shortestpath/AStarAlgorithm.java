package org.insa.algo.shortestpath;

import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Graph;
import org.insa.graph.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {



	public AStarAlgorithm(ShortestPathData data) {
		super(data);
	}
	
	
    protected Label[] init(Graph graph, ShortestPathData data,BinaryHeap<Label> tas) {
    	Label[] labels = new Label[graph.size()];
    	//INITIALISATION
        for (Node n : graph) {
         	if (n.equals(data.getOrigin())) {
         		Label templ = new LabelStar(n,0,data.getDestination());
            		labels[n.getId()]=templ; 
            		tas.insert(templ);
            		
            		
         	}
         	else {
         		labels[n.getId()]=new LabelStar(n,data.getDestination()); 
         		//notifyNodeMarked(n);
         	}
         }
        return labels;
    }

	
}

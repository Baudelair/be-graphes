package org.insa.algo.shortestpath;

import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Graph;
import org.insa.graph.Node;

public class AStarAlgorithm extends DijkstraAlgorithm {



	public AStarAlgorithm(ShortestPathData data) {
		super(data);
	}
	
	
    protected Label CreateLabel (Node node, ShortestPathData data){
    	return new LabelStar(node,data);
    }
    protected Label CreateLabelwCost (Node node, ShortestPathData data, float cost){
    	return new LabelStar(node, cost,data);
    }
	
}

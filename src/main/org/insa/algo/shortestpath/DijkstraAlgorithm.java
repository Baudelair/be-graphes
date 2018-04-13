package org.insa.algo.shortestpath;

import java.util.ArrayList;
import org.insa.algo.AbstractAlgorithm;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	
	BinaryHeap labels = new BinaryHeap();
	
	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graph = data.getGraph();
        // TODO:

        for (Node n : graph) {
        	
        	
        }
        return solution;
    }

}

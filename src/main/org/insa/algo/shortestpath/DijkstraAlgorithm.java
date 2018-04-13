package org.insa.algo.shortestpath;

import java.util.ArrayList;
import org.insa.algo.AbstractAlgorithm;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	
	

	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
    	BinaryHeap<Label> tas = new BinaryHeap();
    	ArrayList<Label> labels = new ArrayList<Label>();
    	
    	Label temp  = new Label();//RAJOUTER CONSTRUCTEUR VIDE AVEC NODE A NULL
    	
        ShortestPathData data = getInputData();
        ShortestPathSolution solution = null;
        Graph graph = data.getGraph();
        // TODO:

        
        //INITIALISATION
        for (Node n : graph) {
        	if (n.equals(data.getOrigin())) {
           		labels.add(new Label(n,0)); 
           		tas.insert(new Label(n,0));
        	}
        	else {
        		labels.add(new Label(n));        		
        	}
        }
        for(int i=0;i<NOMBRENOEUDS,i++){
        	//SUITE
        }
        //LE RECONFORT
        return solution;
    }

}

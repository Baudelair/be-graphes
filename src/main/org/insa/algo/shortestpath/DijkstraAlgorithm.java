package org.insa.algo.shortestpath;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.insa.algo.AbstractAlgorithm;
import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.ElementNotFoundException;
import org.insa.graph.*;




public class DijkstraAlgorithm extends ShortestPathAlgorithm {

	
    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
 protected ShortestPathSolution doRun() {
    	
    	BinaryHeap<Label> tas = new BinaryHeap<Label>();

		//System.out.println("depart");    	
    	ShortestPathData data = getInputData(); // DATA
    	ShortestPathSolution solution = null;	// SOLUTION
    	Graph graph = data.getGraph();

    	int i = 0;
    	int trouve = 0;
    	Label[] labels = new Label[graph.size()];
    	labels[data.getOrigin().getId()] = CreateLabelwCost(data.getOrigin(), data, 0);
    	tas.insert(labels[data.getOrigin().getId()]);

      //ITERATION
    	
        while (i<graph.size() && trouve ==0 && tas.isEmpty() ==false) { 

        	Label x =tas.deleteMin();
    		x.setMark(1);
        	notifyNodeMarked(x.getNode());
        		
        	for (Arc a : x.getNode()) { //Parcourt les successeurs (arcs)
        		if(data.isAllowed(a)){
					Node n2 = a.getDestination();
    				if (labels[n2.getId()]==null){
    					labels[n2.getId()]=CreateLabel(n2,data);
    					//System.out.println("AJOUT LABEL");
    				}
					Label l=labels[n2.getId()];
    				if (l.getMark()!=1 && (l.getCost()>x.getCost()+data.getCost(a))){// SI successeur pas marque, et cout chemin + cout x meilleur
    					l.setCost(x.getCost()+data.getCost(a));
    					l.setFather(x);
    					if (tas.existe(l)==-1){
    						tas.insert(l);	        						
    					}
    				}
        		}
        	}
        	if (x.getNode() == data.getDestination()) {
        		trouve = 1;
        	}
        	i++;
        }
        
        //CONSTRUCTION SOLUTION
        if (trouve ==1){
	        ArrayList<Node> nodes = new ArrayList<Node>();
	        Label temp = null;
	        //for (Label l : labels){
	        	//if (l.getNode()==data.getDestination()){
	        		Label l = labels[data.getDestination().getId()];
	        		nodes.add(l.getNode());
	        		temp = l.getFather();
	        	//}
	        //}
	        if (temp !=null){
		    	while (temp.getNode() != data.getOrigin()){
		    		nodes.add(temp.getNode());
		    		temp=temp.getFather();
		    	}
		    	nodes.add(temp.getNode());
	        }
	
	    	Collections.reverse(nodes);
	    	Path path = Path.createShortestPathFromNodes(graph, nodes);
	    	//System.out.println("Taille chemin :" + path.size());
	    	//System.out.println("iterations necessaires" + i);
	    	org.insa.algo.AbstractSolution.Status status = org.insa.algo.AbstractSolution.Status.OPTIMAL;
	    	solution = new ShortestPathSolution(data,status , path);
        }
        else{
        	org.insa.algo.AbstractSolution.Status status = org.insa.algo.AbstractSolution.Status.INFEASIBLE;
        	solution =  new ShortestPathSolution(data,status,null);
        }
    	
        

     //apres les for(), LE RECONFORT
        return solution;
    }
    
    protected Label CreateLabel (Node node, ShortestPathData data){
    	return new Label(node);
    }
    protected Label CreateLabelwCost (Node node, ShortestPathData data, float cost){
    	return new Label(node, cost);
    }
}

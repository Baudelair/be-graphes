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
    	
    	
    	
    	// A OPTIMISER AVEC UNE MAP OU UN TABLEAU
    	//V2
    	BinaryHeap<Label> tas = new BinaryHeap<Label>();

    	
    	ShortestPathData data = getInputData(); // DATA
    	ShortestPathSolution solution = null;	// SOLUTION
    	Graph graph = data.getGraph();
    	//Label templ;
    	int i = 0;
    	int trouve = 0;
    	//Label[] labels = new Label[graph.size()];
    	//HashMap<Node,Label> map = new  HashMap<Node,Label>(graph.size());
    	
    	Label[] labels = init(graph,data,tas);
    	

    	
      //ITERATION
    	
        while (i<graph.size() && trouve ==0 && tas.isEmpty() ==false) { // a changer, verifier si tous marqués
        	//System.out.println("iteration :" + i);
        	//System.out.println("total :" + graph.size());
        	Label x =tas.deleteMin();
        	//System.out.println("Retrait d'un element, Taille tas :" + tas.size());
        	//System.out.println("test");
    		x.setMark(1);
    		System.out.println("cout de l'element marqué:" + x.getCost());
        	notifyNodeMarked(x.getNode());
        		
        	for (Arc a : x.getNode()) { //Parcourt les successeurs (arcs)
        		if(data.isAllowed(a)){
	        		
        					Label l=labels[a.getDestination().getId()];
	        				if (l.getMark()!=1 && (l.getCost()>x.getCost()+a.getLength())){// SI successeur pas marque, et cout chemin + cout x meilleur
	        					l.setCost(x.getCost()+a.getLength());
	        					l.setFather(x);
	        					if (tas.existe(l)==-1){
	        						tas.insert(l);
	        						//System.out.println("Rajout d'un element, Taille tas :" + tas.size());
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
	    	System.out.println("Taille chemin :" + path.size());
	    	System.out.println("iterations necessaires" + i);
	    	org.insa.algo.AbstractSolution.Status status = org.insa.algo.AbstractSolution.Status.OPTIMAL;
	    	solution = new ShortestPathSolution(data,status , path);
        }
        else{
        	org.insa.algo.AbstractSolution.Status status = org.insa.algo.AbstractSolution.Status.OPTIMAL;
        	solution =  new ShortestPathSolution(data,status,null);
        }
    	
        

     //LE RECONFORT
        return solution;
    }
    
    protected Label[] init(Graph graph, ShortestPathData data,BinaryHeap<Label> tas) {
    	Label[] labels = new Label[graph.size()];
    	Label templ;
    	//INITIALISATION
        for (Node n : graph) {
         	if (n.equals(data.getOrigin())) {
         		templ = new Label(n,0);
            		labels[n.getId()]=templ; 
            		tas.insert(templ);
            		
            		
         	}
         	else {
         		labels[n.getId()]=new Label(n); 
         		//notifyNodeMarked(n);
         	}
         }
        return labels;
    }

}

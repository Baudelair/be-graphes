package org.insa.algo.shortestpath;

import java.util.ArrayList;
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
    	
    	//V2
    	BinaryHeap<Label> tas = new BinaryHeap<Label>();
    	ArrayList<Label> labels = new ArrayList<Label>();
    	
    	ShortestPathData data = getInputData(); // DATA
    	ShortestPathSolution solution = null;	// SOLUTION
    	Graph graph = data.getGraph();
    	Label templ;
    	//INITIALISATION
        for (Node n : graph) {
        	if (n.equals(data.getOrigin())) {
        		templ = new Label(n,0);
           		labels.add(templ); 
           		tas.insert(templ);
           		
        	}
        	else {
        		labels.add(new Label(n)); 
        		//notifyNodeMarked(n);
        	}
        }
    	
      //ITERATION
    	
        for(int i=0;i<graph.size();i++){ // a changer, verifier si tous marqués
        	System.out.println("iteration :" + i);
        	System.out.println("total :" + graph.size());
        	Label x =tas.deleteMin();
        	System.out.println("Retrait d'un element, Taille tas :" + tas.size());
        	System.out.println("test");
        	for (Label l : labels) {
        		if (l.getNode().equals(x.getNode())) {
        			l.setMark(1);
        			notifyNodeMarked(l.getNode());
        		}
        	}
        	for (Arc a : x.getNode()) { //Parcourt les successeurs (arcs)
        		for (Label l : labels){	//Parcourt les labels pour retrouver label du successeur
        			if (l.getNode()==a.getDestination()) {//dans ce if le l est le bon label
        				if (l.getMark()!=1 && (l.getCost()>x.getCost()+a.getLength())){// SI successeur pas marque, et cout chemin + cout x meilleur
        					l.setCost(x.getCost()+a.getLength());
        					l.setFather(x);
        					if (tas.existe(l)==-1){
        						tas.insert(l);
        						System.out.println("Rajout d'un element, Taille tas :" + tas.size());
        					}
        				}
        			}
        		}
        	}
        }
        
        //CONSTRUCTION SOLUTION
        ArrayList<Node> nodes = new ArrayList<Node>();
        Label temp = null;
        for (Label l : labels){
        	if (l.getNode()==data.getDestination()){
        		nodes.add(l.getNode());
        		temp = l.getFather();
        	}
        }
    	while (temp.getNode() != data.getOrigin()){
    		nodes.add(temp.getNode());
    		temp=temp.getFather();
    	}
    	nodes.add(temp.getNode());
    	Path path = Path.createShortestPathFromNodes(graph, nodes);
    	org.insa.algo.AbstractSolution.Status status = org.insa.algo.AbstractSolution.Status.OPTIMAL;
    	solution = new ShortestPathSolution(data,status , path);
    	
    /*	BinaryHeap<Label> tas = new BinaryHeap<Label>();
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
        		notifyNodeReached(n);
        	}
        }
        //ITERATION
        for(int i=0;i<graph.size();i++){ // a changer, verifier si tous marqués
        	Label x =tas.deleteMin();
        	
        	for (Label l : labels) {
        		if (l.getNode().equals(x.getNode())) {
        			l.setMark(1);
        			notifyNodeMarked(l.getNode());
        		}
        	}

        	Label temp2 = null;
        	for (Arc a : x.getNode()) { //Parcourt les successeurs (arcs)
        		Iterator<Label> it = labels.iterator();
        		while (it.hasNext()) { //parcourt les labels pour trouver le label du successeur
        			temp = it.next();
        			if (temp.getNode()==a.getDestination()) {//dans ce if le temp est le bon label
        				if (temp.getMark()==0) {
        					if (temp.getCost()>x.getCost()+a.getLength()) {
        						
        						//temp2.setCost(x.getCost()+a.getLength());
        			        	for (Label l : labels) {
        			        		if (l.getNode().equals(temp.getNode())) {
        			        			l.setCost(x.getCost()+a.getLength());
        			        			l.setFather(x.getFather());
        			        			temp2=l;
        			        		}
        			        	}
        						try {
        						tas.remove(temp);
        						} catch(ElementNotFoundException e) {        							
        						}
        						tas.insert(temp2);

        						}
        					}
        				}
        			}
        		System.out.println("a");
        		}
        	
        	}
        ArrayList<Node> sol = new ArrayList<Node>();
        for (Label l : labels) {
    		if (l.getNode().equals(data.getDestination())) {
    			sol.add(data.getDestination());
    		}
    	}
        
        while (x.)
    */    //LE RECONFORT
        return solution;
    }

}

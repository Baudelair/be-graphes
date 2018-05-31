package org.insa.algo.carpooling;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.Label;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;

public class CarPoolingDT extends CarPoolingAlgorithm {
	

    public CarPoolingDT(CarPoolingData data) {
        super(data);
    }
    
    public CarPoolingData getInputData() {
        return (CarPoolingData) super.getInputData();
    }
    
    protected CarPoolingSolution doRun() {
    	
    	BinaryHeap<Label> tas1 = new BinaryHeap<Label>(); 
    	BinaryHeap<Label> tas2 = new BinaryHeap<Label>();
    	BinaryHeap<Label> tas3 = new BinaryHeap<Label>();
    	CarPoolingData data = getInputData(); // DATA
    	Graph graph = data.getGraph();
    	//Label templ;
 
    	//int trouve = 0;
    	   	
    	Label[] labelsAC = new Label[graph.size()];
    	Label[] labelsBC = new Label[graph.size()];
    	Label[] labelsDC = new Label[graph.size()];
    	Label templ;
    	//INITIALISATION
        for (Node n : graph) {
         	if (n.equals(data.getA())) {
         		templ = new Label(n,0);
            	labelsAC[n.getId()]=templ; 
         		labelsBC[n.getId()]=new Label(n); 
         		labelsDC[n.getId()]=new Label(n);
            	tas1.insert(templ);    		
         	}
         	if (n.equals(data.getB())) {
         		templ = new Label(n,0);
         		labelsAC[n.getId()]=new Label(n); 
         		labelsBC[n.getId()]=templ; 
         		labelsDC[n.getId()]=new Label(n); 
            	tas2.insert(templ);    		
         	}
         	if (n.equals(data.getD())) {
         		templ = new Label(n,0);
         		labelsAC[n.getId()]=new Label(n); 
         		labelsBC[n.getId()]=new Label(n); 
         		labelsDC[n.getId()]=templ; 
            	tas3.insert(templ);    		
         	}
         	else {
         		labelsAC[n.getId()]=new Label(n); 
         		labelsBC[n.getId()]=new Label(n); 
         		labelsDC[n.getId()]=new Label(n); 
         		//notifyNodeMarked(n);
         	}
         }
        System.out.println("fin init");
        //ITERATION






        		
        		//PART 1
        	int i = 0;
	        while (i<graph.size() /*&& trouve ==0*/ && tas1.isEmpty() ==false) { // a changer, verifier si tous marqués
	        	//System.out.println("iteration :" + i);
	        	//System.out.println("total :" + graph.size());
	        	Label x =tas1.deleteMin();
	        	//System.out.println("Retrait d'un element, Taille tas1 :" + tas1.size());
	        	//System.out.println("test");
	    		x.setMark(1);
	    		//System.out.println("cout de l'element marqué:" + x.getCost());
	        	//notifyNodeMarked(x.getNode());
	        		
	        	for (Arc a : x.getNode()) { //Parcourt les successeurs (arcs)
	        		if(data.isAllowed(a)){
		        		
	        					Label l=labelsAC[a.getDestination().getId()];
		        				if (l.getMark()!=1 && (l.getCost()>x.getCost()+data.getCost(a))){// SI successeur pas marque, et cout chemin + cout x meilleur
		        					l.setCost(x.getCost()+data.getCost(a));
		        					l.setFather(x);
		        					if (tas1.existe(l)==-1){
		        						tas1.insert(l);
		        						//System.out.println("Rajout d'un element, Taille tas1 :" + tas1.size());
		        					}
		        		}
	        		}
	        	}
	        	
	        	/*if (x.getNode() == data.getDestination()) {
	        		trouve = 1;
	        	}*/
	        	i++;
	        }
       		//PART 2
        	i = 0;
	        while (i<graph.size() /*&& trouve ==0*/ && tas2.isEmpty() ==false) { // a changer, verifier si tous marqués
	        	//System.out.println("iteration :" + i);
	        	//System.out.println("total :" + graph.size());
	        	Label x =tas2.deleteMin();
	        	//System.out.println("Retrait d'un element, Taille tas2 :" + tas2.size());
	        	//System.out.println("test");
	    		x.setMark(1);
	    		//System.out.println("cout de l'element marqué:" + x.getCost());
	        	//notifyNodeMarked(x.getNode());
	        		
	        	for (Arc a : x.getNode()) { //Parcourt les successeurs (arcs)
	        		if(data.isAllowed(a)){
		        		
	        					Label l=labelsBC[a.getDestination().getId()];
		        				if (l.getMark()!=1 && (l.getCost()>x.getCost()+data.getCost(a))){// SI successeur pas marque, et cout chemin + cout x meilleur
		        					l.setCost(x.getCost()+data.getCost(a));
		        					l.setFather(x);
		        					if (tas2.existe(l)==-1){
		        						tas2.insert(l);
		        						//System.out.println("Rajout d'un element, Taille tas :" + tas.size());
		        					}
		        		}
	        		}
	        	}
	        	
	        	/*if (x.getNode() == data.getDestination()) {
	        		trouve = 1;
	        	}*/
	        	i++;
	        }
       		//PART 3
        	i = 0;
	        while (i<graph.size() /*&& trouve ==0*/ && tas3.isEmpty() ==false) { // a changer, verifier si tous marqués
	        	//System.out.println("iteration :" + i);
	        	//System.out.println("total :" + graph.size());
	        	Label x =tas3.deleteMin();
	        	//System.out.println("Retrait d'un element, Taille tas3 :" + tas3.size());
	        	//System.out.println("test");
	    		x.setMark(1);
	    		//System.out.println("cout de l'element marqué:" + x.getCost());
	        	//notifyNodeMarked(x.getNode());
	        		
	        	for (Arc a : x.getNode()) { //Parcourt les successeurs (arcs)
	        		if(data.isAllowed(a)){
		        		
	        					Label l=labelsDC[a.getDestination().getId()];
		        				if (l.getMark()!=1 && (l.getCost()>x.getCost()+data.getCost(a))){// SI successeur pas marque, et cout chemin + cout x meilleur
		        					l.setCost(x.getCost()+data.getCost(a));
		        					l.setFather(x);
		        					if (tas3.existe(l)==-1){
		        						tas3.insert(l);
		        						//System.out.println("Rajout d'un element, Taille tas :" + tas.size());
		        					}
		        		}
	        		}
	        	}
	        	
	        	/*if (x.getNode() == data.getDestination()) {
	        		trouve = 1;
	        	}*/
	        	i++;
	        }
	    
    	System.out.println("iterations finies, trouver C");
    	//TROUVER C
    	double currCost= Double.POSITIVE_INFINITY;
    	Node currNode = null;
    	double tempCost;
    	for(Node n : graph){
    		tempCost = labelsAC[n.getId()].getCost()+labelsBC[n.getId()].getCost()+labelsDC[n.getId()].getCost();
    		if (tempCost<currCost){
    			currCost = tempCost;
    			currNode = n;
    		}
    		
    	}
    	//Construction solution
    	return Resoudre(graph,data,data.getA(),data.getB(),currNode,data.getD(), data.getArcInspector());

    }
	
    protected CarPoolingSolution Resoudre (Graph graph,CarPoolingData data, Node A, Node B, Node C, Node D, ArcInspector AI){
    	CarPoolingSolution solution;
    	ShortestPathSolution sol1 = new DijkstraAlgorithm(new ShortestPathData(graph, A, C, AI)).run();
    	ShortestPathSolution sol2 = new DijkstraAlgorithm(new ShortestPathData(graph, B, C, AI)).run();
    	ShortestPathSolution sol3 = new DijkstraAlgorithm(new ShortestPathData(graph, C, D, AI)).run();
    	
    	if (sol1.getStatus() == org.insa.algo.AbstractSolution.Status.INFEASIBLE || sol2.getStatus() == org.insa.algo.AbstractSolution.Status.INFEASIBLE || sol3.getStatus() == org.insa.algo.AbstractSolution.Status.INFEASIBLE){
    		solution= new CarPoolingSolution(data, org.insa.algo.AbstractSolution.Status.INFEASIBLE, sol1.getPath(), sol2.getPath(), sol3.getPath());
    		System.out.println("solution impossible et envoyee");
    	}
    	else{
    		solution = new CarPoolingSolution(data, org.insa.algo.AbstractSolution.Status.FEASIBLE, sol1.getPath(), sol2.getPath(), sol3.getPath());
    		System.out.println("solution trouv�e et envoyee");
    		
    	}
    	return solution;
    	   	
    }
	
	
}
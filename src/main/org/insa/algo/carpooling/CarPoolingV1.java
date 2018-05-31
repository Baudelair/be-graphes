package org.insa.algo.carpooling;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import org.insa.algo.AbstractAlgorithm;
import org.insa.algo.ArcInspectorFactory;
import org.insa.algo.shortestpath.DijkstraAlgorithm;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.algo.shortestpath.ShortestPathSolution;
import org.insa.algo.utils.BinaryHeap;
import org.insa.algo.utils.ElementNotFoundException;
import org.insa.graph.*;



public class CarPoolingV1 extends CarPoolingAlgorithm {

	
    public CarPoolingV1(CarPoolingData data) {
        super(data);
    }
    
    public CarPoolingData getInputData() {
        return (CarPoolingData) super.getInputData();
    }

    @Override
    protected CarPoolingSolution doRun() {
    	CarPoolingSolution solution;
    	CarPoolingData data = getInputData(); // DATA
    	Graph graph = data.getGraph();
    	System.out.println("test : vitesse max -> " + data.getMaximumSpeed()+ " A: " + data.getA().getId());
    	Node A = data.getA();
    	Node B = data.getB();
    	Node D = data.getD();
    	Node C = B;
    	
    	
    	CarPoolingSolution currSol = Resoudre(graph,data,A,B,C,D);
    	double currCost = getCost(currSol,data);
    	System.out.println("CurrCost = " + currCost);
    	
    	Node tempC;
    	CarPoolingSolution tempSol;
    	double tempCost;
    	
    	int cont = 1;
    	int cont2;
    	int stuck = 0;
    	

    	Iterator<Arc> iter;
    	
    	while (cont == 1){
    		cont2 = 1;
    		iter = C.iterator();
    		while (cont2 == 1 && iter.hasNext()){
    			tempC = iter.next().getDestination();
    			tempSol = Resoudre(graph,data,A,B,tempC,D);
    			tempCost = getCost(tempSol,data);
    			System.out.println("tempCost = " + tempCost);
    			if (tempCost<currCost){
    				currSol = tempSol;
    				currCost = tempCost;
    				C = tempC;
    				
    				cont2=0; // on a trouve mieux, on passe a l iteration suivante
    				System.out.println("trouve meilleure solution");
    				
    			}
    			if(cont2==1 && iter.hasNext() == false){ //si on finit de parcourir successeurs sans trouver, on arrete tout et envoie solution actuelle
    				cont=0;
    			}
    			System.out.println("itération2");
    		}
    		System.out.println("itération1");
    	}
    	solution = currSol;
    	return solution;
    }
    
    
    
    
    protected CarPoolingSolution Resoudre (Graph graph,CarPoolingData data, Node A, Node B, Node C, Node D){
    	CarPoolingSolution solution;
    	ShortestPathSolution sol1 = new DijkstraAlgorithm(new ShortestPathData(graph, A, C, ArcInspectorFactory.getAllFilters().get(0))).run();
    	ShortestPathSolution sol2 = new DijkstraAlgorithm(new ShortestPathData(graph, B, C, ArcInspectorFactory.getAllFilters().get(0))).run();
    	ShortestPathSolution sol3 = new DijkstraAlgorithm(new ShortestPathData(graph, C, D, ArcInspectorFactory.getAllFilters().get(0))).run();
    	
    	if (sol1.getStatus() == org.insa.algo.AbstractSolution.Status.INFEASIBLE || sol2.getStatus() == org.insa.algo.AbstractSolution.Status.INFEASIBLE || sol3.getStatus() == org.insa.algo.AbstractSolution.Status.INFEASIBLE){
    		solution= new CarPoolingSolution(data, org.insa.algo.AbstractSolution.Status.INFEASIBLE, sol1.getPath(), sol2.getPath(), sol3.getPath());
    	}
    	else{
    		solution = new CarPoolingSolution(data, org.insa.algo.AbstractSolution.Status.FEASIBLE, sol1.getPath(), sol2.getPath(), sol3.getPath());
    		
    	}
    	return solution;
    	   	
    }
    protected double getCost(CarPoolingSolution s, CarPoolingData data){
    	return data.getCost(s.getPAC())+data.getCost(s.getPBC())+data.getCost(s.getPCD());
    }
}

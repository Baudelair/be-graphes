package org.insa.algo.carpooling;

import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;

public class CarPoolingV2 extends CarPoolingV1 {
    public CarPoolingV2(CarPoolingData data) {
        super(data);
    }
    
    
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
    		System.out.println("itération1");
    		cont2 = 1;
    		iter = C.iterator();
    		while (cont2 == 1 && iter.hasNext()){
    			System.out.println("itération2");
    			tempC = iter.next().getDestination();
    			tempSol = Resoudre(graph,data,A,B,tempC,D);
    			tempCost = getCost(tempSol,data);
    			//System.out.println("tempCost = " + tempCost);
    			if (tempCost<currCost){
    				currSol = tempSol;
    				currCost = tempCost;
    		    	System.out.println("CurrCost = " + currCost);
    				C = tempC;
    				notifyNodeMarked(C);
    				cont2=0; // on a trouve mieux, on passe a l iteration suivante
    				System.out.println("trouve meilleure solution");
    				
    			}
    			if(cont2==1 && iter.hasNext() == false){ //si on finit de parcourir successeurs sans trouver, on tente de débloquer algo
    				int compt = 0;
    				int cont3 = 1;
    				int maxcompt = 100;
    				int maxrand = (int)((currSol.getPAC().size()+currSol.getPBC().size()+currSol.getPCD().size())*0.5);
    				System.out.println(maxrand);
    				while (compt<maxcompt && cont3==1){ // on tente au hasard 10 fois
    					compt=compt+1;
    					
    					tempC = C; // on repart de C
    					int randOrdre = ThreadLocalRandom.current().nextInt(2, maxrand); // nous donne l'ordre auquel on doit chercher le successeur    					
    					System.out.println("tentative aléatoire profondeur : "+ randOrdre);
    					for (int i=0;i<randOrdre;i++){
    						if (tempC.getNumberOfSuccessors()!=0){
	    						int randSelect = ThreadLocalRandom.current().nextInt(0,tempC.getNumberOfSuccessors());
	    						Iterator<Arc> it = tempC.iterator();
	    						for (int j=0;j<randSelect-2;j++){
	    							it.next();
	    						}
	    						tempC = it.next().getDestination();
	    					}
    						else{
    							System.out.println("BLOQUE");
    						}
    					}
    					tempSol = Resoudre(graph,data,A,B,tempC,D);
    	    			tempCost = getCost(tempSol,data);
    	    			//System.out.println("tempCost = " + tempCost);
    	    			
    	    			if (tempCost<currCost){
    	    				currSol = tempSol;
    	    				currCost = tempCost;
    	    		    	System.out.println("CurrCost = " + currCost);
    	    				C = tempC;
    	    				notifyNodeMarked(C);
    	    				cont3=0; // on a trouve mieux, on passe a l iteration suivante
    	    				System.out.println("trouve meilleure solution avec aleatoire!");
    	    				
    	    			}
    	    			
    	    			if(cont3 == 1 && compt == maxcompt){
    	    				cont = 0;
    	    			}
    				}
    			}
    			
    		}
    		
    	}
    	solution = currSol;
    	return solution;
    }
    
}
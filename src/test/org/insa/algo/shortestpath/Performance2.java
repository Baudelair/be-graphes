package org.insa.algo.shortestpath;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Graph;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.junit.Test;

public class Performance2 {
	
	
	@Test
	public void TestPerformance() throws Exception {
		int nombreTests = 50;
		int filtre = 0;
		String ligne;
		
    	String mapPath = "C:\\Users\\Maxence\\workspace\\Descartes\\brazil.mapgr";
    	Graph graph;
        GraphReader lect = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapPath))));
    	graph = lect.read();
    	System.out.println("read terminé");
    	ShortestPathSolution sol;
		double tmsDebut;
		double tmsFin;
    	
    	
		PrintWriter writer = new PrintWriter("C:\\Users\\Maxence\\Desktop\\RESULTATS\\brazil50.txt");
		
		
		for (int i=0;i<nombreTests;i++){
			int randNode1 = ThreadLocalRandom.current().nextInt(0,graph.size());
			int randNode2 = ThreadLocalRandom.current().nextInt(0,graph.size());
			ShortestPathData data = new ShortestPathData(graph, graph.get(randNode1), graph.get(randNode2), ArcInspectorFactory.getAllFilters().get(filtre));
			tmsDebut = System.currentTimeMillis();
			sol = new DijkstraAlgorithm(data).doRun();
			tmsFin = System.currentTimeMillis();
	    	System.out.println("Dijkstra terminé");
			if (sol.isFeasible()){
				ligne = sol.getPath().getLength() + " " + (tmsFin - tmsDebut); 
				
				tmsDebut = System.currentTimeMillis();
				sol = new AStarAlgorithm(data).doRun();
				tmsFin = System.currentTimeMillis();
		    	System.out.println("A* terminé");
				ligne = ligne + " " + (tmsFin - tmsDebut);
				
				writer.println(ligne);
				System.out.println(ligne);
			}
			else{
				i--;
			}
			System.out.println("boucle " + i);
			
			
		}
		writer.close();
	}
	
	
	
	
}
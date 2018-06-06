package org.insa.algo.shortestpath;

import org.insa.graph.Graph;

import org.insa.graph.Node;
import org.insa.graph.RoadInformation;
import org.insa.graph.RoadInformation.RoadType;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import org.insa.algo.shortestpath.ShortestPathData;
import org.insa.graph.Arc;

import org.junit.BeforeClass;
import org.junit.Test;

import org.insa.algo.ArcInspector;
import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.io.BinaryGraphReader;
import org.insa.graph.io.GraphReader;
import org.insa.graphics.drawing.Drawing;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;


public abstract class AlgoTest{
  
    
    @Test
    public void TestScenarios() throws Exception{
    	
    	//test de longs chemins sur la carte de Haute-Garonne (de taille raisonnable pour l'algorithme de BellmanFord)
    	
    	String mapPath = "C:\\Users\\Maxence\\workspace\\Descartes\\haute-garonne.mapgr";
    	Graph graph;
        GraphReader lect = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapPath))));
    	ShortestPathSolution solDijk;
    	ShortestPathSolution solBell;
    	ShortestPathData data;
    	double tolerance = 0.02;
    	
        //On a créé un lecteur de carte, on va le faire lire la carte
        graph = lect.read();
    	
        // test d'un long trajet avec tous les filtres possibles:
        
    	for (int i = 0; i<=4;i++){
    	
	        data = new ShortestPathData(graph, graph.get(125526), graph.get(123153) ,ArcInspectorFactory.getAllFilters().get(i));
	        solDijk = Resoudre(data);
	        solBell = new BellmanFordAlgorithm(data).doRun();
	        assertEquals(solDijk.getPath().getLength(),solBell.getPath().getLength(),solDijk.getPath().getLength()*tolerance);
	        System.out.println("test reussi haute garonne avec filtre " + i);
    	}
        
    	// test d'un autre long trajet avec tous les filtres possibles:
        
    	for (int i = 0; i<=4;i++){
    	
	        data = new ShortestPathData(graph, graph.get(72785), graph.get(87470) ,ArcInspectorFactory.getAllFilters().get(i));
	        solDijk = Resoudre(data);
	        solBell = new BellmanFordAlgorithm(data).doRun();
	        assertEquals(solDijk.getPath().getLength(),solBell.getPath().getLength(),solDijk.getPath().getLength()*tolerance);
	        System.out.println("test2 reussi haute garonne avec filtre " + i);
    	}
 
    	for (int i = 0; i<=4;i++){
        	
	        data = new ShortestPathData(graph, graph.get(99623), graph.get(71956) ,ArcInspectorFactory.getAllFilters().get(i));
	        solDijk = Resoudre(data);
	        solBell = new BellmanFordAlgorithm(data).doRun();
	        assertEquals(solDijk.getPath().getLength(),solBell.getPath().getLength(),solDijk.getPath().getLength()*tolerance);
	        System.out.println("test3 reussi haute garonne avec filtre " + i);
    	}
    	
    	for (int i = 0; i<=4;i++){
        	
	        data = new ShortestPathData(graph, graph.get(72481), graph.get(46197) ,ArcInspectorFactory.getAllFilters().get(i));
	        solDijk = Resoudre(data);
	        solBell = new BellmanFordAlgorithm(data).doRun();
	        assertEquals(solDijk.getPath().getLength(),solBell.getPath().getLength(),solDijk.getPath().getLength()*tolerance);
	        System.out.println("test4 reussi haute garonne avec filtre " + i);
    	}
        
        //Verification que les trajets impossibles sont bien reportes comme tels: trajet en voiture jusqu'a une partie de l'INSA non atteignable en voiture
    	mapPath = "C:\\Users\\Maxence\\workspace\\Descartes\\insa.mapgr";
    	lect = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapPath))));
    	graph = lect.read();
        data = new ShortestPathData(graph, graph.get(1175), graph.get(69) ,ArcInspectorFactory.getAllFilters().get(1));
        solDijk = Resoudre(data);
        assertEquals(solDijk.getStatus(), org.insa.algo.AbstractSolution.Status.INFEASIBLE);
        System.out.println("test infaisabilité reussi");
    }
    
    protected abstract ShortestPathSolution Resoudre(ShortestPathData data);
}
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



public class DijkstraTest{
    // Small graph use for tests
    private static Graph graph;
    
    // List of nodes
    private static Node[] nodes;
    
    private static Arc arc1a2, arc1a3,arc2a4,arc2a5,arc2a6,arc3a1,arc3a2,arc3a6,arc5a4,arc5a3,arc5a6,arc6a5;    
    
    @BeforeClass
    public static void Init(){
    	nodes = new Node[6];
	    for (int i=0;i<6;i++) {
	    	nodes[i]= new Node(i, null) ;
	    }
	    RoadInformation vit20 = new RoadInformation(RoadType.MOTORWAY, null, true, 20, "") ;
	    arc1a2 = Node.linkNodes(nodes[0],nodes[1],7,vit20,null);
	    arc1a3 = Node.linkNodes(nodes[0],nodes[2],8,vit20,null);
	    arc2a4 = Node.linkNodes(nodes[1],nodes[3],4,vit20,null);
	    arc2a5 = Node.linkNodes(nodes[1],nodes[4],1,vit20,null);
	    arc2a6 = Node.linkNodes(nodes[1],nodes[5],5,vit20,null);
	    arc3a1 = Node.linkNodes(nodes[2],nodes[0],7,vit20,null);
	    arc3a2 = Node.linkNodes(nodes[2],nodes[1],2,vit20,null);
	    arc3a6 = Node.linkNodes(nodes[2],nodes[5],2,vit20,null);
	    arc5a4 = Node.linkNodes(nodes[4],nodes[3],2,vit20,null);
	    arc5a3 = Node.linkNodes(nodes[4],nodes[2],2,vit20,null);
	    arc5a6 = Node.linkNodes(nodes[4],nodes[5],2,vit20,null);
	    arc6a5 = Node.linkNodes(nodes[5],nodes[4],2,vit20,null);
	    
        graph = new Graph("ID", "", Arrays.asList(nodes), null);
    }
    
    @Test
    public void TestResultats(){
    	int tabDijk[][] = new int[6][6];
    	int tabBell[][] = new int[6][6];
    	ShortestPathSolution solDijk;
    	ShortestPathSolution solBell;    	
    	ShortestPathData data;
    	
    	//pas optimise car fait n² fois les algorithmes alors que n fois suffirait si les algos renvoyaient tout le tableau
    	for (int i=0;i<6;i++){
    		for (int j=0;j<6;j++){
    			if (i!=j){
    				data = new ShortestPathData(graph, nodes[i], nodes[j],ArcInspectorFactory.getAllFilters().get(0));
    				solDijk =new DijkstraAlgorithm(data).doRun();
    				solBell =new BellmanFordAlgorithm(data).doRun();
    				if (solDijk.getPath()!=null){

    					if (solDijk.getPath().size()==1){
    						tabDijk[i][j] = i;
    					}
    					else{
    						tabDijk[i][j] = solDijk.getPath().getArcs().get(solDijk.getPath().size()- 2).getOrigin().getId();
    					}
    				}
    				else{
    					tabDijk[i][j] = -2;
    				}
    				if (solBell.getPath()!=null){
    					if (solBell.getPath().size()==1){
    						tabBell[i][j] = i;
    					}
    					else{
    						tabBell[i][j] = solBell.getPath().getArcs().get(solBell.getPath().size()- 2).getOrigin().getId();
    					}
    				}
    				else{
    					tabBell[i][j] = -2;
    				}
    				
    			}
    			else{
    				tabDijk[i][j]=-1;
    				tabBell[i][j]=-1;
    			}
    			System.out.println("assertequals ligne " + (i+1) + " colonne " + (j+1));
    			assertEquals(tabDijk[i][j],tabBell[i][j]);
    		}
    	}
    	System.out.println("Dijkstra");
    	for (int i=0;i<6;i++){
    		String Aff = "x" + (i+1) + " ";
    		for (int j=0;j<6;j++){
    			Aff = Aff + "| "+ tabDijk[i][j] + " ";
    		}
    		System.out.println(Aff);
    	}
    	System.out.println("BellmanFord");
    	for (int i=0;i<6;i++){
    		String Aff = "x" + (i+1) + " ";
    		for (int j=0;j<6;j++){
    			Aff = Aff + "| "+ tabBell[i][j] + " ";
    		}
    		System.out.println(Aff);
    	}
    }
    @Test
    public void TestScenarios(){
    	
    }
}
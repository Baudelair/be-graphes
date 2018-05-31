package org.insa.algo.shortestpath;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.insa.algo.ArcInspectorFactory;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.Node;
import org.insa.graph.RoadInformation;
import org.insa.graph.RoadInformation.RoadType;
import org.junit.BeforeClass;
import org.junit.Test;

public class DijkstraTest extends AlgoTest{
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
	    	float tabDijk[][] = new float[6][6];
	    	float tabBell[][] = new float[6][6];
	    	ShortestPathSolution solDijk;
	    	ShortestPathSolution solBell;    	
	    	ShortestPathData data;
	    	
	    	
	    	for (int i=0;i<6;i++){
	    		for (int j=0;j<6;j++){
	    			if (i!=j){
	    				data = new ShortestPathData(graph, nodes[i], nodes[j],ArcInspectorFactory.getAllFilters().get(0));
	    				solDijk = Resoudre(data);
	    				solBell =new BellmanFordAlgorithm(data).doRun();
	    				if (solDijk.getPath()!=null){

	    					if (solDijk.getPath().size()==1){
	    						tabDijk[i][j] = i;
	    					}
	    					else{
	    						tabDijk[i][j] = solDijk.getPath().getLength();
	    					}
	    				}
	    				else{
	    					tabDijk[i][j] = -1;
	    				}
	    				if (solBell.getPath()!=null){
	    					if (solBell.getPath().size()==1){
	    						tabBell[i][j] = i;
	    					}
	    					else{
	    						tabBell[i][j] = solBell.getPath().getLength();
	    					}
	    				}
	    				else{
	    					tabBell[i][j] = -1;
	    				}
	    				
	    			}
	    			else{
	    				tabDijk[i][j]=0;
	    				tabBell[i][j]=0;
	    			}
	    			System.out.println("assertequals ligne " + (i+1) + " colonne " + (j+1));
	    			assertEquals(tabDijk[i][j],tabBell[i][j], 0.001);
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
	    
	    protected ShortestPathSolution Resoudre(ShortestPathData data){
	    	return new DijkstraAlgorithm(data).doRun();
	    }
}
package org.insa.graph;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.*;
import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MAX_VALUE;
import static java.lang.Double.MAX_VALUE;/**
 * Class representing a path between nodes in a graph.
 * 
 * A path is represented as a list of {@link Arc} and not a list of {@link Node}
 * due to the multigraph nature of the considered graphs.
 *
 */
public class Path {

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the fastest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     * 
     */
    public static Path createFastestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
    	
    	if (nodes.size()==0) {
    		return new Path(graph);
    	}
    	if (nodes.size()==1) {
    		return new Path(graph,nodes.get(0));
    	}	
        List<Arc> arcs = new ArrayList<Arc>(); //path final
        ListIterator<Node> itnodes = nodes.listIterator();
        Arc arctemp;
        Arc plusRap = null; //arc actuellement le plus court dans iteration arcs
        Node n;
        Node prevNode = null;
        
        if (itnodes.hasNext()){
        	prevNode= itnodes.next();
        }
       
        
        while(itnodes.hasNext()){ //iterations Nodes de nodes
        	n = itnodes.next();
        	Iterator<Arc> itarc = prevNode.iterator();
        	double duree =0;
        	boolean found = false; //si pas trouve, exception
        	 
        	while (itarc.hasNext()){ //iteration arcs successeurs de n
        		arctemp = itarc.next();
        		if (arctemp.getDestination() == n && (arctemp.getMinimumTravelTime()<duree || found == false)){
        			plusRap = arctemp;
        			duree = plusRap.getMinimumTravelTime();
        			found = true;
        			//ON TESTE SI PLUS COURT QUE LES AUTRES ET STOCKE
        			//MET found A TRUE
        		}
        		
        	}
        	if (found) {
        	arcs.add(plusRap);
        	prevNode = n;
        	}
        	else {
        		throw new IllegalArgumentException();
        	}
        }
        
        return new Path(graph, arcs);
        
       
    }

    /**
     * Create a new path that goes through the given list of nodes (in order),
     * choosing the shortest route if multiple are available.
     * 
     * @param graph Graph containing the nodes in the list.
     * @param nodes List of nodes to build the path.
     * 
     * @return A path that goes through the given list of nodes.
     * 
     * @throws IllegalArgumentException If the list of nodes is not valid, i.e. two
     *         consecutive nodes in the list are not connected in the graph.
     * 
     */ 
    public static Path createShortestPathFromNodes(Graph graph, List<Node> nodes)
            throws IllegalArgumentException {
    	
    	
    	  // Unique node case
        if (nodes.size() == 1) {
            return new Path(graph, nodes.get(0));
        }

        List<Arc> arcs = new ArrayList<Arc>();

        int i = 0;
        while (i < (nodes.size() - 1)){
            boolean connected = false;
            double fastest_time = MAX_VALUE;
            Arc arc_to_add = null;
            for (Arc arc : nodes.get(i)) {
                if (arc.getDestination().equals(nodes.get(i + 1))) {
                    connected = true;
                    double min_time = arc.getMinimumTravelTime();
                    if (min_time < fastest_time) {
                        fastest_time = min_time;
                        arc_to_add = arc;
                    }
                }
            }

            // Two consecutive nodes are not connected
            if (!connected){
                throw  new IllegalArgumentException("All nodes are not connected.");
            }
            // Add the fastest to the list
            else {
                arcs.add(arc_to_add);
            }
            i++;
        }

        return new Path(graph, arcs);
    	
    /*	if (nodes.size()==0) {
    		return new Path(graph);
    	}
    	if (nodes.size()==1) {
    		return new Path(graph,nodes.get(0));
    	}	
    	
        List<Arc> arcs = new ArrayList<Arc>(); //path final
        ListIterator<Node> itnodes = nodes.listIterator();
        Arc arctemp;
        Arc plusRap = null; //arc actuellement le plus court dans iteration arcs
        Node n;
        Node prevNode = null;
        
        if (itnodes.hasNext()){
        	prevNode= itnodes.next();
        }
       
        
        while(itnodes.hasNext()){ //iterations Nodes de nodes
        	n = itnodes.next();
        	Iterator<Arc> itarc = prevNode.iterator();
        	double longueur =0;
        	boolean found = false; //si pas trouve, exception
        	 
        	while (itarc.hasNext()){ //iteration arcs successeurs de n
        		arctemp = itarc.next();
        		if (arctemp.getDestination() == n && (arctemp.getLength()<longueur || found == false)){
        			plusRap = arctemp;
        			longueur = plusRap.getLength();
        			found = true;
        			//ON TESTE SI PLUS COURT QUE LES AUTRES ET STOCKE
        			//MET found A TRUE
        		}
        		
        	}
        	if (found) {
        	arcs.add(plusRap);
        	prevNode = n;
        	}
        	else {
        		throw new IllegalArgumentException();
        	}
        }
        
        return new Path(graph, arcs);
        */
       
    }    	
    	
    /*	if (nodes.size()==0) {
    		return new Path(graph);
    	}
    	if (nodes.size()==1) {
    		return new Path(graph,nodes.get(0));
    	}		
    	
        List<Arc> arcs = new ArrayList<Arc>(); //path final
        ListIterator<Node> itnodes = nodes.listIterator();
        Arc arctemp;
        Arc plusCourt = null; //arc actuellement le plus court dans iteration arcs
        Node n;
       
        Node prevNode = null;
        
        if (itnodes.hasNext()){
        	prevNode= itnodes.next();
        }
       
        
        while(itnodes.hasNext()){ //iterations Nodes de nodes
        	n = itnodes.next();
        	// verifier que liste arcs pas vide
        	Iterator<Arc> itarc = prevNode.iterator();
        	float longueur =0;
        	 boolean found = false; //si pas trouve, exception
        	 
        	while (itarc.hasNext()){ //iteration arcs successeurs de n
        		arctemp = itarc.next();
        		if (arctemp.getDestination() == n && (arctemp.getLength()<longueur || found == false)){
        			plusCourt = arctemp;
        			longueur = plusCourt.getLength();
        			//ON TESTE SI PLUS COURT QUE LES AUTRES ET STOCKE
        			//MET found A TRUE
        		}
        		
        	}
        	if (found) {
        	arcs.add(plusCourt);
        	prevNode = n;
        	}
        	else {
        		throw new IllegalArgumentException();
        	}
        }
        
        return new Path(graph, arcs);
    }
	*/
    /**
     * Concatenate the given paths.
     * 
     * @param paths Array of paths to concatenate.
     * 
     * @return Concatenated path.
     * 
     * @throws IllegalArgumentException if the paths cannot be concatenated (IDs of
     *         map do not match, or the end of a path is not the beginning of the
     *         next).
     */
    
        
    
    public static Path concatenate(Path... paths) throws IllegalArgumentException {
        if (paths.length == 0) {
            throw new IllegalArgumentException("Cannot concatenate an empty list of paths.");
        }
        final String mapId = paths[0].getGraph().getMapId();
        for (int i = 1; i < paths.length; ++i) {
            if (!paths[i].getGraph().getMapId().equals(mapId)) {
                throw new IllegalArgumentException(
                        "Cannot concatenate paths from different graphs.");
            }
        }
        ArrayList<Arc> arcs = new ArrayList<>();
        for (Path path: paths) {
            arcs.addAll(path.getArcs());
        }
        Path path = new Path(paths[0].getGraph(), arcs);
        if (!path.isValid()) {
            throw new IllegalArgumentException(
                    "Cannot concatenate paths that do not form a single path.");
        }
        return path;
    }

    // Graph containing this path.
    private final Graph graph;

    // Origin of the path
    private final Node origin;

    // List of arcs in this path.
    private final List<Arc> arcs;

    /**
     * Create an empty path corresponding to the given graph.
     * 
     * @param graph Graph containing the path.
     */
    public Path(Graph graph) {
        this.graph = graph;
        this.origin = null;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path containing a single node.
     * 
     * @param graph Graph containing the path.
     * @param node Single node of the path.
     */
    public Path(Graph graph, Node node) {
        this.graph = graph;
        this.origin = node;
        this.arcs = new ArrayList<>();
    }

    /**
     * Create a new path with the given list of arcs.
     * 
     * @param graph Graph containing the path.
     * @param arcs Arcs to construct the path.
     */
    public Path(Graph graph, List<Arc> arcs) {
        this.graph = graph;
        this.arcs = arcs;
        this.origin = arcs.size() > 0 ? arcs.get(0).getOrigin() : null;
    }

    /**
     * @return Graph containing the path.
     */
    public Graph getGraph() {
        return graph;
    }

    /**
     * @return First node of the path.
     */
    public Node getOrigin() {
        return origin;
    }

    /**
     * @return Last node of the path.
     */
    public Node getDestination() {
        return arcs.get(arcs.size() - 1).getDestination();
    }

    /**
     * @return List of arcs in the path.
     */
    public List<Arc> getArcs() {
        return Collections.unmodifiableList(arcs);
    }

    /**
     * Check if this path is empty (it does not contain any node).
     * 
     * @return true if this path is empty, false otherwise.
     */
    public boolean isEmpty() {
        return this.origin == null;
    }

    /**
     * Get the number of <b>nodes</b> in this path.
     * 
     * @return Number of nodes in this path.
     */
    public int size() {
        return isEmpty() ? 0 : 1 + this.arcs.size();
    }

    /**
     * Check if this path is valid.
     * 
     * A path is valid if any of the following is true:
     * <ul>
     * <li>it is empty;</li>
     * <li>it contains a single node (without arcs);</li>
     * <li>the first arc has for origin the origin of the path and, for two
     * consecutive arcs, the destination of the first one is the origin of the
     * second one.</li>
     * </ul>
     * 
     * @return true if the path is valid, false otherwise.
     * 
     * 
     */
    public boolean isValid() {
    	
    	if (arcs == null) {
    		return true;
    	}
    	
    	Node node = this.origin;
    	  	
    	for (Arc arc : this.arcs) {
            	if (arc.getOrigin() !=node) {
            		return false;
            	}
            node=arc.getDestination();
    	  	}
        return true;
    }

    /**
     * Compute the length of this path (in meters).
     * 
     * @return Total length of the path (in meters).
     * 

     */
    public float getLength() {
    	float lenght =0;
    	for (Arc arcs  : this.arcs) {
			lenght = lenght + arcs.getLength();
		}
        return lenght;
    }

    /**
     * Compute the time required to travel this path if moving at the given speed.
     * 
     * @param speed Speed to compute the travel time.
     * 
     * @return Time (in seconds) required to travel this path at the given speed (in
     *         kilometers-per-hour).
     * 

     */
    public double getTravelTime(double speed) {
        return getLength() * 3600/(speed * 1000);
    }

    /**
     * Compute the time to travel this path if moving at the maximum allowed speed
     * on every arc.
     * 
     * @return Minimum travel time to travel this path (in seconds).
     * 

     */
    public double getMinimumTravelTime() {
        double duree =0;
        for (Arc arc : this.arcs) {
        	duree = duree + arc.getMinimumTravelTime();
        	}
        return duree;
    }

}

package org.insa.algo.carpooling;

import org.insa.algo.AbstractInputData.Mode;
import org.insa.algo.AbstractSolution;
import org.insa.graph.Path;

public class CarPoolingSolution extends AbstractSolution {

	private Path pac; // pour Path A -> C
	private Path pbc;
	private Path pcd;
	
    public CarPoolingSolution(CarPoolingData data, Status status) {
        super(data, status);
    }
    
    public CarPoolingSolution(CarPoolingData data, Status status,Path p1,Path p2,Path p3){
    	super(data,status);
    	this.pac = p1;
    	this.pbc = p2;
    	this.pcd = p3; 	
    }

    public Path getPAC(){
    	return this.pac;
    }
    public Path getPBC(){
    	return this.pbc;
    }
    public Path getPCD(){
    	return this.pcd;
    }
}

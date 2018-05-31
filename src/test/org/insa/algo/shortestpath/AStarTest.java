package org.insa.algo.shortestpath;

public class AStarTest extends AlgoTest{
    protected ShortestPathSolution Resoudre(ShortestPathData data){
    	return new AStarAlgorithm(data).doRun();
    }
}
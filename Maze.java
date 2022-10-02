package Maze;

/*
 *  Author:Dixaben Patel 10456998
 *  CS 570A - Data Structure
 *  Homework Assignment #3 The Maze
 * 
 */

import java.util.*;

/**
 * Class that solves maze problems with backtracking.
 * @author Koffman and Wolfgang
 **/
public class Maze implements GridColors {

    /** The maze */
    private TwoDimGrid maze;

    public Maze(TwoDimGrid m) {
        maze = m;
    }

    /** Wrapper method. */
    public boolean findMazePath() {
    	return findMazePath(0, 0); // (0, 0) is the start point.
    }

    /**
     * Attempts to find a path through point (x, y).
     * @pre Possible path cells are in BACKGROUND color;
     *      barrier cells are in ABNORMAL color.
     * @post If a path is found, all cells on it are set to the
     *       PATH color; all cells that were visited but are
     *       not on the path are in the TEMPORARY color.
     * @param x The x-coordinate of current point
     * @param y The y-coordinate of current point
     * @return If a path through (x, y) is found, true;
     *         otherwise, false
     */
    
    
    // Problem 1 method
    
    public boolean findMazePath(int x, int y) {
        
    	// Return false, if current cell is out of grid bound.
    	 if (x < 0 || y < 0 || x >= maze.getNCols() || y >= maze.getNRows()) {
             return false;
     	}
        // Return False, if current cell cannot be part of the path
    	else if(!maze.getColor(x,y).equals(NON_BACKGROUND)) 
     	{
     		return false;
     	}	
        // return true, if current cell  exit
    	else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) {
            maze.recolor(x, y, PATH);
            return true;
     	} 
        //  if didn't find exit at current cell, set current cell to PATH
        else 
        {
            maze.recolor(x, y, PATH);

         // if the neighbour cell of current cell is exit, return true
            if (findMazePath(x - 1, y) || findMazePath(x + 1, y) || findMazePath(x, y + 1) || findMazePath(x, y - 1)) {
                return true;

                // else, recolor current cell to TEMPORARY
            } else {
                maze.recolor(x, y, TEMPORARY);
                return false;
			}
        }
    	
    } 
    

    
    // PROBLEM 2.1 Method
    public void findMazePathStackBased(int x, int y, ArrayList<ArrayList<PairInt>> result, Stack<PairInt> trace)
    {
    	// If the current cell exist, falls outside the grid 
    	if (x < 0 || y < 0 || x > maze.getNCols() - 1 || y > maze.getNRows() - 1 || (!maze.getColor(x, y).equals(NON_BACKGROUND)))
    	{
            return;
    	}
        //  If exit Pair is found,  push the current cell to trace, and result. Once visited  remove from trace and recolor this point to Non_background for Re-visiting
    
        else if (x == maze.getNCols() - 1 && y == maze.getNRows() - 1) 
        {
            trace.push(new PairInt(x, y));
            ArrayList<PairInt> curTrace = new ArrayList<>(trace);
            result.add(curTrace);
            trace.pop();
            maze.recolor(x, y, NON_BACKGROUND);
            return;
        }
    	// Implementation of  Backtracking 
        else
        {
        	trace.push(new PairInt(x, y));
            maze.recolor(x, y, PATH);
            findMazePathStackBased(x, y + 1, result, trace);
            findMazePathStackBased(x, y - 1, result, trace);
            findMazePathStackBased(x + 1, y, result, trace);
            findMazePathStackBased(x - 1, y, result, trace);
            maze.recolor(x, y, NON_BACKGROUND);
            trace.pop();
            return;
        }
    }
    
    
    
    // PROBLEM 2.2 Method
    public ArrayList < ArrayList < PairInt >> findAllMazePaths ( int x , int y) 
    {
    	ArrayList < ArrayList < PairInt >> result = new ArrayList < >();
    	Stack < PairInt > trace = new Stack < >();
    	findMazePathStackBased (0 ,0 , result , trace );
    	return result ;	
    }
    

    
    //PROBLEM 3 Method
    public ArrayList<PairInt> findMazePathMin(int x, int y) {

        int index = 0;
        int[] count;
        int min;

        ArrayList<ArrayList<PairInt>> allPaths;
        allPaths = findAllMazePaths(x, y);

        count = new int[allPaths.size()];

        for (int i = 0; i < allPaths.size(); i++) {
            count[i] = allPaths.get(i).size();
        }

        min = count[0];

        for (int i = 1; i < count.length; i++) {
            if (count[i] < min) {
                min = count[i];
                index = i;
            }
        }

        return allPaths.get(index);
    }
    

    /*<exercise chapter="5" section="6" type="programming" number="2">*/
    public void resetTemp() {
        maze.recolor(TEMPORARY, BACKGROUND);
    }
    /*</exercise>*/

    /*<exercise chapter="5" section="6" type="programming" number="3">*/
    public void restore() {
        resetTemp();
        maze.recolor(PATH, BACKGROUND);
        maze.recolor(NON_BACKGROUND, BACKGROUND);
    }
    /*</exercise>*/
}
/*</listing>*/
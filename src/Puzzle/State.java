package Puzzle;

import java.util.Arrays;


public class State implements Comparable<State>{
    public int[] array = new int[9];
    public int blankIndex;
    private int depth, h, g;

    // Anfangsknoten wird incrementiert deshalb -1
    private static int steps = -1;
    private State previous;
    
    
    public State(int[] input) {
        this.array = input;
        this.blankIndex = getIndex(input, 0);
        this.previous = null;
        this.depth = 0;
        this.g=0;
    }
    
    public State(State previous, int blankIndex) {
        this.array = Arrays.copyOf(previous.array, previous.array.length);
        this.array[previous.blankIndex] = this.array[blankIndex];
        this.array[blankIndex] = 0;
        this.blankIndex = blankIndex;
        this.depth = previous.depth + 1;
        this.g = previous.g + 1;
        this.previous = previous;
    }
    
    public static int getIndex(int[] array, int value) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == value) { 
                return i; 
            }  
        }
        return -1;
    }
    
    public boolean isSolved(State goalState) {
        for(int i = 0; i < goalState.array.length; i++) {
            if(goalState.array[i] != this.array[i]) { 
                return false;
            }
        }
        return true;
    }
    
    public String toString() {
        int[] state = this.array;
        String s = "\n\n";
        for(int i = 0; i < state.length; i++) {
            if(i % 3 == 0 && i != 0) {
                    s += "\n";
            }
            s += (state[i] != 0) ? String.format("%d ", state[i]) : "  ";
        }
        return s;
    }
    
    public int getDepth() {
        return depth;
    }

    public int getSteps() {
        return steps;
    }
    
    public void incSteps() {
        steps++;
    }
    
    public int getH()
    {
    	return h;
    }
    
    public int getF()
    {
    	return h+g;
    }
    
    public void berechneH1(State goalState) 
    {
        int counter = 0;
        for(int i = 0; i < array.length; i++) 
        {
            if(goalState.array[i] != this.array[i]) 
            {
                counter++;
            }
        }
        this.h = counter;
    }
    
    private int getArrayIndex(int[] array, int number) 
    {
        for(int i = 0; i < array.length; i++) 
        {
            if(number == array[i]) 
            {
                return i;
            }   
        }
        return -1;
    }
    
    public void berechneH2(State goalState) 
    {
        int sum = 0;
        for(int i = 1; i < array.length; i++) 
        {
            int indexState = getArrayIndex(array,i);
            int indexGoal = getArrayIndex(goalState.array,i);
            
            if(indexState != indexGoal) 
            {
                int xState = indexState % 3;
                int yState = indexState / 3;
                
                int xGoalState = indexGoal % 3;
                int yGoalState = indexGoal / 3;
                
                sum += Math.abs((xState - xGoalState)) + Math.abs((yState - yGoalState));
            }
        }
        this.h = sum;
    }
            
    
	public int compareTo(State o) 
	{
		return this.getF() - o.getF();
	}
}
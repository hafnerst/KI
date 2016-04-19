package Puzzle;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import Algo.AH1Algo;
import Algo.AH2Algo;
import Algo.BFSAlgo;
import Algo.DFSAlgo;
import Algo.IDAlgo;


public class Puzzle {
    int[] puzzleInput;
    State initialState;
    Queue<State> queue = new LinkedList<State>();
    State state;
    static State goalState;
    
    static int searchAlgo = 0;
    
    public Puzzle(int[] puzzleInput) {
        this.initialState = new State(puzzleInput);
        this.state = this.initialState;
        queue.clear();
        queue.add(this.initialState);  
    }
    
    public void solve() {
        if(searchAlgo == 1) {
            BFSAlgo bfsAlgo= new BFSAlgo();
            bfsAlgo.solvePuzzle(queue, goalState);

        } else if(searchAlgo == 2) {
            DFSAlgo dfsAlgo= new DFSAlgo();
            String returnValue = dfsAlgo.solvePuzzle(initialState, goalState);
            System.out.println(returnValue);

        } else if(searchAlgo == 3) {
        	IDAlgo idAlgo = new IDAlgo();
        	idAlgo.solvePuzzle(initialState, goalState);
        	
        } else if(searchAlgo == 4) {
        	AH1Algo ah1Algo = new AH1Algo();
        	String returnValue = ah1Algo.solvePuzzle(initialState, goalState);
        	System.out.println(returnValue);
        
        } else if(searchAlgo == 5) {
        	AH2Algo ah2Algo = new AH2Algo();
        	String returnValue = ah2Algo.solvePuzzle(initialState, goalState);
        	System.out.println(returnValue); 
        
        
        } else {
            System.out.println("falsche Eingabe");  
        }
    }
    
    public static void main(String[] args) {
        // Ausgangsknoten  
        /* 0 Ebene */
        //int[] input = {1,2,3,4,5,6,7,8,0};
        
        /* 1-Ebene */
        //int[] input = {1,2,3,4,5,6,7,0,8};
        
        /* 2-Ebene */
        //int[] input = {1,2,3,4,5,6,0,7,8};
        
        /* 3-Ebene | Uebungsaufgabe */
        //int[] input = {1,0,3,4,2,6,7,5,8};

        /* 4 Ebene */
        //int[] input = {0,1,3,4,2,5,7,8,6};
        
        /* 4-Ebene */
        //int[] input = {0,2,3,1,5,6,4,7,8};
        
        /* 7 Ebene */
        //int[] input = {4,1,2,5,8,3,7,0,6};    
        
        /* 8 Ebene */
        //int[] input = {0,5,2,1,8,3,4,7,6};
        
        /* 12-Ebene */
        //int[] input = {1,2,3,4,5,7,8,6,0}; 
        
        /* 20-Ebene */
        int[] input = {2,5,0,1,4,8,7,3,6};
            
        
        /* OutOfMemoryError: GC overhead limit exceeded */
        //int[] input = {7,2,3,4,6,5,1,8,0};
       
        // Zielknoten festlegen
        int[] arrGoalState = {1,2,3,4,5,6,7,8,0};
        goalState = new State(arrGoalState);

        Puzzle puzzle = new Puzzle(input);
        Scanner scan = new Scanner(System.in);
         
        System.out.println("for breadth-first search | type: 1");
        System.out.println("for depth-first search | type: 2");
        System.out.println("for depth-first search with iterative deepening | type: 3");
        System.out.println("for A* with h1 Function | type: 4");
        System.out.println("for A* with h2 Function | type: 5 \n");
        System.out.print("Auswahl: ");
        searchAlgo = scan.nextInt(); 
        scan.close();
        
        puzzle.solve();
    }
}
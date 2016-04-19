package Algo;

import java.util.PriorityQueue;

import Puzzle.Move;
import Puzzle.State;

public class AH1Algo 
{
	State nextState;
    PriorityQueue<State> neueKnoten = new PriorityQueue<State>();
	
	public String solvePuzzle(State curState, State goalState)
	{
		curState.berechneH1(goalState);
		neueKnoten.add(curState);
		
		while(true)
		{
			if(neueKnoten.isEmpty())
			{
				return "Keine Lösung gefunden";
			}
			
			nextState = neueKnoten.poll();
			
			System.out.println(nextState);
			System.out.println("Aktueller H-Wert: " + nextState.getH());
			
			nextState.incSteps();
			
			if(nextState.isSolved(goalState))
			{
				System.out.println("Anzahl Schritte: " + nextState.getSteps());
				return "Lösung gefunden";
			}
			
			this.addToQueue(Move.left(nextState), goalState);
			this.addToQueue(Move.right(nextState), goalState);
			this.addToQueue(Move.down(nextState), goalState);
			this.addToQueue(Move.up(nextState), goalState);
		}
		
	}
	
    private void addToQueue(State nextState, State goalState)
    {
        if(nextState != null) {
            nextState.berechneH1(goalState);
            this.neueKnoten.add(nextState);
        }
    }
}


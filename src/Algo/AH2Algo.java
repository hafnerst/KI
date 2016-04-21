package Algo;

import java.util.PriorityQueue;

import Puzzle.Move;
import Puzzle.State;

public class AH2Algo 
{
	State nextState;
	PriorityQueue<State> neueKnoten = new PriorityQueue<State>();
	
	public String solvePuzzle(State curState, State goalState)
	{
		curState.berechneH2(goalState);
		this.neueKnoten.offer(curState);
		
		while(true)
		{
			if(neueKnoten.isEmpty())
			{
				return "Keine Lösung gefunden";
			}
			
			nextState = neueKnoten.poll();
			
			nextState.incSteps();
			
			
			
			if(nextState.isSolved(goalState))
			{
				int depth = 0;
				System.out.println(nextState);
				while(nextState.getPrevious() != null)
				{
					System.out.println(nextState.getPrevious());
					nextState = nextState.getPrevious();
					depth++;
				}
				System.out.println("Tiefe: "+depth);
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
            nextState.berechneH2(goalState);
            this.neueKnoten.offer(nextState);
        }
    }
}

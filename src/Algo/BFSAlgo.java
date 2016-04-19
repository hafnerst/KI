package Algo;

import java.util.LinkedList;
import java.util.Queue;

import Puzzle.Move;
import Puzzle.State;

public class BFSAlgo {
	private Queue<State> neueKnoten;
	State curState;
	
	private void addToQueue(State nextState)
	{
		if(nextState!=null)
		{
			this.neueKnoten.add(nextState);
		}
	}
	
	public void solvePuzzle(Queue<State> queue, State goalState)
	{
		neueKnoten = new LinkedList<State>();
		while(!queue.isEmpty())
		{
			curState = queue.poll();
			System.out.println(curState.toString());
			curState.incSteps();
			if(curState.isSolved(goalState))
			{
				System.out.println("Lösung gefunden");
				System.out.println("Anzahl Schritte: " + curState.getSteps());
				return;
			}
			this.addToQueue(Move.up(curState));
			this.addToQueue(Move.down(curState));
			this.addToQueue(Move.right(curState));
			this.addToQueue(Move.left(curState));
		}
		if(neueKnoten.size() > 0)
		{
			System.out.println("Ebene Nr. "+ (curState.getDepth()+1));
			this.solvePuzzle(neueKnoten, goalState);
		}
		else 
		{
			System.out.println("Keine Lösung gefunden");
			return;
		}
	}
	
}
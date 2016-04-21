package Algo;

import java.util.LinkedList;
import java.util.Queue;

import Puzzle.Move;
import Puzzle.State;

public class DFSAlgo 
{
	private Queue<State> neueKnoten;
	
	public String solvePuzzle(State curState, State goalState)
	{
		if(curState.isSolved(goalState))
		{
			int depth = 0;
			System.out.println(curState);
			while(curState.getPrevious() != null)
			{
				System.out.println(curState.getPrevious());
				curState = curState.getPrevious();
				depth++;
			}
			System.out.println("Tiefe: "+depth);
			return "Lösung gefunden";
		}
		
		neueKnoten = new LinkedList<State>();
		this.addToQueue(Move.right(curState));
		this.addToQueue(Move.down(curState));
		this.addToQueue(Move.up(curState));
		this.addToQueue(Move.left(curState));
		
		while(neueKnoten.size() > 0)
		{
			String ergebnis = this.solvePuzzle(neueKnoten.poll(), goalState);
			if(ergebnis.equals("Lösung gefunden"))
			{
				return "Lösung gefunden";
			}
		}
		return "Keine Lösung gefunden";
	}
	
	private void addToQueue(State nextState)
	{
		if(nextState!=null)
		{
			this.neueKnoten.add(nextState);
		}
	}
}

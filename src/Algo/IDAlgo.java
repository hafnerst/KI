package Algo;

import java.util.LinkedList;

import Puzzle.Move;
import Puzzle.State;

public class IDAlgo 
{
	private State nextState;
	
	public void solvePuzzle(State curState, State goalState)
	{
		String result;
		int limit = 0;
		do
		{
			result = depthSearch(curState, goalState, 0, limit);
			limit++;
		} while(!result.equals("Lösung gefunden"));
	}
	
	private String depthSearch(State curState, State goalState, int depth, int limit)
	{
		this.nextState = curState;
		this.nextState.incSteps();
		
		if(this.nextState.isSolved(goalState))
		{
			System.out.println(nextState);
			while(nextState.getPrevious() != null)
			{
				System.out.println(nextState.getPrevious());
				nextState = nextState.getPrevious();
			}
			System.out.println("Anzahl Schritte: " + nextState.getSteps() + " Tiefe: " + depth + " Grenze: " + limit);
			return "Lösung gefunden";
		}
		
		LinkedList<State> neueKnoten = new LinkedList<State>(); 
		this.addToQueue(Move.up(this.nextState), neueKnoten);
		this.addToQueue(Move.down(this.nextState), neueKnoten);
		this.addToQueue(Move.right(this.nextState), neueKnoten);
		this.addToQueue(Move.left(this.nextState), neueKnoten);
		
		while(!neueKnoten.isEmpty() && depth < limit)
		{
			String returnValue = this.depthSearch(neueKnoten.poll(), goalState, depth+1, limit);
			if(returnValue.equals("Lösung gefunden"))
			{
				return "Lösung gefunden";
			}
		}
		return "Keine Lösung";
	}
	
	private void addToQueue(State curState, LinkedList<State> knotenListe)
	{
		if(curState!=null)
		{
			knotenListe.add(curState);
		}
	}
}

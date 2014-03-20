package cs342project3;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Queue;

public class BackgroundSolver implements Runnable {
	private Board board;
	private ArrayList<String> history  = new ArrayList<String>();
	private ArrayList<String> snapshots = new ArrayList<String>();
	private Queue<Board> queue = new LinkedList<Board>();
	public BackgroundSolver(Board b, ArrayList<String> h){
		board=b;
		history=h;
	}
	@Override
	public void run(){
		if(!board.isGoalState())
		{
			queue.add(board);
			history.add(board.toString());
			snapshots.add(board.toString());
			while(!queue.isEmpty())
			{
				Board ssc = queue.remove();
				ArrayList <Board> moves = new ArrayList<Board>();
				for(int i=0; i<ssc.numberOfPieces(); i++){
					Piece p = ssc.get(i);
					if(p.direction() == Piece.HORIZONTAL){
						for(int j=0; j<ssc.getRows()-1; j++)
						{
							if(ssc.moveHorizontal(p, j)){
								moves.add(ssc);
							}else 
							if(ssc.moveHorizontal(p, -j)){
								moves.add(ssc);
							}
						}
						
					}else if(p.direction() == Piece.VERTICAL){
						for(int j=0; j<ssc.getCols()-1; j++)
						{
							if(ssc.moveVertical(p, j)){
								moves.add(ssc);
							}else 
							if(ssc.moveVertical(p, -j)){
								moves.add(ssc);
							}
						}
					}
				}
				System.out.println(moves.size());
				for(Board b : moves)
				{
					//System.out.println(b);
					if(b.isGoalState()){
						//System.out.println(b);
						for(String s : history){
							System.out.println(s);
						}
						System.out.println("Solved");
						return;
					}
					if(!snapshots.contains(b.toString())){
						queue.add(b);
						System.out.println(b.toString());
						history.add(b.toString());
						snapshots.add(b.toString());
					}
				}
				
			}
		}
	}

}

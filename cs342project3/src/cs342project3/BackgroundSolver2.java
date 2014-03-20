package cs342project3;

import java.util.HashSet;

public class BackgroundSolver2 implements Runnable{
	HashSet<String> history = new HashSet<String>();
	SearchPiece pieces[];
	public BackgroundSolver2(SearchPiece b[]){
		pieces = b;
	}
	@Override
	public void run() {
		SearchBoard sb = new SearchBoard(pieces, 0, 0, 0);
		//FirstMove firstmove=new FirstMove(sb, 1,1,"Right");
		
		//sb.printBoard();
		//System.out.println(firstmove.getMoves());
		//System.out.println(sb.getBoardString());
	}

}

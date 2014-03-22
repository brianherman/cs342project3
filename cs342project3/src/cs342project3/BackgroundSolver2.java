package cs342project3;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashSet;

public class BackgroundSolver2 implements Runnable{
	private HashSet<String> history = new HashSet<String>();
	private SearchPiece pieces[];
	private String hint = "Still thinking.";
	private String Colors[] = {"red", "blue", "green", "cyan" ,"magenta","orange","pink","yellow", "lightGray"};

	public BackgroundSolver2(SearchPiece b[]){
		pieces = b;
	}
	public void update(SearchPiece b[])
	{
		String hint = "Still thinking.";
		pieces = b;
	}
	@Override
	public void run() {
		ArrayList<FirstMove> firstmoves = new ArrayList<FirstMove>();
		firstmoves=makeFirstMoves(pieces);
		//System.out.println(firstmoves.size());
		int min=1000;
		FirstMove temp=null;
		for (int i=0; i<firstmoves.size(); i++){
			if (firstmoves.get(i).getMoves()<min)
				min = firstmoves.get(i).getMoves();
			temp = firstmoves.get(i);
		}
		//System.out.println("The " + Colors[temp.getPiece()] +" Piece number " + temp.getPiece()+" " +temp.getDirection()+" wins in "+min+" moves");
		hint = "The " + Colors[temp.getPiece()] +" Piece number "+ temp.getPiece()+" " +temp.getDirection()+" wins in "+min+" moves";

	}
	public ArrayList<FirstMove> makeFirstMoves(SearchPiece[] board) {
		ArrayList<String> history = new ArrayList<String>();
		ArrayList<FirstMove> firstmoves = new ArrayList<FirstMove>();
		for (int i = 0; i < board.length; i++) {
			SearchBoard move1 = new SearchBoard(board, 1, 1, i);
			
			SearchBoard move2 = new SearchBoard(board, 1, 2, i);
			move1.setBoardSize(board[0].getBoardSize());
			move2.setBoardSize(board[0].getBoardSize());
			
			move1.printBoard();
			//System.out.println("");
			move2.printBoard();
			//System.out.println("");
			String direction1;
			String direction2;
			if (board[i].getPieceType() == 1 || board[i].getPieceType() == 2)
				direction1 = "Left";
			else
				direction1 = "Up";
			if (board[i].getPieceType() == 1 || board[i].getPieceType() == 2)
				direction2 = "Right";
			else
				direction2 = "Down";

			if (move1.isLegalBoard()) {
				FirstMove firstmove1 = new FirstMove(move1, i, 1, direction1,history);
				firstmoves.add(firstmove1);
				history=firstmove1.getHistory();
			}
			if (move2.isLegalBoard()) {
				FirstMove firstmove2 = new FirstMove(move2, i, 1, direction2,history);
				firstmoves.add(firstmove2);
				history=firstmove2.getHistory();
			}

		}
		
		return firstmoves;
	}
	public String getHint(){
		return hint;
	}

}

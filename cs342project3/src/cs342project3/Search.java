package cs342project3;
import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

public class Search {

	public static int BOARDSIZE = 6;
	

	public static void main(String[] args) {
		long starttime = System.currentTimeMillis();
		/*
		 * File file = new File("input.txt"); Piece pieces [] = new Piece[8];
		 * try{ Scanner scanner = new Scanner(file); scanner.nextInt();
		 * scanner.nextInt();
		 * 
		 * for (int i=0; i<8; i++){ int[] temppiece = new int[4];
		 * temppiece[0]=scanner.nextInt(); temppiece[1]=scanner.nextInt();
		 * temppiece[2]=scanner.nextInt(); temppiece[3]=scanner.nextInt(); int
		 * direction; int length; if (temppiece[2]==1){
		 * direction=Piece.VERTICAL; length=temppiece[3]; } else {
		 * direction=Piece.HORIZONTAL; length=temppiece[2]; } pieces[i]= new
		 * Piece(temppiece[0],temppiece[1], length, direction, null,i ); }
		 * 
		 * }
		 * 
		 * catch(Exception e){}
		 */
		HashSet<String> history = new HashSet<String>();
		ArrayList<FirstMove> firstmoves = new ArrayList<FirstMove>();
		SearchPiece[] board = new SearchPiece[7];
		board[0] = new SearchPiece(1, 1, 2, 1, 0);
		
		board[1] = new SearchPiece(3, 1, 2, 3, 1);
		
		board[2] = new SearchPiece(3, 3, 2, 1, 2);
		
		board[3] = new SearchPiece(1, 5, 3, 2, 3);
		
		board[4] = new SearchPiece(4, 5, 2, 3, 4);
		
		board[5] = new SearchPiece(5, 3, 3, 4, 5);
		
		board[6] = new SearchPiece(6, 1, 2, 3, 6);
		
		
		/*
		firstmoves=makeFirstMoves(board);
		
		for (int i=0; i<firstmoves.size(); i++)
			System.out.println("Piece "+firstmoves.get(i).getPiece()+" "+ firstmoves.get(i).getDirection()+
					" in "+firstmoves.get(i).getMoves()+" moves");
		
		System.out.println(firstmoves.get(0).getSearchBoard().wins);
		//System.out.println(firstmoves.get(1).getSearchCount());
		 */
		
		SearchBoard searchboard = new SearchBoard(board, 0, 0, 0,history);
		searchboard.printBoard();
		System.out.println(searchboard.movesToWin());
		
		
		long endtime = System.currentTimeMillis();
		System.out.println("Runtime is " + (endtime - starttime));
	}

	public static ArrayList<FirstMove> makeFirstMoves(SearchPiece[] board) {

		ArrayList<FirstMove> firstmoves = new ArrayList<FirstMove>();
		for (int i = 0; i < board.length; i++) {
			SearchBoard move1 = new SearchBoard(board, 1, 1, i,null);
			
			SearchBoard move2 = new SearchBoard(board, 1, 2, i,null);
			
			move1.printBoard();
			System.out.println("");
			move2.printBoard();
			System.out.println("");
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
				FirstMove firstmove1 = new FirstMove(move1, i, 1, direction1);
				firstmoves.add(firstmove1);
			}
			if (move2.isLegalBoard()) {
				FirstMove firstmove2 = new FirstMove(move2, i, 1, direction2);
				firstmoves.add(firstmove2);
			}

		}
		return firstmoves;
	}

}

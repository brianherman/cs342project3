package cs342project3;
import java.util.ArrayList;
import java.util.HashSet;
public class FirstMove {

	private SearchBoard board;
	private int piecenumber;
	private int numsquarestomove;
	private String direction;
	private int moves;
	
	private int BOARDSIZE=7;
	private int moveNumber=1;
	private HashSet<String> history=new HashSet<String>();
	private ArrayList<SearchBoard> queue=new ArrayList<SearchBoard>();
	private String boardString;
	
	FirstMove(SearchBoard board, int piecenumber, int numsquarestomove, String direction){
		
		this.board=board;
		this.piecenumber=piecenumber;
		this.numsquarestomove=numsquarestomove;
		this.direction=direction;
		
		
		
		history.add(board.getBoardString());
		queue.add(board);
		moves=generateBoards();
	}
	
	public int getMoves(){
		return moves;
	}
	
	public int getPiece(){
		return piecenumber;
	}
	
	public String getDirection(){
		return direction;
	}
	
	public SearchBoard getSearchBoard(){
		return board;
	}
	
	public int generateBoards(){
		SearchBoard board1;
		SearchBoard board2;
		
		for (int i = 0; i < queue.get(0).getBoard().length; i++) {
			
			board1 = new SearchBoard(queue.get(0).getBoard(), moveNumber,1,i);
			if (!board1.isLegalBoard()){}
			else{
			
			boardString=board1.getBoardString();
			if (!history.contains(boardString)){
			queue.add(board1);
			history.add(boardString);
			}
			}
			
			
			board2 = new SearchBoard(queue.get(0).getBoard(), moveNumber,2,i);
			if (!board2.isLegalBoard()){}
			else{
			
			
			boardString=board2.getBoardString();
			if (!history.contains(boardString)){
			queue.add(board2);
			history.add(boardString);
			
			}
		}
		}
		moveNumber++;
		while(!queue.isEmpty()){
			SearchBoard currentBoard=queue.get(0);
			if(currentBoard.isWinningBoard()){
				System.out.println(history);
				return currentBoard.getMoveNumber()+1;
				
			}
			else
			{
				queue.remove(0);
				
				generateBoards();
			}
		}
		System.out.println(history);
		return -1;
	}
	
	
public boolean isLegalMoveOne(int pieceNumber){
		
		if (board.getBoard()[pieceNumber].getPieceType()==1 || board.getBoard()[pieceNumber].getPieceType()==2){
			
			if (board.getBoard()[pieceNumber].getX()==0)
				return false;
		}
		
		else{
			if (board.getBoard()[pieceNumber].getY()==0)
				return false;
		}
		int position = board.getBoard()[pieceNumber].getPositions()[0];
		if (board.getBoard()[pieceNumber].getPieceType()==1 || board.getBoard()[pieceNumber].getPieceType()==2){
			
		
		if (board.getPositions().contains(position-1))
			return false;
		}
		else{
			if (board.getPositions().contains(position-BOARDSIZE))
				return false;
		}
		return true;
	}

public boolean isLegalMoveTwo(int pieceNumber){
	
	if (board.getBoard()[pieceNumber].getPieceType()==1 || board.getBoard()[pieceNumber].getPieceType()==2){
		
		if (board.getBoard()[pieceNumber].getX()==(BOARDSIZE-board.getBoard()[pieceNumber].getLength()+1))
			return false;
	}
	
	else{
		if (board.getBoard()[pieceNumber].getY()==(BOARDSIZE-board.getBoard()[pieceNumber].getLength()+1))
			return false;
	}
	int position = board.getBoard()[pieceNumber].getPositions()[board.getBoard()[pieceNumber].getPositions().length-1];
	if (board.getBoard()[pieceNumber].getPieceType()==1 || board.getBoard()[pieceNumber].getPieceType()==2){
		
	
	if (board.getPositions().contains(position+1))
		return false;
	}
	else{
		if (board.getPositions().contains(position+BOARDSIZE))
			return false;
	}
	return true;
}


public void setBoardString() {
	
	for (int i = 0; i < BOARDSIZE * BOARDSIZE; i++) {
		boolean found=false;
		for (int j = 0; j < board.getBoard().length; j++) {
			for (int k = 0; k < board.getBoard()[j].getPositions().length; k++) {
				if (board.getBoard()[j].getPositions()[k] == i){
					boardString = boardString + j;
					found=true;
				}
			}
		}
		if (found==false)
			boardString = boardString + "-";
	}
}
	
}

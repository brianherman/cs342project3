package cs342project3;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Queue;
public class FirstMove {

	private SearchBoard board;
	private int piecenumber;
	private int numsquarestomove;
	private String direction;
	private int moves;
	
	private int BOARDSIZE=7;
	private int moveNumber=0;
	private ArrayList<String> history;
	private ArrayList<SearchBoard> queue=new ArrayList<SearchBoard>();
	private String boardString;
	
	FirstMove(SearchBoard board, int piecenumber, int numsquarestomove, String direction,ArrayList<String> inhistory){
		
		this.board=board;
		this.piecenumber=piecenumber;
		this.numsquarestomove=numsquarestomove;
		this.direction=direction;
		history=inhistory;
		
		
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
	
public ArrayList<String> getHistory(){
	return history;
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

public int generateBoards(){
	
	while (!queue.isEmpty()){
		SearchBoard board1;
		SearchBoard board2;
		SearchBoard currentBoard=queue.get(0);
		queue.remove(0);
		moveNumber=currentBoard.getMoveNumber();
		ArrayList<SearchBoard> moves = new ArrayList<SearchBoard>();
		for (int i=0; i<currentBoard.getBoard().length; i++){
			
			board1 = new SearchBoard(currentBoard.getBoard(), moveNumber+1,1,i);
			moves.add(board1);
			//System.out.println(board1.isLegalBoard());
			
				
			
			
			
				board2 = new SearchBoard(currentBoard.getBoard(), moveNumber+1,2,i);
				moves.add(board2);
				//System.out.println(board2.isLegalBoard());
				
				
			
			
		
		for (int j=0; j<moves.size(); j++)
		{
			if (moves.get(j).isWinningBoard())
				return moves.get(j).getMoveNumber();
			if (moves.get(j).isLegalBoard() && !history.contains(moves.get(j).getBoardString())){
				queue.add(moves.get(j));
				history.add(moves.get(j).getBoardString());
			}
		}
		
		//System.out.println(queue);
	}
		
	}
	System.out.println(history);
	return 1000000;
}

}

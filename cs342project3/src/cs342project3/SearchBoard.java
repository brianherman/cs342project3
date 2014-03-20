package cs342project3;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.TreeSet;

public class SearchBoard {

	private SearchPiece[] board;
	private int BOARDSIZE = 7;
	private String boardString = "";
	private int moveNumber;
	private ArrayList<Integer> positions;
	
	private boolean legal=true;
	private int lowestWinningLine;
	
	public static TreeSet<Integer> wins=new TreeSet<Integer>();
	
	SearchBoard(SearchBoard inboard){
		this.board=inboard.board;
		board = new SearchPiece[inboard.board.length];
		for (int i = 0; i < inboard.board.length; i++) {
			board[i] = new SearchPiece(inboard.board[i]);
		}
	}

	SearchBoard(SearchPiece[] inputboard, int moveNumber, int moveDirection, int movePiece) {

		
		positions = new ArrayList<Integer>();
		board = new SearchPiece[inputboard.length];
		for (int i = 0; i < inputboard.length; i++) {
			board[i] = new SearchPiece(inputboard[i]);
		}
		
		
		
		this.moveNumber=moveNumber;
		
		if (moveDirection ==1){
			moveOne(movePiece);
		}
		else if (moveDirection ==2){
			moveTwo(movePiece);
		}
		
		setBoardString();
		setPositions();
		
		printBoard();
		
		if (isWinningBoard()){
			
			wins.add(moveNumber);
			
		}
		
		
	}

	public SearchPiece getPiece(int n) {
		return board[n];
	}
	
	
	
	public int movesToWin(){
		if (wins.size()==0)
			return 1001;
		
		return wins.first();
	}
	
	
	
	
	
	public ArrayList<Integer> getPositions(){
		return positions;
	}

	public void setPositions() {
		positions.clear();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].getPositions().length; j++) {
				int a = board[i].getPositions()[j];
				positions.add(a);
			}
		}
	}

	public void setBoardString() {
		
		for (int i = 0; i < BOARDSIZE * BOARDSIZE; i++) {
			boolean found=false;
			for (int j = 0; j < board.length; j++) {
				for (int k = 0; k < board[j].getPositions().length; k++) {
					if (board[j].getPositions()[k] == i){
						boardString = boardString + j;
						found=true;
					}
				}
			}
			if (found==false)
				boardString = boardString + "-";
		}
	}
	
	public String getBoardString(){
		return boardString;
	}		
		
	

	public boolean isLegalBoard() {

		if(legal==false)
			return false;
		
		else{

		// if two positions intersect
		for (int i = 0; i < positions.size(); i++)
			for (int j = i + 1; j < positions.size(); j++)
				if (positions.get(i).equals(positions.get(j)))
					return false;
		return true;
		}
	}

	public boolean isWinningBoard() {

		if (board[0].getPieceType() == 1 && board[0].getX() == BOARDSIZE - 1)
			return true;
		else if (board[0].getPieceType() == 2
				&& board[0].getX() == BOARDSIZE - 2)
			return true;
		else
			return false;
	}

	public SearchPiece[] getBoard() {
		return board;
	}

	public int getMoveNumber() {
		return moveNumber;
	}
	

	public void printBoard() {
		System.out.println("Move: "+moveNumber);
		for (int i = 0; i < BOARDSIZE; i++) {
			for (int j = 0; j < BOARDSIZE; j++) {
				if (positions.contains(BOARDSIZE * i + j))
					System.out.print("x ");
				else
					System.out.print("- ");
			}
			System.out.println("");
		}
	}

	// move left or up
	public void moveOne(int piecenumber) {
		
		if (board[piecenumber].getPieceType() == 1
				|| board[piecenumber].getPieceType() == 2) {
			if (board[piecenumber].getX()==1)
				legal=false;
			else {
			int x = board[piecenumber].getX();
			board[piecenumber].setX(x - 1);
			}
		} else {
			if (board[piecenumber].getY()==1)
				legal=false;
			else {
			int y = board[piecenumber].getY();
			board[piecenumber].setY(y - 1);
			}
		}
		
	}

	// move down or right
	public void moveTwo(int piecenumber) {
		if (board[piecenumber].getPieceType() == 1
				|| board[piecenumber].getPieceType() == 2) {
			if (board[piecenumber].getX()==(BOARDSIZE-board[piecenumber].getLength()+1))
				legal=false;
			else{
			int x = board[piecenumber].getX();
			board[piecenumber].setX(x + 1);
			}
		} else {
			if (board[piecenumber].getY()==(BOARDSIZE-board[piecenumber].getLength()+1))
				legal=false;
			else{
			int y = board[piecenumber].getY();
			board[piecenumber].setY(y + 1);
			}
		}
		
	}

	
	public void generateBoards() {
		SearchBoard board1;
		SearchBoard board2;
		if (movesToWin()>moveNumber){
		for (int i = 0; i < board.length; i++) {
			if (isLegalMoveOne(i))
			board1 = new SearchBoard(board, moveNumber + 1,1,i);
			if (isLegalMoveTwo(i))
			board2 = new SearchBoard(board, moveNumber + 1,2,i);
		}
		}
	}
	
	
	
	
	
	public boolean isLegalMoveOne(int pieceNumber){
		
		if (board[pieceNumber].getPieceType()==1 || board[pieceNumber].getPieceType()==2){
			
			if (board[pieceNumber].getX()==0)
				return false;
		}
		
		else{
			if (board[pieceNumber].getY()==0)
				return false;
		}
		int position = board[pieceNumber].getPositions()[0];
		if (board[pieceNumber].getPieceType()==1 || board[pieceNumber].getPieceType()==2){
			
		
		if (positions.contains(position-1))
			return false;
		}
		else{
			if (positions.contains(position-BOARDSIZE))
				return false;
		}
		return true;
	}
	
public boolean isLegalMoveTwo(int pieceNumber){
		
		if (board[pieceNumber].getPieceType()==1 || board[pieceNumber].getPieceType()==2){
			
			if (board[pieceNumber].getX()==(BOARDSIZE-board[pieceNumber].getLength()+1))
				return false;
		}
		
		else{
			if (board[pieceNumber].getY()==(BOARDSIZE-board[pieceNumber].getLength()+1))
				return false;
		}
		int position = board[pieceNumber].getPositions()[board[pieceNumber].getPositions().length-1];
		if (board[pieceNumber].getPieceType()==1 || board[pieceNumber].getPieceType()==2){
			
		
		if (positions.contains(position+1))
			return false;
		}
		else{
			if (positions.contains(position+BOARDSIZE))
				return false;
		}
		return true;
	}
	
	

	}



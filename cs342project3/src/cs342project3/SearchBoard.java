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
	/**
	 * Copy constructor for the search board.
	 * @param inboard
	 */
	public SearchBoard(SearchBoard inboard){
		this.board=inboard.board;
		board = new SearchPiece[inboard.board.length];
		for (int i = 0; i < inboard.board.length; i++) {
			board[i] = new SearchPiece(inboard.board[i]);
		}
	}
	/**
	 * Creates a new search board.
	 * @param inputboard
	 * @param moveNumber
	 * @param moveDirection
	 * @param movePiece
	 */
	public SearchBoard(SearchPiece[] inputboard, int moveNumber, int moveDirection, int movePiece) {

		
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
		
		//printBoard();
		
		if (isWinningBoard()){
			
			wins.add(moveNumber);
			
		}
		
		
	}
	/**
	 * Returns a piece at the specified index.
	 * @param n
	 * @return
	 */
	public SearchPiece getPiece(int n) {
		return board[n];
	}
	/**
	 * Returns the moves until a win state has been reached.
	 * @return
	 */
	public int movesToWin(){
		if (wins.size()==0)
			return 1001;
		
		return wins.first();
	}
	/**
	 * Returns the number of positions.
	 * @return ArrayList<Integer> of positions
	 */
	public ArrayList<Integer> getPositions(){
		return positions;
	}
	/**
	 * Sets the number of postions
	 */
	public void setPositions() {
		positions.clear();
		for (int i = 0; i < board.length; i++) {
			for (int j = 0; j < board[i].getPositions().length; j++) {
				int a = board[i].getPositions()[j];
				positions.add(a);
			}
		}
	}
	/**
	 * Sets the string that identifies a board.
	 */
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
	/**
	 * Returns a string that identifies the board.
	 * @return String the board string
	 */
	public String getBoardString(){
		return boardString;
	}		
	/**
	 * Sets the size of a board.
	 * @param size
	 */
	public void setBoardSize(int size)
	{
		BOARDSIZE=size;
	}
	/**
	 * Determines if a board is legal.
	 * @return
	 */
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
	/**
	 * Determines if a board is a winner.
	 * @return
	 */
	public boolean isWinningBoard() {

		if (board[0].getPieceType() == 1 && board[0].getX() == BOARDSIZE - 1)
			return true;
		else if (board[0].getPieceType() == 2
				&& board[0].getX() == BOARDSIZE - 2)
			return true;
		else
			return false;
	}
	/**
	 * Returns the board with all the search pieces.
	 * @return
	 */
	public SearchPiece[] getBoard() {
		return board;
	}
	/**
	 * Gets the number of moves.
	 * @return
	 */
	public int getMoveNumber() {
		return moveNumber;
	}
	/**
	 * Prints the board.
	 */
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
	/**
	 * Makes a move left or up.
	 * @param piecenumber
	 */
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
	/**
	 * Makes a move down or right.
	 * @param piecenumber
	 */
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
	/**
	 * Recursively calls the searchboard and simulates the moves.
	 */
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



package cs342project3;

public class FirstMove {

	private SearchBoard board;
	private int piecenumber;
	private int numsquarestomove;
	private String direction;
	private int moves;
	
	
	FirstMove(SearchBoard board, int piecenumber, int numsquarestomove, String direction){
		
		this.board=board;
		this.piecenumber=piecenumber;
		this.numsquarestomove=numsquarestomove;
		this.direction=direction;
		this.moves=board.movesToWin();
		
		
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
	
	
	
	
}

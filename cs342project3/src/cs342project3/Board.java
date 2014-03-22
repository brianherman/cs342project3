package cs342project3;


import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;

public class Board {
	private char board[][];
	public boolean visited;
	private int rows,cols;
	private SearchPiece sp[];
	private BackgroundSolver2 BS2;
	private Thread solver;
	private Color Colors[] = {Color.red, Color.blue, Color.green, Color.cyan,Color.magenta,Color.orange,Color.pink,Color.yellow, Color.lightGray};
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	private Rectangle boundary;
	/**
	 * Creates a new board based on a filename
	 * @param fname
	 */
	public Board(File fname)
	{
		try{
			read(fname);
		}catch(IOException e){
			if(e.getMessage().equals("Wrong number of rows."))
				System.err.println("Error in file");
			if(e.getMessage().equals("Wrong number of cols."))
				System.err.println("Error in file");
			e.printStackTrace();
		}
		board = new char[rows][cols];
		for(int i=0; i<rows; i++)
		{
			for(int j=0; j<cols; j++)
			{
				board[i][j]=' ';
			}
		}
		for(int i=0; i<pieces.size(); i++)
		{
			if(pieces.get(i).direction()==Piece.HORIZONTAL){		
				pieces.get(i).setBounds( new Rectangle(
						pieces.get(i).getX()*114,pieces.get(i).getY()*114,pieces.get(i).length()*114,114));
			}else{
				pieces.get(i).setBounds(  new Rectangle(
						pieces.get(i).getX()*114,pieces.get(i).getY()*114,114,pieces.get(i).length()*114));
			}
		}
		
		//placePieces();
	}
	public Board(Board b)
	{
		rows = b.rows;
		cols = b.cols;
		pieces = b.pieces;
		board = b.board;
	}
	/*
	 * The first integer will be the starting row position. 
 	The second integer will be the starting column position. 
 	The third integer will be the width in columns. 
 	The fourth integer will be the height in rows. 
 	The character value will specify the direction of movement the piece can have. This character can be either 
	an "h" for horizontal movement (left or right), a "v" for vertical movement (up or down), a "b" for both 
	horizontal and vertical movement, or a "n" for no movement (the piece cannot move, it must stay in that 
	space). 
	 */
	/**
	 * Reads a file from and converts it into a board.
	 * @param f
	 * @throws IOException
	 */
	public void read(File f) throws IOException
	{
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line = br.readLine();
		/* Get the rows and columns */
		String dimentions[]=line.split(" ");
		rows = Integer.parseInt(dimentions[0]);
		cols = Integer.parseInt(dimentions[1]);
		System.out.println(rows+" "+ cols);
		int counter = 0;
		/* Get the goal piece */
		line = br.readLine();
		String input[] = line.split(" ");
		int row = Integer.parseInt(input[0])-1;
		if(row+1<0 || row+1 > rows){
			br.close();
			throw new IOException("Wrong number of rows.");
		}
		int column = Integer.parseInt(input[1])-1;
		if(column+1<0 || column+1 > cols){
			br.close();
			throw new IOException("Wrong Number of Cols");
		}
		int width = Integer.parseInt(input[2]);
		int height = Integer.parseInt(input[3]);
		char direction = input[4].charAt(0);
		if(direction == 'h'){
			pieces.add(new Piece(row,column, width, Piece.HORIZONTAL, Colors[counter],counter,true));
		}
		if(direction == 'v'){
			pieces.add(new Piece(row,column, height, Piece.VERTICAL, Colors[counter],counter,true));
		}
		counter++;
		System.out.println("GOAL PIECE: "+ row +" "+ column +" "+ width +" "+ height +" "+ direction);
		while (	(line = br.readLine()) != null){
			input = line.split(" ");
			row = Integer.parseInt(input[0])-1;
			if(row+1<0 || row+1 > rows){
				br.close();
				throw new IOException("Wrong number of rows.");
			}
			column = Integer.parseInt(input[1])-1;
			if(column+1<0 || column+1 > cols){
				br.close();
				throw new IOException("Wrong Number of Cols");
			}
			width = Integer.parseInt(input[2]);
			height = Integer.parseInt(input[3]);
			direction = input[4].charAt(0);
			System.out.println(row +" "+ column +" "+ width +" "+ height +" "+ direction);
			if(direction == 'h'){
				pieces.add(new Piece(row,column, width, Piece.HORIZONTAL, Colors[counter%8],counter,false));
			}
			if(direction == 'v'){
				pieces.add(new Piece(row,column, height, Piece.VERTICAL, Colors[counter%8],counter,false));
			}
			counter++;
		}
		System.out.println("Number of pieces: " + pieces.size());
		sp = new SearchPiece[pieces.size()];
		// 1=2 horizontal 2=3horizontal 3=2vertical 4=3vertical
		for(int i=0; i<pieces.size(); i++){
			int X = pieces.get(i).getX();
			int Y = pieces.get(i).getY();
			int d = pieces.get(i).direction();
			int size = pieces.get(i).length();
			int converted = 0;
			if(d == Piece.HORIZONTAL && size == 2){
				converted = 1;
			}else if(d == Piece.HORIZONTAL && size == 3)
			{
				converted = 2;
			}else if(d == Piece.VERTICAL && size == 2){
				converted = 3;
			}else{
				converted = 4;
			}
			System.out.println("Adding "+ i);
			sp[i] = new SearchPiece(X+1, Y+1, size, converted, i);
			sp[i].printPiece();
		}
		BS2 = new BackgroundSolver2(sp);
		solver = new Thread(BS2);
		solver.start();
		br.close();
		boundary = new Rectangle(0,0,rows*114,cols*114);
	}
	public void update(){
		solver.interrupt();
		sp = new SearchPiece[pieces.size()];
		// 1=2 horizontal 2=3horizontal 3=2vertical 4=3vertical
		for(int i=0; i<pieces.size(); i++){
			int X = pieces.get(i).getX();
			int Y = pieces.get(i).getY();
			int d = pieces.get(i).direction();
			int size = pieces.get(i).length();
			int converted = 0;
			if(d == Piece.HORIZONTAL && size == 2){
				converted = 1;
			}else if(d == Piece.HORIZONTAL && size == 3)
			{
				converted = 2;
			}else if(d == Piece.VERTICAL && size == 2){
				converted = 3;
			}else{
				converted = 4;
			}
			System.out.println("Adding "+ i);
			sp[i] = new SearchPiece(X+1, Y+1, size, converted, i);
			sp[i].printPiece();
			sp[i].setBoardSize(rows);
		}
		BS2 = new BackgroundSolver2(sp);
		solver = new Thread(BS2);
		solver.start();
	}
	/**
	 * Moves a piece Vertically.
	 * @param p the piece to be moved
	 * @param modifier, how many squares to move it
	 */
	public boolean moveVertical(Piece p, int modifier)
	{
		int previous = p.getY();
		int move=p.getY()+modifier;
		//System.out.println("MOVE IS:"+move);

			p.setY(move);
			
			//System.out.println("Preforming move");
			for(int i=0; i<pieces.size(); i++){
				if(i != p.id()){
					//System.out.println(i + ": "+p.bounds().intersects(pieces.get(i).bounds()));
					if(p.bounds().intersects(pieces.get(i).bounds()) || !boundary.contains(p.bounds()))
					{
						p.setY(previous);
						return false;
					}
				}
			
			p.setY(move);
		}
		update();
		
		return true;

	}
	/**
	 * Moves a piece horizontally.
	 * @param p the piece to be moved
	 * @param modifier, how many squares to move it
	 */
	public boolean moveHorizontal(Piece p, int modifier)
	{
		int previous = p.getX();
		int move=p.getX()+modifier;
		//System.out.println("MOVE IS:"+move);
		if(boundary.contains(p.bounds())){
			p.setX(move);
			for(int i=0; i<pieces.size(); i++){
				//System.out.println(i + ": "+p.bounds().intersects(pieces.get(i).bounds()));
				if(i != p.id()){
					if(p.bounds().intersects(pieces.get(i).bounds())|| !boundary.contains(p.bounds()))
					{
						p.setX(previous);
						return false;
					}
				}
			}
			p.setX(move);
		}
		update();

		return true;
	}
	/**
	 * Returns the number of pieces
	 * @return
	 */
	public int numberOfPieces(){
		return pieces.size();
	}
	/**
	 * Gets the piece based on the index
	 * @param index
	 * @return
	 */
	public Piece get(int index)
	{
		return pieces.get(index);
	}
	/**
	 * Gets the number of rows
	 * @return
	 */
	public int getRows(){
		return rows;
	}
	/**
	 * Gets the number of Columns.
	 * @return
	 */
	public int getCols(){
		return rows;
	}
	private char char2int[] = {'Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y'};

	public String toString(){
			String ret = "";
			for(int i=0; i<rows; i++)
			{
				for(int j=0; j<cols; j++)
				{
					board[i][j]=' ';
				}
			}
			for(int i=0; i<pieces.size(); i++)
			{
				if(pieces.get(i).direction()==Piece.HORIZONTAL)
				{
					for(int j=0; j <pieces.get(i).length(); j++)
					{
						if(pieces.get(i).getX()+j< cols)
							board[pieces.get(i).getY()][pieces.get(i).getX()+j]=char2int[pieces.get(i).id()];
					}
				}else{
					for(int j=0; j <pieces.get(i).length(); j++)
					{
						if(pieces.get(i).getY()+j < rows)
							board[pieces.get(i).getY()+j][pieces.get(i).getX()]=char2int[pieces.get(i).id()];
					}
				}
			}
			for(int i=0; i<rows; i++){
				for(int j=0; j<cols; j++){
					ret += board[i][j] ;
				}
				ret += "\n";
			}
			return ret;
	}
	public boolean isGoalState() {
		System.err.println(pieces.get(0).getX());
		if(pieces.get(0).getX()==(cols-2))
			return true;
		return false;
	}
	public String getHint(){
		return BS2.getHint();
	}
}

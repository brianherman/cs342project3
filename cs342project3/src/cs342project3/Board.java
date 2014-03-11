package cs342project3;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Board {
	private ArrayList<char[][]> history = new ArrayList<char[][]>();
	private char board[][];
	private int rows,cols;
	private char char2int[] = {'Z','A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y'};
	private Color Colors[] = {Color.red, Color.blue, Color.green, Color.cyan,Color.magenta,Color.orange,Color.pink,Color.yellow, Color.lightGray};
	private ArrayList<Piece> pieces = new ArrayList<Piece>();
	
	public Board(File fname)
	{
		read(fname);
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

		placePieces();
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
	public void read(File f)
	{
		try {
			BufferedReader br = new BufferedReader(new FileReader(f));
			String line = br.readLine();
			/* Get the rows and columns */
			String dimentions[]=line.split(" ");
			rows = Integer.parseInt(dimentions[0]);
			cols = Integer.parseInt(dimentions[1]);
			System.out.println(rows+cols);
			int counter = 0;
			/* Get the goal piece */
			line = br.readLine();
			String input[] = line.split(" ");
			int row = Integer.parseInt(input[0]);
			int column = Integer.parseInt(input[1]);
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
				 row = Integer.parseInt(input[0]);
				 column = Integer.parseInt(input[1]);
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
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void placePieces(){
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
						board[pieces.get(i).getY()][pieces.get(i).getX()+j]=char2int[pieces.get(i).id()];
				}
			}else{
				for(int j=0; j <pieces.get(i).length(); j++)
				{
						board[pieces.get(i).getY()+j][pieces.get(i).getX()]=char2int[pieces.get(i).id()];
				}
			}
		}
		System.out.println("===BOARD===");
		for(int i=0; i<rows; i++){
			for(int j=0; j<cols; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
	}
	public void moveVertical(Piece p, int modifier)
	{
		int previous = p.getY();
		int move=p.getY()+modifier;
		System.out.println("MOVE IS:"+move);
		if(move==-1)
			move=1;
		if(move >= 0 && move < rows){
			p.setY(move);

			System.out.println("Preforming move");
			for(int i=0; i<pieces.size(); i++){
				if(i != p.id()){
					System.out.println(i + ": "+p.bounds().intersects(pieces.get(i).bounds()));
					if(p.bounds().intersects(pieces.get(i).bounds()))
					{
						p.setY(previous);
						return;
					}
				}
			}
			p.setY(move);
			
			placePieces();
		}
	}
	public void moveHorizontal(Piece p, int modifier)
	{
		int previous = p.getX();
		int move=p.getX()+modifier;
		System.out.println("MOVE IS:"+move);
		if(move==-1)
			move=1;
		if(move >= 0 && move < cols){
			p.setX(move);
			for(int i=0; i<pieces.size(); i++){
				System.out.println(i + ": "+p.bounds().intersects(pieces.get(i).bounds()));
				if(i != p.id()){
					if(p.bounds().intersects(pieces.get(i).bounds()))
					{
						p.setX(previous);
						return;
					}
				}
			}
			p.setX(move);
			placePieces();
		}
	}
	public int numberOfPieces(){
		return pieces.size();
	}
	public Piece get(int index)
	{
		return pieces.get(index);
	}
	public int getRows(){
		return rows;
	}
	public int getCols(){
		return rows;
	}
}

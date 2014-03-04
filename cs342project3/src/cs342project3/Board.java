package cs342project3;

import java.awt.Color;
import java.io.File;
import java.util.ArrayList;

public class Board {
	ArrayList<char[][]> history = new ArrayList<char[][]>();
	char board[][] = new char[6][6];
	char char2int[] = {'1','2','3','4','5','6','7','8'};
	Piece pieces [] = new Piece[8];
	public Board()
	{
		for(int i=0; i<6; i++)
		{
			for(int j=0; j<6; j++)
			{
				board[i][j]=' ';
			}
		}
		
		pieces[0] = new Piece(0,0, 2, Piece.HORIZONTAL, Color.red);
		pieces[1] = new Piece(0,1, 3, Piece.VERTICAL, Color.orange);
		pieces[2] = new Piece(0,5, 2, Piece.VERTICAL, Color.green);
		pieces[3] = new Piece(1,2, 2, Piece.HORIZONTAL, Color.blue);
		pieces[4] = new Piece(3,1, 3, Piece.VERTICAL, Color.CYAN);
		pieces[5] = new Piece(0,5, 3, Piece.HORIZONTAL, Color.PINK);
		pieces[6] = new Piece(5,4, 2, Piece.VERTICAL, Color.black);
		pieces[7] = new Piece(2,4, 3, Piece.HORIZONTAL, new Color(255,0,255));

		placePieces();
	}
	public void read(File f)
	{
		
	}
	private void placePieces(){
		for(int i=0; i<6; i++)
		{
			for(int j=0; j<6; j++)
			{
				board[i][j]='_';
			}
		}
		for(int i=0; i<pieces.length; i++)
		{
			if(pieces[i].direction()==Piece.VERTICAL)
			{
				for(int j=0; j <pieces[i].length(); j++)
				{
					if(pieces[i].getX()+j < 6)
						board[pieces[i].getY()][pieces[i].getX()+j]=char2int[i];
				}
			}else{
				for(int j=0; j < pieces[i].length(); j++)
				{
					if(pieces[i].getY()+j < 6)
						board[pieces[i].getY()+j][pieces[i].getX()]=char2int[i];
				}
			}
		}
		for(int i=0; i<6; i++){
			for(int j=0; j<6; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
	}
	public boolean moveVertical(Piece p, int modifier)
	{
		int move=p.getY()+modifier;
		if(move>6 && move < 0)
		{
			return false;
		}
		if(board[p.getX()][move]!= ' ')
		{
			return false;
		}
		p.setY(move);
		return true;
	}
	public boolean moveHorizontal(Piece p, int modifier)
	{
		int move=p.getX()+modifier;
		if(move > 6 && move < 0)
		{
			return false;
		}
		if(board[move][p.getY()]!= ' ')
		{
			return false;
		}
		p.setX(move);
		return true;
	}
	public Piece get(int index)
	{
		return pieces[index];
	}
}

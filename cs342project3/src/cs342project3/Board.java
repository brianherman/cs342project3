package cs342project3;

import java.awt.Color;
import java.awt.Rectangle;
import java.io.File;
import java.util.ArrayList;

public class Board {
	ArrayList<char[][]> history = new ArrayList<char[][]>();
	char board[][] = new char[6][6];
	char char2int[] = {'0','1','2','3','4','5','6','7','8'};
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

		pieces[0] = new Piece(0,0, 2, Piece.HORIZONTAL, Color.red,0);
		pieces[1] = new Piece(0,1, 3, Piece.VERTICAL, Color.orange,1);
		pieces[2] = new Piece(5,0, 2, Piece.VERTICAL, Color.green,2);
		pieces[3] = new Piece(1,2, 2, Piece.HORIZONTAL, Color.blue,3);
		pieces[4] = new Piece(3,1, 3, Piece.VERTICAL, Color.CYAN,4);
		pieces[5] = new Piece(0,5, 3, Piece.HORIZONTAL, Color.PINK,5);
		pieces[6] = new Piece(5,4, 2, Piece.VERTICAL, Color.black,6);
		pieces[7] = new Piece(2,4, 3, Piece.HORIZONTAL, new Color(255,0,255),7);

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
				board[i][j]=' ';
			}
		}
		for(int i=0; i<pieces.length; i++)
		{
			if(pieces[i].direction()==Piece.HORIZONTAL)
			{
				for(int j=0; j <pieces[i].length(); j++)
				{
					if(pieces[i].getX()+j < 6)
						board[pieces[i].getY()][pieces[i].getX()+j]=char2int[pieces[i].id()];
				}
			}else{
				for(int j=0; j < pieces[i].length(); j++)
				{
					if(pieces[i].getY()+j < 6)
						board[pieces[i].getY()+j][pieces[i].getX()]=char2int[pieces[i].id()];
				}
			}
		}
		System.out.println("===BOARD===");
		for(int i=0; i<6; i++){
			for(int j=0; j<6; j++){
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		for(int i=0; i<8; i++)
		{
			if(pieces[i].direction()==Piece.HORIZONTAL){
				pieces[i].setBounds( new Rectangle(
						pieces[i].getX()*114,pieces[i].getY()*114,pieces[i].length()*114,114));
			}else{
				pieces[i].setBounds(  new Rectangle(
						pieces[i].getX()*114,pieces[i].getY()*114,114,pieces[i].length()*114));
			}
		}
	}
	public void moveVertical(Piece p, int modifier)
	{
		int move=p.getY()+modifier;
		System.out.println("MOVE IS:"+move);
		
		if(move > 0 && move < 6){
			for(int i=0; i<pieces.length; i++){
				if(i != p.id()){
				
					if(p.bounds().intersects(pieces[i].bounds()))
					{
						return;
					}
				}
			}
			System.out.println("Preforming move");
			p.setY(move);
			p.setBounds(new Rectangle(
					p.getX()*114,p.getY()*114,p.length()*114,114));

			placePieces();
		}
	}
	public void moveHorizontal(Piece p, int modifier)
	{
		int move=p.getX()+modifier;
		boolean preform = true;
		System.out.println("MOVE IS:"+move);
		if(move >= 0 && move < 6){
			for(int i=0; i<pieces.length; i++){
				if(i != p.id()){
					if(p.bounds().intersects(pieces[i].bounds()))
					{
						preform = false;
					}
				}
			}
			if(preform == true)
			{
				System.out.println("Preforming move");
				p.setX(move);
				p.setBounds(new Rectangle(
						p.getX()*114,p.getY()*114,114,p.length()*114));
				placePieces();
			}
		}
	}
	public Piece get(int index)
	{
		return pieces[index];
	}
}

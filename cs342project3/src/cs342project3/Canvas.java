package cs342project3;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private File level= new File("level0.txt");
	private Board board = new Board(level);
	private Board board2 = new Board(level);
	private boolean freezeboard=false;
	/**
	 * Constuctor for the canvas.
	 */
	public Canvas() {
		PanelMouseAdapter ma = new PanelMouseAdapter();
		setPreferredSize(new Dimension(board.getRows()*114,board.getCols()*114));
		addMouseListener(ma);
		ArrayList<String> history = new ArrayList<String>();
	}
	/**
	 * Paints the components onto the graphics object.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		for(int i=0; i<board.getRows(); i++)
			for(int j=0; j<board.getCols(); j++)
				g.drawRect(i * 114, j * 114, 114, 114);

		for (int i = 0; i < board.numberOfPieces(); i++) {
			g.setColor(board.get(i).getColor());
			g.fillRect(board.get(i).bounds().x, board.get(i).bounds().y, board
					.get(i).bounds().width, board.get(i).bounds().height);
		}
		g.setColor(Color.black);
	}
	/**
	 * Handles the mouse Clicks for the Canvas.
	 * @author brianherman
	 *
	 */
	private class PanelMouseAdapter extends MouseAdapter{
		Point pressed = new Point();
		/**
		 * Capture the inital click.
		 */
		public void mousePressed(MouseEvent e) {
			pressed = e.getPoint();
		}
		/**
		 * If they released the mouse calculate the distance and reflect that onto the board.
		 */
		public void mouseReleased(MouseEvent e) {
			if(freezeboard)
				return;
			if(board.isGoalState())
			{
				JOptionPane.showMessageDialog(null, "You won!");
				freezeboard=true;
			}
			int modifier = (int) Math.floor(Point.distance(pressed.x,pressed.y,e.getX(),e.getY())/114);
			for(int i=0; i<board.numberOfPieces(); i++){
				//System.out.println(Math.ceil(Point.distance(pressed.x,pressed.y,e.getX(),e.getY())/(board.get(i).length()*114)));
				modifier = (int) Math.ceil(Point.distance(pressed.x,pressed.y,e.getX(),e.getY())/(board.get(i).length()*114));
    			if(board.get(i).bounds().contains(pressed)){
    				if(board.get(i).direction() == Piece.HORIZONTAL){
    					if(board.get(i).whichSideClicked(pressed) == Piece.LEFT){
    						board.moveHorizontal(board.get(i), -modifier);
    					}else{
    						board.moveHorizontal(board.get(i), modifier);
    					}
    				}else{
    					if(board.get(i).whichSideClicked(pressed)==Piece.UP){
    						board.moveVertical(board.get(i), modifier);
    					}else{
    						board.moveVertical(board.get(i), -modifier);
    					}
    				}
    			}
			}
			pressed = new Point();
			repaint();	
		}
	}
	/**
	 * Resets the board
	 */
	public void reset()
	{
		board = new Board(level);
		board.update();
		freezeboard=false;
		repaint();
	}
	/**
	 * Loads the level
	 * @param l number of the level
	 */
	public void load(int l)
	{
		level = new File("level" + l + ".txt");
		board = new Board(level);
		board.update();
		freezeboard=false;
		repaint();
	}
	/**
	 * Loads level from a file
	 * @param f 
	 */
	public void load(File f)
	{
		level = f;
		board = new Board(level);
		board.update();
		freezeboard=false;
		repaint();
	}
	public String getHint(){
		return board.getHint();
	}
}

package cs342project3;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private Board board = new Board(new File("test.txt"));
	public Canvas() {
		PanelMouseAdapter ma = new PanelMouseAdapter();
		setPreferredSize(new Dimension(board.getRows()*114,board.getCols()*114));
		addMouseListener(ma);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.drawImage(grid, 0, 0, this);
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
	private class PanelMouseAdapter extends MouseAdapter{
		Point pressed = new Point();
		public void mousePressed(MouseEvent e) {
			pressed = e.getPoint();
		}
		
		public void mouseReleased(MouseEvent e) {
			int modifier = (int) Math.floor(Point.distance(pressed.x,pressed.y,e.getX(),e.getY())/114);
			for(int i=0; i<board.numberOfPieces(); i++){
				System.out.println(Math.ceil(Point.distance(pressed.x,pressed.y,e.getX(),e.getY())/(board.get(i).length()*114)));
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

}

package cs342project3;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private Image grid;
	private Board board = new Board();
	private Point click = new Point();
	Canvas() {
		try {
			 grid = ImageIO.read(getClass().getResource("/grid.gif"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		setPreferredSize(new Dimension(686,686));
		addMouseListener(new PanelMouseAdapter());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		g.drawImage(grid, 0, 0, this);
		
		for(int i=0; i<8; i++){
			g.setColor(board.get(i).getColor());
			g.fillRect(board.get(i).bounds().x,board.get(i).bounds().y,board.get(i).bounds().width,board.get(i).bounds().height);
		}
		g.setColor(Color.black);
		g.drawRect(click.x*114, click.y*114, 114, 114);
    }
    private class PanelMouseAdapter extends MouseAdapter{
    	 public void mousePressed(MouseEvent e)
    	 {
    		int X = e.getX();
    		int Y = e.getY();
    		for(int i=0; i<8; i++){
    		if(board.get(i).bounds().contains(new Point(X,Y)))
				if(board.get(i).direction() == Piece.HORIZONTAL)
					board.moveHorizontal(board.get(i), 1);
				else
					board.moveVertical(board.get(i), 1);
    		}
    		System.out.println(X +" "+Y);
    		click = new Point(X/114,Y/114);
    		System.out.println(click.x +" "+click.y);
    		repaint();
    	 }
         public void mouseReleased(MouseEvent e) 
         {
        	int X = e.getX();
        	int Y = e.getY();
         }
    }

}

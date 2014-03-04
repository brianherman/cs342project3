package cs342project3;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Canvas extends JPanel {
	private Image grid;
	private Rectangle cars[] = new Rectangle[8];
	private Color CarColors[] = new Color[8];
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
		g.drawRect(click.x*114, click.y*114, 114, 114);
		for(int i=0; i<cars.length; i++){
			
			g.drawRect(cars[i].x,cars[i].y,cars[i].width,cars[i].height);
			g.setColor(CarColors[i]);
		}
    }
    private class PanelMouseAdapter extends MouseAdapter{
    	 public void mousePressed(MouseEvent e) 
    	 {
    		int X = e.getX();
    		int Y = e.getY();
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

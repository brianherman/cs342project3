package cs342project3;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;


public class Piece {
	public static final int NOTCLICKED = 0;
	public static final int LEFT = 1;
	public static final int RIGHT = 2;
	public static final int UP = 3;
	public static final int DOWN = 4;

	public static int VERTICAL = 0;
	public static int HORIZONTAL = 1;
	private int x;
	private int y;
	private Color color;
	private int length;
	private int direction;
	private int id;
	private Rectangle bounds;
	private boolean goal;
	
	
	public Piece(int a, int b,int l, int d, Color c, int i, boolean g)
	{
		x=a;
		y=b;
		length=l;
		direction=d;
		color=c;
		id=i;
		goal=g;
	}
	public Piece(Piece p) {
		x=p.x;
		y=p.y;
		length=p.length;
		direction=p.direction;
		color=p.color;
		id=p.id;
	}
	/**
	 * Returns the X value
	 * @return
	 */
	public int getX()
	{
		return x;
	}
	/**
	 * Returns the Y value
	 * @return
	 */
	public int getY()
	{
		return y;
	}
	/**
	 * Sets the X value and resets the bounds of the rectangle.
	 * @param a
	 */
	public void setX(int a)
	{
		x=a;
		setBounds(new Rectangle(
				x*114,y*114,length*114,114));
	}
	/**
	 * Sets the Y value and resets the bounds of the rectangle.
	 * @param a
	 */
	public void setY(int b)
	{
		y=b;
		setBounds(new Rectangle(
				x*114,y*114,114,length*114));
	}
	public Color getColor() {
		return color;
	}
	public void setColor(Color color) {
		this.color = color;
	}
	/**
	 * Returns the direction 0 for vertical 1 for horizontal.
	 * @return
	 */
	public int direction(){
		return direction;
	}
	/**
	 * Returns the length of the piece.
	 * @return
	 */
	public int length() {
		return length;
	}
	/**
	 * Sets the bounds.
	 * @param rectangle
	 */
	public void setBounds(Rectangle rectangle)
	{
		bounds=new Rectangle(rectangle);
	}
	/**
	 * Returns the bounds, the rectangle that surrounds the piece.
	 * @return
	 */
	public Rectangle bounds(){
		return new Rectangle(bounds);
	}
	public int id()
	{
		return id;
	}
	/**
	 * Checks if the piece is the goal piece.
	 * @return
	 */
	public boolean isGoal(){
		return goal;
	}
	/**
	 * Returns the side that was clicked.
	 * @param p
	 * @return
	 */
	public int whichSideClicked(Point p) {
		if (this.direction() == Piece.HORIZONTAL)
			if (this.bounds().getCenterX() < p.x
					&& this.bounds().getCenterY() < p.y)
				return LEFT;
			else
				return RIGHT;
		else if (this.bounds().getCenterX() < p.x
				&& this.bounds().getCenterY() < p.y)
			return DOWN;
		else
			return UP;
	}
}

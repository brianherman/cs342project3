package cs342project3;

import java.awt.Color;


public class Piece {
public static int VERTICAL = 0;
public static int HORIZONTAL = 1;
private int x;
private int y;
private Color color;
private int length;
private int direction;
public Piece(int a, int b,int l, int d, Color c)
{
	x=a;
	y=b;
	length=l;
	direction=d;
	color=c;
}
public int getX()
{
	return x;
}
public int getY()
{
	return y;
}
public void setX(int a)
{
	x=a;
}
public void setY(int b)
{
	y=b;
}
public Color getColor() {
	return color;
}
public void setColor(Color color) {
	this.color = color;
}
public int direction(){
	return direction;
}
public int length() {
	return length;
}
}

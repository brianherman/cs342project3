package cs342project3;
public class SearchPiece {

	private int x;
	private int y;
	private int length;
	private int pieceid;
	private int[] positions;
	private int BOARDSIZE = 7;

	// 1=2 horizontal 2=3horizontal 3=2vertical 4=3vertical
	private int piecetype;
	/**
	 * Creates a new search piece.
	 * @param x
	 * @param y
	 * @param length
	 * @param piecetype
	 * @param pieceid
	 */
	public SearchPiece(int x, int y, int length, int piecetype, int pieceid) {

		this.x = x;
		this.y = y;
		this.length = length;
		this.pieceid = pieceid;
		this.piecetype = piecetype;

		positions = new int[length];
		if (piecetype == 1 || piecetype == 2) {
			for (int i = 0; i < length; i++)
				positions[i] = (BOARDSIZE * (y - 1)) + (x - 1) + i;

		} else {
			for (int i = 0; i < length; i++)
				positions[i] = (BOARDSIZE * ((y - 1) + i)) + (x - 1);

		}
	}
	/**
	 * Copy constructor for search piece.
	 * @param p
	 */
	public SearchPiece(SearchPiece p) {
		this.x = p.x;
		this.y = p.y;
		this.length = p.length;
		this.pieceid = p.pieceid;
		this.positions = new int[p.positions.length];
		this.piecetype = p.piecetype;
		if (piecetype == 1 || piecetype == 2) {
			for (int i = 0; i < length; i++)
				positions[i] = (BOARDSIZE * (y - 1)) + (x - 1) + i;

		} else {
			for (int i = 0; i < length; i++)
				positions[i] = (BOARDSIZE * ((y - 1) + i)) + (x - 1);

		}
	}
	/**
	 * Returns the number of positions.
	 * @return
	 */
	public int[] getPositions() {
		return positions;
	}
	/**
	 * Returns the piece id.
	 * @return
	 */
	public int getPieceId(){
		return pieceid;
	}
	/**
	 * Returns the length of the piece
	 * @return
	 */
	public int getLength(){
		return length;
	}
	/**
	 * Returns the orentation of the piece.
	 * @return
	 */
	public boolean getHorizontal() {
		if (piecetype == 1 || piecetype == 2)
			return true;
		return false;
	}
	/**
	 * Returns the X value of a piece.
	 * @return
	 */
	public int getX() {
		return x;
	}
	/**
	 * Returns the Y value of a piece.
	 * @return
	 */
	public int getY() {
		return y;
	}
	/**
	 * Sets the X value of a piece.
	 * @param newx
	 */
	public void setX(int newx) {
		this.x = newx;
		positions = new int[length];
		if (piecetype == 1 || piecetype == 2) {
			for (int i = 0; i < length; i++)
				positions[i] = (BOARDSIZE * (y - 1)) + (x - 1) + i;

		} else {
			for (int i = 0; i < length; i++)
				positions[i] = (BOARDSIZE * ((y - 1) + i)) + (x - 1);

		}
		// bad board
		if (newx + length > BOARDSIZE) {
			// bad board flag
			positions[0] = -1;
		}
	}
	/**
	 * Sets the Y value of a piece.
	 * @param newy
	 */
	public void setY(int newy) {
		this.y = newy;
		positions = new int[length];
		if (piecetype == 1 || piecetype == 2) {
			for (int i = 0; i < length; i++)
				positions[i] = (BOARDSIZE * (y - 1)) + (x - 1) + i;

		} else {
			for (int i = 0; i < length; i++)
				positions[i] = (BOARDSIZE * ((y - 1) + i)) + (x - 1);

		}
		// bad board
		if (newy + length > BOARDSIZE|| positions[0]==-6) {
			// bad board flag
			positions[0] = -1;
		}
	}
	/**
	 * Sets the board size.
	 * @param size
	 */
	public void setBoardSize(int size)
	{
		BOARDSIZE=size;
	}
	/**
	 * Gets the type of the piece
	 * @return
	 */
	public int getPieceType() {
		return piecetype;
	}
	/**
	 * A nice way to print out the pieces.
	 */
	public void printPiece(){
		System.out.println(""+ x +" "+ y+" "+length+" "+piecetype+" "+pieceid);
	}
	/**
	 * Returns the board size.
	 * @return
	 */
	public int getBoardSize() {
		return BOARDSIZE;
	}

}

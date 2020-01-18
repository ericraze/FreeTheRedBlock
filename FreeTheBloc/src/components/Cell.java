package components;

import java.awt.Color;
import java.awt.Graphics;

public class Cell {

	private int x, y, dimension, xIndex, yIndex; // x and y positions and the length of each dimension
	private char value; // the content of a cell
	private int nEdge, eEdge, sEdge, wEdge; // the pixel values of cell boundaries
	private boolean isGate; // is this cell the gate/winning cell
	private Game game; // the game that the cell belongs in

	/**
	 * Cell Constructor
	 * 
	 * Cells are used to store moves the user has made in the puzzle
	 * 
	 * @param x         x position in pixels
	 * @param y         y position in pixels
	 * @param dimension the length of each side
	 * @param value     the value of the cell - 0 = blank, 1 = horizontal, 2 =
	 *                  vertical, 3 = red
	 * @param xIndex    the x position of the cell in 2D array of cells
	 * @param yIndex    the y position of the cell in 2S array of cells
	 * @param game      the game that the cell is in
	 */
	public Cell(int x, int y, int dimension, char value, int xIndex, int yIndex, Game game) {
		this.x = x;
		this.y = y;
		this.dimension = dimension;
		this.value = value;
		this.xIndex = xIndex;
		this.yIndex = yIndex;
		this.game = game;

		this.nEdge = 0;
		this.eEdge = 0;
		this.sEdge = 0;
		this.wEdge = 0;
		// setting the borders of the cell
		setBorders();
		this.isGate = false;

	}

	/**
	 * Draws the cell
	 * 
	 * @param g the graphics object used to draw cell
	 */
	public void draw(Graphics g) {
		// is this cell standard
		if (!isGate) {
			// border of cell
			g.setColor(Color.black);
			g.drawRect(x, y, dimension, dimension);

			// fill cell
			g.setColor(new Color(230,230,230));
			g.fillRect(x + 1, y + 1, dimension - 2, dimension - 2);
			
			//Making the cells appear 3D
			for (int i = 0; i < dimension / 20; i++) {
				//Bottom and right bevels
				g.setColor(new Color(217,217,217));
				g.drawLine(x + i, y + dimension - i, x + dimension, y + dimension - i);
				g.drawLine(x + dimension - 2 - i, y + i, x + dimension - 2 - i, y + dimension);

				//Top and left bevels
				g.setColor(new Color(242,242,242));
				g.drawLine(x + 1, y + i, x + dimension - 2 - i, y + i);
				g.drawLine(x + 1 + i, y, x + i, y + dimension - i);
			}

		} else { // winning cell
			g.setColor(Color.black);
			g.drawRect(x, y, dimension, dimension);

			// fill cell orange
			g.setColor(new Color(244,122,0));
			g.fillRect(x + 1, y + 1, dimension - 2, dimension - 2);
			
			for (int i = 0; i < dimension / 20; i++) {
				//Bottom and right bevels
				g.setColor(new Color(218,109,0));
				g.drawLine(x + i, y + dimension - i, x + dimension, y + dimension - i);
				g.drawLine(x + dimension - 2 - i, y + i, x + dimension - 2 - i, y + dimension);

				//Top and left bevels
				g.setColor(new Color(255,135,14));
				g.drawLine(x + 1, y + i, x + dimension - 2 - i, y + i);
				g.drawLine(x + 1 + i, y, x + i, y + dimension - i);
			}
		}
	}

	/**
	 * 
	 * @return the value of the cell - 0 = blank, 1 = horizontal, 2 = vertical, 3 =
	 *         red
	 */
	public char getValue() {
		return value;
	}

	/**
	 * Sets the value of the cell
	 * 
	 * @param value - 0 = blank, 1 = horizontal, 2 = vertical, 3 = red
	 */
	public void setValue(char value) {
		this.value = value;
	}

	/**
	 * Sets the border of the cell in pixels
	 */
	public void setBorders() {
		nEdge = y;
		eEdge = x + dimension; // dimension = length/width of cell
		sEdge = y + dimension;
		wEdge = x;
	}

	/**
	 * Used to find the values of cell's edges
	 * 
	 * @param direction the edge to check - e = east, etc.
	 * @return the value of the border passed
	 */
	public int getEdge(char direction) {
		switch (direction) {
		case 'n':
			return nEdge;
		case 'e':
			return eEdge;
		case 's':
			return sEdge;
		case 'w':
			return wEdge;
		default:
			return -1;
		}
	}

	/**
	 * Checks whether this cell is the gate cell(winning cell)
	 */
	public void checkGate() {
		// is this cell halfway down the right hand side of the game layout
		if (xIndex == game.indexBoundary && yIndex == game.indexBoundary / 2) {
			isGate = true;
		}
	}

	/**
	 * Checks to see if game has been won
	 * 
	 * @return whether game has been won
	 */
	public boolean getWin() {
		if (isGate) {
			if (value == '3') {
				// if this cell is gate and the red block is above it, then game is won
				return true;
			}
		}
		return false;
	}

}

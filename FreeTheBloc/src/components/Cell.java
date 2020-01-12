package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Cell {
	
	private int x, y, dimension; // x and y positions and the length of each dimension
	private char value; // the content of a cell
	boolean nBorder, eBorder, sBorder, wBorder;
	
	// value - 0 = blank, 1 = horizontal, 2 = vertical, 3 = red
	public Cell(int x, int y, int dimension, char value, Game game, boolean nBorder, boolean eBorder, boolean sBorder,boolean wBorder ) {
		this.x = x;
		this.y = y;
		this.dimension = dimension;
		this.value = value;
		this.nBorder = nBorder;
		this.eBorder = eBorder;
		this.sBorder = sBorder;
		this.wBorder = wBorder;
	}
	
	public void draw(Graphics g) {
		// border of cell
		g.setColor(Color.cyan);
		g.drawRect(x , y , dimension , dimension);
		
		// fill cell
		g.setColor(Color.red);
		g.fillRect(x+ 10, y+ 10, dimension - 20, dimension - 20);
	}
	
	public char getValue() {
		return value;
	}
	
	public void setValue(char value) {
		this.value = value;
	}
	
}

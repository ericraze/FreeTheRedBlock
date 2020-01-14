package components;

import java.awt.Color;
import java.awt.Graphics;


public class Cell {
	
	private int x, y, dimension; // x and y positions and the length of each dimension
	private char value; // the content of a cell
	boolean nBorder, eBorder, sBorder, wBorder;
	private int nEdge, eEdge, sEdge, wEdge;
	
	// value - 0 = blank, 1 = horizontal, 2 = vertical, 3 = red
	public Cell(int x, int y, int dimension, char value, Game game) {
		this.x = x;
		this.y = y;
		this.dimension = dimension;
		this.value = value;
		this.nEdge = 0;
		this.eEdge = 0;
		this.sEdge = 0;
		this.wEdge = 0;
		setBorders();
		
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
	
	public void setBorders() {
		nEdge = y;
		eEdge = x + dimension;
		sEdge = y + dimension;
		wEdge = x;
	}
	
	public int getEdge(char direction) {
		switch(direction) {
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
	
}

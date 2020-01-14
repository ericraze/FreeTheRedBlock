package components;

import java.awt.Color;
import java.awt.Graphics;


public class Cell {
	
	private int x, y, dimension, xIndex, yIndex; // x and y positions and the length of each dimension
	private char value; // the content of a cell
	boolean nBorder, eBorder, sBorder, wBorder;
	private int nEdge, eEdge, sEdge, wEdge;
	private boolean isGate, isWon;
	private Game game;
	
	// value - 0 = blank, 1 = horizontal, 2 = vertical, 3 = red
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
		setBorders();
		this.isGate = false;
		this.isWon = false;
		
	}
	
	public void draw(Graphics g) {
		if(!isGate) {
		// border of cell
		g.setColor(Color.cyan);
		g.drawRect(x , y , dimension , dimension);
		
		// fill cell
		g.setColor(new Color(139,69,19));
		g.fillRect(x+ 1, y+ 1, dimension - 2, dimension - 2);
		} else {
			g.setColor(Color.cyan);
			g.drawRect(x , y , dimension , dimension);
			
			// fill cell
			g.setColor(Color.orange);
			g.fillRect(x+ 1, y+ 1, dimension - 2, dimension - 2);
		}
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
	
	public void checkGate() {
		if(xIndex == game.indexBoundary && yIndex == game.indexBoundary / 2) {
			isGate = true;
		}
	}
	
	public boolean getWin() {
		if(isGate) {
			if(value == '3') {
				isWon = true;
				return true;
			}
		} 
		return false;
	}
	
}

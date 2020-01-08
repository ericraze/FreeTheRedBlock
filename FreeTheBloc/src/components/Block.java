package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Block{

	int x, y; // the x and y positions of block in the puzzle layout
	int width, height; // width and height of block in units
	int xOffset, yOffset; // Offsets for moving the block
	private int blockID; // the integer that represents the block in the puzzle layout
	private boolean isHorizontal; // used to check which direction the block can slide in
	private Puzzle puzzle; // the puzzle in which the block is used
	private Point lastCursorPoint; // the last mouse position
	private boolean mouseIn = false;
	private boolean mousePressed = false;
	
	/**
	 * 
	 * @param x the x position of the upper left corner of the block
	 * @param y the y position of the upper left corner of the block
	 * @param width the width of the block in integer units
	 * @param height the height of the block in integer units
	 * @param blockID the integer associated with the block in the puzzle layout
	 * @param isHorizontal boolean value used to determine whether block can slide horizontally
	 * @param puzzle the puzzle object that the block is used in
	 */
	public Block(int x, int y, int width, int height, int blockID,
			boolean isHorizontal, Puzzle puzzle) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.blockID = blockID;
		this.isHorizontal = isHorizontal;
		this.puzzle = puzzle;
		this.lastCursorPoint = new Point();
		this.xOffset = 0;
		this.yOffset = 0;
		this.mouseIn = false;
		this.mousePressed = false;
	}
	
	public void drag(MouseEvent e) {
		
		System.out.println(mousePressed + " " + mouseIn);
	
		System.out.println(x + " " + y);
		if(mousePressed && mouseIn) {
			x = e.getX() - xOffset;
			y = e.getY() - yOffset; 
		}
		
		
	}
	
	public void pressed(MouseEvent e) {
		mouseOver(e);
		
		if(this.mouseIn) {
			xOffset = e.getX() - x;
			yOffset = e.getY() - y;
			this.mousePressed = true;
		}
		
		
		
		//System.out.println("pressed " + xOffset +" " + yOffset);//eric
		
	}
	
	public void released() {
		xOffset = 0;
		yOffset = 0;
		this.mousePressed = false;
	}
	

	/**
	 * checks if the mouse is over block
	 */
	public void mouseOver(MouseEvent e) {
		
		boolean isMouseXIn = e.getX() > this.x  && e.getX() < this.x + this.width;
		boolean isMouseYIn = e.getY() > this.y && e.getY() < this.y + this.height;
		if(isMouseXIn && isMouseYIn) {
			
			this.mouseIn = true;
		}else {
			this.mouseIn = false;
			
		}
	}
	
	public void draw(Graphics g) {
		 
		g.setColor(Color.red);
		g.fillRect(x, y, width, height);
		
		if(this.mouseIn) {
			g.setColor(Color.pink);
			g.drawRect(x, y, width, height);
		}
	}
	
}

package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

public class Block extends JButton{

	int xPos, yPos; // the x and y postions of block in the puzzle layout
	int wid, hei; // width and height of block in units
	int xOffset, yOffset; // Offsets for moving the block
	private int blockID; // the integer that represents the block in the puzzle layout
	private boolean isHorizontal; // used to check which direction the block can slide in
	private Puzzle puzzle; // the puzzle in which the block is used
	private Point lastCursorPoint; // the last mouse position
	/**
	 * 
	 * @param xPos the x position of the upper left corner of the block
	 * @param yPos the y position of the upper left corner of the block
	 * @param wid the width of the block in integer units
	 * @param hei the height of the block in integer units
	 * @param blockID the integer associated with the block in the puzzle layout
	 * @param isHorizontal boolean value used to determine whether block can slide horizontally
	 * @param puzzle the puzzle object that the block is used in
	 */
	public Block(int xPos, int yPos, int wid, int hei, int blockID,
			boolean isHorizontal, Puzzle puzzle) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.wid = wid;
		this.hei = hei;
		this.blockID = blockID;
		this.isHorizontal = isHorizontal;
		this.puzzle = puzzle;
		this.setLayout(null);
		this.setBackground(Color.red);
		this.setSize(wid, hei);
		this.setLocation(xPos, yPos);
		this.lastCursorPoint = new Point();
		this.xOffset = 0;
		this.yOffset = 0;
	}
	
	public void drag(MouseEvent e) {
		System.out.println("block " + e.getX() +" " + e.getY());//eric
		xPos = e.getX() - xOffset;
		yPos = e.getY() - yOffset; 
		setLocation(xPos, yPos);
	}
	
	public void pressed(MouseEvent e) {
		
		xOffset = e.getX() - xPos;
		yOffset = e.getY() - yPos;
		System.out.println("pressed " + xOffset +" " + yOffset);//eric
		
	}
	
	public void released() {
		xOffset = 0;
		yOffset = 0;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(xPos, yPos, wid, hei);
	}
}

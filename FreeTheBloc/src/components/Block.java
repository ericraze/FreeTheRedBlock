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
		this.setBackground(Color.red);
		this.setSize(wid, hei);
		this.setLocation(xPos, yPos);
		this.lastCursorPoint = new Point();
	}
	
	public void move(MouseEvent e) {
		System.out.println("X " + e.getX() + " Y " + e.getY());
		xPos = e.getX();
		yPos = e.getY();
		setLocation(xPos, yPos);
	}
	
	
}

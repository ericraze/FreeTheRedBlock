package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Block {

	private int x, y, xOffset, yOffset; // x and y positions of block
	private int width, height; // width and height of block
	private boolean isHorizontal; // Whether block is horizontal or vertical
	private boolean isRed; // Whether block is the red block
	private boolean isPressed;
	private boolean mouseIn;
	private Cell[][] cells; // Cells in game
	private Game game; // game block is in
	
	public Block(int x, int y, int width, int height, boolean isHorizontal, boolean isRed, Cell[][] cells, Game game) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isHorizontal = isHorizontal;
		this.isRed = isRed;
		this.cells = cells;
		this.game = game;
		
		this.isPressed = false;
		this.xOffset = 0;
		this.yOffset = 0;
		this.mouseIn = false;
	}

	public void draw(Graphics g) {
		// border of block
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		// fill block
		g.setColor(Color.green);
		g.fillRect(x + 10, y + 10, width - 20, height - 20);
	}

	public void pressed(MouseEvent e) {
		isPressed = true;

		// saving the offsets for smooth movement
		xOffset = e.getX() - x;
		yOffset = e.getY() - y;
	}

	public void released(MouseEvent e) {
		isPressed = false;

		// resetting the offsets
		xOffset = 0;
		yOffset = 0;
	}

	public void moved(MouseEvent e) {
		mouseOver(e);

		// if block is pressed, make the block follow the mouse
		if (isPressed && mouseIn) {
			if (!isRed) {
				if (isHorizontal) {
					// if horizontal, follow mouse x movement
					x = e.getX() - xOffset;
				} else if (!isHorizontal) {
					// if vertical, follow mouse y movement
					y = e.getY() - yOffset;
				}
			} else {
				// if red, follow mouse x movement
				x = e.getX() - xOffset;
			}
			int[] cellUnder = cellOn(e.getX(), e.getY());
			System.out.println(cellUnder[0] + " " + cellUnder[1]);//eric

		}
	}

	public void mouseOver(MouseEvent e) {

		// checking to see if mouse is within block
		boolean isMouseXIn = e.getX() > x && e.getX() < x + width;
		boolean isMouseYIn = e.getY() > y && e.getY() < y + height;

		if (isMouseXIn && isMouseYIn) {
			mouseIn = true;
		} else {
			mouseIn = false;
		}
	}
	
	public int[] cellOn(int x, int y) {
		int cellX = (int) Math.floor(x / game.unitsToPixels);
		int cellY = (int) Math.floor(y / game.unitsToPixels);
		int[] cellOn = {cellX, cellY};
		return cellOn;
	}
	

}

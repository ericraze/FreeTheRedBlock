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

	public Block(int x, int y, int width, int height, boolean isHorizontal, boolean isRed) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isHorizontal = isHorizontal;
		this.isRed = isRed;
		this.isPressed = false;
		this.xOffset = 0;
		this.yOffset = 0;
		this.mouseIn = false;
	}

	public void draw(Graphics g) {
		//System.out.println("x " + x + " y " + y + " width " + width + " height " + height); //eric
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);
		g.setColor(Color.green);
		g.fillRect(x + 10, y + 10, width - 20, height - 20);
	}

	public void pressed(MouseEvent e) {
		isPressed = true;
		xOffset = e.getX() - x;
		yOffset = e.getY() - y;
	}
	
	public void released(MouseEvent e) {
		isPressed = false;
		xOffset = 0;
		yOffset = 0;
	}
	
	public void moved(MouseEvent e) {
		mouseOver(e);
		
		if(isPressed && mouseIn) {
			x = e.getX() - xOffset;
			y = e.getY() - yOffset;
			
			System.out.println(x); // eric
			
		}
	}
	
	public void mouseOver(MouseEvent e) {
		boolean isMouseXIn = e.getX() > x  && e.getX() < x + width;
		boolean isMouseYIn = e.getY() > y && e.getY() < y + height ;
		if(isMouseXIn && isMouseYIn) {
			mouseIn = true;
		}else {
			mouseIn = false;
		}
	}

}

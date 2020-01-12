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
	private boolean isMoved;
	private boolean isStart;
	private int[] initialPosition; // initial position of block in array

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
		this.isMoved = false;
		this.isStart = true;
		this.initialPosition = new int[] {x / game.unitsToPixels, y / game.unitsToPixels};
	}

	public void draw(Graphics g) {
		// border of block
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		// fill block
		g.setColor(Color.green);
		g.fillRect(x + 10, y + 10, width - 20, height - 20);//eric
		//System.out.println(x + " " + y);// eric
		if(isStart) {
			updateCells();
			isStart = false;
		}
	}

	public void moved(MouseEvent e) {
		boolean canMove = false;
		mouseOver(e);

		// if block is pressed, make the block follow the mouse
		if (isPressed && mouseIn) {
			isMoved = true;
			if (!isRed) {
				if (isHorizontal) {
					if(e.getX() - xOffset > x) {
						canMove = nextCell('e', e);
						
					} else if(e.getX() - xOffset < x) {
						
						canMove = nextCell('w', e);
						
					}

					if(canMove) {
					x = e.getX() - xOffset;
					}
				} else if (!isHorizontal) {
					// if vertical, follow mouse y movement
					y = e.getY() - yOffset;
				}
			} else {
				// if red, follow mouse x movement
				x = e.getX() - xOffset;
			}

		}
	}
	
	public void pressed(MouseEvent e) {
		isPressed = true;
		
		
		
		initialPosition = cellOn(e.getX(), e.getY());//eric
		
		// saving the offsets for smooth movement
		xOffset = e.getX() - x;
		yOffset = e.getY() - y;
	}

	public void released(MouseEvent e) {
		isPressed = false;
		if (isMoved) {
			snapTo(e);
			updateCells();
			isMoved = false;
		}

		// resetting the offsets
		xOffset = 0;
		yOffset = 0;

	}
	
	
	public void snapTo(MouseEvent e) {
		// Snapping block to correct position upon release
		if (!isRed) {
			// if horizontal
			if (isHorizontal) {
				// Getting position of block within cell

				// Finding the decimal
				double number = (double) x / game.unitsToPixels;
				String sNumber = String.valueOf(number);
				sNumber = sNumber.substring(sNumber.indexOf("."));
				number = Double.parseDouble(sNumber);

				// if block is at least halfway, move right
				if (number >= 0.5) {
					x += game.unitsToPixels - number * game.unitsToPixels;
				}
				// if block is less than half way, move left
				else {
					x -= number * game.unitsToPixels;
				}

				// if vertical
			} else if (!isHorizontal) {
				// Getting position of block within cell

				// Finding the decimal
				double number = (double) y / game.unitsToPixels;
				String sNumber = String.valueOf(number);
				sNumber = sNumber.substring(sNumber.indexOf("."));
				number = Double.parseDouble(sNumber);

				// if block is at least halfway, move right
				if (number >= 0.5) {
					y += game.unitsToPixels - number * game.unitsToPixels;
				}
				// if block is less than half way, move left
				else {
					y -= number * game.unitsToPixels;
				}
			}

			// if block is red
		} else {
			// Getting position of block within cell

			// Finding the decimal
			double number = (double) x / game.unitsToPixels;
			String sNumber = String.valueOf(number);
			sNumber = sNumber.substring(sNumber.indexOf("."));
			number = Double.parseDouble(sNumber);

			// if block is at least halfway, move right
			if (number >= 0.5) {
				x += game.unitsToPixels - number * game.unitsToPixels;
			}
			// if block is less than half way, move left
			else {
				x -= number * game.unitsToPixels;
			}
		}
	}

	public void updateCells() {
		if(!isRed) {
			if(isHorizontal) {
				
				// Removing cells from initial position
				for(int i = 0; i < width / game.unitsToPixels; i++) {
					
					//Changing initial positions to blank
					cells[initialPosition[0]] [initialPosition[1]+i].setValue('0');
				}
				
				// Updating all cells under block
				for(int i = 0; i < width / game.unitsToPixels  ; i++) {
					//getting coordinates of cell to replace
					int[] cellCoordinates = cellOn((x + i * 100), y);
					
					cells[cellCoordinates[0]][cellCoordinates[1]].setValue('1');
				}
			} else if(!isHorizontal) {
				
				
				
				// Removing cells from initial position
				for(int i = 0; i < height / game.unitsToPixels ; i++) { //eric
					
					System.out.println(initialPosition[1] + " " + initialPosition[0]);//eric
					//System.out.println(cells[initialPosition[0]] [initialPosition[1]].getValue());//eric
					
					//Changing initial positions to blank
					cells[initialPosition[1]] [initialPosition[0]].setValue('0');
				}
				
				// Updating all cells under block
				for(int i = 0; i < height / game.unitsToPixels ; i++) {
					//getting coordinates of cell to replace
					int[] cellCoordinates = cellOn(x, y + i * 100);
					//cells[cellCoordinates[0]][cellCoordinates[1]].setValue('2'); eric
				}
			}
			//if Red
		} else {
			
			// Removing cells from initial position
			for(int i = 0; i < width / game.unitsToPixels; i++) {
				
				//Changing initial positions to blank
				cells[initialPosition[0]] [initialPosition[1] + i].setValue('0');
			}
			
			// Updating all cells under block
			for(int i = 0; i < width / game.unitsToPixels ; i++) {
				//getting coordinates of cell to replace
				int[] cellCoordinates = cellOn((x + i * 100), y);
				cells[cellCoordinates[0]][cellCoordinates[1]].setValue('3');
			}
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
		int[] cellOn = {cellX , cellY }; //eric
		return cellOn;
	}

	public int getEdge(char edge) {
		
		// returning appropriate edge location
		switch(edge) {
		
		// east
		case 'e': 
			return x + width;
			
		// west	
		case 'w':
			return x;
		
		// south
		case 's':
			return y + height;
		
		//north
		case 'n':
			return y;
		default:
			return -1;
		}
	}
	
	public boolean nextCell(char direction, MouseEvent e) {
		int location;
		char value;
		int[] cellUnder = new int[2];
		
		
		switch(direction) {
		
		//Checking next cell west
		case 'w':
			
			//getting location of edge
			location = getEdge('w');
			//location -= (e.getX() - xOffset);//eric
			
			//getting cell in location
			cellUnder = cellOn(location - game.unitsToPixels, y);
			break;
			
		//Checking next cell
		case 'e':
			
			//Checking next cell
			location = getEdge('e');
			//location += (e.getX() - xOffset);//eric

			//getting cell in location
			cellUnder = cellOn(location + game.unitsToPixels, y);

			break;
		//Checking next cell	
		case 's':
			
			//Checking next cell
			location = getEdge('s');
			location += (e.getY() - yOffset);
			
			//getting cell in location
			cellUnder = cellOn(x, location);
			break;
		//Checking next cell
		case 'n':
			
			//Checking next cell
			location = getEdge('n');
			location -= (e.getY() - yOffset);
			
			//getting cell in location
			cellUnder = cellOn(x, location);
			break;
		}
		

		
		try { 
			// try to get value of cell in cell array
			value = cells[cellUnder[0]][cellUnder[1]].getValue();
			
		} catch (ArrayIndexOutOfBoundsException ignore) {
			// if cell is not valid return false
			return false;
		}
		
		
		
		// if value is blank, block can move into it
		if(value == '0') {
			return true;
			
		// if block isn't blank, block cannot move into it	
		} else {
			return false;
		}
		
	}
	
}

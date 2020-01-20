package components;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;

public class Block {

	private int x, y, xOffset, yOffset; // x and y positions of block
	private int width, height; // width and height of block
	private boolean isHorizontal; // Whether block is horizontal or vertical
	private boolean isRed; // Whether block is the red block
	private boolean isPressed; // If this block is pressed
	private boolean mouseIn; // Is mouse hovering over block
	private Cell[][] cells; // Cells in game
	private Game game; // game block is in
	private boolean isMoved; // is the block being moved
	private boolean isStart; // have any moves been made yet
	private int[] initialPosition; // initial position of block in array
	private int eastConstraint, westConstraint, northConstraint, southConstraint; // Block movement constraints
	private int widthCells, heightCells; // width and height of block in cells

	/**
	 * Block constructor
	 * 
	 * @param x            the x position of block in pixels
	 * @param y            the y position of block in pixels
	 * @param width        the width of block in pixels
	 * @param height       the height of block in pixels
	 * @param isHorizontal if the block is horizontal or vertical
	 * @param isRed        if the block is the main block
	 * @param cells        array of cells that the block corresponds with
	 * @param game         the game in which the block is in
	 */
	public Block(int x, int y, int width, int height, boolean isHorizontal, boolean isRed, Cell[][] cells, Game game) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isHorizontal = isHorizontal;
		this.isRed = isRed;
		this.cells = cells;
		this.game = game;

		this.widthCells = width / game.unitsToPixels; // Finding the width and height of block in cells
		this.heightCells = height / game.unitsToPixels;
		this.isPressed = false;
		this.xOffset = 0;
		this.yOffset = 0;
		this.mouseIn = false;
		this.isMoved = false;
		this.isStart = true;
		this.initialPosition = new int[] { x / game.unitsToPixels, y / game.unitsToPixels }; // finding the initial cell
																								// that block is on
		this.eastConstraint = x;
		this.westConstraint = x;
		this.northConstraint = y;
		this.southConstraint = y;
	}

	/**
	 * Used to draw the block
	 * 
	 * @param g the graphics object that is used to draw the block
	 */
	public void draw(Graphics g) {
		if (!isRed) {
			// border of block
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);

			// fill block
			g.setColor(new Color(77,77,255));
			g.fillRect(x + 1, y + 1, width - 2, height - 2);

			// Making bevels appear 3D
			for (int i = 0; i < game.unitsToPixels / 15; i++) {
				//Bottom and right bevels
				g.setColor(new Color(52,52,255));
				g.drawLine(x + i, y + height - i, x + width - 2, y + height - i);
				g.drawLine(x + width - 2 - i, y + i, x + width - 2 - i, y + height);

				//Top and left bevels
				g.setColor(new Color(103,103,255));
				g.drawLine(x + 1, y + i, x + width - 2 - i, y + i);
				g.drawLine(x + 1 + i, y, x + i, y + height - i);
			}

			if (isStart) {
				updateCells();
				isStart = false;
			}
		} else {
			// border of block
			g.setColor(Color.black);
			g.drawRect(x, y, width, height);

			// fill block
			g.setColor(new Color(204, 0, 0));
			g.fillRect(x + 1, y + 1, width - 2, height - 2);

			// Making bevels appear 3D
			for (int i = 0; i < game.unitsToPixels / 15; i++) {
				// Bottom and right bevels
				g.setColor(new Color(179,0,0));
				g.drawLine(x + i, y + height - i, x + width - 2, y + height - i);
				g.drawLine(x + width - 2 - i, y + i, x + width - 2 - i, y + height);

				//Top and left bevels
				g.setColor(new Color(230,0,0));
				g.drawLine(x + 1, y + i, x + width - 2 - i, y + i);
				g.drawLine(x + 1 + i, y, x + i, y + height - i);
			}

			if (isStart) {
				updateCells();
				isStart = false;
			}
		}
	}

	/**
	 * Changes the block's position when moved
	 * 
	 * @param e the mouse event that triggered moved method
	 */
	public void moved(MouseEvent e) {
		mouseOver(e);

		// is block is pressed
		if (isPressed && mouseIn) {
			isMoved = true;
			if (!isRed) {
				if (isHorizontal) {
					// is the desired location within bounds
					if (e.getX() - xOffset + width <= eastConstraint && e.getX() - xOffset >= westConstraint) {
						// changing position of block
						x = e.getX() - xOffset;
					}

				} else if (!isHorizontal) {
					// is the desired location within bounds
					if (e.getY() - yOffset + height <= southConstraint && e.getY() - yOffset >= northConstraint) {
						// changing position of block
						y = e.getY() - yOffset;
					}
				}

			} else { // Is red
				// is the desired location within bounds
				if (e.getX() - xOffset + width <= eastConstraint && e.getX() - xOffset >= westConstraint) {
					// changing position of block
					x = e.getX() - xOffset;
				}
			}

		}
	}

	/**
	 * Calculates offsets, constraints and initial position of block when pressed by
	 * user
	 * 
	 * @param e the mouse event when pressed
	 */
	public void pressed(MouseEvent e) {
		isPressed = true;
		mouseOver(e);

		// calculate constraints
		setConstraints();

		// cell position of block before it is moved
		initialPosition = cellOn(x, y);

		// saving the offsets for smooth movement
		xOffset = e.getX() - x;
		yOffset = e.getY() - y;

	}

	/**
	 * Adjusts the block's position, updates cells and resets according values
	 * 
	 * @param e the mouse event when mouse is released
	 */
	public void released(MouseEvent e) {
		isPressed = false;

		// If block was moved
		if (isMoved) {
			// adjust block to proper position and update cell values
			snapTo();
			updateCells();
			isMoved = false;

			// Increase moves if the block positon has moved
			int[] newBlockPos = cellOn(x, y);
			if (Math.abs(newBlockPos[0] - initialPosition[0]) >= 1
					|| Math.abs(newBlockPos[1] - initialPosition[1]) >= 1) {
				game.numMoves++;
				if(game.checkMoves) {
					game.winMoves = game.numMoves;
				}
			}

		}
		// resetting the offsets
		xOffset = 0;
		yOffset = 0;

	}

	/**
	 * Ensures that the block is always in a legal position If the block is released
	 * halfway between cells, it will adjust the block's position so that it is
	 * sitting flush on a cell
	 */
	public void snapTo() {
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

	/**
	 * Changes the values of cells upon release
	 */
	public void updateCells() {
		char value = '4';

		if (isHorizontal || isRed) {

			// Saving value of cell
			value = cells[initialPosition[0]][initialPosition[1]].getValue();

			// removing cells
			for (int i = 0; i < width / game.unitsToPixels; i++) {
				// Setting initial position to blank
				cells[initialPosition[0] + i][initialPosition[1]].setValue('0');
			}

			// replacing cells
			for (int i = 0; i < width / game.unitsToPixels; i++) {

				// Relocating blank block in cell layout
				int[] cellToChange = cellOn(x + (i * game.unitsToPixels), y);
				cells[cellToChange[0]][cellToChange[1]].setValue(value);
			}

		} else if (!isHorizontal) {
			// Saving value of cell
			value = cells[initialPosition[0]][initialPosition[1]].getValue();

			// removing cells
			for (int i = 0; i < height / game.unitsToPixels; i++) {

				// Setting initial position to blank
				cells[initialPosition[0]][initialPosition[1] + i].setValue('0');
			}

			// replacing cells
			for (int i = 0; i < height / game.unitsToPixels; i++) {

				// Relocating blank block in cell layout
				int[] cellToChange = cellOn(x, y + (i * game.unitsToPixels));
				cells[cellToChange[0]][cellToChange[1]].setValue(value);
			}
		}

	}

	/**
	 * Checks to see if the mouse is hovering over the block
	 * 
	 * @param e the mouse event that triggers the method
	 */
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

	/**
	 * Used to find the cell that the block is on top of
	 * 
	 * @param x the x position in pixels of the block
	 * @param y the y position in pixels of the block
	 * @return cellOn[0] - the x position of cell in 2D array cellOn[1] - the y
	 *         position of cell in 2D array
	 */
	public int[] cellOn(int x, int y) {
		// x and y positions of cell
		int cellX = (x / game.unitsToPixels);
		int cellY = (y / game.unitsToPixels);
		int[] cellOn = { cellX, cellY };
		return cellOn;
	}

	/**
	 * Used to find the pixel value of any edge of block
	 * 
	 * @param edge the edge to check
	 * @return the pixel value of the edge
	 */
	public int getEdge(char edge) {

		// returning appropriate edge location
		switch (edge) {

		// east
		case 'e':
			return x + width;

		// west
		case 'w':
			return x;

		// south
		case 's':
			return y + height;

		// north
		case 'n':
			return y;
		default:
			return -1;
		}
	}

	/**
	 * Used to set the constraints of block movement, calculated each press of block
	 * Constraint determined by neighboring blocks or borders
	 */
	public void setConstraints() {
		if (mouseIn && isPressed) {
			if (!isRed) {
				if (isHorizontal) {
					int[] checkCell = cellOn(x, y);
					int count = 0;
					// default constraint values are borders of current position
					eastConstraint = cells[checkCell[0] + widthCells - 1][checkCell[1]].getEdge('e');
					westConstraint = cells[checkCell[0]][checkCell[1]].getEdge('w');

					// east boundary
					while (count + checkCell[0] + widthCells <= game.indexBoundary) {

						// if cell being scanned is empty, eastern edge of cell is constraint
						if (cells[count + widthCells + checkCell[0]][checkCell[1]].getValue() == '0') {
							eastConstraint = cells[count + widthCells + checkCell[0]][checkCell[1]].getEdge('e');

							// if cell being scanned is occupied, break
						} else if (cells[count + widthCells + checkCell[0]][checkCell[1]].getValue() != '0') {
							break;
						}

						count++;

					}

					count = 1;
					// west Boundary
					while (checkCell[0] - count >= 0) {

						// if cell being scanned is empty, eastern edge of cell is constraint
						if (cells[checkCell[0] - count][checkCell[1]].getValue() == '0') {
							westConstraint = cells[checkCell[0] - count][checkCell[1]].getEdge('w');

							// if cell being scanned is occupied, break
						} else if (cells[checkCell[0] - count][checkCell[1]].getValue() != '0') {
							break;
						}

						count++;

					}

				} else if (!isHorizontal) {
					int[] checkCell = cellOn(x, y);
					int count = 0;
					// default constraint values are borders of current position
					northConstraint = cells[checkCell[0]][checkCell[1]].getEdge('n');
					southConstraint = cells[checkCell[0]][checkCell[1] + heightCells - 1].getEdge('s');

					// south boundary
					while (count + checkCell[1] + heightCells <= game.indexBoundary) {

						// if cell being scanned is empty, southern edge of cell is constraint
						if (cells[checkCell[0]][checkCell[1] + count + heightCells].getValue() == '0') {
							southConstraint = cells[checkCell[0]][checkCell[1] + count + heightCells].getEdge('s');

							// if cell being scanned is occupied, break
						} else if (cells[checkCell[0]][checkCell[1] + count + heightCells].getValue() != '0') {
							break;
						}

						count++;

					}

					count = 1;

					// north Boundary
					while (checkCell[1] - count >= 0) {

						// if cell being scanned is empty, eastern edge of cell is constraint
						if (cells[checkCell[0]][checkCell[1] - count].getValue() == '0') {
							northConstraint = cells[checkCell[0]][checkCell[1] - count].getEdge('n');

							// if cell being scanned is occupied, break
						} else if (cells[checkCell[0]][checkCell[1] - count].getValue() != '0') {
							break;
						}
						count++;
					}
				}
			} else {
				int[] checkCell = cellOn(x, y);
				int count = 0;
				// default constraint values are borders of current position
				eastConstraint = cells[checkCell[0] + widthCells - 1][checkCell[1]].getEdge('e');
				westConstraint = cells[checkCell[0]][checkCell[1]].getEdge('w');

				// east boundary
				while (count + checkCell[0] + widthCells <= game.indexBoundary) {

					// if cell being scanned is empty, eastern edge of cell is constraint
					if (cells[count + widthCells + checkCell[0]][checkCell[1]].getValue() == '0') {
						eastConstraint = cells[count + widthCells + checkCell[0]][checkCell[1]].getEdge('e');

						// if cell being scanned is occupied, break
					} else if (cells[count + widthCells + checkCell[0]][checkCell[1]].getValue() != '0') {

						break;
					}
					count++;
				}

				count = 1;
				// west Boundary
				while (checkCell[0] - count >= 0) {

					// if cell being scanned is empty, eastern edge of cell is constraint
					if (cells[checkCell[0] - count][checkCell[1]].getValue() == '0') {
						westConstraint = cells[checkCell[0] - count][checkCell[1]].getEdge('w');

						// if cell being scanned is occupied, break
					} else if (cells[checkCell[0] - count][checkCell[1]].getValue() != '0') {
						break;
					}
					count++;
				}
			}

		}
	}

}

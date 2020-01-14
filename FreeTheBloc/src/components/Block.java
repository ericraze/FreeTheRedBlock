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
	private int eastConstraint, westConstraint, northConstraint, southConstraint;
	private int widthCells, heightCells;

	public Block(int x, int y, int width, int height, boolean isHorizontal, boolean isRed, Cell[][] cells, Game game) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.isHorizontal = isHorizontal;
		this.isRed = isRed;
		this.cells = cells;
		this.game = game;

		this.widthCells = width / game.unitsToPixels;
		this.heightCells = height / game.unitsToPixels;
		this.isPressed = false;
		this.xOffset = 0;
		this.yOffset = 0;
		this.mouseIn = false;
		this.isMoved = false;
		this.isStart = true;
		this.initialPosition = new int[] { x / game.unitsToPixels, y / game.unitsToPixels };
		this.eastConstraint = x;
		this.westConstraint = x;
		this.northConstraint = y;
		this.southConstraint = y;
	}

	public void draw(Graphics g) {
		// border of block
		g.setColor(Color.black);
		g.drawRect(x, y, width, height);

		// fill block
		g.setColor(Color.green);
		g.fillRect(x + 10, y + 10, width - 20, height - 20);// eric
		// System.out.println(x + " " + y);// eric
		if (isStart) {
			updateCells();
			isStart = false;
		}
	}

	public void moved(MouseEvent e) {
		mouseOver(e);

		// if block is pressed, make the block follow the mouse
		if (isPressed && mouseIn) {
			isMoved = true;
			if (!isRed) {
				if (isHorizontal) {

					if (e.getX() - xOffset + width <= eastConstraint && e.getX() - xOffset >= westConstraint) {// eric

						x = e.getX() - xOffset;
					} // eric

				} else if (!isHorizontal) {
					if(e.getY() - yOffset + height <= southConstraint && e.getY() - yOffset >= northConstraint) {
						y = e.getY() - yOffset;
					}
					
				}
			} else {
				// if red, follow mouse x movement
				if (e.getX() - xOffset + width <= eastConstraint && e.getX() - xOffset >= westConstraint) {// eric

					x = e.getX() - xOffset;
				} // eric
			}

		}
	}

	public void pressed(MouseEvent e) {
		isPressed = true;
		// calculate constraints
		mouseOver(e);
		setConstraints();

		initialPosition = cellOn(x, y);

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
		eastConstraint = 0;
		westConstraint = 0;
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
		int cellX = (x / game.unitsToPixels);

		int cellY = (y / game.unitsToPixels);
		int[] cellOn = { cellX, cellY }; // eric
		return cellOn;
	}

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

	public void setConstraints() {
		if (mouseIn && isPressed) {
			if (!isRed) {

				if (isHorizontal) {

					int[] checkCell = cellOn(x, y);
					int count = 0;
					eastConstraint = cells[checkCell[0] + widthCells - 1][checkCell[1]].getEdge('e');
					westConstraint = cells[checkCell[0]][checkCell[1]].getEdge('w');

					// east boundary
					while (count + checkCell[0] + widthCells <= game.indexBoundary) {

						// if cell being scanned is empty, eastern edge of cell is constraint
						if (cells[count + widthCells + checkCell[0]][checkCell[1]].getValue() == '0') {

							eastConstraint = cells[count + widthCells + checkCell[0]][checkCell[1]].getEdge('e');

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

						} else if (cells[checkCell[0] - count][checkCell[1]].getValue() != '0') {

							break;
						}

						count++;

					}

				} else if (!isHorizontal) {
					int[] checkCell = cellOn(x, y);
					int count = 0;
					northConstraint = cells[checkCell[0]][checkCell[1]].getEdge('n');
					southConstraint = cells[checkCell[0]][checkCell[1] + heightCells - 1].getEdge('s');

					// south boundary
					while (count + checkCell[1] + heightCells <= game.indexBoundary) {

						// if cell being scanned is empty, southern edge of cell is constraint
						if (cells[checkCell[0]][checkCell[1] + count + heightCells].getValue() == '0') {

							southConstraint = cells[checkCell[0]][checkCell[1] + count + heightCells].getEdge('s');

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

						} else if (cells[checkCell[0]][checkCell[1] - count].getValue() != '0') {

							break;
						}

						count++;
					}
				}
			} else {
				System.out.println("eric");// eric
				int[] checkCell = cellOn(x, y);
				int count = 0;
				eastConstraint = cells[checkCell[0] + widthCells - 1][checkCell[1]].getEdge('e');
				westConstraint = cells[checkCell[0]][checkCell[1]].getEdge('w');

				// east boundary
				while (count + checkCell[0] + widthCells <= game.indexBoundary) {

					// if cell being scanned is empty, eastern edge of cell is constraint
					if (cells[count + widthCells + checkCell[0]][checkCell[1]].getValue() == '0') {

						eastConstraint = cells[count + widthCells + checkCell[0]][checkCell[1]].getEdge('e');

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

					} else if (cells[checkCell[0] - count][checkCell[1]].getValue() != '0') {

						break;
					}

					count++;

				}
			}

		}
	}

}

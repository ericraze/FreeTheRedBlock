package components;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

public class Game extends JPanel implements MouseListener, MouseMotionListener {

	private int numBlocks = 0;
	private Cell[][] cells;
	private Block[] blocks;
	private int counter = 0;
	int dimension = 0;
	int unitsToPixels;
	int indexBoundary;

	
	// 0 = blank, 10's = horizontal, 20's = vertical, 30 = red
	public Game(int[][] gameLayout, int unitsToPixels) {

		this.unitsToPixels = unitsToPixels;
		
		// setting size of the game
		this.dimension = gameLayout.length * unitsToPixels;
		
		this.indexBoundary =  (dimension / unitsToPixels) - 1;
		
		setSize(dimension, dimension);

		// adding listeners to the game
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		// Initializing cell array
		cells = new Cell[gameLayout.length][gameLayout[0].length];
		
		// Amount of blocks in game and instantiating cells
		for (int i = 0; i < gameLayout.length; i++) {
			for (int j = 0; j < gameLayout[i].length; j++) {
				
				// instantiating cell object for each cell in array with the properties
				// x, y, side length, value of block over cell
				cells[j][i] = new Cell(j * unitsToPixels, i * unitsToPixels, unitsToPixels,
						String.valueOf(gameLayout[j][i]).charAt(0), this, i == 0, j==indexBoundary, i == indexBoundary, j == 0);
				
				
				if (gameLayout[j][i] > 0) {
					this.numBlocks++;
				}
			}
		//	System.out.println(""); //eric
		}

		// Initializing array of blocks
		blocks = new Block[this.numBlocks];

		// Initializing arrays of cells and blocks
		// Looping through game layout array
		for (int i = 0; i < gameLayout.length; i++) {
			for (int j = 0; j < gameLayout[i].length; j++) {


				// instantiating block objects
				// a 1 in tens column is horizontal block
				if (String.valueOf(gameLayout[j][i]).charAt(0) == '1') {
					// create new block where first digit is orientation and second is length
					blocks[counter] = new Block(j * unitsToPixels, i * unitsToPixels,
							gameLayout[j][i] % 10 * unitsToPixels, 1 * unitsToPixels, true, false, cells, this);
					counter++;

					// a 2 in the tens column is vertical block
				} else if (String.valueOf(gameLayout[j][i]).charAt(0) == '2') {
					// create new block where first digit is orientation and second is length
					blocks[counter] = new Block(j * unitsToPixels, i * unitsToPixels, 1 * unitsToPixels,
							gameLayout[j][i] % 10 * unitsToPixels, false, false, cells, this);
					counter++;

					// a 3 in the tens column is red block
				} else if (String.valueOf(gameLayout[j][i]).charAt(0) == '3') {
					// create new block where first digit is orientation and second is length
					blocks[counter] = new Block(j * unitsToPixels, i * unitsToPixels,
							gameLayout[j][i] % 10 * unitsToPixels, 1 * unitsToPixels, false, true, cells, this); 
					counter++;

				}

			}
		}

	}

	// Drawing all components in game
	public void paintComponent(Graphics g) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				// looping through and drawing cells
				cells[j][i].draw(g);
				char ch = cells[j][i].getValue();//eric
				//System.out.print(ch);//eric
			}
			//System.out.println("");//eric
		}
		//ystem.out.println("");//eric
		for (int i = 0; i < blocks.length; i++) {
			// looping through and drawing blocks
			blocks[i].draw(g);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].pressed(e);
		}

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].released(e);
		}
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].moved(e);
		}
		repaint();

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}

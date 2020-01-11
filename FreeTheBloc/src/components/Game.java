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

	// 0 = blank, 10's = horizontal, 20's = vertical, 30 = red
	public Game(int[][] gameLayout, int unitsToPixels, int size) {
		setSize(size, size);
		
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		
		
		// getting amount of blocks and cells in game
		for (int i = 0; i < gameLayout.length; i++) {
			for (int j = 0; j < gameLayout[i].length; j++) {
				if (gameLayout[i][j] > 0) {
					this.numBlocks++;
				}
			}
		}

		// Creating arrays of cells and blocks
		cells = new Cell[gameLayout.length][gameLayout[0].length];
		blocks = new Block[this.numBlocks];

		// Initializing arrays of cells and blocks
		for (int i = 0; i < gameLayout.length; i++) {
			for (int j = 0; j < gameLayout[i].length; j++) {
				cells[i][j] = new Cell(j * unitsToPixels, i * unitsToPixels, unitsToPixels,
						String.valueOf(gameLayout[i][j]).charAt(0));
				if (String.valueOf(gameLayout[i][j]).charAt(0) == '1'
						|| String.valueOf(gameLayout[i][j]).charAt(0) == '3') {
					blocks[counter] = new Block(j * unitsToPixels, i * unitsToPixels, 1 * unitsToPixels,
							1 * unitsToPixels, false, false); // eric
					counter++;
				} else if (String.valueOf(gameLayout[i][j]).charAt(0) == '2') {
					blocks[counter] = new Block(j * unitsToPixels, i * unitsToPixels, 1 * unitsToPixels,
							gameLayout[i][j] * unitsToPixels, false, false); // eric
					counter++;
				}

			}
		}

	}

	//Drawing all components in game
	public void paintComponent(Graphics g) {
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				//looping through and drawing cells
				cells[i][j].draw(g);
			}
		}
		for (int i = 0; i < blocks.length; i++) {
			//looping through and drawing blocks
			blocks[i].draw(g);
		}	
	}

	

	@Override
	public void mousePressed(MouseEvent e) {
		for(int i = 0; i < blocks.length; i++) {
			blocks[i].pressed(e);
		}
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < blocks.length; i++) {
			blocks[i].released(e);
		}
		repaint();
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		
		
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

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		for(int i = 0; i < blocks.length; i++) {
			blocks[i].moved(e);
		}repaint();

	}

	

}

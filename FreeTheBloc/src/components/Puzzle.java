package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Puzzle extends Component implements MouseMotionListener {

	// making block
	Block red = new Block(50, 50, 50, 100, 1, true, this);

	JFrame f = new JFrame();
	JButton blue = new JButton();

	private int highScore, optimalScore, numMoves; // score information and number of moves
	int[][] boardLayout; // all the blocks on the puzzle in a grid

	// Blocks[] aBlocks = new Blocks[3];
	// array to store all blocks

	/**
	 * @param optimalScore the best possible score for puzzle
	 * @param boardLayout  all components on the board in an integer grid
	 */
	public Puzzle(int optimalScore, int[][] boardLayout) {
		this.optimalScore = optimalScore;
		this.boardLayout = boardLayout;
		this.highScore = 0;
		this.numMoves = 0;

	}

	public void display() {

		red.addMouseMotionListener(this);

		// ABSOLUTE LAYOUT POSITIONING
		f.setLayout(null);

		// setting up frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 500);

		blue.setLocation(100, 100);
		blue.setSize(100, 100);
		blue.setBackground(Color.blue);

		f.add(blue);

		f.add(red);
		f.setVisible(true);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		red.move(e);
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {

		
	}

	@Override
	public void paint(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(red.xPos, red.yPos, red.wid, red.hei);
	}


}

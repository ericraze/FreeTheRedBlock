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

public class Puzzle extends JPanel implements MouseMotionListener, MouseListener{

	// making block
	Block red = new Block(50, 50, 50, 50, 1, true, this);

	private int highScore, optimalScore, numMoves; // score information and number of moves
	int[][] boardLayout; // all the blocks on the puzzle in a grid
	int unitToPixels; // pixel value of length of one unit in boardLayout array

	/**
	 * @param optimalScore the best possible score for puzzle
	 * @param boardLayout  all components on the board in an integer grid
	 */
	public Puzzle(int optimalScore, int[][] boardLayout, int unitToPixels) {
		this.optimalScore = optimalScore;
		this.boardLayout = boardLayout;
		this.highScore = 0;
		this.numMoves = 0;
		this.unitToPixels = unitToPixels;
		this.addMouseListener(this);
		this.addMouseMotionListener(this);
		this.setSize(500, 500);
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		red.draw(g);
		
	}
	
	@Override
	public void mouseMoved(MouseEvent e) {
		red.mouseOver(e);
		red.drag(e);
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
		red.pressed(e);
		red.drag(e);
		repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		red.released();
		repaint();
	}
	
	@Override
	public void mouseDragged(MouseEvent e) {

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

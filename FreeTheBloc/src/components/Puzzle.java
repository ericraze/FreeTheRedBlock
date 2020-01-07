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

	JFrame f = new JFrame();

	private int highScore, optimalScore, numMoves; // score information and number of moves
	int[][] boardLayout; // all the blocks on the puzzle in a grid


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
		red.addMouseListener(this);

		// ABSOLUTE LAYOUT POSITIONING
		f.setLayout(null);

		// setting up frame
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(500, 500);

		f.add(red);
		f.setVisible(true);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("puz " + e.getX() +" " + e.getY());//eric
		red.drag(e);
		repaint();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		red.draw(g);
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		red.pressed(e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		red.released();
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

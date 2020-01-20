package components;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import audio.GameMusic;

public class Game extends JPanel implements MouseListener, MouseMotionListener {

	private int numBlocks; // number of blocks in game
	private Cell[][] cells; // array of cells in game
	private Block[] blocks; // array of blocks in game
	private int counter; // counter
	private boolean isWon; // is game won
	int numMoves, winMoves; // Number of moves made and moves made when won
	boolean checkMoves;
	int dimension; // length of a single unit in game in pixels
	int unitsToPixels; // amount of pixels in a unit
	int indexBoundary; // width and height of game in cells
	private int redY; // Y Value of the red Block
	private int perfectScore; // Best score user can achieve
	private CardLayout cardLayout; // Cardlayout used in display
	private JPanel container; // Container Panel used in card layout
	private GameMusic gameMusics; // gameMusic object to play music
	private boolean playMusic;

	/**
	 * Game Constructor Used to create new games
	 * 
	 * @param gameLayout     a 2D array of the game - There are flag values
	 *                       scattered through this array that correspond to blocks.
	 *                       A non-zero number signifies that a block's top left
	 *                       corner is in the top left corner of that array index.
	 *                       The first digit of a non-zero number denotes the
	 *                       block's orientation (1 = horizontal, 2 = vertical, 3 =
	 *                       red). The second digit represents it's cell
	 *                       length/width
	 * 
	 * @param unitsToPixels  the amount of pixels per index in the array - this
	 *                       controls the size of cells and blocks
	 * 
	 * @param perfectScore   the best possible score for this game
	 *
	 * @param cl             the cardLayout used in this game
	 *
	 * @param containerPanel the containerPanel used in the cardLayout
	 * 
	 * @param gameMusic      a music object to play music
	 */
	// 0 = blank, 10's = horizontal, 20's = vertical, 30 = red
	public Game(int[][] gameLayout, int unitsToPixels, int perfectScore, CardLayout cl, JPanel containerPanel,
			GameMusic gameMusic) {

		setLayout(new BorderLayout());

		this.checkMoves = true;
		this.unitsToPixels = unitsToPixels;
		this.perfectScore = perfectScore;
		this.cardLayout = cl;
		this.container = containerPanel;
		this.gameMusics = gameMusic;

		// setting size of the game
		this.dimension = gameLayout.length * unitsToPixels;
		this.indexBoundary = (dimension / unitsToPixels) - 1;
		this.numMoves = 0;
		playMusic = true;

		// Setting size of JPanel the block will be displayed in
		setSize(dimension, dimension);

		// adding listeners to the game
		this.addMouseListener(this);
		this.addMouseMotionListener(this);

		// Initializing cell array
		cells = new Cell[gameLayout.length][gameLayout[0].length];

		// Recording the amount of blocks in game and instantiating cells
		for (int i = 0; i < gameLayout.length; i++) {
			for (int j = 0; j < gameLayout[i].length; j++) {

				// instantiating cell object for each cell in array with the properties
				// x, y, side length, value of block over cell
				cells[j][i] = new Cell(j * unitsToPixels, i * unitsToPixels, unitsToPixels,
						String.valueOf(gameLayout[j][i]).charAt(0), j, i, this);
				// checking whether this cell is the gate cell
				cells[j][i].checkGate();

				// if this index's value is nonzero it represents a block
				if (gameLayout[j][i] > 0) {
					this.numBlocks++;
				}
			}

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
					redY = (i + 1) * unitsToPixels - (unitsToPixels / 4);

				}

			}
		}

		setSize(dimension, dimension);

		// Adding button to return to main menu
		JButton mainMenuButton = new JButton("Main Menu");
		mainMenuButton.setPreferredSize(new Dimension(dimension, 25));

		add(mainMenuButton, BorderLayout.SOUTH);

		mainMenuButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// show instructions screen
				cardLayout.show(container, "m");
				gameMusics.stop();

				String pathname = System.getProperty("user.dir");
				String musicFile = pathname + "\\res\\easyLevelMusic.wav";

				gameMusics.playMusic(musicFile);
			}
		});

	}

	/**
	 * Used to paint the game
	 */
	public void paintComponent(Graphics g) {

		// Clearing then setting background
		g.clearRect(0, 0, dimension + 100, dimension + 100);
		g.setColor(Color.DARK_GRAY);
		g.fillRect(0, 0, getWidth(), getHeight());
		if (!isWon) { // if game is not won
			for (int i = 0; i < cells.length; i++) {
				for (int j = 0; j < cells[i].length; j++) {
					// looping through and drawing cells
					cells[j][i].draw(g);
				}
			}

			// drawing current score
			g.setColor(Color.MAGENTA);
			String movesMessage = "Moves: " + numMoves;
			int fontSize = 90 / movesMessage.length();
			Font font = new Font("Monospaced", Font.BOLD, fontSize + 5);
			g.setFont(font);
			g.drawString(movesMessage, unitsToPixels * cells.length + 5, dimension / 2 + 50);

			// drawing perfect score
			g.setColor(Color.RED);
			font = new Font("Monospaced", Font.ITALIC, fontSize + 5);
			g.setFont(font);
			g.drawString("Perfect", unitsToPixels * cells.length + 5, 50);
			g.drawString("Score:", unitsToPixels * cells.length + 5, 75);
			g.drawString(String.valueOf(perfectScore), unitsToPixels * cells.length + 5, 100);

			// Drawing arrows
			g.setColor(Color.orange);
			font = new Font("Monospaced", Font.BOLD, 75);
			g.setFont(font);
			g.drawString("\u21c9", unitsToPixels * cells.length + 5, redY);

			for (int i = 0; i < blocks.length; i++) {
				// looping through and drawing blocks
				blocks[i].draw(g);
			}
		} else {
			// Playing victory music
			if (playMusic) {
				gameMusics.stop();

				String pathname = System.getProperty("user.dir");
				String musicFile = pathname + "\\res\\victoryMusic.wav";

				gameMusics.playMusic(musicFile);

				playMusic = false;
			}
			// If game is won
			g.setColor(new Color(244, 122, 0));
			g.fillRect(0, 0, dimension + 100, dimension + 100 - 25);

			// drawing winning message
			g.setColor(new Color(217, 217, 217));
			Font font = new Font("Monospaced", Font.BOLD, 60);
			g.setFont(font);
			g.drawString("You Won!", dimension / 2 - 100, dimension / 2 - 100);

			// drawing Player score
			g.setColor(new Color(77, 77, 255));
			String movesMessage = "Your Score: " + winMoves;
			font = new Font("Monospaced", Font.BOLD, 20);
			g.setFont(font);
			g.drawString(movesMessage, dimension / 2 - 100, dimension / 2 - 25);

			// drawing perfect score
			g.setColor(Color.RED);
			font = new Font("Monospaced", Font.BOLD, 20);
			g.setFont(font);
			g.drawString("Perfect", dimension / 2 - 100, dimension / 2 + 25);
			g.drawString("Score:", dimension / 2, dimension / 2 + 25);
			g.drawString(String.valueOf(perfectScore), dimension / 2 + 100, dimension / 2 + 25);

		}

	}

	/**
	 * Used to handle mouse presses
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].pressed(e);
		}
	}

	/**
	 * Used to handle mouse release
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		for (int i = 0; i < blocks.length; i++) {
			blocks[i].released(e);
		}

		// Checking to see if game is won
		for (int i = 0; i < cells.length; i++) {
			for (int j = 0; j < cells[i].length; j++) {
				if (!isWon) { // if red block is over gate cell
					isWon = cells[indexBoundary][indexBoundary / 2].getWin();
				}
			}
		}

		repaint();
	}

	/**
	 * Used to handle mouse movement
	 */
	@Override
	public void mouseDragged(MouseEvent e) {
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

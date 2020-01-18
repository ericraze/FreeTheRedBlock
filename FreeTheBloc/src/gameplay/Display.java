package gameplay;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import components.Game;

public class Display {

	int unitsToPixels; // Pixels to array element
	int sizes, gameSize; // size of game and size of display window
	int[][] layout; // layout of the game
	int perfectScore; // best possible score for the given game
	int levelChosen; // The level chosen by the player

	JFrame f = new JFrame("Free The Block"); // Window that the game is displayed in

	// Panels used to play the game
	JPanel containerPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JPanel menuPanel = new JPanel();
	JPanel namePanel = new JPanel(new BorderLayout());// eric
	JPanel instructionsPanel = new JPanel();
	JPanel levelsPanel = new JPanel();

	// Buttons used to switch between scenes
	JButton levelsButton = new JButton("Levels");
	JButton menuButtonName = new JButton("Menu");
	JButton nameButton = new JButton("Name");
	JButton menuButtonInstructions = new JButton("Menu");
	JButton instructionsButton = new JButton("Instructions");

	// Level buttons
	JButton[] levelButtons;

	// Cardlayout object
	CardLayout cl = new CardLayout();

	// Game
	Game game;

	public Display(int size) {
		this.sizes = size;

		// Level object to find the amount of games to determine the amount of level
		// selection buttons
		Level test = new Level(0);

		// Initializing array of buttons for level selection
		levelButtons = new JButton[test.levelLayouts.length];

		// Setting up the panels
		namePanel();
		menuPanel();
		instructionsPanel();

		// Setting up the cardlayout
		containerPanel.setLayout(cl);

		containerPanel.add(menuPanel, "m");
		containerPanel.add(namePanel, "n");
		containerPanel.add(instructionsPanel, "i");
		containerPanel.add(levelsPanel, "l");

		cl.show(containerPanel, "m");

		// Action listeners
		levelsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// show level selection screen
				cl.show(containerPanel, "l");
			}
		});

		menuButtonName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// show main menu screen
				cl.show(containerPanel, "m");
			}
		});

		menuButtonInstructions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// show main menu screen
				cl.show(containerPanel, "m");
			}
		});

		nameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// show name screen
				cl.show(containerPanel, "n");
			}
		});

		instructionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				// show instructions screen
				cl.show(containerPanel, "i");
			}
		});

		// Checking to see if level buttons are pressed
		ActionListener listener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (e.getSource() instanceof JButton) {

					// Converting text of JButton to int
					String text = ((JButton) e.getSource()).getText();
					levelChosen = Integer.parseInt(text);

					// New instance of level with the value of level button that is pressed
					Level level = new Level(levelChosen);

					// Setup for the new game
					perfectScore = level.perfectScore;
					gameSize = sizes - 100;
					layout = level.layout;
					unitsToPixels = gameSize / layout[0].length;

					// Instantiating game and setting game panel to game object
					game = new Game(layout, unitsToPixels, perfectScore);
					gamePanel = game;

					//adding game to the card layout
					containerPanel.add(gamePanel, "g");

					//Show the game
					cl.show(containerPanel, "g");
				}
			}
		};

		// Instantiating level selection buttons
		for (int i = 0; i < test.levelLayouts.length; i++) {
			//Giving each button the appropriate name
			levelButtons[i] = new JButton(String.valueOf(i));
			
			//adding listeners and adding it to the panel for display
			levelButtons[i].addActionListener(listener);
			levelsPanel.add(levelButtons[i]);
		}

		//setting up the frame
		f.add(containerPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setSize(size, size);
		f.setVisible(true);

	}

	public void namePanel() {
		
		// Printing name and setting up fonts
		JLabel nameLabel = new JLabel();
		String nameMessage = "A Game By Ericraze";
		nameLabel.setForeground(Color.red);
		nameLabel.setFont(new Font("Monospace", Font.BOLD, sizes / nameMessage.length()));
		nameLabel.setText("<html><div style='text-align: center;'>" + nameMessage + "</div></html>");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setVerticalAlignment(JLabel.CENTER);

		//adding everything to name panel
		namePanel.add(menuButtonName, BorderLayout.SOUTH);
		namePanel.add(nameLabel, BorderLayout.CENTER);
		namePanel.setBackground(Color.blue);

	}

	public void menuPanel() {

		//making and setting up necessary panels
		menuPanel.setLayout(new BorderLayout());
		JPanel menuPanelSouth = new JPanel(new GridLayout(1, 3));

		//background
		menuPanel.setBackground(Color.blue);
		menuPanelSouth.setBackground(Color.blue);

		//Buttons
		menuPanelSouth.add(nameButton);
		menuPanelSouth.add(levelsButton);
		menuPanelSouth.add(instructionsButton);

		//Displaying messages
		String sMessage = "Free The Red Block";
		JLabel message = new JLabel();
		message.setFont(new Font("Monospace", Font.BOLD, 36));
		message.setForeground(Color.red);
		message.setText("<html><div style='text-align: center;'>" + sMessage + "</div></html>");
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setVerticalAlignment(JLabel.CENTER);

		//adding to menuPanel
		menuPanel.add(message, BorderLayout.CENTER);
		menuPanel.add(menuPanelSouth, BorderLayout.SOUTH);
	}

	public void instructionsPanel() {
		
		//Setting layout for the instructions panel
		instructionsPanel.setLayout(new BorderLayout());
		JLabel instructions = new JLabel();
		
		//Instructions
		String sInstructions = "<html>Free The Red Block<br>An Intuitive Puzzle Game<br><br>A puzzle is solved when the red block is "
				+ "located above the gate, a yellow square.<br>"
				+ "The gate is always on the rightmost side of the puzzle, across from the red block. "
				+ "The direction<br>in which to slide the red block is shown by the double arrows located to the right of the puzzle<br>"
				+ "However, there will be other blocks that obstruct the red block's path.<br>"
				+ "To rearrange any block you must click on, then drag the block you wish to move.<br>"
				+ "Horizontal blocks may only be dragged along their X-axis.<br>"
				+ "Vertical blocks can only slide on their Y-axis.<br>"
				+ "Blocks cannot move through each other or the boundaries of the puzzle.<br>"
				+ "Try to solve each puzzle using the fewest moves possible!<br><br>" + "GOOD LUCK</html>";

		//Setting font, color and alignment of instructions
		instructions.setFont(new Font("Monospace", Font.PLAIN, 14));
		instructions.setForeground(new Color(220,220,220));
		instructions.setText("<html><div style='text-align:;'>" + sInstructions + "</div></html>");
		instructions.setHorizontalAlignment(JLabel.CENTER);
		instructions.setVerticalAlignment(JLabel.CENTER);

		//adding to the instructions panel
		instructionsPanel.setBackground(Color.DARK_GRAY);
		instructionsPanel.add(instructions, BorderLayout.CENTER);
		instructionsPanel.add(menuButtonInstructions, BorderLayout.SOUTH);

	}

}

package gameplay;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.swing.JPanel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import components.Game;
import audio.GameMusic;

public class Display {

	int unitsToPixels; // Pixels to array element
	int sizes, gameSize; // size of game and size of display window
	int[][] layout; // layout of the game
	int perfectScore; // best possible score for the given game
	int levelChosen; // The level chosen by the player
	private String musicFile; // Filepath for playing music
	GameMusic gameMusic; // GameMusic object to play music

	JFrame f = new JFrame("Free The Block"); // Window that the game is displayed in

	// Panels used to play the game
	JPanel containerPanel = new JPanel();
	JPanel gamePanel = new JPanel();
	JPanel menuPanel = new JPanel();
	JPanel namePanel = new JPanel(new BorderLayout());
	JPanel instructionsPanel = new JPanel();
	JPanel levelsPanel = new JPanel();

	// Buttons used to switch between scenes
	JButton levelsButton = new JButton("Levels");
	JButton menuButtonName = new JButton("Menu");
	JButton nameButton = new JButton("Author");
	JButton menuButtonInstructions = new JButton("Menu");
	JButton instructionsButton = new JButton("Instructions");

	// Level buttons
	JButton[] levelButtons;

	// Cardlayout object
	CardLayout cl = new CardLayout();

	// Game
	Game game;

	public Display(int size) throws IOException {

		String pathname = System.getProperty("user.dir");

		this.musicFile = pathname + "\\res\\menuSelectionMusic.wav";

		this.sizes = size;

		// Level object to find the amount of games to determine the amount of level
		// selection buttons
		Level test = new Level(0);

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
					game = new Game(layout, unitsToPixels, perfectScore, cl, containerPanel, gameMusic);
					gamePanel = game;

					// adding game to the card layout
					containerPanel.add(gamePanel, "g");

					// Show the game
					cl.show(containerPanel, "g");

					// Switching music depending on level chosen
					gameMusic.stop();
					if (Integer.parseInt(((JButton) e.getSource()).getText()) < levelButtons.length / 3) { // if easy
						String pathname = System.getProperty("user.dir");
						musicFile = pathname + "\\res\\easyLevelMusic.wav";																			// level

					} else if (Integer.parseInt(((JButton) e.getSource()).getText()) < levelButtons.length * 2 / 3) { // if
						String pathname = System.getProperty("user.dir");
						musicFile = pathname + "\\res\\mediumLevelMusic.wav";																									// medium
																														// level

					} else if (Integer.parseInt(((JButton) e.getSource()).getText()) < levelButtons.length - 1) { // if
						String pathname = System.getProperty("user.dir");
						musicFile = pathname + "\\res\\hardLevelMusic.wav";																								// hard

					} else if (Integer.parseInt(((JButton) e.getSource()).getText()) == levelButtons.length - 1) { // if
						String pathname = System.getProperty("user.dir");
						musicFile = pathname + "\\res\\lastLevelMusic.wav";	
						
					}

					gameMusic.playMusic(musicFile);

				}
			}
		};
		// Initializing array of buttons for level selection
		levelButtons = new JButton[test.levelLayouts.length];

		// Instantiating level selection buttons
		for (int i = 0; i < test.levelLayouts.length; i++) {
			// Giving each button the appropriate name
			levelButtons[i] = new JButton(String.valueOf(i));

			// adding listeners
			levelButtons[i].addActionListener(listener);

		}

		// Setting up the panels
		namePanel();
		menuPanel();
		instructionsPanel();
		levelsPanel();

		// Setting up the cardlayout
		containerPanel.setLayout(cl);

		containerPanel.add(menuPanel, "m");
		containerPanel.add(namePanel, "n");
		containerPanel.add(instructionsPanel, "i");
		containerPanel.add(levelsPanel, "l");

		cl.show(containerPanel, "m");

		// Play music initially
		gameMusic = new GameMusic();
		gameMusic.playMusic(musicFile);

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

		// setting up the frame
		f.add(containerPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setSize(size, size);
		f.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		f.setLocation(dim.width / 2 - f.getSize().width / 2, dim.height / 2 - f.getSize().height / 2);
		f.setVisible(true);

	}

	public void namePanel() {

		// Printing name and setting up fonts
		JLabel nameLabel = new JLabel();
		String nameMessage = "<html>A Game By<br>@ericraze</html>";
		nameLabel.setForeground(new Color(204, 0, 0));
		nameLabel.setFont(new Font("Monospace", Font.ROMAN_BASELINE, sizes / 10));
		nameLabel.setText("<html><div style='text-align: center;'>" + nameMessage + "</div></html>");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setVerticalAlignment(JLabel.CENTER);

		// adding everything to name panel
		namePanel.add(menuButtonName, BorderLayout.SOUTH);
		namePanel.add(nameLabel, BorderLayout.CENTER);
		namePanel.setBackground(new Color(230, 230, 230));

	}

	public void menuPanel() throws IOException {

		String pathname = System.getProperty("user.dir");

		pathname += "\\res\\mainMenuBackground.jpg";

		BufferedImage img = ImageIO.read(new File(pathname));

		ImageIcon image = new ImageIcon(img);

		JLabel labelImage = new JLabel("", image, JLabel.CENTER);
		labelImage.setSize(sizes, sizes);

		// making and setting up necessary panels
		menuPanel.setLayout(new BorderLayout());
		JPanel menuPanelSouth = new JPanel(new GridLayout(1, 3));
		JPanel menuPanelCenter = new JPanel();

		// backgrounds
		menuPanel.setBackground(Color.DARK_GRAY);
		menuPanelSouth.setBackground(Color.DARK_GRAY);
		menuPanelCenter.setBackground(Color.DARK_GRAY);

		// Buttons

		menuPanelSouth.add(nameButton);
		menuPanelSouth.add(levelsButton);
		menuPanelSouth.add(instructionsButton);

		// Displaying messages
		String sMessage = "Free The Red Block";
		JLabel message = new JLabel();
		message.setFont(new Font("Monospace", Font.BOLD, 36));
		message.setForeground(Color.red);
		message.setText("<html><div style='text-align: center;'>" + sMessage + "</div></html>");
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setVerticalAlignment(JLabel.CENTER);

		// adding to menuPanel

		menuPanelCenter.add(labelImage, BorderLayout.SOUTH);
		menuPanel.add(menuPanelCenter, BorderLayout.CENTER);
		menuPanel.add(message, BorderLayout.NORTH);
		menuPanel.add(menuPanelSouth, BorderLayout.SOUTH);
	}

	public void instructionsPanel() {

		// Setting layout for the instructions panel
		instructionsPanel.setLayout(new BorderLayout());
		JLabel instructions = new JLabel();

		// Instructions
		String sInstructions = "<html>Free The Red Block<br>A Puzzle Game<br><br>A puzzle is solved when the red block is "
				+ "slid onto the gate, an orange square.<br>"
				+ "The gate is always to the right of the red block, its position indicated by arrows.<br>"
				+ "Blue blocks obstruct the red's path to the gate and must be rearranged.<br>"
				+ "To move a block you must click on, then drag the block you wish to move.<br>"
				+ "Horizontal blocks may only be dragged along their X-axis.<br>"
				+ "Vertical blocks can only slide on their Y-axis.<br>"
				+ "Blocks cannot move through each other or the boundaries of the puzzle.<br>"
				+ "Try to solve each puzzle using the fewest moves possible!<br><br>" + "TIME TO FAIL</html>";

		// Setting font, color and alignment of instructions
		instructions.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		instructions.setForeground(new Color(204, 0, 0));
		instructions.setText("<html><div style='text-align:;'>" + sInstructions + "</div></html>");
		instructions.setHorizontalAlignment(JLabel.CENTER);
		instructions.setVerticalAlignment(JLabel.CENTER);

		// adding to the instructions panel
		instructionsPanel.setBackground(new Color(230, 230, 230));
		instructionsPanel.add(instructions, BorderLayout.CENTER);
		instructionsPanel.add(menuButtonInstructions, BorderLayout.SOUTH);

	}

	public void levelsPanel() {

		// Labels and panels
		JPanel easyButtonsPanel = new JPanel(new BorderLayout());
		JPanel mediumButtonsPanel = new JPanel(new BorderLayout());
		JPanel hardButtonsPanel = new JPanel(new BorderLayout());

		JPanel easyButtonsCenterPanel = new JPanel(new GridLayout(1, levelButtons.length / 3));
		JPanel mediumButtonsCenterPanel = new JPanel(new GridLayout(1, levelButtons.length / 3));
		JPanel hardButtonsCenterPanel = new JPanel(new GridLayout(1, levelButtons.length / 3));

		JLabel easyLabel = new JLabel("Easy");
		JLabel mediumLabel = new JLabel("Medium");
		JLabel hardLabel = new JLabel("Hard");

		// Setting fonts and alignments of all labels
		easyLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		easyLabel.setForeground(new Color(204, 0, 0));
		easyLabel.setHorizontalAlignment(JLabel.CENTER);
		easyLabel.setVerticalAlignment(JLabel.CENTER);

		mediumLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		mediumLabel.setForeground(new Color(204, 0, 0));
		mediumLabel.setHorizontalAlignment(JLabel.CENTER);
		mediumLabel.setVerticalAlignment(JLabel.CENTER);

		hardLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 15));
		hardLabel.setForeground(new Color(204, 0, 0));
		hardLabel.setHorizontalAlignment(JLabel.CENTER);
		hardLabel.setVerticalAlignment(JLabel.CENTER);

		// Adding buttons to their corresponding panel
		for (int i = 0; i < levelButtons.length / 3; i++) {
			easyButtonsCenterPanel.add(levelButtons[i], BorderLayout.CENTER);
		}

		// Adding buttons to their corresponding panel
		for (int i = levelButtons.length / 3; i < levelButtons.length * 2 / 3; i++) {
			mediumButtonsCenterPanel.add(levelButtons[i], BorderLayout.CENTER);
		}

		// Adding buttons to their corresponding panel
		for (int i = levelButtons.length * 2 / 3; i < levelButtons.length; i++) {
			hardButtonsCenterPanel.add(levelButtons[i], BorderLayout.CENTER);
		}

		// Adding panels and lables
		easyButtonsPanel.add(easyLabel, BorderLayout.NORTH);
		easyButtonsPanel.add(easyButtonsCenterPanel, BorderLayout.CENTER);

		mediumButtonsPanel.add(mediumLabel, BorderLayout.NORTH);
		mediumButtonsPanel.add(mediumButtonsCenterPanel, BorderLayout.CENTER);

		hardButtonsPanel.add(hardLabel, BorderLayout.NORTH);
		hardButtonsPanel.add(hardButtonsCenterPanel, BorderLayout.CENTER);

		// Background of panels
		easyButtonsPanel.setBackground(new Color(230, 230, 230));
		mediumButtonsPanel.setBackground(new Color(230, 230, 230));
		hardButtonsPanel.setBackground(new Color(230, 230, 230));

		// Completing the levelsPanel
		levelsPanel.setBackground(new Color(230, 230, 230));
		levelsPanel.add(easyButtonsPanel);
		levelsPanel.add(mediumButtonsPanel);
		levelsPanel.add(hardButtonsPanel);

	}

}

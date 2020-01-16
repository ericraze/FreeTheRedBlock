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

	int unitsToPixels;
	int size, gameSize;
	int[][] layout;

	JFrame f = new JFrame("Free The Block");
	JPanel containerPanel = new JPanel();
	JPanel gamePanel;
	JPanel menuPanel = new JPanel();
	JPanel namePanel = new JPanel(new BorderLayout());// eric
	JPanel instructionsPanel = new JPanel();

	JButton gameButton = new JButton("Game");
	JButton menuButtonName = new JButton("Menu");
	JButton nameButton = new JButton("Name");
	JButton menuButtonInstructions = new JButton("Menu");
	JButton instructionsButton = new JButton("Instructions");

	CardLayout cl = new CardLayout();

	Game game;

	public Display(int size) {
		
		Level level = new Level(1);
		
		this.size = size;
		this.gameSize = size - 100;
		this.layout = level.layout;
		this.unitsToPixels = gameSize / this.layout[0].length;
		this.game = new Game(layout, unitsToPixels);

		namePanel();
		menuPanel();
		instructionsPanel();
		
		containerPanel.setLayout(cl);

		gamePanel = game;

		containerPanel.add(menuPanel, "m");
		containerPanel.add(gamePanel, "g");
		containerPanel.add(namePanel, "n");
		containerPanel.add(instructionsPanel, "i");
		cl.show(containerPanel, "m");

		gameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cl.show(containerPanel, "g");
			}
		});

		menuButtonName.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cl.show(containerPanel, "m");
			}
		});

		menuButtonInstructions.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cl.show(containerPanel, "m");
			}
		});

		nameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cl.show(containerPanel, "n");
			}
		});
		
		instructionsButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cl.show(containerPanel, "i");
			}
		});

		f.add(containerPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setSize(size, size);
		f.setVisible(true);

	}

	public void namePanel() {
		// NamePanel
		JLabel nameLabel = new JLabel();
		String nameMessage = "A Game By Ericraze";
		nameLabel.setForeground(Color.red);
		nameLabel.setFont(new Font("Monospace", Font.BOLD, size / nameMessage.length()));
		nameLabel.setText("<html><div style='text-align: center;'>" + nameMessage + "</div></html>");
		nameLabel.setHorizontalAlignment(JLabel.CENTER);
		nameLabel.setVerticalAlignment(JLabel.CENTER);
		
		namePanel.add(menuButtonName, BorderLayout.SOUTH);
		namePanel.add(nameLabel, BorderLayout.CENTER);
		namePanel.setBackground(Color.blue);

	}

	public void menuPanel() {

		menuPanel.setLayout(new BorderLayout());
		JPanel menuPanelSouth = new JPanel(new GridLayout(1, 3));

		menuPanel.setBackground(Color.blue);
		menuPanelSouth.setBackground(Color.blue);
		
		menuPanelSouth.add(nameButton);
		menuPanelSouth.add(gameButton);
		menuPanelSouth.add(instructionsButton);
		
		
		String sMessage = "Free The Red Block";
		JLabel message = new JLabel();
		message.setFont(new Font("Monospace", Font.BOLD, 36));
		message.setForeground(Color.red);
		message.setText("<html><div style='text-align: center;'>" + sMessage + "</div></html>");
		message.setHorizontalAlignment(JLabel.CENTER);
		message.setVerticalAlignment(JLabel.CENTER);
		
		menuPanel.add(message, BorderLayout.CENTER);
		menuPanel.add(menuPanelSouth, BorderLayout.SOUTH);
	}

	public void instructionsPanel() {
		instructionsPanel.setLayout(new BorderLayout());

		String sInstructions =  "<html>Free The Red Block<br>An Intuitive Puzzle Game<br><br>A puzzle is solved when the red block is "
				+ "located above the gate, a yellow square. <br>However, there will be other blocks that obstruct the red block's path.<br>"
				+ "To rearrange any block you must click on, then drag the block you wish to move.<br>"
				+ "Horizontal blocks may only be dragged along their X-axis.<br>"
				+ "Vertical blocks can only slide on their Y-axis.<br>"
				+ "Blocks cannot move through each other or the boundaries of the puzzle.<br>"
				+ "Try to beat the level using the fewest moves possible!<br><br>"
				+ "GOOD LUCK</html>";

		JLabel instructions = new JLabel();
		instructions.setFont(new Font("Monospace", Font.BOLD, 12));
		instructions.setForeground(Color.yellow);
		instructions.setText("<html><div style='text-align: center;'>" + sInstructions + "</div></html>");
		instructions.setHorizontalAlignment(JLabel.CENTER);
		instructions.setVerticalAlignment(JLabel.CENTER);
		
		instructionsPanel.setBackground(Color.blue);
		instructionsPanel.add(instructions, BorderLayout.CENTER);
		instructionsPanel.add(menuButtonInstructions, BorderLayout.SOUTH);

	}

}

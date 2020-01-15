package gameplay;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;

import components.Game;

public class Display {
	
	int unitsToPixels;
	int size, gameSize;
	int[][]layout;

	JFrame f = new JFrame("Free The Block");
	JPanel containerPanel = new JPanel();
	JPanel gamePanel;
	JPanel menuPanel = new JPanel();
	
	JButton gameButton = new JButton("Game");
	JButton menuButton = new JButton("Menu");
	
	CardLayout cl = new  CardLayout();
	
	Game game;
	
	public Display(int size, int[][] layout) {
		this.size = size;
		this.gameSize = size - 100;
		this.layout = layout;
		this.unitsToPixels = gameSize / layout[0].length;
		this.game = new Game(layout, unitsToPixels);

		containerPanel.setLayout(cl);
		
		//containerPanel.setSize(size,size);
		menuPanel.add(gameButton);
		//add menuButton to gamePanel

		gamePanel = game;
		menuPanel.setBackground(Color.blue);
		
		containerPanel.add(menuPanel, "m");
		containerPanel.add(gamePanel, "g");
		cl.show(containerPanel, "m");
		
		gameButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				cl.show(containerPanel, "g");
				
			}
		});
		
		f.add(containerPanel);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.pack();
		f.setSize(size, size);
		f.setVisible(true);
	
	}



}

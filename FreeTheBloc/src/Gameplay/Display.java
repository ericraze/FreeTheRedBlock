package Gameplay;

import javax.swing.JFrame;

import components.Game;

public class Display {
	
	
	
	static JFrame f = new  JFrame();
	static Game g;
	static int size = 600;
	
	public static void main(String[] args) {
		int[][] layout =  { 
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 12},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 22, 0},
		};
		int unitsToPixels = 100;
		
		g = new Game(layout, unitsToPixels);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(size + 30, size + 30);
		f.add(g);
		f.setVisible(true);
		
	}

}

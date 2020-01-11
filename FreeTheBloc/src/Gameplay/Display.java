package Gameplay;

import javax.swing.JFrame;

import components.Game;

public class Display {
	
	
	
	static JFrame f = new  JFrame();
	static Game g;
	static int size = 600;
	
	public static void main(String[] args) {
		int[][] layout =  { 
			{11, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
		};
		int uToP = 100;
		
		g = new Game(layout, uToP, size);
		
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setSize(size, size);
		f.add(g);
		f.setVisible(true);
		
	}

}

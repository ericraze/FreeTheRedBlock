package gameplay;


import javax.swing.JFrame;
import javax.swing.JPanel;

import components.Game;

public class Main {
	
	static int size = 600;
	static int[][]layout = { 
			{0, 0, 0, 0, 0, 0},
			{0, 0, 0, 0, 0, 0},
			{0, 0, 32, 0, 0, 0},
			{0, 0, 0, 13, 0, 0},
			{23, 0, 0, 0, 12, 0},
			{0, 0, 0, 0, 0, 0},
		};
	
	static JFrame f = new JFrame();
	static JPanel p = new JPanel();
	Display d;
	
	public static void main(String[] args) {

		Display d = new Display(size, layout);

	}
}

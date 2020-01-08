package components;

import javax.swing.JFrame;

public class MainClass {
	static Puzzle one;
	static int[][] layout = {
			{3,3},
			{3,3}
	};
	static JFrame f;
	
	
	public static void main(String[] args) {
		one = new Puzzle(10, layout, 50);
		
		f = new JFrame();//create the window with the title
		//frame setup
		f.setSize(500, 500);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.add(one);
		f.setVisible(true);
		f.setResizable(false);
	}
}

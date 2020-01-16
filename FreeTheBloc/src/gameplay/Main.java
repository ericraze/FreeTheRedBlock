package gameplay;


import javax.swing.JFrame;
import javax.swing.JPanel;


public class Main {
	
	static int size = 600;

	static JFrame f = new JFrame();
	static JPanel p = new JPanel();
	Display d;
	
	public static void main(String[] args) {

		Display d = new Display(size);

	}
}

package components;

public class MainClass {
	static Puzzle one;
	static int[][] layout = {
			{3,3},
			{3,3}
	};
	public static void main(String[] args) {
		one = new Puzzle(10, layout);
		one.display();
	}
}

/**
 * Free the Red Block
 * 
 * A simple and intuitive puzzle program. Free the red block is a geometry puzzle
 * with several different levels. The mechanics are simple, the user clicks, then drags the block he 
 * wants to move. Enjoy!
 * 
 * @author Ericraze
 * 
 * Created January 4, 2020 (Last Updated January 19, 2020)
 */

package gameplay;

import java.io.IOException; 


public class Main {
	
	//Size of game
	static int size = 600;

	Display d;
	
	public static void main(String[] args) throws IOException {

		Display d = new Display(size);

	}
}
package gameplay;

public class Level {
	
	int[][] layout;
	
	public int[][][] levelLayouts = {
			{ 
				{0, 32, 12, 0},
				{0, 0, 0, 12},
				{22, 0, 0, 0},
				{0, 22, 0, 0},
			},
			{
				{0, 12, 32, 0, 0},
				{0, 0, 0, 0, 0},
				{0, 22, 0, 22, 0},
				{0, 0, 0, 0, 12},
				{22, 0, 22, 0, 0},
				
				
			},
			{
				{12, 0, 33, 22, 0, 0},
				{0, 13, 0, 0, 12, 0},
				{0, 0, 22, 0, 0, 13},
				{13, 0, 22, 0, 0, 0},
				{0, 0, 23, 0, 0, 0},
				{0, 22, 0, 0, 22, 0},
				
				
				
				
				
			}
			
			
	};

	
	public Level(int level) {
		this.layout = levelLayouts[level];
	}

}

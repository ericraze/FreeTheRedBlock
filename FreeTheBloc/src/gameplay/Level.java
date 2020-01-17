package gameplay;

public class Level {
	
	int[][] layout;
	int perfectScore;
	
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
				{12, 0, 32, 22, 0, 0},
				{0, 13, 0, 0, 12, 0},
				{0, 0, 22, 0, 0, 13},
				{13, 0, 22, 0, 0, 0},
				{0, 0, 23, 0, 0, 0},
				{0, 22, 0, 0, 22, 0},
				
				
				
				
				
			}
			
			
	};

	public int[] perfectScores = {4, 6, 12};
	
	public Level(int level) {
		this.perfectScore = perfectScores[level];
		this.layout = levelLayouts[level];
	}

}

package gameplay;

public class Level {
	
	int[][] layout;
	int perfectScore;
	
	//All levels
	public int[][][] levelLayouts = {
			
			//Easy Levels
			{ 
				{0, 0, 32, 23, 0, 0},
				{0, 0, 0, 0, 22, 0},
				{0, 22, 0, 0, 0, 13},
				{23, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 23, 0, 0, 0, 0},
			},
			{
				{0, 0, 32, 0, 0, 0},
				{22, 0, 0, 0, 22, 0},
				{0, 0, 0, 0, 0, 13},
				{0, 0, 0, 0, 13, 0},
				{0, 23, 0, 0, 0, 0},
				{23, 0, 0, 0, 0, 0},
				
				
			},
			
			{
				{22, 0, 32, 0, 13, 0},
				{13, 0, 0, 0, 0, 0},
				{0, 23, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{22, 0, 0, 0, 22, 0},

			},
			
			
			{
				{0, 0, 32, 22, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 12, 0, 0},
				{23, 0, 0, 0, 13, 0},
				{22, 0, 22, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},

			},
			
			{
				{0, 13, 32, 0, 0, 0},
				{0, 0, 0, 22, 0, 12},
				{0, 0, 0, 0, 13, 0},
				{0, 0, 0, 0, 0, 12},
				{23, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
			},
			
			{
				{22, 0, 32, 13, 0, 0},
				{13, 0, 0, 0, 0, 0},
				{0, 22, 0, 0, 0, 0},
				{0, 0, 0, 23, 0, 0},
				{0, 0, 0, 22, 0, 12},
				{0, 0, 23, 0, 0, 0},
			},
			
			{
				{13, 0, 32, 12, 22, 0},
				{0, 0, 0, 0, 12, 0},
				{0, 0, 22, 0, 0, 0},
				{0, 0, 0, 23, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 22, 0, 22, 0},
			},
			
			{
				{0, 0, 32, 22, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{12, 0, 0, 0, 22, 0},
				{0, 0, 22, 0, 13, 12},
				{23, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
			},
			
			{
				{12, 0, 32, 23, 0, 0},
				{0, 0, 0, 0, 0, 13},
				{0, 0, 0, 0, 0, 0},
				{22, 0, 0, 22, 0, 0},
				{22, 0, 0, 12, 0, 0},
				{23, 0, 0, 0, 0, 0},
			},
			
			{
				{0, 0, 32, 13, 22, 0},
				{0, 12, 0, 0, 13, 0},
				{0, 0, 0, 0, 0, 0},
				{0,23, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{23, 0, 0, 0, 0, 0},
			},
			
			//Medium Levels
			{
				{0, 0, 32, 23, 0, 0},
				{0, 0, 0, 0, 0, 13},
				{13, 0, 23, 0, 0, 0},
				{0, 22, 0, 12, 0, 0},
				{0, 0, 0, 0, 22, 0},
				{0, 0, 0, 0, 0, 0},
			},
			
			{
				{0, 0, 32, 22, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{23, 0, 0, 0, 0, 0},
				{23, 0, 0, 0, 12, 12},
				{0, 0, 0, 0, 0, 0},
				{22, 0, 0, 22, 0, 0},
			},
			
			{
				{0, 0, 32, 22, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{23, 0, 0, 0, 0, 0},
				{22, 0, 0, 0, 22, 0},
				{23, 0, 0, 12, 0, 12},
				{0, 0, 0, 0, 0, 0},
			},
			
			{
				{0, 12, 32, 12, 13, 0},
				{0, 0, 0, 0, 0, 0},
				{23, 0, 0, 0, 0, 0},
				{12, 0, 22, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 0, 23, 0, 0},
			},
			
			{
				{22, 0, 32, 0, 0, 0},
				{0, 0, 0, 22, 0, 0},
				{0, 22, 0, 0, 0, 0},
				{0, 23, 0, 0, 12, 13},
				{0, 0, 0, 0, 0, 0},
				{23, 0, 0, 0, 0, 0},
			},
			
			{
				{13, 0, 32, 0, 0, 0},
				{0, 0, 0, 22, 0, 0},
				{0, 0, 23, 0, 0, 0},
				{0, 23, 0, 0, 0, 0},
				{0, 0, 0, 0, 12, 0},
				{23, 0, 0, 0, 0, 0},
			},
			
			{
				{22, 0, 32, 0, 0, 0},
				{13, 0, 0, 22, 0, 0},
				{0, 0, 0, 0, 12, 0},
				{0, 23, 0, 0, 0, 12},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 23, 0, 0, 0},
			},
			
			{
				{0, 13, 32, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 22, 0, 13, 0},
				{23, 0, 0, 0, 0, 13},
				{0, 0, 0, 12, 0, 0},
				{23, 0, 0, 0, 0, 0},
			},
			
			{
				{0, 0, 32, 0, 0, 0},
				{0, 0, 0, 13, 22, 0},
				{23, 0, 0, 0, 0, 12},
				{0, 0, 0, 0, 0, 0},
				{0, 12, 0, 0, 0, 0},
				{0, 0, 0, 23, 0, 0},
			},
			
			{
				{12, 0, 32, 22, 0, 0},
				{0, 0, 0, 12, 0, 13},
				{23, 0, 0, 0, 12, 0},
				{22, 0, 0, 0, 0, 0},
				{0, 12, 0, 0, 0, 0},
				{0, 0, 0, 23, 0, 0},
			},
			
			//Hard Levels
			{
				{0, 0, 32, 0, 0, 12},
				{13, 0, 0, 0, 0, 0},
				{0, 12, 0, 13, 22, 0},
				{0, 0, 0, 0, 13, 12},
				{23, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 0, 0},
			},
			
			{
				{0, 0, 32, 22, 0, 0},
				{22, 0, 0, 0, 0, 0},
				{0, 12, 22, 0, 0, 0},
				{0, 0, 0, 13, 12, 12},
				{23, 0, 0, 0, 0, 0},
				{0, 22, 0, 0, 0, 0},
			},
			
			{
				{0, 12, 32, 0, 22, 0},
				{0, 0, 0, 13, 0, 0},
				{23, 0, 0, 0, 0, 12},
				{0, 0, 0, 0, 0, 0},
				{0, 22, 0, 0, 0, 12},
				{23, 0, 0, 0, 0, 0},
			},
			
			{
				{0, 0, 32, 22, 0, 0},
				{22, 0, 0, 0, 12, 0},
				{0, 23, 0, 0, 0, 0},
				{0, 22, 0, 0, 22, 0},
				{0, 0, 0, 0, 0, 0},
				{0, 0, 22, 0, 22, 0},
			},
			
			{
				{0, 0, 32, 22, 0, 0},
				{0, 0, 0, 0, 22, 0},
				{22, 0, 0, 22, 0, 0},
				{0, 23, 0, 0, 0, 0},
				{0, 23, 0, 0, 12, 12},
				{23, 0, 0, 0, 0, 0},
			},
			
			{
				{0, 0, 32, 22, 0, 0},
				{13, 0, 0, 0, 0, 0},
				{0, 0, 0, 0, 22, 0},
				{0, 22, 0, 13, 13, 0},
				{23, 0, 0, 0, 0, 12},
				{22, 0, 0, 0, 0, 0},
			},
			
			{
				{0, 0, 32, 13, 0, 0},
				{12, 0, 0, 0, 22, 0},
				{0, 22, 0, 0, 0, 12},
				{0, 13, 0, 22, 0, 0},
				{0, 0, 0, 0, 12, 0},
				{0, 0, 22, 0, 0, 0},
			},
			
			{
				{0, 0, 32, 22, 0, 0},
				{0, 13, 0, 0, 0, 0},
				{13, 0, 22, 0, 22, 0},
				{0, 0, 22, 0, 0, 12},
				{0, 0, 0, 12, 12, 0},
				{22, 0, 0, 0, 0, 0},
			},
			
			{
				{12, 0, 32, 0, 13, 0},
				{0, 0, 0, 0, 0, 0},
				{23, 0, 0, 0, 0, 0},
				{0, 12, 0, 12, 22, 0},
				{0, 0, 0, 0, 22, 0},
				{22, 0, 22, 0, 0, 0},
			},
			
			{
				{13, 0, 32, 22, 0, 0},
				{0, 0, 0, 0, 12, 13},
				{0, 0, 22, 0, 0, 0},
				{22, 0, 0, 0, 0, 0},
				{12, 12, 0, 23, 0, 0},
				{0, 0, 0, 23, 0, 0},
			},
		
			
			
	};

	//Perfect Scores
	public int[] perfectScores = {8, 7, 8, 7, 7, 7, 7, 7, 7, 9,  
								11, 10, 11, 10, 12, 13, 13, 11, 10, 10,  
								10, 12, 11, 13, 14, 13, 14, 13, 13, 15};
	
	public Level(int level) {
		this.perfectScore = perfectScores[level];
		this.layout = levelLayouts[level];
	}

}

package Model;

import Model.Nonograma.DifficultyLevels;

public interface INonograma {

	public void changeDifficultyLevel(DifficultyLevels difficulty);

	public int[] getLengthOfBlackChainsInRows();
	
	public int[] getLengthOfBlackChainsInColumns();
	
	public int[] askCorrectHint();
	
	public boolean askIfSolutionIsCorrect();
	
	public void restartGame();
	
	public void exitGame();
	
	public String notifyResult();
}

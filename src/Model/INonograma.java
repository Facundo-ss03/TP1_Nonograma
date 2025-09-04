package Model;

import java.util.List;

public interface INonograma {

	public enum DifficultyLevels { EASY, NORMAL, HARD};
	
	public static INonograma createNonograma(DifficultyLevels difficulty) {
		return new Nonograma(difficulty);
	}
	
	public void changeDifficultyLevel(DifficultyLevels difficulty);

	public String getLengthOfBlackChainsInOneRow(int row);
	
	public String getLengthOfBlackChainsInOneColumn(int column);

	public String getLengthOfBlackChainsInAllRows();
	
	public String getLengthOfBlackChainsInAllColumns();
	
	public void askCorrectHint();
	
	public int getPlayboardSize();
	
	public boolean askIfSolutionIsCorrect();
	
	public void restartGame();
	
	public void exitGame();
	
	public String notifyResult();
}

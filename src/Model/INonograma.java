package Model;

import java.util.List;

public interface INonograma {

	public enum DifficultyLevels { EASY, NORMAL, HARD};
	
	public static INonograma createNonograma(DifficultyLevels difficulty) {
		return new Nonograma(difficulty);
	}
	
	public void changeDifficultyLevel(DifficultyLevels difficulty);

	public ITuple getLengthOfBlackChainsInOneRow(int row);
	
	public ITuple getLengthOfBlackChainsInOneColumn(int column);

	public List<ITuple> getLengthOfBlackChainsInAllRows();
	
	public List<ITuple> getLengthOfBlackChainsInAllColumns();
	
	public ITuple askCorrectHint();
	
	public int getPlayboardSize();
	
	public boolean askIfSolutionIsCorrect();
	
	public void restartGame();
	
	public void exitGame();
	
	public String notifyResult();
}

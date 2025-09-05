package Model;

import java.util.List;

public interface INonograma {

	public enum DifficultyLevels { EASY, NORMAL, HARD};
	
	public static INonograma createNonograma(DifficultyLevels difficulty) {
		return new Nonograma(difficulty);
	}
	
	public void changeDifficultyLevel(DifficultyLevels difficulty);

	public String  getHorizontalStringWithLengthOfBlackChainsInOneRow(int row);
	
	public String  getVerticalStringWithLengthOfBlackChainsInOneColumn(int column);

	//public List<List<Integer>> getLengthOfBlackChainsInAllRows();
	
	//public List<List<Integer>> getLengthOfBlackChainsInAllColumns();
	
	public void askCorrectHint();
	
	public int getPlayboardSize();
	
	public boolean askIfSolutionIsCorrect();
	
	public void restartGame();
	
	public void exitGame();
}

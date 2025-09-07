package Model;

import java.util.List;

public interface INonograma {

	public enum DifficultyLevels { EASY, NORMAL, HARD, VERY_HARD};
	
	public static INonograma createNonograma(DifficultyLevels difficulty) {
		return new Nonograma(difficulty);
	}
	
	public void changeDifficultyLevel(DifficultyLevels difficulty);

	public List<Integer>  getBlackChainsLengthsInRow(int row);
	
	public List<Integer>  getBlackChainsLengthsInColumn(int column);
	
	public void markCellWithBlack(int row, int column);
	
	public void askCorrectHint();
	
	public int getPlayboardSize();
	
	public boolean askIfSolutionIsCorrect();
	
	public void restartGame();
	
	public void exitGame();
}

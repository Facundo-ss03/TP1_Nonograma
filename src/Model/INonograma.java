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

	public ICell getCellOfPlayerPlayboard(int row, int column);

	public void setCellAsBlack(int row, int column); 

	public void setCellAsBlank(int row, int column); 

	public void setCellAsFlagged(int row, int column); 
		
	public int[] askCorrectHint();
	
	public int getPlayboardSize();
	
	public boolean askIfSolutionIsCorrect();
	
	public void restartGame();
	
	public void exitGame();
}

package Model;

public interface INonograma {

	public void establishDifficultyLevel(int difficultyLevel);
	
	public int askCorrectHint();
	
	public boolean askIfSolutionIsCorrect();
	
	public void restartGame();
	
	public void exitGame();
	
	public String notifyResult();
}

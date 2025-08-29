package Model;

public class Nonograma implements INonograma {

	private Playboard currentLevel;
	private Playboard solutionPlayboard;
	private PlayboardSingleton initialGameLevels;
	
	public Nonograma() {

		this.currentLevel = new Playboard(5);		
		this.solutionPlayboard = Playboard.createSolutionOfPlayboard(currentLevel);
		
		
	}
	
	@Override
	public void establishDifficultyLevel(int difficultyLevel) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public int askCorrectHint() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean askIfSolutionIsCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void restartGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void exitGame() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String notifyResult() {
		// TODO Auto-generated method stub
		return null;
	}
}
	

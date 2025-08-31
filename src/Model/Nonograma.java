package Model;

public class Nonograma implements INonograma {

	private Playboard currentLevel;
	private PlayboardSingleton initialGameLevels;
	private DifficultyLevels currentDifficulty;
	public enum DifficultyLevels { EASY, NORMAL, HARD};
	
	public Nonograma(DifficultyLevels difficulty) {

		this.currentDifficulty = difficulty;
		
		createPlayboardAccordingToDifficulty();
		
		
	}
	
	private void createPlayboardAccordingToDifficulty() {

		switch(currentDifficulty){

		case EASY:
			currentLevel = new Playboard(10);
			break;
		case NORMAL:
			currentLevel = new Playboard(15);
			break;
		case HARD:
			currentLevel = new Playboard(20);
			break;
		}
	}
	
	@Override
	public void changeDifficultyLevel(DifficultyLevels difficulty) {

		
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
	

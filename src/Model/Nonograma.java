package Model;

public class Nonograma implements INonograma {

	private Playboard currentLevel;
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
	public int[] askCorrectHint() {
		
		return currentLevel.getPaintedCellCoordinate();
	}

	@Override
	public boolean askIfSolutionIsCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void restartGame() {
		// with this, both player and solution board should be generated anew. So the model is ready to play. UI would need to update visuals and run the hints methods.
		createPlayboardAccordingToDifficulty();
		
	}

	@Override
	public void exitGame() {
		System.exit(0);
		
	}

	@Override
	public String notifyResult() {
		if(askIfSolutionIsCorrect){
			return "WIN";
		} else {
		return "Answer Incomplete or Incorrect";
		}
	}
}
	

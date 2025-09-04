package Model;

import java.util.List;

class Nonograma implements INonograma {

	private Playboard currentLevel;
	private DifficultyLevels currentDifficulty;
	
	public Nonograma(DifficultyLevels difficulty) {

		currentDifficulty = difficulty;
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

		currentDifficulty = difficulty;
		createPlayboardAccordingToDifficulty();
		
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

	@Override
	public int getPlayboardSize() {

		return currentLevel.getSize();
		
	}

	@Override
	public String getLengthOfBlackChainsInOneRow(int row) {

		return currentLevel.getBlackChainsLengthsInRow(row);
		
	}

	@Override
	public String getLengthOfBlackChainsInOneColumn(int column) {

		return currentLevel.getBlackChainsLengthsInColumn(column);
		
	}

	@Override
	public String getLengthOfBlackChainsInAllRows() {

		return currentLevel.getBlackChainsInAllRows();
		
	}

	@Override
	public String getLengthOfBlackChainsInAllColumns() {
		
		return currentLevel.getBlackChainsInAllColumns();
		
	}

	@Override
	public void askCorrectHint() {
		// TODO Auto-generated method stub
		
	}
	
		

}
	

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
	public int getPlayboardSize() {

		return currentLevel.getSize();
		
	}

	@Override
	public String getHorizontalStringWithLengthOfBlackChainsInOneRow(int row) { //why ? Who hurt you XD, name it getRowHints(int row) 

		return currentLevel.getBlackChainsLengthsInRow(row);
		
	}

	@Override
	public String  getVerticalStringWithLengthOfBlackChainsInOneColumn(int column) { //why ? Who hurt you XD, name it getColumnHints(int column) 

		return currentLevel.getBlackChainsLengthsInColumn(column);
		
	}

	@Override
	public void askCorrectHint() {
		// TODO Auto-generated method stub
		
	}
	public boolean isSolution() {
    	CellsGrid solutionBoard = currentLevel.getSolutionBoard();
    	CellsGrid playBoard = currentLevel.getPlayerBoard();

    	return currentLevel.verifyWin(solutionBoard, playBoard);
	}
		

}
	

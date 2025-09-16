package Model;

import java.util.List;

class Nonograma implements INonograma {

	private Playboard currentLevel;
	private int numberOfTracksAvaiables;
	private DifficultyLevels currentDifficulty;
	private int remaningAttemps;
	
	public Nonograma(DifficultyLevels difficulty) {

		if(difficulty == null)
			throw new IllegalArgumentException("La dificultad ingresada es null. La dificultad inicial fue: " + difficulty);
		
		this.numberOfTracksAvaiables = 3;
		this.remaningAttemps = 3;
		this.currentDifficulty = difficulty;
		createPlayboardAccordingToDifficulty();
		
	}
	
	private void createPlayboardAccordingToDifficulty() {

		switch(currentDifficulty){

		case EASY:
			currentLevel = new Playboard(5);
			break;
		case NORMAL:
			currentLevel = new Playboard(10);
			break;
		case HARD:
			currentLevel = new Playboard(15);
			break;
		case VERY_HARD:
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
	public int[] askCorrectHint() {
    	return currentLevel.darPista(); 
	}

	@Override
	public void restartGame() {
		
		remaningAttemps = 3;
		createPlayboardAccordingToDifficulty();
		
	}

	@Override
	public void exitGame() {
		System.exit(0);
		
	}

	@Override
	public int getPlayboardSize() {

		return currentLevel.getSize();
		
	}

	@Override
	public List<Integer> getBlackChainsLengthsInRow(int row) {
		
		return currentLevel.getBlackChainsLengthsInRow(row);
		
	}

	@Override
	public List<Integer>  getBlackChainsLengthsInColumn(int column) {

		return currentLevel.getBlackChainsLengthsInColumn(column);
		
	}

	@Override
	public boolean askIfSolutionIsCorrect() {
		
		remaningAttemps -= 1;
		return currentLevel.verifyWin();
	}
	
	@Override
	public void setCellAsBlack(int row, int column) {

		currentLevel.changeCellStateToBlack(row, column);
		
	}
	
	@Override
	public void setCellAsBlank(int row, int column) {

		currentLevel.changeCellStateToBlank(row, column);
		
	}
	
	@Override
	public void setCellAsFlagged(int row, int column) {

		currentLevel.changeCellStateToFlagged(row, column);
		
	}

	@Override
	public ICell getCellOfPlayerPlayboard(int row, int column) {
		
		return currentLevel.getCellOfPlayerPlayboard(row, column);
		
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		sb.append("Estado actual del juego:\n\n");
		sb.append(currentLevel.toString());
		sb.append("\n");
		sb.append("Número de pistas disponibles: " + numberOfTracksAvaiables);
		
		return sb.toString();
	}

	@Override
	public int askremainingattempts() {
		return remaningAttemps;
	}

	@Override
	public ICell getCellOfSolution(int row, int column) {
		return currentLevel.getCellOfSolutionPlayboard(row, column);
	}
}
	

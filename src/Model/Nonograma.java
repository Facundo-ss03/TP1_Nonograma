package Model;

import java.util.List;

class Nonograma implements INonograma {

	private Playboard currentLevel;
	private int numberOfTracksAvaiables;
	private DifficultyLevels currentDifficulty;
	
	public Nonograma(DifficultyLevels difficulty) {

		if(difficulty == null)
			throw new IllegalArgumentException("La dificultad ingresada es null. La dificultad inicial fue: " + difficulty);
		
		this.numberOfTracksAvaiables = 3;
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
	public boolean askIfSolutionIsCorrect() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void restartGame() {

		createPlayboardAccordingToDifficulty();
		
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
	public List<Integer> getBlackChainsLengthsInRow(int row) {

		return currentLevel.getBlackChainsLengthsInRow(row);
		
	}

	@Override
	public List<Integer>  getBlackChainsLengthsInColumn(int column) {

		return currentLevel.getBlackChainsLengthsInColumn(column);
		
	}

	@Override
	public void askCorrectHint() {

		currentLevel.markBlackCellHint();
		numberOfTracksAvaiables--;
		
	}

	@Override
	public void markCellWithBlack(int row, int column) {

		currentLevel.markCellHowBlack(row, column);
		
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
}
	

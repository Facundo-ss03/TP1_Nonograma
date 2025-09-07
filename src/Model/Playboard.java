package Model;

import java.util.List;
import java.util.Objects;

class Playboard {

	private final CellsGrid solutionBoard;
	private CellsGrid playerBoard;
	
	public Playboard(int size) {

		solutionBoard = new CellsGrid(size);
		playerBoard = CellsGrid.createInBlankCopyOfCellsGrid(solutionBoard);
	}
	
	public void markCellHowBlack(int row, int column) {
		
		playerBoard.paintCell(row, column);
		
	}
	
	public boolean comparePlayerboardWithSolution() {

		return solutionBoard.equals(playerBoard);
		
	}

	public List<Integer> getBlackChainsLengthsInRow(int row) throws IllegalArgumentException {

		return solutionBoard.getBlackChainsLengthsInRow(row);
		
	}
	
	public List<Integer> getBlackChainsLengthsInColumn(int column) throws IllegalArgumentException {

		return solutionBoard.getBlackChainsLengthsInRow(column);
		
	}
	
	/*
	private List<Integer> convertRowHintInHorizontalString(int row) {
		
		StringBuilder sb = new StringBuilder();
		
		for(Integer value : solutionBoard.getBlackChainsLengthsInRow(row))
			sb.append(" " + value + " ");
		
		return sb.toString();
		
	}
	
	private String convertColumnHintInVerticalString(int column) {

		StringBuilder sb = new StringBuilder();
		
		for(Integer value : solutionBoard.getBlackChainsLengthsInColumn(column))
			sb.append(" " + value + " " + "\n");
		
		return sb.toString();
	}
	*/
	
	public void markBlackCellHint() {

		solutionBoard.setRandomCellOfPlayerBoardHowBlack(playerBoard);
		
	}

	public int getSize() {
		return solutionBoard.size;
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		
		Playboard playboard = (Playboard) obj;
		
		return solutionBoard.equals(playboard.solutionBoard) && playerBoard.equals(playboard.playerBoard);
	}

	@Override
	public int hashCode() {
		
		return Objects.hash(solutionBoard, playerBoard);
		
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();

		sb.append("Tablero solución: \n\n");
		sb.append(solutionBoard.toString());
		sb.append("\n\n");
		sb.append("Estado del tablero del jugador: \n\n");
		sb.append(playerBoard.toString());
		
		return sb.toString();
	}
}

package Model;

import java.util.List;

class Playboard {

	private CellsGrid solutionBoard;
	private CellsGrid playerBoard;
	
	public Playboard(int size) {

		solutionBoard = new CellsGrid(size);
		playerBoard = CellsGrid.createInBlankCopyOfCellsGrid(solutionBoard);

	}
	public boolean verifyWin() {
    	playBoard.refreshHints(); // actualizar estado dinámico

    	return solutionBoard.getRowHints().equals(playBoard.getRowHints()) &&
           	solutionBoard.getColumnHints().equals(playBoard.getColumnHints());
}

	public String getBlackChainsLengthsInRow(int row) throws IllegalArgumentException {

		if(row < 0)
			throw new IllegalArgumentException("La fila ingresada es negativa.");
		
		return convertRowHintInHorizontalString(row);
		
	}
	
	private String convertRowHintInHorizontalString(int row) {
		
		StringBuilder sb = new StringBuilder();
		
		for(Integer value : solutionBoard.getBlackChainsLengthsInRow(row))
			sb.append(" " + value + " ");
		
		return sb.toString();
		
	}
	
	public String getBlackChainsLengthsInColumn(int column) throws IllegalArgumentException {

		if(column < 0)
			throw new IllegalArgumentException("La columna ingresada es negativa.");
		
		return convertColumnHintInVerticalString(column);
		
	}
	
	private String convertColumnHintInVerticalString(int column) {

		StringBuilder sb = new StringBuilder();
		
		for(Integer value : solutionBoard.getBlackChainsLengthsInColumn(column))
			sb.append(" " + value + " " + "\n");
		
		return sb.toString();
	}
	
	public String toString() {
		return solutionBoard.toString();
	}
	
	public int getSize() {
		return solutionBoard.size;
	}
	
	public CellsGrid getPlayerBoard(){
		return playerBoard;
	}
	public CellsGrid getSolutionBoard(){
		return solutionBoard;
	}
	
}

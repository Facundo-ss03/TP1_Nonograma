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
	public boolean verifyWin() {

        playerBoard.refreshHints();
        
    	return solutionBoard.getRowHints().equals(solutionBoard.getRowHints()) &&
           	solutionBoard.getColumnHints().equals(playerBoard.getColumnHints());
	
    	//return playerBoard.equals(solutionBoard);
	}

	public void changeCellStateToBlack(int row, int column) {

		playerBoard.paintCell(row, column);

		System.out.print(playerBoard.toString());
		System.out.print(solutionBoard.toString());
	}
	
	public void changeCellStateToBlank(int row, int column) {

		playerBoard.blankCell(row, column);

		System.out.print(playerBoard.toString());
		System.out.print(solutionBoard.toString());
	}
	
	public void changeCellStateToFlagged(int row, int column) {

		playerBoard.flagCell(row, column);

		System.out.print(playerBoard.toString());
		System.out.print(solutionBoard.toString());
	}
	
	public Cell getCellOfPlayerPlayboard(int row, int column) {
		
		return playerBoard.getCell(row, column);
		
	}
	
	public Cell getCellOfSolutionPlayboard(int row, int column) {
		
		return solutionBoard.getCell(row, column);
		
	}
	
	public boolean comparePlayerboardWithSolution() {

		return solutionBoard.equals(playerBoard);
		
	}

	public List<Integer> getBlackChainsLengthsInRow(int row) throws IllegalArgumentException {

		return solutionBoard.getBlackChainsLengthsInRow(row);
		
	}
	
	public List<Integer> getBlackChainsLengthsInColumn(int column) throws IllegalArgumentException {

		return solutionBoard.getBlackChainsLengthsInColumn(column);
		
	}
	
	public void markBlackCellHint() {

		solutionBoard.setRandomCellOfPlayerBoardHowBlack(playerBoard);
		
	}

	public int getSize() {
		return solutionBoard.getSize();
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
	
	public CellsGrid getPlayerBoard(){
		return playerBoard;
	}
	public CellsGrid getSolutionBoard(){
		return solutionBoard;
	}
	public int[] getPaintedCellCoordinate() {
		
		
		
		return new int[2];
		
	}
	public int[] darPista() {
	    int size = solutionBoard.getSize();
	    
	    for (int row = 0; row < size; row++) {
	        for (int col = 0; col < size; col++) {
	            Cell solCell = solutionBoard.getCell(row, col);
	            Cell playerCell = playerBoard.getCell(row, col);
	            
	            if (solCell.getCurrentState() == Cell.CellStates.PAINTED &&
	                playerCell.getCurrentState() == Cell.CellStates.BLANK) {
	                return new int[]{row, col};
	            }
	        }
	    }
	    
	    return null;
	}
}

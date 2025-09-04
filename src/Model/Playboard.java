package Model;

import java.util.ArrayList;
import java.util.List;

class Playboard {

	private CellsGrid solutionBoard;
	private CellsGrid playerBoard;
	private List<String> rowHints;
	private List<String> columnHints;
	
	public Playboard(int size) {

		solutionBoard = new CellsGrid(size);
		playerBoard = CellsGrid.createInBlankCopyOfCellsGrid(solutionBoard);

		rowHints = solutionBoard.getAllBlackChainsLengthsInRows();
		columnHints = solutionBoard.getAllBlackChainsLengthsInColumns();
		
	}

	public String getBlackChainsLengthsInRow(int row){

		if(row < 0)
			throw new IllegalArgumentException("La fila ingresada es negativa.");
		if(row >= rowHints.size())
			throw new IllegalArgumentException("La fila solicitada es mayor que el tamaño del tablero.");
		
		return rowHints.get(row);
		
	}
	
	public String getBlackChainsLengthsInColumn(int column){

		if(column < 0)
			throw new IllegalArgumentException("La columna ingresada es negativa.");
		if(column >= rowHints.size())
			throw new IllegalArgumentException("La columna solicitada es mayor que el tamaño del tablero.");
		
		return columnHints.get(column);
		
	}
	
	public String getBlackChainsInAllRows(){

		return rowHints.toString();
	}	
	
	public String getBlackChainsInAllColumns(){
		return columnHints.toString();
	}	
	
	public String toString() {
		return solutionBoard.toString();
	}
	
	public int getSize() {
		return solutionBoard.size;
	}
}

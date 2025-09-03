package Model;

import java.util.List;

public class Playboard {

	private CellsGrid solutionCellsGrid;
	private CellsGrid cellsGridInBlank;
	private List<ITuple> rowHints;
	private List<ITuple> columnHints;
	
	public Playboard(int size) {

		solutionCellsGrid = new CellsGrid(size);
		cellsGridInBlank = CellsGrid.createInBlankCopyOfCellsGrid(solutionCellsGrid);

		rowHints = solutionCellsGrid.getSetOfLengthsOfBlackChainsInRows();
		columnHints = solutionCellsGrid.getSetOfLengthsOfBlackChainsInColumns();
		
	}

	public ITuple getBlackChainsLengthsInRow(int row){

		if(row < 0)
			throw new IllegalArgumentException("La fila ingresada es negativa.");
		if(row >= rowHints.size())
			throw new IllegalArgumentException("La fila solicitada es mayor que el tamaño del tablero.");
		
		return rowHints.get(row);
		
	}
	
	public ITuple getBlackChainsLengthsInColumn(int column){

		if(column < 0)
			throw new IllegalArgumentException("La columna ingresada es negativa.");
		if(column >= rowHints.size())
			throw new IllegalArgumentException("La columna solicitada es mayor que el tamaño del tablero.");
		
		return columnHints.get(column);
		
	}
	
	public List<ITuple> getBlackChainsInAllRows(){
		return rowHints;
	}	
	
	public List<ITuple> getBlackChainsInAllColumns(){
		return columnHints;
	}	
	
	public String toString() {
		return solutionCellsGrid.toString();
	}
	
	public int getSize() {
		return solutionCellsGrid.size;
	}
}

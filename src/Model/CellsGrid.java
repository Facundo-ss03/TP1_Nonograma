package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Cell.CellStates;

class CellsGrid {

	private Cell[][] cellsSet;
	private Random generator;
	protected final int size;
	private List<List<Integer>> rowHints;
	private List<List<Integer>> columnHints;
	
	public CellsGrid(int cellsSetSize) {
		
		if(cellsSetSize < 5)
			throw new IllegalArgumentException("El tamaño del playboard no puede ser menor a 5. Cantidad Ingresada: " + cellsSetSize);
		
		this.size = cellsSetSize;
		generator = new Random();
		initializeCellsGrid(size);
		
		this.rowHints = createListOfBlackChainsLengthsInRows();
		this.columnHints = createListOfBlackChainsLengthsInColumns();
		
	}
	
	private CellsGrid(Cell[][] voidCellsGrid) {
		
		this.cellsSet = voidCellsGrid;
		size = voidCellsGrid.length;
	}
	
	public void initializeCellsGrid(int size) {
		
		//boolean[][] baseStructure = createBaseAleatoryBooleanStructure(size);
		//replaceBooleanStructureForStartedCells(baseStructure);

		cellsSet = new Cell[size][size];
		createAleatoryCellsStructure();
		
	}
	
	private void createAleatoryCellsStructure() {
		
		while(!haveBlankOrBlackColumn()) {

			for (int row = 0; row < cellsSet.length; row++) {
				cellsSet[row] = fillRowWithBlackCells();
			}
		}
		
	}

	private Cell[] fillRowWithBlackCells() {
		
		Cell[] fillRow = new Cell[size];
		int numberOfBlackCellsInRow = 0;
		int numberOfBlankCellsInRow = 0;
		int minimalSeparation = 1;		
		boolean rowHasAtLeastOneBlackCell = false;
		
		while(!rowHasAtLeastOneBlackCell) {
				
			for(int i = 0; i < size; i++) {
					
				if(generator.nextDouble() < 0.4 && numberOfBlackCellsInRow < size - minimalSeparation) {
						
					fillRow[i] = new Cell(CellStates.PAINTED);
					numberOfBlackCellsInRow++;
						
				} else {
						
					fillRow[i] = new Cell(CellStates.BLANK);
					numberOfBlankCellsInRow++;
				}
			}
			
			rowHasAtLeastOneBlackCell |= numberOfBlackCellsInRow > 0 && numberOfBlankCellsInRow > 0;
		}
		
		return fillRow;
		
	}
	
	private boolean haveBlankOrBlackColumn() {

		if(cellsSet == null) return false;
		
		boolean haveBlackCellInColumn;
		boolean haveBlankCellInColumn;
		boolean haveColumnBlackOrBlank = true;
		
		for(int row = 0; row < cellsSet.length; row++) {
			
			haveBlackCellInColumn = false;
			haveBlankCellInColumn = false;
			
			for(int column = 0; column < cellsSet.length; column++) {

				if(cellsSet[column][row] == null) cellsSet[column][row] = new Cell(CellStates.BLANK);
				
				haveBlankCellInColumn |= cellsSet[column][row].isBlank() == true;
				haveBlackCellInColumn |= cellsSet[column][row].isPainted() == true;
				
			}
			
			haveColumnBlackOrBlank &= haveBlackCellInColumn && haveBlankCellInColumn;
			if(haveColumnBlackOrBlank == false) return false;
			
		}
		
		return haveColumnBlackOrBlank;
	}

	public List<Integer> getBlackChainsLengthsInRow(int row) {
		
		return rowHints.get(row);
		
	}

	public List<Integer> getBlackChainsLengthsInColumn(int column) {
		
		return columnHints.get(column);
		
	}
	
	private List<List<Integer>> createListOfBlackChainsLengthsInRows(){

		List<List<Integer>> blackChainsLengths = new ArrayList<>();
		
		for(Cell[] row : cellsSet)
			blackChainsLengths.add(BlackChainsDetector.detectBlackChainsByRows(row));

		return blackChainsLengths;
	
	}
	
	private List<List<Integer>> createListOfBlackChainsLengthsInColumns() {

		List<List<Integer>> blackChainsLengths = new ArrayList<>();
		
		for(int column = 0; column < size; column++)
			blackChainsLengths.add(BlackChainsDetector.detectBlackChainsByColumns(cellsSet, column));

		return blackChainsLengths;
		
	}
	
	private int generateRandomNumberOfRow() {
		return generator.nextInt(size);
	}
	
	public int[] getHintOfBlackCell() throws RuntimeException {

		int randomRow = generateRandomNumberOfRow();
		int[] coordenates = new int[2];
		
		for(int column = 0; column < size; column++) {
			if(cellsSet[randomRow][column].isPainted()) {
				coordenates[0] = randomRow;
				coordenates[1] = column;
				return coordenates;
			}
		}

		throw new RuntimeException("Error al buscar una pista: No hay ninguna celda negra en la CellGrid.");
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(Cell[] row : cellsSet) {
			
			for(Cell column : row) {

				if(column.isBlank())
					sb.append(" + " + "0" + " + ");
				if(column.isPainted())
					sb.append(" + " + "1" + " + ");
			}
			
			sb.append("\n");
		}

		sb.append("\n");
		return sb.toString();
	}
	
	public static CellsGrid createInBlankCopyOfCellsGrid(CellsGrid originalGrid) {

		int gridLength = originalGrid.cellsSet.length;
		Cell[][] cellsGrid = new Cell[gridLength][gridLength];
		
		for(int row = 0; row < gridLength; row++)
			for(int column = 0; column < gridLength; column++)
				cellsGrid[row][column] = new Cell(CellStates.BLANK);
		
		return new CellsGrid(cellsGrid);
	}
	public void refreshHints() {
    this.rowHints = createListOfBlackChainsLengthsInRows();
    this.columnHints = createListOfBlackChainsLengthsInColumns();
}

}

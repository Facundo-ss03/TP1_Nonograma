package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import Model.ICell.CellStates;

class CellsGrid {

	private Cell[][] cellsSet;
	private Random generator;
	protected final int size;
	private List<List<Integer>> rowHints;
	private List<List<Integer>> columnHints;
	
	public CellsGrid(int size) {
		
		if(size < 5)
			throw new IllegalArgumentException("El tamaño del playboard no puede ser menor a 5. Cantidad Ingresada: " + size);
		
		this.size = size;
		generator = new Random();
		initializeCellsGrid();
		
		this.rowHints = createListOfBlackChainsLengthsInRows();
		this.columnHints = createListOfBlackChainsLengthsInColumns();
		
	}
	
	public Cell getCell(int row, int column) {
		
		return cellsSet[row][column];
		
	}
	
	public CellStates getCellState(int row, int column) {
		
		return cellsSet[row][column].getCurrentState();
		
	}
	
	public void paintCell(int row, int column) {

		if(row < 0)
			throw new IllegalArgumentException("La fila ingresada es negativa. Fila: " + row);
		if(row < 0)
			throw new IllegalArgumentException("La columna ingresada es negativa. Columna: " + column);
		
		cellsSet[row][column].ToBlack();
		
	}
	
	public void flagCell(int row, int column) {

		if(row < 0)
			throw new IllegalArgumentException("La fila ingresada es negativa. Fila: " + row);
		if(row < 0)
			throw new IllegalArgumentException("La columna ingresada es negativa. Columna: " + column);
		
		cellsSet[row][column].ToFlagged();
		
	}
	
	public void blankCell(int row, int column) {

		if(row < 0)
			throw new IllegalArgumentException("La fila ingresada es negativa. Fila: " + row);
		if(row < 0)
			throw new IllegalArgumentException("La columna ingresada es negativa. Columna: " + column);
		
		cellsSet[row][column].ToBlank();
		
	}
	
	private CellsGrid(Cell[][] voidCellsGrid) {
		
		this.cellsSet = voidCellsGrid;
		size = voidCellsGrid.length;
	}
	
	private void initializeCellsGrid() {
		
		cellsSet = new Cell[size][size];
		createAleatoryCellsStructure();
		
	}
	
	private void createAleatoryCellsStructure() {
		
		while(!haveBlankOrBlackColumn()) {

			for (int row = 0; row < cellsSet.length; row++) {
				cellsSet[row] = fillRowWithBlackCells(row);
			}
		}
		
	}

	private Cell[] fillRowWithBlackCells(int row) {
		
		Cell[] fillRow = new Cell[size];
		int numberOfBlackCellsInRow = 0;
		int numberOfBlankCellsInRow = 0;
		int minimalSeparation = 1;		
		boolean rowHasAtLeastOneBlackCell = false;
		
		while(!rowHasAtLeastOneBlackCell) {
				
			for(int column = 0; column < size; column++) {
					
				if(generator.nextDouble() < 0.4 && numberOfBlackCellsInRow < size - minimalSeparation) {
						
					fillRow[column] = new Cell(CellStates.PAINTED);
					numberOfBlackCellsInRow++;
						
				} else {
						
					fillRow[column] = new Cell(CellStates.BLANK);
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
				haveBlackCellInColumn |= cellsSet[column][row].isBlack() == true;
				
			}
			
			haveColumnBlackOrBlank &= haveBlackCellInColumn && haveBlankCellInColumn;
			if(haveColumnBlackOrBlank == false) return false;
			
		}
		
		return haveColumnBlackOrBlank;
	}

	public List<Integer> getBlackChainsLengthsInRow(int row) {

		if(row < 0)
			throw new IllegalArgumentException("La fila solicitada es negativa. El número de fila ingresado fue: " + row);
		if(row >= cellsSet.length)
			throw new IllegalArgumentException("La fila solicitada es mayor que el tamaño del tablero. El número de fila ingresado fue: " + row);
		
		return rowHints.get(row);
		
	}

	public List<Integer> getBlackChainsLengthsInColumn(int column) {

		if(column < 0)
			throw new IllegalArgumentException("La columna solicitada es negativa. El número de columna ingresado fue: " + column);
		if(column >= cellsSet.length)
			throw new IllegalArgumentException("La columna solicitada es mayor que el tamaño del tablero. El número de columna ingresado fue: " + column);
		
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
	
	private int generateRandomRowNumber() {
		return generator.nextInt(size);
	}
	
	public void setRandomCellOfPlayerBoardHowBlack(CellsGrid grid) {

		int randomRow = generateRandomRowNumber();
		
		for(int column = 0; column < size; column++) {
			if(cellsSet[randomRow][column].isBlack()) {

				grid.cellsSet[randomRow][column].ToBlack();
				
			}
		}
	}
	
	public void setCellHowBlack(int row, int column) {
		
		cellsSet[row][column].ToBlack();
		
	}
	
	public void setCellHowBlank(int row, int column) {
		
		cellsSet[row][column].ToBlank();
		
	}
	
	public void setCellHowFlagged(int row, int column) {
		
		cellsSet[row][column].ToFlagged();
		
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(Cell[] row : cellsSet) {
			
			for(Cell column : row) {

				if(column.isBlank())
					sb.append(" + " + "0" + " + ");
				if(column.isBlack())
					sb.append(" + " + "1" + " + ");
				if(column.isFlagged())
					sb.append(" + " + "F" + " + ");
			}
			
			sb.append("\n");
		}

		sb.append("\n");
		return sb.toString();
	}
	
	private boolean areEquals(CellsGrid otherGrid) {
		
		for(int row = 0; row < size; row++) {
			
			for(int column = 0; column < size; column++) {

				Cell currentCellOfThisGrid = cellsSet[row][column];
				Cell currentCellOfOtherGrid = otherGrid.cellsSet[row][column];
				
				if(currentCellOfThisGrid.equals(currentCellOfOtherGrid) == false)
					return false;
				
			}
			
		}
		
		return true;
		
	}
	
	public static CellsGrid createInBlankCopyOfCellsGrid(CellsGrid originalGrid) {

		int gridLength = originalGrid.cellsSet.length;
		Cell[][] cellsGrid = new Cell[gridLength][gridLength];
		
		for(int row = 0; row < gridLength; row++)
			for(int column = 0; column < gridLength; column++)
				cellsGrid[row][column] = new Cell(CellStates.BLANK);
		
		return new CellsGrid(cellsGrid);
	}
	
	@Override
	public boolean equals(Object obj) {
		
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		
		CellsGrid grid = (CellsGrid) obj;
		
		return areEquals(grid);
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(size, rowHints, columnHints);
		
	}
	
	public void refreshHints() {
    	this.rowHints = createListOfBlackChainsLengthsInRows();
    	this.columnHints = createListOfBlackChainsLengthsInColumns();
	}
	
	public List<List<Integer>> getRowHints() {
		return rowHints;
	}

	public List<List<Integer>> getColumnHints() {
		return columnHints;
	}
}

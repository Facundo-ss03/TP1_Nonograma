package Model;

import java.util.Random;

import Model.Cell.CellStates;

public class CellsGrid {

	private Cell[][] cellsMap;
	private Random generator;
	
	public CellsGrid(int size) {
		
		if(size < 5)
			throw new IllegalArgumentException("El tamaño del playboard no puede ser menor a 5. Cantidad Ingresada: " + size);
		
		generator = new Random();
		initializeCellsGrid(size);
		
	}
	
	private CellsGrid(Cell[][] voidCellsGrid) {
		
		this.cellsMap = voidCellsGrid;
		
	}
		
	public void initializeCellsGrid(int size) {
		
		boolean[][] baseStructure = createBaseAleatoryBooleanStructure(size);
		replaceBooleanStructureForStartedCells(baseStructure);
		
	}
	
	public static CellsGrid createInBlankCopyOfCellsGrid(CellsGrid originalGrid) {

		int gridLength = originalGrid.cellsMap.length;
		Cell[][] cellsGrid = new Cell[gridLength][gridLength];
		
		for(int row = 0; row < gridLength; row++)
			for(int column = 0; column < gridLength; column++)
				cellsGrid[row][column] = new Cell(CellStates.BLANK);
		
		return new CellsGrid(cellsGrid);
	}
	
	private boolean[][] createBaseAleatoryBooleanStructure(int size) {

		 boolean[][] structure = new boolean[size][size];
			
		 int firstMiddle = size%2 == 0 ? size/2 : size/2+1;
		 int secondMiddle = size - firstMiddle;

		 while(checkIfHaveBlankColumn(structure) == false) {
			 
			 for (int row = 0; row < firstMiddle; row++) {
				 
				 structure[row] = fillRowWithBooleans(size);
				 //structure[]
			 }
			 
			 for(int row = secondMiddle; row < size; row++)
				 structure[row] = fillRowWithBooleans(size);

		 }
			 
		 return structure;
	}
	
	private boolean checkIfHaveBlankColumn(boolean[][] grid) {

		boolean haveBlackCellInColumn;
		boolean haveBlankCellInColumn;
		boolean haveColumnBlackOrBlank = true;
		
		for(int i = 0; i < grid.length; i++) {
			
			haveBlackCellInColumn = false;
			haveBlankCellInColumn = false;
			
			for(int j = 0; j < grid.length; j++) {

				haveBlankCellInColumn |= grid[j][i] == false;
				haveBlackCellInColumn |= grid[j][i] == true;
				
			}
			
			haveColumnBlackOrBlank &= haveBlackCellInColumn && haveBlankCellInColumn;
			
		}
		
		return haveColumnBlackOrBlank;
		
	}
	
	
	private boolean[] fillRowWithBooleans(int rowLength) {

		int minimalSeparation = 1;
    	boolean[] filledRow = new boolean[rowLength];

        int firstPaintedChainLength = calculatePaintedChainLength(rowLength, minimalSeparation);
        int secondPaintedChainLength = calculatePaintedChainLength(rowLength, minimalSeparation);
        
        int startLimit = rowLength - calculateTotalSpaceOccupiedForPaintedCellsChains(firstPaintedChainLength, secondPaintedChainLength, minimalSeparation);

        int startOfFirstChain = generateRandomStartPosition(startLimit + minimalSeparation);
        int finalOfFirstChain = startOfFirstChain + firstPaintedChainLength;
        
        int startOfSecondChain = finalOfFirstChain + generateRandomStartPosition(rowLength - (finalOfFirstChain + secondPaintedChainLength) + minimalSeparation);

        filledRow = setBooleansToTrue(filledRow, startOfFirstChain, firstPaintedChainLength);
        filledRow = setBooleansToTrue(filledRow, startOfSecondChain, secondPaintedChainLength);

        return filledRow;
	}
	
	private boolean[] setBooleansToTrue(boolean[] row, int startPaintedChain, int paintedChainLength) {
		
		for(int i = startPaintedChain; i < startPaintedChain + paintedChainLength; i++) {
			row[i] = true;
		}
		return row;
	}
	
	private int calculateTotalSpaceOccupiedForPaintedCellsChains(int firstPaintedChainLength, int secondPaintedChainLength, int minimalSeparation) {

		return firstPaintedChainLength + secondPaintedChainLength + minimalSeparation;
		
	}
	
	private int calculatePaintedChainLength(int maxLengthOfGridRow, int minimalSeparation) {

		int maxPaintedChainLength = maxLengthOfGridRow - minimalSeparation;
		return generateRandomChainLength(maxPaintedChainLength);
		
	}
	
	private int generateRandomStartPosition(int startLimit) {
		return generator.nextInt(startLimit);
	}
	
	private int generateRandomChainLength(int maxPaintedChainLength) {
		
		return generator.nextInt(maxPaintedChainLength/2)+1;
		
	}
	
	private void replaceBooleanStructureForStartedCells(boolean[][] grid) {
		
		cellsMap = new Cell[grid.length][grid.length];
		
		for (int i = 0; i < grid.length; i++) {
		    for (int j = 0; j < grid.length; j++) {
		        cellsMap[i][j] = grid[i][j] ? new Cell(CellStates.PAINTED) : new Cell(CellStates.BLANK);
		    }
		}
	}
	
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < cellsMap.length; i++) {
			for(int j = 0; j < cellsMap.length; j++) {
				
				sb.append(" + " + cellsMap[i][j].toString() + " + ");
				
			}
			
			sb.append("\n");
		}
		
		return sb.toString();
	}
}

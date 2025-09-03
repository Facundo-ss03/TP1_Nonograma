package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Model.Cell.CellStates;

public class CellsGrid {

	private Cell[][] cellsSet;
	private Random generator;
	protected final int size;
	
	public CellsGrid(int cellsSetSize) {
		
		if(cellsSetSize < 5)
			throw new IllegalArgumentException("El tamaño del playboard no puede ser menor a 5. Cantidad Ingresada: " + cellsSetSize);
		
		this.size = cellsSetSize;
		generator = new Random();
		initializeCellsGrid(size);
		
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
		
		fixColumnChains();
	}
	
	private void fixColumnChains() {
	    int size = cellsSet.length;

	    for (int column = 0; column < size; column++) {
	        ArrayList<Integer> blackIndices = new ArrayList<>();
	        for (int row = 0; row < size; row++) {
	            if (cellsSet[row][column].isPainted()) blackIndices.add(row);
	        }

	        int blockCount = countBlocksInColumn(column);

	        if (blockCount > 2) {
	            // Reducir bloques: eliminar celdas negras intermedias
	            int excess = blockCount - 2;
	            for (int i = 1; i < blackIndices.size() - 1 && excess > 0; i++) {
	                int prev = blackIndices.get(i - 1);
	                int curr = blackIndices.get(i);
	                int next = blackIndices.get(i + 1);

	                // Si hay separación, podemos eliminar el bloque
	                if (curr - prev > 1 && next - curr > 1) {
	                	cellsSet[curr][column].establishCellHowBlank();
	                    excess--;
	                }
	            }
	        } else if (blockCount < 2) {
	            // Agregar bloques: buscar espacio libre
	            for (int row = 0; row < size && blockCount < 2; row++) {
	                if (!cellsSet[row][column].isPainted() &&
	                    (row == 0 || !cellsSet[row - 1][column].isPainted()) &&
	                    (row == size - 1 || !cellsSet[row + 1][column].isPainted())) {
	                	cellsSet[row][column].isPainted();
	                    blockCount++;
	                }
	            }
	        }
	    }
	}

	private int countBlocksInColumn(int col) {
	    int count = 0;
	    boolean inBlock = false;
	    for (int row = 0; row < cellsSet.length; row++) {
	        if (cellsSet[row][col].isPainted()) {
	            if (!inBlock) {
	                count++;
	                inBlock = true;
	            }
	        } else {
	            inBlock = false;
	        }
	    }
	    return count;
	}

	private Cell[] fillRowWithBlackCells() {
		
		int minimalSeparation = 1;
    	Cell[] filledRow = new Cell[cellsSet.length];

        int firstPaintedChainLength = calculatePaintedChainLength(cellsSet.length, minimalSeparation);
        int secondPaintedChainLength = calculatePaintedChainLength(cellsSet.length, minimalSeparation);
        
        int startLimit = cellsSet.length - calculateTotalSpaceOccupiedForPaintedCellsChains(firstPaintedChainLength, secondPaintedChainLength, minimalSeparation);

        int startOfFirstChain = generateRandomStartPosition(startLimit + minimalSeparation);
        int finalOfFirstChain = startOfFirstChain + firstPaintedChainLength;
        
        int startOfSecondChain = finalOfFirstChain + generateRandomStartPosition(cellsSet.length - (finalOfFirstChain + secondPaintedChainLength) + minimalSeparation);

        filledRow = setCellsToBlack(filledRow, startOfFirstChain, firstPaintedChainLength);
        filledRow = setCellsToBlack(filledRow, startOfSecondChain, secondPaintedChainLength);

        return filledRow;
		
	}
	
	private Cell[] setCellsToBlack(Cell[] row, int startPaintedChain, int paintedChainLength) {

		for(int column = startPaintedChain; column < startPaintedChain + paintedChainLength; column++) {
			
			row[column] = new Cell(CellStates.PAINTED);
		}
		
		return row;
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

				if(cellsSet[column][row] == null) cellsSet[column][row] = createBlankCell();
				
				haveBlankCellInColumn |= cellsSet[column][row].isBlank() == true;
				haveBlackCellInColumn |= cellsSet[column][row].isPainted() == true;
				
			}
			
			haveColumnBlackOrBlank &= haveBlackCellInColumn && haveBlankCellInColumn;
			if(haveColumnBlackOrBlank == false) return false;
			
		}
		
		return haveColumnBlackOrBlank;
	}
	
	private Cell createBlankCell() {

		return new Cell(CellStates.BLANK);
		
	}
	
	/*////////////////////////////////////////////////////////////////////////////////

	 	NO BORRAR ESTOS BLOQUES POR SI ACASO
	
	private boolean[][] createBaseAleatoryBooleanStructure(int size) {

		 boolean[][] structure = new boolean[size][size];
		
		 while(checkIfHaveBlankColumn(structure) == false) {
			 
			 for (int row = 0; row < size; row++) 
				 structure[row] = fillRowWithBooleans(size);

		 }
			 
		 return structure;
	}
	
	private void replaceBooleanStructureForStartedCells(boolean[][] grid) {
		
		cellsMap = new Cell[grid.length][grid.length];
		
		for (int i = 0; i < grid.length; i++) {
		    for (int j = 0; j < grid.length; j++) {
		        cellsMap[i][j] = grid[i][j] ? new Cell(CellStates.PAINTED) : new Cell(CellStates.BLANK);
		    }
		}
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
	
	//////////////////////////////////////////////////////////////////////////////////*/
		
	private int generateRandomNumberOfRow() {
		
		return generator.nextInt(cellsSet.length);
		
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
	
	public int[] getHintOfBlackCell() throws RuntimeException {

		int randomRow = generateRandomNumberOfRow();
		int[] coordenates = new int[2];
		
		for(int column = 0; column < cellsSet.length; column++) {
			if(cellsSet[randomRow][column].isPainted()) {
				coordenates[0] = randomRow;
				coordenates[1] = column;
				return coordenates;
			}
		}

		throw new RuntimeException("Error al buscar una pista: No hay ninguna celda negra en la CellGrid.");
	}
	
	public List<ITuple> getSetOfLengthsOfBlackChainsInRows() {

		return getLengthsOfBlackChainsInRows();
		
	}
	
	public List<ITuple> getSetOfLengthsOfBlackChainsInColumns(){
		
		return getLengthsOfBlackChainsInColumns();
		
	}
	
	private List<ITuple> getLengthsOfBlackChainsInColumns(){

		List<ITuple> listLengthsTuple = new ArrayList<>();
		int column = 0;
		for(int row = 0; row < cellsSet.length; row++) {
			
			listLengthsTuple.add(createLengthsTuple(BlackChainsDetector.detectBlackChainsByColumns(cellsSet, column)));
			column++;
		}
		
		return listLengthsTuple;
		
	}
	
	private List<ITuple> getLengthsOfBlackChainsInRows(){

		List<ITuple> listLengthsTuple = new ArrayList<>();
		
		for(int row = 0; row < cellsSet.length; row++) {
			
			listLengthsTuple.add(createLengthsTuple(BlackChainsDetector.detectBlackChainsByRows(cellsSet[row])));
			
		}
		
		return listLengthsTuple;
		
	}
	
	private Tuple createLengthsTuple(List<Integer> lengthsSet) {
		
		if(lengthsSet.size() == 2) 
			return new Tuple(lengthsSet.get(0), lengthsSet.get(1));
		else 
			return new Tuple(lengthsSet.get(0), 0);
		
	}

	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		for(int i = 0; i < cellsSet.length; i++) {
			for(int j = 0; j < cellsSet.length; j++) {
				
				sb.append(" + " + cellsSet[i][j].toString() + " + ");
				
			}
			
			sb.append("\n");
		}
		
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
}

package Model;

import java.util.Random;

import Model.Cell.CellStates;

public class CellsMap {

	private Cell[][] cellsMap;
	private Random generator;
	
	public CellsMap(int size) {
		
		if(size < 5)
			throw new IllegalArgumentException("El tamaño del playboard no puede ser menor a 5. Cantidad Ingresada: " + size);
		
		generator = new Random();
		initializeCellsMap(size);
		
	}
	
	private void initializeCellsMap(int size) {
		
		boolean[][] baseStructure = createBaseAleatoryBooleanStructure(size);
		replaceBooleanStructureForStartedCells(baseStructure);
		
	}
	
	private boolean[][] createBaseAleatoryBooleanStructure(int size) {

		boolean[][] structure = new boolean[size][size];

		for (int i = 0; i < size; i++) {
				
			structure[i] = fillRowWithRandomBooleans(size);
			
		}	
		
		return structure;
		
	}
	
	private boolean[] fillRowWithRandomBooleans(int size) {

		int numberOfBlackCellsInTheRow = 0;
		boolean[] row = new boolean[size];
		
		for (int i = 0; i < size; i++) {
			
			row[i] = generateRandomNumber() < 0.4;
			
			if(row[i] == true)
				numberOfBlackCellsInTheRow++;

			if(numberOfBlackCellsInTheRow == size-1) {
				row[i] = false;
				continue;

			}
		}
		
		return row;
	}
	
	private double generateRandomNumber() {
		return generator.nextDouble();
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

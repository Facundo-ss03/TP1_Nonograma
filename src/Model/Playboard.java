package Model;

import java.util.Random;
import Model.Cell.CellStates;

public class Playboard {

	private Cell[][] cellsMap;
	private int[] rowHints;
	private int[] columnHints;
	private static Random generator;
	
	public Playboard(int size) {

		if(size < 5)
			throw new IllegalArgumentException("El tamaño del playboard no puede ser menor a 5. Cantidad Ingresada: " + size);
		
		generator = new Random();
		
		initializeCellsMap(size);
		
	}
	
	private Playboard(Cell[][] map) {
		
		this.cellsMap = map.clone();
		this.rowHints = new int[map.length];
		this.columnHints= new int[map.length];
		
	}
	
	private void initializeCellsMap(int size) {
		
		boolean[][] baseStructure = createBaseAleatoryBooleanStructure(size);
		replaceBooleanStructureForStartedCells(baseStructure);
		
	}
	
	private boolean[][] createBaseAleatoryBooleanStructure(int size) {

		boolean[][] structure = new boolean[size][size];
		int count;

		for (int i = 0; i < size; i++) {
			
			count = 0;
			
				for (int j = 0; j < size; j++) {
					structure [i][j] = generateRandomNumber() < 0.4;
					if(structure[i][j] == true)
						count++;
					if(count == size-1) {
						structure[i][j] = false;
						j++;
					}
					
				}			
		}	
		
		return structure;
	}
	
	public boolean sinColumnasFullColor() {

		int countBlanks;
		int countBlacks;

		for (int i = 0; i < cellsMap.length; i++) {
			
			countBlanks = 0;
			countBlacks = 0;
			
			for (int j = 0; j < cellsMap.length; j++) {
				
				if(cellsMap[i][j].isBlank())
					countBlanks++;
				if(cellsMap[i][j].isPainted())
					countBlacks++;
				
			}			
			
			if(countBlanks != cellsMap.length || countBlacks != cellsMap.length)
				return true;
		}	
		
		return false;
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

	private boolean isFullyPainted(boolean[] row) {
	    for(boolean cell : row) {
	        if (cell) return false;
	    }
	    return true;
	}
	
	public void despintarCelda(int f, int c) {
		cellsMap[f][c].establishCellHowFlagged();;
	}

	public String toString() {
		
		String a = "";
		
		for(int i = 0; i < cellsMap.length; i++) {
			for(int j = 0; j < cellsMap.length; j++) {
				
				a += " + " + cellsMap[i][j].toString() + " + ";
				
			}
			
			a += "\n";
		}
		
		return a;
	}
	
	public static Playboard createSolutionOfPlayboard(Playboard existentPlayboard) {
		
		return new Playboard(existentPlayboard.cellsMap);
		
	}
}

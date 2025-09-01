package Model;

import java.util.ArrayList;

public class Playboard {

	private CellsGrid solutionCellsGrid;
	private CellsGrid cellsGridInBlank;
	private ArrayList<Integer> rowHints;
	private ArrayList<Integer> columnHints;
	
	public Playboard(int size) {

		solutionCellsGrid = new CellsGrid(size);
		cellsGridInBlank = CellsGrid.createInBlankCopyOfCellsGrid(solutionCellsGrid);
		
	}
	
	public int[] getPaintedCellCoordinate() {
		
		
		
		return new int[2];
		
	}
	
	public String toString() {
		return solutionCellsGrid.toString();
	}
}

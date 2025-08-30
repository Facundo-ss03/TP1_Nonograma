package Model;

import java.util.ArrayList;

public class Playboard {

	private CellsMap cellsMap;
	private ArrayList<Integer> rowHints;
	private ArrayList<Integer> columnHints;
	
	public Playboard(int size) {

		cellsMap = new CellsMap(size);
		
	}
	
	public String toString() {
		return cellsMap.toString();
	}
}

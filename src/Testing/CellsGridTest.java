package Testing;

import org.junit.Test;

import Model.CellsGrid;

public class CellsGridTest {

	@Test(expected = IllegalArgumentException.class)
	public void createInvalidCellsGridTest() {
		
		CellsGrid grid = new CellsGrid(0);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createInvalidCellsGridWithNegativeTest() {
		
		CellsGrid grid = new CellsGrid(-1);
		
	}
	
	@Test
	public void createCellsGrid() {
		
		CellsGrid grid = new CellsGrid(5);
		
	}
	
}

package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class CellsGridTest {
	
	int size;
	CellsGrid aleatoryCellsGrid;
	CellsGrid inBlankCellsGrid;
	int[] hintOfBlackCell;
	
	@Before
	public void init() {
		
		size = 5;
		aleatoryCellsGrid = new CellsGrid(size);
		inBlankCellsGrid = CellsGrid.createInBlankCopyOfCellsGrid(aleatoryCellsGrid);
		
		hintOfBlackCell = aleatoryCellsGrid.getHintOfBlackCell();
		
	}

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
	
	@Test(expected = RuntimeException.class)
	public void getHintOfBlackCellOfInBlankCellsGridTest() {
		
		inBlankCellsGrid.getHintOfBlackCell();
		
	}
	
	@Test
	public void getHintOfBlackCellOfAleatoryCellsGridTest() {
		
		int[] hint = new int[2];
		assertEquals(hint.length, aleatoryCellsGrid.getHintOfBlackCell().length);
		
	}
	
	@Test
	public void hintInRangeTest() {

		boolean validHint = hintOfBlackCell[0] < size && hintOfBlackCell[0] >= 0;
		
		assertTrue(validHint);
		
	}
	
}

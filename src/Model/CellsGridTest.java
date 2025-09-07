package Model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

public class CellsGridTest {
	
	int size;
	CellsGrid aleatoryCellsGrid;
	CellsGrid inBlankCellsGrid;
	
	@Before
	public void init() {
		
		size = 5;
		aleatoryCellsGrid = new CellsGrid(size);
		inBlankCellsGrid = CellsGrid.createInBlankCopyOfCellsGrid(aleatoryCellsGrid);
		
		aleatoryCellsGrid.setRandomCellOfPlayerBoardHowBlack(inBlankCellsGrid);
		
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
	
	
	@Test
	public void revelateBlackCellOfAleatoryRowTest() {

		CellsGrid grid = new CellsGrid(10);

		grid.setRandomCellOfPlayerBoardHowBlack(inBlankCellsGrid);
	
	}
	
	@RepeatedTest(10)
	public void createCellsGridsForVerifyVelocityTest() {
		
		CellsGrid testCellsGrid = new CellsGrid(10);
		System.out.print(testCellsGrid.toString());
	}
	
	@RepeatedTest(10)
	public void notVoidStringOfBlackChainsLengthsTest() {
		
		CellsGrid testCellsGrid = new CellsGrid(10);
		
		assertNotEquals(0, testCellsGrid.getBlackChainsLengthsInRow(0).size());
		
	}
}

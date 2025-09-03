package Model;

import Model.Cell.CellStates;

import static org.junit.Assert.assertTrue;

import org.junit.*;

public class CellTest {

	Cell blankCell;
	
	@Before
	public void init() {
		
		blankCell = new Cell(CellStates.BLANK);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void createNullCellTest() {

		Cell testCell = new Cell(null);
		
	}
	
	@Test
	public void createInBlankCellTest() {
		
		Cell testCell = new Cell(CellStates.BLANK);
		
		assertTrue(testCell.isBlank());
		
	}
	
	@Test
	public void createPaintedCellTest() {
		
		Cell testCell = new Cell(CellStates.PAINTED);
		
		assertTrue(testCell.isPainted());
		
	}
	
	@Test
	public void createFlaggedCellTest() {
		
		Cell testCell = new Cell(CellStates.FLAGGED);
		
		assertTrue(testCell.isFlagged());
		
	}	

	@Test
	public void markCellWithFlagTest() {
		
		blankCell.establishCellHowFlagged();

		assertTrue(blankCell.isFlagged());
		
	}

	@Test
	public void markCellWithBlackTest() {
		
		blankCell.establishCellHowPainted();
		
		assertTrue(blankCell.isPainted());
		
	}
	
	@Test
	public void markCellWithBlankTest() {
		
		blankCell.establishCellHowBlank();

		assertTrue(blankCell.isBlank());
	}
	
	
}

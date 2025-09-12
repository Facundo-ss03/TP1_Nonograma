package Model;

import Model.ICell.CellStates;

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
		
		assertTrue(testCell.isBlack());
		
	}
	
	@Test
	public void createFlaggedCellTest() {
		
		Cell testCell = new Cell(CellStates.FLAGGED);
		
		assertTrue(testCell.isFlagged());
		
	}	

	@Test
	public void markCellWithFlagTest() {
		
		blankCell.ToFlagged();

		assertTrue(blankCell.isFlagged());
		
	}

	@Test
	public void markCellWithBlackTest() {
		
		blankCell.ToBlack();
		
		assertTrue(blankCell.isBlack());
		
	}
	
	@Test
	public void markCellWithBlankTest() {
		
		blankCell.ToBlank();

		assertTrue(blankCell.isBlank());
	}
	
	
}

package Testing;

import Model.Cell;
import org.junit.*;
import static org.junit.jupiter.api.Assertions.*;

public class CellTest {

	Cell blankCell;
	
	@Before
	public void init() {
		
		blankCell = new Cell();
		
	}
	
	
	@Test
	public void createInBlankCellTest() {
		
		Cell testCell = new Cell();
		
		assertTrue(testCell.isBlank());
		
	}
	
	@Test
	public void createPaintedCellTest() {
		
		Cell testCell = new Cell(true);
		
		assertTrue(testCell.isPainted());
		
	}
	
	@Test
	public void createFlaggedCellTest() {
		
		Cell testCell = new Cell(false);
		
		assertTrue(testCell.isFlagged());
		
	}	

	@Test
	public void markCellWithFlagTrueTest() {
		
		blankCell.markWithFlag();

		assertTrue(blankCell.isFlagged());
		
	}
	
	@Test
	public void markCellWithFlagFalseTest() {

		assertFalse(blankCell.isPainted());
		
	}

	@Test
	public void markCellWithBlackTest() {
		
		blankCell.markWithBlack();
		
		assertTrue(blankCell.isPainted());
		
	}
	
	@Test
	public void markCellWithBlankTest() {
		
		blankCell.markWithBlank();

		assertTrue(blankCell.isBlank());
	}
	
	
}

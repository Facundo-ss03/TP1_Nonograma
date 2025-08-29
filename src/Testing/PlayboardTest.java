package Testing;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.jupiter.api.RepeatedTest;

import Model.Playboard;

public class PlayboardTest {

	Playboard testBoard;
	
	@Before
	public void init() {
		
		testBoard = new Playboard(100);
		
	}
	
	@RepeatedTest(100)
	public void createNewPlayboardWithoutFullPaintedRows() {
		
		testBoard = new Playboard(100);
		
		assertTrue(testBoard.sinColumnasFullColor());
	}
	
}

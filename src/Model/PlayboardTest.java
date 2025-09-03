package Model;

import org.junit.Before;

public class PlayboardTest {

	private Playboard testBoard;
	private int[] testCell = {3, 5};
	
	@Before
	public void init() {
		
		this.testBoard = new Playboard(5);
	
	}
}

package Testing;

import org.junit.Before;
import Model.Playboard;

public class PlayboardTest {

	private Playboard testBoard;
	private int[] testCell = {3, 5};
	
	@Before
	public void init() {
		
		this.testBoard = new Playboard(5);
	
	}
}

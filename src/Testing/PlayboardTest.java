package Testing;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.RepeatedTest;

import Model.Cell;
import Model.Playboard;

public class PlayboardTest {

	private Playboard testBoard;
	private int[] testCell = {3, 5};
	
	@Before
	public void init() {
		
		this.testBoard = new Playboard(5);
	
	}
}

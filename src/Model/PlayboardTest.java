package Model;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class PlayboardTest {

	private Playboard testBoard;
	private int[] testCell = {3, 5};
	
	@Before
	public void init() {
		
		this.testBoard = new Playboard(5);
		
	}
	@Test
	    public void testListsExactlyEqual() {
	        List<List<Integer>> solution = new ArrayList<>();
	        solution.add(Arrays.asList(1,2));
	        solution.add(Arrays.asList(2,2));
	        solution.add(Arrays.asList(2,1));

	        List<List<Integer>> player = new ArrayList<>();
	        player.add(Arrays.asList(1,2));
	        player.add(Arrays.asList(2,2));
	        player.add(Arrays.asList(2,1));

	        assertTrue(solution.equals(player)); //, "Las listas deberían ser iguales"
	    }

	    @Test
	    public void testListsNotEqual() {
	        List<List<Integer>> solution = new ArrayList<>();
	        solution.add(Arrays.asList(1,2));
	        solution.add(Arrays.asList(2,2));
	        solution.add(Arrays.asList(2,1));

	        List<List<Integer>> player = new ArrayList<>();
	        player.add(Arrays.asList(2,1));
	        player.add(Arrays.asList(2,2));
	        player.add(Arrays.asList(2,1));

	        assertFalse(solution.equals(player)); //, "Las listas no deberían ser iguales"
	    }
	    @Test
	    public void testListsNotEqualEdge() {
	        List<List<Integer>> solution = new ArrayList<>();
	        solution.add(Arrays.asList(1,2));
	        solution.add(Arrays.asList(2,2));
	        solution.add(Arrays.asList(2,1));

	        List<List<Integer>> player = new ArrayList<>();
	        player.add(Arrays.asList(1,2));
	        player.add(Arrays.asList(2,2));
	        player.add(Arrays.asList(1,1));

	        assertFalse(solution.equals(player)); //, "Las listas no deberían ser iguales"
	    }
}

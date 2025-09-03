package Testing;

import org.junit.Test;

import Model.Tuple;

public class TuplaTest {

	@Test(expected = IllegalArgumentException.class)
	public void negativeTupleTest() {
		
		Tuple testTuple = new Tuple();
		
	}
	
}

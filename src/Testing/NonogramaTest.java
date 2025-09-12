package Testing;

import org.junit.Before;
import org.junit.Test;

import Model.INonograma;
import Model.INonograma.DifficultyLevels;

public class NonogramaTest {

	private INonograma nonograma;
	
	@Before
	public void init() {
		
		this.nonograma = INonograma.createNonograma(DifficultyLevels.EASY);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void rowNegativeInSearchOfBlackChainsInRow() {
		
		nonograma.getBlackChainsLengthsInRow(-1);
		
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void rowGreatInSearchOfBlackChainsInRow() {

		nonograma.getBlackChainsLengthsInColumn(10);
		
	}
	
}

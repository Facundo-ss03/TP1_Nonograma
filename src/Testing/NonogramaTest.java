package Testing;

import org.junit.Before;
import org.junit.Test;

import Model.INonograma;
import Model.INonograma.DifficultyLevels;

public class NonogramaTest {

	private INonograma nonograma;
	private int rowOutOfBounds;
	private int columnOutOfBounds;
	private int negativeIndex;
	
	@Before
	public void init() {
		
		this.nonograma = INonograma.createNonograma(DifficultyLevels.EASY);
		this.rowOutOfBounds = nonograma.getPlayboardSize();
		this.columnOutOfBounds = rowOutOfBounds;
		this.negativeIndex = -1;
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void rowNegativeInSearchOfBlackChainsInRow() {
		
		nonograma.getBlackChainsLengthsInRow(negativeIndex);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void rowGreatInSearchOfBlackChainsInRow() {

		nonograma.getBlackChainsLengthsInRow(rowOutOfBounds);
		
	}

	@Test(expected = IllegalArgumentException.class)
	public void columnNegativeInSearchOfBlackChain() {
		
		nonograma.getBlackChainsLengthsInColumn(negativeIndex);
	}

	@Test(expected = IllegalArgumentException.class)
	public void columnOutOfBoundsInSearchOfBlackChain() {
		
		nonograma.getBlackChainsLengthsInColumn(columnOutOfBounds);
	}
	
}

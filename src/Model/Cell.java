package Model;

import java.util.Objects;

class Cell implements ICell {

	private CellStates currentState;
	
	public Cell(CellStates state) {
		
		
		initializeCellState(state);
	}
	
		
	private void initializeCellState(CellStates state) throws IllegalArgumentException {
		
		if(state == null)
			throw new IllegalArgumentException("You cannot instantiate a Cell as null. The parameter 'state' was: " + state);
		
		switch(state) {
		
		case BLANK:
			currentState = CellStates.BLANK;
			break;
		case FLAGGED:
			currentState = CellStates.FLAGGED;
			break;
		case PAINTED:
			currentState = CellStates.PAINTED;
			break;
	
		}
	}
	
	@Override
	public CellStates getCurrentState() {
		return currentState;
	}
	
	@Override
	public void ToBlank() {

		currentState = CellStates.BLANK;
		
	}
	
	@Override
	public void ToFlagged() {

		currentState = CellStates.FLAGGED;
		
	}
	
	@Override
	public void ToBlack() {

		currentState = CellStates.PAINTED;
		
	}
	
	public boolean isBlank() {

		return currentState.equals(CellStates.BLANK);
		
	}
	
	public boolean isFlagged() {
		
		return currentState.equals(CellStates.FLAGGED);
		
	}
	
	public boolean isBlack() {

		return currentState.equals(CellStates.PAINTED);
		
	}
	
	@Override
	public String toString() {
		return currentState.toString();
	}
	
	public boolean equals(Object obj) {
		
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		
		Cell cell = (Cell)obj;
		
		return currentState == cell.currentState;
	}
	
	@Override
	public int hashCode() {
		
		return Objects.hash(currentState);
		
	}
	
}

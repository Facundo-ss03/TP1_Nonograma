package Model;

public class Cell {

	private CellStates CurrentState;
	public enum CellStates{ BLANK, FLAGGED, PAINTED }
	
	public Cell(CellStates state) {
		
		startCellState(state);
		
	}
	
	private void startCellState(CellStates state) {

		initializeCell(state);
		
	}
	
	private void initializeCell(CellStates state) {
		
		switch(state) {
		
		case BLANK:
			establishCellHowBlank();
			break;
		case FLAGGED:
			establishCellHowFlagged();
			break;
		case PAINTED:
			establishCellHowPainted();
			break;
	
		}
	}
	
	public void establishCellHowBlank() {

		CurrentState = CellStates.BLANK;
		
	}
	
	public void establishCellHowFlagged() {

		CurrentState = CellStates.FLAGGED;
		
	}
	
	public void establishCellHowPainted() {

		CurrentState = CellStates.PAINTED;
		
	}
	
	public boolean isBlank() {

		return CurrentState.equals(CellStates.BLANK);
		
	}
	
	public boolean isFlagged() {
		
		return CurrentState.equals(CellStates.FLAGGED);
		
	}
	
	public boolean isPainted() {

		return CurrentState.equals(CellStates.PAINTED);
		
	}
	
	public String toString() {
		return CurrentState.toString();
	}
}

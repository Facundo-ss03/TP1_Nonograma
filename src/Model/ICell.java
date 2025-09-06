package Model;

public interface ICell {

	public enum CellStates{ BLANK, FLAGGED, PAINTED }
	
	public void establishCellHowBlank();
	
	public void establishCellHowFlagged();
	
	public void establishCellHowBlack();
	
	public CellStates getCurrentState();
}

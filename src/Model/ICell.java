package Model;

public interface ICell {

	public enum CellStates{ BLANK, FLAGGED, PAINTED }
	
	public CellStates getCurrentState();

	public boolean isFlagged();
	
	public boolean isBlack();
	
	public boolean isBlank();
}

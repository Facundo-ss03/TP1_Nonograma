package Model;

public interface ICell {

	public enum CellStates{ BLANK, FLAGGED, PAINTED }
	
	public void ToBlank();
	
	public void ToFlagged();
	
	public void ToBlack();
	
	public CellStates getCurrentState();
}

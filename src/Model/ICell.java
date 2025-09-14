package Model;

public interface ICell {

	public enum CellStates{ BLANK, FLAGGED, PAINTED }
	
	public CellStates getCurrentState();
}

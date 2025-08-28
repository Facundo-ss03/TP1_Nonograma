package Model;

public class Cell {

	private boolean isFlagged;
	private boolean isPainted;
	
	public Cell() {
		
		this.isFlagged = false;
		this.isPainted = false;
		
	}

	public Cell(boolean paintedOrFlaggedCell) {

		startCellState(paintedOrFlaggedCell);
		
	}
	
	private void startCellState(boolean paintedOrFlaggedCell) {
		
		if(paintedOrFlaggedCell)
			establishCellHowPainted();
		else establishCellHowFlagged();
		
	}
	
	private void establishCellHowFlagged() {

		isFlagged = true;
		isPainted= false;
		
	}
	
	private void establishCellHowPainted() {

		isFlagged = false;
		isPainted= true;
		
	}
	
	public boolean isBlank() {

		if(isFlagged == false && isPainted == false)
			return true;
		else return false;
		
	}
	
	public boolean isFlagged() {
		
		return isFlagged;
		
	}
	
	public boolean isPainted() {
		
		return isPainted;
		
	}
	
	public void markWithFlag() {

		isPainted = false;
		isFlagged = true;
		
	}
	
	public void markWithBlack() {

		isFlagged = false;
		isPainted = true;
		
	}
	
	public void markWithBlank() {

		isFlagged = false;
		isPainted = false;
		
	}
}

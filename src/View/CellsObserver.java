package View;

import Model.ICell;
import Model.CellObserver;

public class CellsObserver implements CellObserver{

	@Override
	public void notify(ICell cell) {
		
		System.out.print("Celda cambió a : " + cell.getCurrentState());
		
	}

}

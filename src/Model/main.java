package Model;

public class main {

	public static void main(String args[]) {

		CellsGrid d = new CellsGrid(5);
		
		System.out.print(d.toString());


		Iterable<ITuple> blackChainsInRows = d.getSetOfLengthsOfBlackChainsInRows();
		Iterable<ITuple> blackChainsInColumns = d.getSetOfLengthsOfBlackChainsInColumns();

		System.out.print("Longitudes de cadenas negras en las filas: \n");
		for(ITuple tuple : blackChainsInRows)
			System.out.print(tuple.getFirstChainLength() + " " + tuple.getSecondChainLength() + "\n");

		System.out.print("Longitudes de cadenas negras en las columnas: \n");
		for(ITuple tuple : blackChainsInColumns)
			System.out.print(tuple.getFirstChainLength() + " " + tuple.getSecondChainLength() + "\n");
		
	}
	
}

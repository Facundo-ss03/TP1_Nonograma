package Model;

import java.util.List;

public class main {

	public static void main(String args[]) {

		//CellsGrid d = new CellsGrid(10);
	
		//System.out.print(d.toString());
		
		/*
		System.out.print("largos por filas:" + "\n");
		System.out.print(d.getAllBlackChainsLengthsInRows());

		System.out.print("largos por columnas:" + "\n");
		System.out.print(d.getAllBlackChainsLengthsInColumns());
		*/

		Playboard a = new Playboard(10);
		System.out.print(a.toString());
		System.out.print("largos por filas:" + "\n");
		System.out.print(a.getBlackChainsInAllRows());

		System.out.print("largos por columnas:" + "\n");
		System.out.print(a.getBlackChainsInAllColumns());
		
		System.out.print("largos por row 1:" + "\n");
		System.out.print(a.getBlackChainsLengthsInRow(0));
				
	}
}

package Model;

import java.util.ArrayList;
import java.util.List;

public class main {

	public static void main(String args[]) {

		CellsGrid d = new CellsGrid(5);
		
		System.out.print(d.toString());

		Tuple blackChains = new Tuple();
		
		blackChains = d.getLengthOfBlackChainsInRow(2);
		
		//System.out.print(blackChains.getX() + " " + blackChains.getY());
		
		List<Tuple> tuplesList = new ArrayList();

		for(int i = 0; i < 5; i++) {
			
			tuplesList.add(d.getLengthOfBlackChainsInRow(i));
			
		}
		
		for(Tuple tuple : tuplesList)
			System.out.print(tuple.getX() + " " + tuple.getY() + "\n");
		
	}
	
}

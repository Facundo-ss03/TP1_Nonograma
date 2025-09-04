package Model;

import java.util.ArrayList;
import java.util.List;

class BlackChainsDetector {

	public static List<Integer> detectBlackChainsByRows(Cell[] row) {
		
		List<Integer> resultado = new ArrayList<>();
	    int columns = row.length;
	    int blackChainLength = 0;

	    for (Cell cell : row) {
	    	if (cell.isPainted()) {
	    		blackChainLength++;
	    	} else {
	        	if (blackChainIsInRange(blackChainLength, columns)) {
	            	resultado.add(blackChainLength);
	        	}
	        	blackChainLength = 0;
	            }
	        }

	    if (blackChainIsInRange(blackChainLength, columns))
	    	resultado.add(blackChainLength);

	    return resultado;
	}
	
	private static boolean blackChainIsInRange(int chainLength, int numberOfColumns) {
		
		if(chainLength > 0 && chainLength < numberOfColumns)
			return true;
		else return false;
		
	}
	
	public static List<Integer> detectBlackChainsByColumns(Cell[][] grid, int column){

		List<Integer> resultado = new ArrayList<>();
	    int rows = grid.length;
	    int blackChainLength = 0;


	    for (int row = 0; row < grid.length; row++) {
	    		
	    	if(grid[row][column].isPainted()) {
	    		blackChainLength++;
	    	} else {
	    			
	    		if(blackChainIsInRange(blackChainLength, rows)) {
	    			resultado.add(blackChainLength);
	    		}
	    			
	    		blackChainLength = 0;
	    			
	    	}
	    		
	    }

	    if(blackChainIsInRange(blackChainLength, rows))
	    		resultado.add(blackChainLength);
    	

	    return resultado;
	}
}

package Model;

import java.util.ArrayList;
import java.util.List;

public class BlackChainsDetector {

	public static List<Integer> detectBlackChainsByRow(Cell[] row) {
		
		List<Integer> resultado = new ArrayList<>();
	    int columnas = row.length;
	    int longitudCadena = 0;

	    for (int col = 0; col < columnas; col++) {
	    	if (row[col].isPainted()) {
	        	longitudCadena++;
	    	} else {
	        	if (longitudCadena > 0 && longitudCadena < columnas) {
	            	resultado.add(longitudCadena);
	        	}
	            	longitudCadena = 0;
	            }
	        }

	    // Verificar si la fila termina con una cadena válida
	    if (longitudCadena > 0 && longitudCadena < columnas) {
	    	resultado.add(longitudCadena);
	    }

	    return resultado;
	}
}

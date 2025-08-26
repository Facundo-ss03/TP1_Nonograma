package Testing;

public class playBoard {
	enum cells {isPainted , isX}
	String playBoard[][] ;
	
	
	public playBoard(int size) {
		for (int i=0;i<size;i++) {
			for(int j=0;j<size;j++) {
				if(Math.random()<0.5) {
				playBoard[i][j]="isPainted";}
			
				else playBoard[i][j]="isX";
			}
		
		}
	}
}
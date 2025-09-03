package Model;

public class Tuple{

	private int x;
	private int y;

	public Tuple(int x, int y) {

		this.x = x;
		this.y = y;
		
	}
	
	public Tuple() {

		this.x = 0;
		this.y = 0;
		
	}

	public int getX() {
		
		return x;
		
	}
	
	
	public int getY() {
		
		return y;
		
	}
	
	public void setFirstChain(int value) {

		if(value < 0) {
			throw new IllegalArgumentException("La tupla no puede tener valores negativos.");
		}
		
		x = value;
		
	}
	
	public void setSecondChain(int value) {

		if(value < 0) {
			throw new IllegalArgumentException("La tupla no puede tener valores negativos.");
		}
		
		y = value;
		
	}
}

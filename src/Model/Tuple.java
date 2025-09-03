package Model;

class Tuple implements ITuple{

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
	
	public void setFirstChain(int value) {

		if(value < 0) {
			throw new IllegalArgumentException("La tupla no puede tener valores negativos.");
		}
		
		x = value;
		
	}
	
	public void setSecondChain(int value) {

	}

	@Override
	public int getFirstChainLength() {
		return x;
	}

	@Override
	public int getSecondChainLength() {
		return y;
	}
}

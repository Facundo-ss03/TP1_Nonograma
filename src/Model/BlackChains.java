package Model;

public class BlackChains {

	private int firstChain;
	private int secondChain;
	
	public BlackChains() {

		firstChain = 0;
		secondChain = 0;
		
	}
	
	public int getFirsttChainLength() {
		
		return firstChain;
		
	}
	
	public int getSecondChainLength() {
		
		return secondChain;
		
	}

	public void incrementFirstChainLength() {
		
		firstChain++;
		
	}
	
	public void setSecondChainLength(int newLength) {
		
		secondChain++;
		
	}
}

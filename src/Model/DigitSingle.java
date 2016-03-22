package Model;

public class DigitSingle implements Digit {
	protected int theValue;
	
	public void setValue(int newValue) {
		this.theValue = newValue;
	}
	
	public int get() {
		return this.theValue;
	}
}

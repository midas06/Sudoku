package Model;

public class DigitMultiple implements Digit {
	protected int[] allValues;
	
	public void setValues(int[] newValues) {
		this.allValues = newValues;
	}
	
	public int[] get() {
		return this.allValues;
	}
	
}

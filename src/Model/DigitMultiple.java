package Model;

public class DigitMultiple implements Digit {
	protected int[] allValues;
	
	public void setValues(int[] newValues) {
		this.allValues = newValues;
	}
	
	public int[] get() {
		return this.allValues;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < this.allValues.length - 1; i++) {
			sb.append(this.allValues[i]);
		}
		sb.append(this.allValues[this.allValues.length]);
		return sb.toString();
	}
	
}

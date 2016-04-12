package Model;

public interface Digit {
	public void addValues(int[] newValues);	
	public int[] getValues();	
	@Override
	public String toString();	
	public void clear();	
	public String serialize();	
	public void deserialize(String s);
}

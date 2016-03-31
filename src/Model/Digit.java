package Model;

import java.util.ArrayList;
import java.util.List;

public class Digit {
	
	protected List<Integer> allValues;
	
	public Digit() {
		this.allValues = new ArrayList<Integer>();
	}
		
	public void addValues(int[] newValues) {
		for (int i : newValues) {
			this.allValues.add(i);
		}
	}
	
	public int[] getValues() {
		int[] output = new int[this.allValues.size()];
		for (int i = 0; i < this.allValues.size(); i++) {
			output[i] = this.allValues.get(i);
		}
		return output;
	}
	
	public String toString() {
		String s;
		
		if (this.allValues.size() == 0) {
			s = " ";
		} else if (this.allValues.size() == 1){
			s = this.allValues.get(0).toString();
		} else {
			s = "M";
		}
			
		return s;
		
	}
	
	public void clear() {
		this.allValues.clear();
	}
	
}

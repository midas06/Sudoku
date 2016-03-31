package Model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Checker {
	
	protected List<Cell> theList;
	protected int[] acceptableValues;
	protected List<Integer> usedValues;
	
	public Checker(int maxValue) {
		this.init(maxValue);
	}
	
	protected void init(int maxValue) {
		this.acceptableValues = new int[maxValue];
		for(int i = 1; i <= maxValue; i++) {
			this.acceptableValues[i - 1] = i;
		}		
	}	
	
	public void set(List<Cell> newList) {
		this.theList = newList;
		this.setUsedValues();
	}
	
	protected void setUsedValues() {
		this.usedValues = new ArrayList<Integer>();
		for (Cell c : this.theList) {
			for (int i : c.getDigit().getValues()) {
				this.usedValues.add(i);
			}
		}	
	}
	
	public void test() {
		for (int i : this.usedValues) {
			System.out.println(i);
		}
	}
	
	public boolean hasDuplicates() {
		Set<Integer> hashset = new HashSet<Integer>();
		for (int i : this.usedValues) {
			if (hashset.contains(i)) {
				return true;
			}
			hashset.add(i);
		}		
		return false;
	}
	
	public boolean isComplete() {
		List<Integer> comparisonList = new ArrayList<Integer>();
		for (int i = 0; i < this.acceptableValues.length; i++) {
			comparisonList.add(this.acceptableValues[i]);
		}
		
		Collections.sort(this.usedValues);
		
		return comparisonList.equals(this.usedValues);
	}
}

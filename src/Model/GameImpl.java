package Model;

import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {//, Gets, Sets {
	
	private int maximum, squareWidth, squareHeight;
	private List<Cell> puzzleArray;

	
	public List<Cell> getByRow(int rowIndex) {
		List<Cell> theList = new ArrayList<Cell>();
		
		for (Cell c : this.puzzleArray) {
			if (c.getRowIndex() == rowIndex) {
				theList.add(c);
			}
		}
		return theList;
	}
	
	public List<Cell> getByColumn(int columnIndex) {
		List<Cell> theList = new ArrayList<Cell>();
		
		for (Cell c : this.puzzleArray) {
			if (c.getColumnIndex() == columnIndex) {
				theList.add(c);
			}
		}
		return theList;
	}
	
	public void init() {
		int count = 0;
		
		while (count < this.maximum) {
			Cell c = new Cell(count, this);
			this.puzzleArray.add(c);
			count++;
		}
	}
	
	public void setMaxValue(int maximum) {
		this.maximum = maximum;
		this.puzzleArray = new ArrayList<Cell>();
	}
	
    public int getMaxValue() {
    	return this.maximum;
    }
    

    
    public void set(int[] cellValues) {
    	
    }
    
    public void setSquareWidth( int squareWidth) {
    	this.squareWidth = squareWidth;
    }
    
    public void setSquareHeight( int squareHeight) {
    	this.squareHeight = squareHeight;
    }
    
    public void restart() {
    	
    }
}

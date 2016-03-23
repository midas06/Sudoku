package Model;

import java.util.ArrayList;
import java.util.List;

public class GameImpl implements Game {//, Gets, Sets {
	
	private int maximum, squareWidth, squareHeight;
	private List<Cell> thePuzzle;
	private PuzzleStringBuilder psb;

	public GameImpl() {
		this.psb = new PuzzleStringBuilder(this);
	}
	
//	public List<Cell> getCellListByRow(int rowIndex) {
//		List<Cell> theList = new ArrayList<Cell>();
//		
//		for (Cell c : this.puzzleArray) {
//			if (c.getRowIndex() == rowIndex) {
//				theList.add(c);
//			}
//		}
//		return theList;
//	}
//	
//	public List<Cell> getCellListByColumn(int columnIndex) {
//		List<Cell> theList = new ArrayList<Cell>();
//		
//		for (Cell c : this.puzzleArray) {
//			if (c.getColumnIndex() == columnIndex) {
//				theList.add(c);
//			}
//		}
//		return theList;
//	}
	
	public void init() {
		int count = 0;
		
		while (count < this.maximum) {
			Cell c = new Cell(count, this);
			this.thePuzzle.add(c);
			count++;
		}
	}
	
	public void setPuzzle(List<Cell> newPuzzle) {
		this.thePuzzle = newPuzzle;
	}
	
	public void setMaxValue(int maximum) {
		
				
		this.maximum = maximum;
		this.thePuzzle = new ArrayList<Cell>();
		this.setSquares();
		this.init();
		
	}
	
	public void setSquares() {
		if (this.maximum == 81) {
			this.setSquareHeight(3);
			this.setSquareWidth(3);
		} else if (this.maximum == 36) {
			this.setSquareHeight(2);
			this.setSquareWidth(3);
		}
	}
	
    public int getMaxValue() {
    	return this.maximum;
    }
    
    public List<Cell> getPuzzle() {
    	return this.thePuzzle;
    }
    
    public void set(int[] cellValues) {
    	
    }
    
    public void setSquareWidth(int squareWidth) {
    	this.squareWidth = squareWidth;
    }
    
    public void setSquareHeight(int squareHeight) {
    	this.squareHeight = squareHeight;
    }
    
    public int getSquareWidth() {
    	return this.squareWidth;
    }
    
    public int getSquareHeight() {
    	return this.squareHeight;
    }
    
    public void restart() {
    	
    }
    
    public void setValueByCoord(int newValue, int column, int row) {
    	Cell theCell = this.thePuzzle.get(0);
    	boolean isFound = false;
    	for (Cell c : this.thePuzzle) {
    		if (c.getColumnIndex() == column && c.getRowIndex() == row) {
    			theCell = c;
    			isFound = true;
    		}
    	}
    	
    	if (isFound) {
        	DigitSingle d = new DigitSingle();
        	d.setValue(newValue);
        	theCell.setDigit(d); 
    	}    	
    }
    
    
    public String exportMap() {
    	return this.psb.toString();
    }
}

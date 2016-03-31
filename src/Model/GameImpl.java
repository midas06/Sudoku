package Model;

import java.util.ArrayList;
import java.util.List;
import Extras.InvalidDimensionException;

public class GameImpl implements Game {//, Gets, Sets {
	
	private int maximum, squareWidth, squareHeight;
	private List<Cell> thePuzzle;
	private List<List<Cell>> moveHistory;
	private PuzzleStringBuilder psb;

	public GameImpl() {
		this.psb = new PuzzleStringBuilder(this);
		this.moveHistory = new ArrayList<List<Cell>>();
	}
		
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
		try {
			if (this.maximum == 81) {
				this.setSquareHeight(3);
				this.setSquareWidth(3);
			} else if (this.maximum == 36) {
				this.setSquareHeight(2);
				this.setSquareWidth(3);
			} else if (this.maximum == 16) {
				this.setSquareHeight(2);
				this.setSquareWidth(2);
			} else {
				throw new InvalidDimensionException();
			}
		} catch (InvalidDimensionException e) {
			System.out.println("Invalid puzzle dimensions");
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
    	for(Cell c : this.thePuzzle) {
    		if (!c.isFixed) {
    			c.clear();
    			this.moveHistory.clear();
    		}
    	}
    }
    
    public Cell getCellByCoord(int column, int row) {
    	List<Cell> byRow = PuzzleHelper.getCellListByRow(this.thePuzzle, row);
    	return byRow.get(column);
    }
    
    public Cell getCellBySquare(int squareNumber, int squareIndex) {
    	List<Cell> bySq = PuzzleHelper.getCellListBySquare(this.thePuzzle, squareNumber);
    	return bySq.get(squareIndex);
    }
    
    
    public void addSingleValue(int newValue, Cell theCell) {    	
    	int[] arr = new int[1];
		arr[0] = newValue;
		theCell.addDigitValues(arr);    	
    }
    
    public void addMultipleValues(int[] newValues, Cell theCell) {
		theCell.addDigitValues(newValues);    
    }
    
    protected void takeSnapshot() {
    	this.moveHistory.add(this.thePuzzle);
    }
    
    public void undo() {
    	this.thePuzzle = this.moveHistory.get(this.moveHistory.size() - 1);
    }
    
    
    public String exportMap() {
    	return this.psb.toString();
    }
}

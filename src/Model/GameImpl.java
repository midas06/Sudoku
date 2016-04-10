package Model;

import java.util.ArrayList;
import java.util.List;
import Extras.InvalidDimensionException;

public class GameImpl implements Game {
	
	private int maximum, squareWidth, squareHeight;
	protected List<Cell> thePuzzle;
	public List<List<String>> moveHistory;
	private PuzzleStringBuilder psb;
	private boolean mapIsSet;

	public GameImpl() {
		this.psb = new PuzzleStringBuilder(this);
		this.moveHistory = new ArrayList<List<String>>();
		this.mapIsSet = false;
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
	
	public void finaliseInitialPuzzle() {
		this.mapIsSet = true;
		for (Cell c : this.thePuzzle) {
			c.setFixed();
		}
		this.takeSnapshot();
	}
	
	
    public int getMaxValue() {
    	return this.maximum;
    }
    
    public int getMaxDimension() {
    	double d = Math.sqrt((double)this.maximum);
    	return (int)d;
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
    	List<Cell> initialState = new ArrayList<Cell>();
    	
    	for (String s : this.moveHistory.get(0)) {
    		Cell c = new Cell(this);
    		c.deserialize(s);
    		initialState.add(c);
    	}
    	
    	this.thePuzzle = initialState;
    	this.moveHistory.clear();
    	this.takeSnapshot();
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
		if (this.mapIsSet) {
			this.takeSnapshot();
		}
		
    }
    
    public void addMultipleValues(int[] newValues, Cell theCell) {
		theCell.addDigitValues(newValues);    
    }
    
    protected void takeSnapshot() {
    	if (this.mapIsSet) {
    		this.moveHistory.add(this.serialize());
    	}    	
    }
    
    public void undo() {    	
    	List<Cell> pastMove = new ArrayList<Cell>();
    	
    	for (String s : this.moveHistory.get(this.moveHistory.size() - 2)) {
    		Cell c = new Cell(this);
    		c.deserialize(s);
    		pastMove.add(c);
    	}
    	
    	this.thePuzzle = pastMove;
    	this.moveHistory.remove(this.moveHistory.size() - 1);
    }    
    
    public String exportMap() {
    	return this.psb.toString();
    }
    
    public List<String> serialize() {
    	List<String> srlPuzzle = new ArrayList<String> ();
    	for (Cell c : this.thePuzzle) {
    		srlPuzzle.add(c.serialize());
    	}
    	return srlPuzzle;
    }
    
    public int getMoveCount() {
    	return this.moveHistory.size() - 1;
    }
    
    
    public boolean isSolved() {
    	Checker c = new Checker(this.getMaxDimension());
    	for (int i = 0; i < this.getMaxDimension(); i++) {
    		c.set(PuzzleHelper.getCellListByColumn(this.thePuzzle, i));
    		if (!c.isComplete()) {
    			System.out.println("col: " + i);
    			return false;
    		}
    	}
    	
    	for (int j = 0; j < this.getMaxDimension(); j++) {
    		c.set(PuzzleHelper.getCellListByRow(this.thePuzzle, j));
    		if (!c.isComplete()) {
    			System.out.println("row: " + j);
    			return false;
    		}
    	}
    	
    	for (int k = 0; k < this.getMaxDimension(); k++) {
    		c.set(PuzzleHelper.getCellListBySquare(this.thePuzzle, k));
    		if (!c.isComplete()) {
    			System.out.println("sq: " + k);
    			return false;
    		}
    	}
    	
    	return true;
    }

}


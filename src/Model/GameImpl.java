package Model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import Extras.InvalidDimensionException;

public class GameImpl implements Game, Gets, Sets {
	
	private int maximum, squareWidth, squareHeight;
	private Checker checker;
	protected List<Cell> thePuzzle;
	private List<List<String>> moveHistory;
	private PuzzleStringBuilder psb;
	private boolean mapIsSet;

	public GameImpl() {
		this.psb = new PuzzleStringBuilder(this);
		this.checker = new Checker();
		this.moveHistory = new ArrayList<List<String>>();
		this.mapIsSet = false;
	}
	
	protected void init() {
		int count = 0;
		
		while (count < this.maximum) {
			Cell c = new Cell(count, this);
			this.thePuzzle.add(c);
			count++;
		}
		
		this.checker.init(this.getMaxDimension());
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
	
	protected void setSquares() {
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
			if (c.digit.getValues().length > 0) {
				c.setFixed();
			}			
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
    
    public Cell getCellByCoord(int columnIndex, int rowIndex) {
    	List<Cell> byRow = PuzzleHelper.getCellListByRow(this.thePuzzle, rowIndex);
    	return byRow.get(columnIndex);
    }
    
    public Cell getCellBySquare(int cellIndex, int theSquare) {
    	List<Cell> bySq = PuzzleHelper.getCellListBySquare(this.thePuzzle, theSquare);
    	return bySq.get(cellIndex);
    }
    
    
    public void setSingleValue(int newValue, Cell theCell) {    	
    	try {
    		if (newValue > 0 && newValue <= this.getMaxDimension()) {
    			int[] arr = new int[1];
    			arr[0] = newValue;
    			theCell.addDigitValues(arr);
    			if (this.mapIsSet) {
    				this.takeSnapshot();
    			}
    		} else {
    			throw new InvalidDimensionException();
    		}
    	} catch (InvalidDimensionException e) {
    		System.out.println("Invalid input");
    	}
    }
    
    public void setMultipleValues(int[] newValues, Cell theCell) {
    	try {
    		if (this.testArrayValidity(newValues)) {
    			theCell.addDigitValues(newValues);
    			if (this.mapIsSet) {
    				this.takeSnapshot();
    			}
    		} else {
    			throw new InvalidDimensionException();
    		}
    	} catch (InvalidDimensionException e){
    		System.out.println("Invalid input");	
    	}    	
    }
    		
    protected boolean testArrayValidity(int[] newValues) {
    	for (int i : newValues) {
    		if (i <= 0 || i > this.getMaxDimension()) {
    			return false;
    		}
    	}
    	return true;
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
    
    protected List<String> serialize() {
    	List<String> srlPuzzle = new ArrayList<String> ();
    	for (Cell c : this.thePuzzle) {
    		srlPuzzle.add(c.serialize());
    	}
    	return srlPuzzle;
    }
    
    public int getMoveCount() {
    	return this.moveHistory.size() - 1;
    }
    
    public void clearCell(Cell c) {
    	c.clear();
    	this.takeSnapshot();
    }
    
    
    public boolean isSolved() {
    	for (int i = 0; i < this.getMaxDimension(); i++) {
    		this.checker.set(PuzzleHelper.getCellListByColumn(this.thePuzzle, i));
    		if (!this.checker.isComplete()) {
    			return false;
    		}
    	}
    	
    	for (int j = 0; j < this.getMaxDimension(); j++) {
    		this.checker.set(PuzzleHelper.getCellListByRow(this.thePuzzle, j));
    		if (!this.checker.isComplete()) {
    			return false;
    		}
    	}
    	
    	for (int k = 0; k < this.getMaxDimension(); k++) {
    		this.checker.set(PuzzleHelper.getCellListBySquare(this.thePuzzle, k));
    		if (!this.checker.isComplete()) {
    			return false;
    		}
    	}
    	
    	return true;
    }
    
    public Checker getChecker() {
    	return this.checker;
    }
    
    public List<Integer> getAllPossibleValues(Cell theCell) {
    	this.checker.set(PuzzleHelper.getCellListByRow(this.getPuzzle(), theCell.getRowIndex()));
    	Set<Integer> rowPossible = new HashSet<Integer> (this.checker.getUnusedValues());
    	Set<Integer> rowImpossible = new HashSet<Integer> (this.checker.getUsedValues());
    	
    	this.checker.set(PuzzleHelper.getCellListByColumn(this.getPuzzle(), theCell.getColumnIndex()));
    	Set<Integer> columnPossible = new HashSet<Integer> (this.checker.getUnusedValues());
    	Set<Integer> columnImpossible = new HashSet<Integer> (this.checker.getUsedValues());
    	
    	this.checker.set(PuzzleHelper.getCellListBySquare(this.getPuzzle(), theCell.getSquareIndex()));
    	Set<Integer> squarePossible = new HashSet<Integer> (this.checker.getUnusedValues());
    	Set<Integer> squareImpossible = new HashSet<Integer> (this.checker.getUsedValues());
    	
    	rowPossible.removeAll(columnImpossible);
    	rowPossible.removeAll(squareImpossible);
    	columnPossible.removeAll(rowImpossible);
    	columnPossible.removeAll(squareImpossible);
    	squarePossible.removeAll(columnImpossible);
    	squarePossible.removeAll(rowImpossible);
    	
    	rowPossible.addAll(columnPossible);
    	rowPossible.addAll(squarePossible);
    	
    	return new ArrayList<Integer>(rowPossible);
    	
    }
    
    public List<Integer> getAllImpossibleValues(Cell theCell) {
    	Set<Integer> possibleValues = new HashSet<Integer> (this.getAllPossibleValues(theCell));
    	Set<Integer> acceptableValues = new HashSet<Integer> (this.checker.acceptableArrayToList());
    	
    	acceptableValues.removeAll(possibleValues);
    	return new ArrayList<Integer>(acceptableValues);
    }
    

}


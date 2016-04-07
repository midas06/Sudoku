package Model;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import Extras.InvalidDimensionException;
import org.apache.commons.lang3.*;
import Extras.DeepCopier; 

public class GameImpl implements Game {//, Gets, Sets {
	
	private int maximum, squareWidth, squareHeight;
	private List<Cell> thePuzzle;
	public List<List<Cell>> moveHistory;
	private PuzzleStringBuilder psb;
	private boolean mapIsSet;
	public List<byte[]> historyTest;

	public GameImpl() {
		this.psb = new PuzzleStringBuilder(this);
		this.moveHistory = new ArrayList<List<Cell>>();
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
		this.historyTest = new ArrayList<byte[]>();
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
		if (this.mapIsSet) {
			this.takeSnapshot();
		}
		
    }
    
    public void addMultipleValues(int[] newValues, Cell theCell) {
		theCell.addDigitValues(newValues);    
    }
    
    protected void takeSnapshot() {
    	//List<Cell> newSnapshot = SerializationUtils.clone((T) this.thePuzzle);
//    	List<Cell> newSnapshot = new ArrayList<Cell>(this.thePuzzle.size());
//    	for (Cell c : this.thePuzzle) {
//    		newSnapshot.add(new Cell(c));
//    	}
//    	this.moveHistory.add(newSnapshot);
    	
    	try {
    		byte[] serializedMap = DeepCopier.toByteStream(this.thePuzzle);
    		this.historyTest.add(serializedMap);
    	} catch (IOException e) {
    		System.out.println("didnt work");
    	}
    	
    	
    }
    
    public List<byte[]> testBytes(List<Cell> newPuzzle) {
    	//List<Cell> newSnapshot = SerializationUtils.clone((T) this.thePuzzle);
//    	List<Cell> newSnapshot = new ArrayList<Cell>(this.thePuzzle.size());
//    	for (Cell c : this.thePuzzle) {
//    		newSnapshot.add(new Cell(c));
//    	}
//    	this.moveHistory.add(newSnapshot);
    	List<byte[]> serializedMap = new ArrayList<byte[]> ();
    	try {
    		for (Cell c : newPuzzle) {
    			byte[] b = DeepCopier.toByteStream(c);
        		serializedMap.add(b);
    		}    		
    		
    	} catch (IOException e) {
    		System.out.println("serialization error");
    	}
    	return serializedMap;    	
    }
    
    @SuppressWarnings("unchecked")
	public List<Cell> testDeserialization(byte[] input) {
    	List<Cell> c = new ArrayList<Cell>();
    	try {
    		c = (List<Cell>)DeepCopier.toObject(input);
    	} catch (IOException e) {
    		System.out.println("deserialization error");
    	} catch (ClassNotFoundException f) {
    		System.out.println("deserialization error 2");
    	}
    	
    	
    	return c;
    }
    
    public void undo() {    	
    	this.thePuzzle = this.moveHistory.get(this.moveHistory.size() - 1);
    }
    
    public List<Cell> testUndo() {    	
    	return this.moveHistory.get(this.moveHistory.size() - 2);
    }
    
    
    public String exportMap() {
    	return this.psb.toString();
    }
}

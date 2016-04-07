package Model;

import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputValidation;
import java.io.ObjectStreamException;
import java.io.Serializable;

public class Cell implements Serializable, ObjectInputValidation{
	
	protected GameImpl theGame;
	protected int cellIndex, rowIndex, columnIndex, squareIndex;
	protected Digit digit;
	protected boolean isFixed;
	
	public Cell(Cell cell) {
		this.cellIndex = cell.getIndex();
		this.rowIndex = cell.getRowIndex();
		this.columnIndex = cell.getColumnIndex();
		this.squareIndex = cell.getSquareIndex();
		this.theGame = this.getGame();
		this.digit = cell.getDigit();
		this.isFixed = cell.getIsFixed();
	}
	
	public GameImpl getGame() {
		return this.theGame;
	}
	
	public Digit getDigit() {
		return this.digit;
	}
	
	public void addDigitValues(int[] newValues) {
		this.digit.addValues(newValues);
	}
	
	
	public Cell(int newIndex, GameImpl theGame) {
		this.cellIndex = newIndex;
		this.theGame = theGame;
		this.calcRowColumn();
		this.calcSquare();
		this.digit = new Digit();
	}
	
	protected void calcRowColumn() {
		double sqrt, row;
		sqrt = Math.sqrt(theGame.getMaxValue());

		row = (double)this.cellIndex / sqrt;
		this.rowIndex = (int)Math.floor(row);
		
		this.columnIndex = this.cellIndex % (int)sqrt;
	}
	
	protected void calcSquare() {
		int squareWidth, squareHeight, numColSquares, numRowSquares, squareRow, squareCol;
		squareWidth = this.theGame.getSquareWidth();
		squareHeight = this.theGame.getSquareHeight();
		numColSquares = (int)Math.sqrt(theGame.getMaxValue()) / squareHeight;
		numRowSquares = (int)Math.sqrt(theGame.getMaxValue()) / squareWidth;
		
		squareCol = this.columnIndex / squareWidth;
		squareRow = this.rowIndex / squareHeight;
		
		this.squareIndex = squareCol + squareRow * numRowSquares;
		
	}
	
	public void setFixed() {
		this.isFixed = true;
	}
	
	public void clear() {
		this.digit.clear();
	}
	
	
	public int getRowIndex() {
		return this.rowIndex;
	}
	
	public int getColumnIndex() {
		return this.columnIndex;
	}
	
	public int getIndex() {
		return this.cellIndex;
	}
	
	public int getSquareIndex() {
		return this.squareIndex;
	}
	
	public boolean getIsFixed() {
		return this.isFixed;
	}

	@Override
	public void validateObject() throws InvalidObjectException {
		System.out.println("validated");
		
	}
	
	private void writeObject(java.io.ObjectOutputStream out) throws IOException {
		System.out.println("writing");
		out.defaultWriteObject();
	}
	
	private Object writeReplace() throws ObjectStreamException {
        System.out.println("writeReplace");
        return this;
    }
 
    private void readObject(java.io.ObjectInputStream in) throws IOException, ClassNotFoundException {
        System.out.println("readObject");
        in.registerValidation(this, 0);
        in.defaultReadObject();
    }
    
    private Object readResolve() throws ObjectStreamException {
        System.out.println("readResolve");
        return this;
    }
	
}

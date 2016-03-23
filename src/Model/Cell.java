package Model;

public class Cell {
	
	protected GameImpl theGame;
	protected int cellIndex, rowIndex, columnIndex, squareIndex;
	protected Helper helper;
	protected Digit digit;
	protected boolean isFixed;
	

	public Digit getDigit() {
		return this.digit;
	}
	
	public void setDigit(Digit newDigit) {
		this.digit = newDigit;
	}
	
//	public Cell (int newIndex) {
//		this.cellIndex = newIndex;
//		this.calcRowColumn(newIndex);
//		this.digit = new DigitNull();
//	}
	
	public Cell(int newIndex, GameImpl theGame) {
		this.cellIndex = newIndex;
		this.theGame = theGame;
		this.calcRowColumn();
		this.calcSquare();
		this.digit = new DigitNull();
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
	
	
}

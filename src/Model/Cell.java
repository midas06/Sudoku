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
	
	public Cell (int newIndex, boolean isFixed) {
		this.cellIndex = newIndex;
		this.calcRowColumn(newIndex);
		this.isFixed = isFixed;
	}
	
	public Cell(int newIndex, GameImpl theGame) {
		this.cellIndex = newIndex;
		this.theGame = theGame;
		this.calcRowColumn(newIndex);
	}
	
	protected void calcRowColumn(int cellIndex) {
		double sqrt, row, column;
		sqrt = Math.sqrt(theGame.getMaxValue());

		row = (double)cellIndex / sqrt;
		this.rowIndex = (int)Math.floor(row);
		
		this.columnIndex = cellIndex % (int)sqrt;
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
	
	
}

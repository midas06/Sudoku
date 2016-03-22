package Model;

public interface Gets {
	public Digit getByColumn(int columnIndex, int rowIndex);
    public Digit getByRow(int rowIndex, int columnIndex);
    public Digit getBySquare(int squareIndex, int positionIndex);
}

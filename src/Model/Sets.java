package Model;

public interface Sets {
	public void setByColumn(Digit value, int columnIndex, int rowIndex);
    public void setByRow(Digit value, int rowIndex, int columnIndex);
    public void setBySquare(Digit value, int squareIndex, int positionIndex);
}

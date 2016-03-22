package Model;

public interface Game {
	public void setMaxValue(int maximum);
    public int getMaxValue();
    //public int[] toArray();
    public void set(int[] cellValues);
    public void setSquareWidth(int squareWidth);
    public void setSquareHeight(int squareHeight);
    public void restart();
}

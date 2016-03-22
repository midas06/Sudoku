package Model;

public interface Validating {
	public boolean isCompleted(); // no zeros
    public boolean isLegal(); // no duplicates
    public boolean isFinished(); // no zeros AND no duplicates
}

package Model;

public interface Validating {
    public boolean hasDuplicates(); // no duplicates
    public boolean isComplete(); // no zeros AND no duplicates
}

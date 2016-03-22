package Model;
import java.util.ArrayList;
import java.util.List;
public class Program {
	public static void main(String[] args) {
		GameImpl g = new GameImpl();
		g.setMaxValue(9);
		g.init();
		
		List<Cell> l = g.getByColumn(2);
		for (Cell c : l) {
			System.out.println(c.getIndex());
		}
	
	}
}

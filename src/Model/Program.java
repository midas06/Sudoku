package Model;

import java.util.List;

public class Program {
	public static void main(String[] args) {
		GameImpl g = new GameImpl();
		g.setMaxValue(81);
		//g.init();
		
		//g.setPuzzle(PuzzleHelper.initialisePuzzle(g.getMaxValue()));
		
		g.setValueByCoord(1, 1, 0);
		g.setValueByCoord(6, 2, 0);
		g.setValueByCoord(5, 4, 0);
		
		g.setValueByCoord(6, 4, 1);
		g.setValueByCoord(1, 5, 1);
		
		g.setValueByCoord(6, 5, 2);
		
		g.setValueByCoord(3, 0, 3);
		
		g.setValueByCoord(5, 0, 4);
		g.setValueByCoord(3, 1, 4);
		
		g.setValueByCoord(4, 1, 5);
		g.setValueByCoord(5, 3, 5);
		g.setValueByCoord(2, 4, 5);
		
		
		PuzzleStringBuilder psb = new PuzzleStringBuilder(g);
		//psb.setPuzzle(g.getPuzzle());
		System.out.println(psb.toString());
		
		
		List<Cell> l = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 8);
		for (Cell c : l) {
			System.out.println(c.getIndex());
		}
	
	}
}

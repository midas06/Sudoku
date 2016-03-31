package Model;

import java.util.List;

public class Program {
	public static void main(String[] args) {
		GameImpl g = new GameImpl();
		g.setMaxValue(81);
		//g.init();
		
		//g.setPuzzle(PuzzleHelper.initialisePuzzle(g.getMaxValue()));
		Cell theCell;
		theCell = g.getCellByCoord(1, 0);
		g.addSingleValue(1, theCell);
		theCell = g.getCellByCoord(2, 0);
		g.addSingleValue(6, theCell);
		theCell = g.getCellByCoord(3, 0);
		g.addSingleValue(5, theCell);
		theCell = g.getCellByCoord(4, 0);
		g.addSingleValue(4, theCell);
		theCell = g.getCellByCoord(5, 0);
		g.addSingleValue(3, theCell);
		theCell = g.getCellByCoord(6, 0);
		g.addSingleValue(2, theCell);
		theCell = g.getCellByCoord(7, 0);
		g.addSingleValue(0, theCell);
		theCell = g.getCellByCoord(8, 0);
		g.addSingleValue(7, theCell);
		theCell = g.getCellByCoord(0, 0);
		//g.addSingleValue(5, theCell);
		int[] arr = new int[3];
		arr[0] = 3;
		arr[1] = 2;
		arr[2] = 0;
		g.addMultipleValues(arr, theCell);
		
		
		theCell = g.getCellByCoord(3, 1);
		g.addSingleValue(4,theCell);
//		g.addSingleValueByCoord(6, 4, 1);
//		g.addSingleValueByCoord(3, 5, 1);
		
//		g.addSingleValueByCoord(6, 5, 2);
		theCell = g.getCellByCoord(0, 3);
		g.addSingleValue(3, theCell);
		
//		g.addSingleValueByCoord(5, 0, 4);
//		g.addSingleValueByCoord(3, 1, 4);
//		
//		g.addSingleValueByCoord(4, 1, 5);
//		g.addSingleValueByCoord(5, 3, 5);
//		g.addSingleValueByCoord(2, 4, 5);
		
		theCell = g.getCellBySquare(3, 3);
		g.addSingleValue(6, theCell);
		
		
		PuzzleStringBuilder psb = new PuzzleStringBuilder(g);
		//psb.setPuzzle(g.getPuzzle());
		System.out.println(psb.toString());
		
		
//		List<Cell> l = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 8);
//		for (Cell c : l) {
//			System.out.println(c.getIndex());
//		}
//		
		int max = (int)Math.sqrt(g.getMaxValue());
		
		Checker c = new Checker(max);
		
		c.set(PuzzleHelper.getCellListByRow(g.getPuzzle(), 0));
		c.test();
		System.out.println(c.hasDuplicates());
		System.out.println(c.isComplete());
		
		c.set(PuzzleHelper.getCellListByColumn(g.getPuzzle(), 3));
		c.test();
		System.out.println(c.hasDuplicates());
		
		c.set(PuzzleHelper.getCellListBySquare(g.getPuzzle(), 1));
		c.test();
		System.out.println(c.hasDuplicates());
		System.out.println(c.isComplete());
		
		int[] values = g.getCellBySquare(0, 0).getDigit().getValues();
		for (int i : values) {
			System.out.println(i);
		}
		
	}
}

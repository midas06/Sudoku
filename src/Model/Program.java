package Model;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import Extras.DeepCopier;

public class Program {
	public static void main(String[] args) {
//		GameImpl g = new GameImpl();
//		g.setMaxValue(81);
//		//g.init();
//		
//		//g.setPuzzle(PuzzleHelper.initialisePuzzle(g.getMaxValue()));
//		Cell theCell;
//		theCell = g.getCellByCoord(1, 0);
//		g.addSingleValue(1, theCell);
//		theCell = g.getCellByCoord(2, 0);
//		g.addSingleValue(6, theCell);
//		theCell = g.getCellByCoord(3, 0);
//		g.addSingleValue(5, theCell);
//		theCell = g.getCellByCoord(4, 0);
//		g.addSingleValue(4, theCell);
//		theCell = g.getCellByCoord(5, 0);
//		g.addSingleValue(3, theCell);
//		theCell = g.getCellByCoord(6, 0);
//		g.addSingleValue(2, theCell);
//		theCell = g.getCellByCoord(7, 0);
//		g.addSingleValue(9, theCell);
//		theCell = g.getCellByCoord(8, 0);
//		g.addSingleValue(7, theCell);
//		theCell = g.getCellByCoord(0, 0);
//		//g.addSingleValue(5, theCell);
//		int[] arr = new int[3];
//		arr[0] = 3;
//		arr[1] = 2;
//		arr[2] = 0;
//		g.addMultipleValues(arr, theCell);
//		
//		
//		theCell = g.getCellByCoord(3, 1);
//		g.addSingleValue(4,theCell);
////		g.addSingleValueByCoord(6, 4, 1);
////		g.addSingleValueByCoord(3, 5, 1);
//		
////		g.addSingleValueByCoord(6, 5, 2);
//		theCell = g.getCellByCoord(0, 3);
//		g.addSingleValue(3, theCell);
//		
////		g.addSingleValueByCoord(5, 0, 4);
////		g.addSingleValueByCoord(3, 1, 4);
////		
////		g.addSingleValueByCoord(4, 1, 5);
////		g.addSingleValueByCoord(5, 3, 5);
////		g.addSingleValueByCoord(2, 4, 5);
//		
////		theCell = g.getCellBySquare(3, 3);
////		g.addSingleValue(6, theCell);
////		g.finaliseInitialPuzzle();
////		
////		
//		PuzzleStringBuilder psb = new PuzzleStringBuilder(g);
////		//psb.setPuzzle(g.getPuzzle());
////		for (List<Cell> l : g.moveHistory) {
////			psb.setPuzzle(l);
////			System.out.println(g.moveHistory.indexOf(l));
////			System.out.println(psb.generate(l));
////		}
////		//System.out.println(psb.toString());
////		
////		
//////		List<Cell> l = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 8);
//////		for (Cell c : l) {
//////			System.out.println(c.getIndex());
//////		}
//////		
//////		int max = (int)Math.sqrt(g.getMaxValue());
//////		
//////		Checker c = new Checker(max);
//////		
//////		c.set(PuzzleHelper.getCellListByRow(g.getPuzzle(), 0));
//////		c.test();
//////		System.out.println(c.hasDuplicates());
//////		System.out.println(c.isComplete());
//////		
//////		c.set(PuzzleHelper.getCellListByColumn(g.getPuzzle(), 3));
//////		c.test();
//////		System.out.println(c.hasDuplicates());
//////		
//////		c.set(PuzzleHelper.getCellListBySquare(g.getPuzzle(), 1));
//////		c.test();
//////		System.out.println(c.hasDuplicates());
//////		System.out.println(c.isComplete());
//////		
//////		int[] values = g.getCellBySquare(0, 0).getDigit().getValues();
//////		for (int i : values) {
//////			System.out.println(i);
//////		}
////		
////		Digit d = new Digit();
////		//int[] a = new int[] {2, 3, 4};
////		
////		int[] a = new int[] {1};
////		
////		d.addValues(a);
////		
////		String str = d.serialize();
////		System.out.println(str);
////		
////		Digit e = new Digit();
////		e.deserialize(str);
////		
////		System.out.println(e.toString());
////		
////		String srlc = theCell.serialize();
////		
////		System.out.println(srlc);
////		
////		Cell newC = new Cell(g);
////		
////		
//		newC.deserialize(srlc);
//		
//		System.out.println(theCell.getValues());
//		System.out.println(newC.getValues());
		
//		System.out.println(psb.toString());
//		g.finaliseInitialPuzzle();
//		theCell = g.getCellByCoord(5, 5);
//		g.addSingleValue(6, theCell);
//		theCell = g.getCellByCoord(6, 5);
//		g.addSingleValue(7, theCell);
//		//List<String> strl= g.serialize();
//		
//		
//		System.out.println(psb.toString());
//		
//		System.out.println(g.moveHistory.size());
//		
//		g.undo();
//		System.out.println(psb.toString());
//		System.out.println(g.moveHistory.size());
//		
//		g.restart();
//		System.out.println(psb.toString());
//		
//		System.out.println(g.moveHistory.size());
//		
//		System.out.println(psb.toString());
//		Checker ch = new Checker(g.getMaxDimension());
//		ch.set(PuzzleHelper.getCellListByColumn(g.getPuzzle(), 3));
//		
//		List<Integer> intList;
//		
//		intList = ch.getUnusedValues();
//		
//		for (Integer i : intList) {
//			System.out.println(i);
//		}
//		
//		ch.set(PuzzleHelper.getCellListByRow(g.getPuzzle(), 0));
//		
//		System.out.println(ch.isComplete());
//		
//		theCell = g.getCellByCoord(0,  0);
//		theCell.clear();
//		g.addSingleValue(8, theCell);
//		
//		ch.set(PuzzleHelper.getCellListByRow(g.getPuzzle(), 0));
//		
//		System.out.println(psb.toString());
//		
//		System.out.println(ch.isComplete());
//		
//		System.out.println(ch.getUnusedValues());
		
		
		GameImpl g = new GameImpl();
//		g.setMaxValue(36);
//		
//		g.finaliseInitialPuzzle();
//		
//		
//		// line 1
//		Cell c =  g.getCellByCoord(0, 0);
//		g.setSingleValue(4, c);
//		c =  g.getCellByCoord(1, 0);
//		g.setSingleValue(5, c);
//		c =  g.getCellByCoord(2, 0);
//		g.setSingleValue(6, c);
//		c =  g.getCellByCoord(3, 0);
//		g.setSingleValue(2, c);
//		c =  g.getCellByCoord(4, 0);
//		g.setSingleValue(1, c);
//		c =  g.getCellByCoord(5, 0);
//		g.setSingleValue(3, c);
//		
//		// line 2
//		c =  g.getCellByCoord(0, 1);
//		g.setSingleValue(3, c);
//		c =  g.getCellByCoord(1, 1);
//		g.setSingleValue(1, c);
//		c =  g.getCellByCoord(2, 1);
//		g.setSingleValue(2, c);
//		c =  g.getCellByCoord(3, 1);
//		g.setSingleValue(5, c);
//		c =  g.getCellByCoord(4, 1);
//		g.setSingleValue(4, c);
//		c =  g.getCellByCoord(5, 1);
//		g.setSingleValue(6, c);
//		System.out.println(g.exportMap());
//		
//		c =  g.getCellByCoord(0, 2);
//		g.setSingleValue(5, c);
//		c =  g.getCellByCoord(1, 2);
//		g.setSingleValue(4, c);
//		c =  g.getCellByCoord(2, 2);
//		g.setSingleValue(3, c);
//		c =  g.getCellByCoord(3, 2);
//		g.setSingleValue(1, c);
//		c =  g.getCellByCoord(4, 2);
//		g.setSingleValue(6, c);
//		c =  g.getCellByCoord(5, 2);
//		g.setSingleValue(2, c);
//		System.out.println(g.exportMap());
//		
//		c =  g.getCellByCoord(0, 3);
//		g.setSingleValue(6, c);
//		c =  g.getCellByCoord(1, 3);
//		g.setSingleValue(2, c);
//		c =  g.getCellByCoord(2, 3);
//		g.setSingleValue(1, c);
//		c =  g.getCellByCoord(3, 3);
//		g.setSingleValue(3, c);
//		c =  g.getCellByCoord(4, 3);
//		g.setSingleValue(5, c);
//		c =  g.getCellByCoord(5, 3);
//		g.setSingleValue(4, c);
//		System.out.println(g.exportMap());
//		
//		c =  g.getCellByCoord(0, 4);
//		g.setSingleValue(2, c);
//		c =  g.getCellByCoord(1, 4);
//		g.setSingleValue(6, c);
//		c =  g.getCellByCoord(2, 4);
//		g.setSingleValue(5, c);
//		c =  g.getCellByCoord(3, 4);
//		g.setSingleValue(4, c);
//		c =  g.getCellByCoord(4, 4);
//		g.setSingleValue(3, c);
//		c =  g.getCellByCoord(5, 4);
//		g.setSingleValue(1, c);
//		System.out.println(g.exportMap());
//		
//		c =  g.getCellByCoord(0, 5);
//		g.setSingleValue(1, c);
//		c =  g.getCellByCoord(1, 5);
//		g.setSingleValue(3, c);
//		c =  g.getCellByCoord(2, 5);
//		g.setSingleValue(4, c);
//		c =  g.getCellByCoord(3, 5);
//		g.setSingleValue(6, c);
//		c =  g.getCellByCoord(4, 5);
//		g.setSingleValue(2, c);
//		c =  g.getCellByCoord(5, 5);
//		g.setSingleValue(5, c);
//		System.out.println(g.exportMap());
		
		
		g = new GameImpl();
		g.setMaxValue(36);
		g.setSingleValue(1, g.getCellByCoord(1, 0));
		g.setSingleValue(6, g.getCellByCoord(2, 0));
		g.setSingleValue(5, g.getCellByCoord(4, 0));
		
		g.setSingleValue(6, g.getCellByCoord(4, 1));
		g.setSingleValue(2, g.getCellByCoord(5, 1));
		
		g.setSingleValue(6, g.getCellByCoord(5, 2));
		
		g.setSingleValue(3, g.getCellByCoord(0, 3));
		
		g.setSingleValue(5, g.getCellByCoord(0, 4));
		g.setSingleValue(3, g.getCellByCoord(1, 4));
		
		g.setSingleValue(4, g.getCellByCoord(1, 5));
		g.setSingleValue(5, g.getCellByCoord(3, 5));
		g.setSingleValue(2, g.getCellByCoord(4, 5));
		
		System.out.println(g.exportMap());
	}
}

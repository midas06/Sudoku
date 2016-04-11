package Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Model.Cell;
import Model.GameImpl;
import Model.PuzzleHelper;
import junit.framework.TestCase;

public class Tests_4x4 extends TestCase {
	GameImpl g;

	protected String getErrorMsg(String expected, String result) {
		return "Expected: " + expected + ", got: " + result;
	}
	
	@Before
	public void setUp() throws Exception {
		g = new GameImpl();
		g.setMaxValue(16);
		
		g.setSingleValue(1, g.getCellByCoord(1, 0));
		g.setSingleValue(3, g.getCellByCoord(2, 0));
		
		g.setSingleValue(2, g.getCellByCoord(0, 1));
		
		g.setSingleValue(3, g.getCellByCoord(3, 2));
		
		g.setSingleValue(2, g.getCellByCoord(1, 3));
		g.setSingleValue(1, g.getCellByCoord(2, 3));
		
		
		g.finaliseInitialPuzzle();
		/*
		 *   --- --- --- ---
			|   | 1 | 3 |   |
			 --- --- --- ---
			| 2 |   |   |   |
			 --- --- --- ---
			|   |   |   | 3 |
			 --- --- --- ---
			|   | 2 | 1 |   |
			 --- --- --- ---
		 */
		
	}
	
	// get by square
	
	@Test
	public void test_getBySq_1() {		

		List<Cell> sq1 = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 0);
		
		// expect 4 cells
		assertTrue(this.getErrorMsg(String.valueOf(4), String.valueOf(sq1.size())), sq1.size() == 4);
		
		// expect cell 1.digit == 1
		Cell theCell = sq1.get(1);
		int value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(1), String.valueOf(value)), value == 1);
		
		// expect cell 2.digit == 2
		theCell = sq1.get(2);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(2), String.valueOf(value)), value == 2);		
		
	}
	
	@Test
	public void test_getBySq_2() {		

		List<Cell> sq2 = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 1);
		
		// expect 4 cells
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(sq2.size())), sq2.size() == 4);
		
		// expect cell 0.digit == 3
		Cell theCell = sq2.get(0);
		int value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(5), String.valueOf(value)), value == 3);		
		
	}
	
	
	// Test square duplicates
	
	@Test
	public void test_duplicates_square_false() {		

		List<Cell> cellList = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = false;
		boolean result = g.getChecker().hasDuplicates();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	@Test
	public void test_duplicates_square_true() {		
		
		g.setSingleValue(1, g.getCellByCoord(1, 1));
		
		List<Cell> cellList = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = true;
		boolean result = g.getChecker().hasDuplicates();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	// get all possible values
	@Test
	public void test_posValues_all() {		

		Cell theCell = g.getCellByCoord(0, 0);
		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(4);
		
		List<Integer> result = g.getAllPossibleValues(theCell);
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
	
	// get all impossible values
	@Test
	public void test_imposValues_all() {		

		Cell theCell = g.getCellByCoord(0, 0);
		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(1);
		expected.add(2);
		expected.add(3);
		
		List<Integer> result = g.getAllImpossibleValues(theCell);
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
	
	// test if square completed 
	
	@Test
	public void test_complete_sq_fail() {		
		
		
		List<Cell> cellList = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = false;
		boolean result = g.getChecker().isComplete();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	// test if puzzle solved 
	
	@Test
	public void test_solved_fail() {		
			
		boolean expected = false;
		boolean result = g.isSolved();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	

}

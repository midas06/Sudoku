package Test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Model.Cell;
import Model.GameImpl;
import Model.PuzzleHelper;
import junit.framework.TestCase;

public class Tests_9x9 extends TestCase {
	GameImpl g;

	protected String getErrorMsg(String expected, String result) {
		return "Expected: " + expected + ", got: " + result;
	}
	
	@Before
	public void setUp() throws Exception {
		g = new GameImpl();
		g.setMaxValue(81);
		g.setSingleValue(8, g.getCellByCoord(0, 0));
		g.setSingleValue(6, g.getCellByCoord(1, 0));
		g.setSingleValue(2, g.getCellByCoord(4, 0));
		
		g.setSingleValue(7, g.getCellByCoord(3, 1));
		g.setSingleValue(5, g.getCellByCoord(7, 1));
		g.setSingleValue(9, g.getCellByCoord(8, 1));
		
		g.setSingleValue(6, g.getCellByCoord(4, 3));
		g.setSingleValue(8, g.getCellByCoord(6, 3));
		
		g.setSingleValue(4, g.getCellByCoord(1, 4));
		
		g.setSingleValue(5, g.getCellByCoord(2, 5));
		g.setSingleValue(3, g.getCellByCoord(3, 5));
		g.setSingleValue(7, g.getCellByCoord(8, 5));
		
		g.setSingleValue(2, g.getCellByCoord(1, 7));
		g.setSingleValue(6, g.getCellByCoord(6, 7));
		
		g.setSingleValue(7, g.getCellByCoord(2, 8));
		g.setSingleValue(5, g.getCellByCoord(3, 8));
		g.setSingleValue(9, g.getCellByCoord(5, 8));
		
		
		
		g.finaliseInitialPuzzle();
		/*
		 *   --- --- --- --- --- --- --- --- ---
			| 8 | 6 |   |   | 2 |   |   |   |   |
			 --- --- --- --- --- --- --- --- ---
			|   |   |   | 7 |   |   |   | 5 | 9 |
			 --- --- --- --- --- --- --- --- ---
			|   |   |   |   |   |   |   |   |   |
			 --- --- --- --- --- --- --- --- ---
			|   |   |   |   | 6 |   | 8 |   |   |
			 --- --- --- --- --- --- --- --- ---
			|   | 4 |   |   |   |   |   |   |   |
			 --- --- --- --- --- --- --- --- ---
			|   |   | 5 | 3 |   |   |   |   | 7 |
			 --- --- --- --- --- --- --- --- ---
			|   |   |   |   |   |   |   |   |   |
			 --- --- --- --- --- --- --- --- ---
			|   | 2 |   |   |   |   | 6 |   |   |
			 --- --- --- --- --- --- --- --- ---
			|   |   | 7 | 5 |   | 9 |   |   |   |
			 --- --- --- --- --- --- --- --- ---
		 */
		
	}
	// get by square
	
		@Test
		public void test_getBySq_1() {		

			List<Cell> sq1 = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 3);
			
			// expect 9 cells
			assertTrue(this.getErrorMsg(String.valueOf(9), String.valueOf(sq1.size())), sq1.size() == 9);
			
			// expect cell 4.digit == 4
			Cell theCell = sq1.get(4);
			int value = theCell.getDigit().getValues()[0];
			
			assertTrue(this.getErrorMsg(String.valueOf(4), String.valueOf(value)), value == 4);
			
			// expect cell 8.digit == 5
			theCell = sq1.get(8);
			value = theCell.getDigit().getValues()[0];
			
			assertTrue(this.getErrorMsg(String.valueOf(5), String.valueOf(value)), value == 5);		
			
		}
		
		@Test
		public void test_getBySq_2() {		

			List<Cell> sq2 = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 1);
			
			// expect 9 cells
			assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(sq2.size())), sq2.size() == 9);
			
			// expect cell 1.digit == 2
			Cell theCell = sq2.get(1);
			int value = theCell.getDigit().getValues()[0];
			
			assertTrue(this.getErrorMsg(String.valueOf(2), String.valueOf(value)), value == 2);		
			
			// expect cell 3.digit == 7
			theCell = sq2.get(3);
			value = theCell.getDigit().getValues()[0];
			
			assertTrue(this.getErrorMsg(String.valueOf(7), String.valueOf(value)), value == 7);		
			
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
			
			g.setSingleValue(8, g.getCellByCoord(1, 1));
			
			List<Cell> cellList = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 0);
			g.getChecker().set(cellList);
			
			boolean expected = true;
			boolean result = g.getChecker().hasDuplicates();
			
			
			assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
			
		}
		
		// get all possible values
		@Test
		public void test_posValues_all() {		

			Cell theCell = g.getCellByCoord(1, 1);
			List<Integer> expected = new ArrayList<Integer> ();
			expected.add(1);
			expected.add(3);
			
			List<Integer> result = g.getAllPossibleValues(theCell);
			
			assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
			
		}
		
		// get all impossible values
		@Test
		public void test_imposValues_all() {		

			Cell theCell = g.getCellByCoord(1, 1);
			List<Integer> expected = new ArrayList<Integer> ();
			expected.add(2);
			expected.add(4);
			expected.add(5);
			expected.add(6);
			expected.add(7);
			expected.add(8);
			expected.add(9);

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
		
		// Must have 20, 21, 22: check if row/col/square completed
		
		@Test
		public void test_complete_row_pass() {		
			
			g.setSingleValue(1, g.getCellByCoord(2, 0));
			g.setSingleValue(3, g.getCellByCoord(3, 0));
			g.setSingleValue(5, g.getCellByCoord(5, 0));
			g.setSingleValue(4, g.getCellByCoord(6, 0));
			g.setSingleValue(7, g.getCellByCoord(7, 0));
			g.setSingleValue(9, g.getCellByCoord(8, 0));
			
			
			List<Cell> cellList = PuzzleHelper.getCellListByRow(g.getPuzzle(), 0);
			g.getChecker().set(cellList);
			
			boolean expected = true;
			boolean result = g.getChecker().isComplete();
			
			
			assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
			
		}
		
		@Test
		public void test_complete_col_pass() {		
			
			g.setSingleValue(1, g.getCellByCoord(1, 1));
			g.setSingleValue(3, g.getCellByCoord(1, 2));
			g.setSingleValue(5, g.getCellByCoord(1, 3));
			g.setSingleValue(7, g.getCellByCoord(1, 5));
			g.setSingleValue(8, g.getCellByCoord(1, 6));
			g.setSingleValue(9, g.getCellByCoord(1, 8));
			
			List<Cell> cellList = PuzzleHelper.getCellListByColumn(g.getPuzzle(), 1);
			g.getChecker().set(cellList);
			
			boolean expected = true;
			boolean result = g.getChecker().isComplete();
			
			
			assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
			
		}
		
		@Test
		public void test_complete_sq_pass() {		
			
			g.setSingleValue(1, g.getCellBySquare(0, 1));
			g.setSingleValue(3, g.getCellBySquare(2, 1));
			g.setSingleValue(4, g.getCellBySquare(4, 1));
			g.setSingleValue(5, g.getCellBySquare(5, 1));
			g.setSingleValue(6, g.getCellBySquare(6, 1));
			g.setSingleValue(8, g.getCellBySquare(7, 1));
			g.setSingleValue(9, g.getCellBySquare(8, 1));
			
			List<Cell> cellList = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 1);
			g.getChecker().set(cellList);
			
			boolean expected = true;
			boolean result = g.getChecker().isComplete();
			
			
			assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
			
		}
}

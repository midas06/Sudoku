package Test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Model.Cell;
import Model.GameImpl;
import Model.PuzzleHelper;
import junit.framework.TestCase;

public class Tests_6x6_Complete extends TestCase {
	GameImpl g;

	protected String getErrorMsg(String expected, String result) {
		return "Expected: " + expected + ", got: " + result;
	}
	
	@Before
	public void setUp() throws Exception {
		g = new GameImpl();
		g.setMaxValue(36);
		
		g.setSingleValue(2, g.getCellByCoord(0, 0));
		g.setSingleValue(1, g.getCellByCoord(1, 0));
		g.setSingleValue(6, g.getCellByCoord(2, 0));
		g.setSingleValue(3, g.getCellByCoord(3, 0));
		g.setSingleValue(5, g.getCellByCoord(4, 0));
		g.setSingleValue(4, g.getCellByCoord(5, 0));
		
		g.setSingleValue(4, g.getCellByCoord(0, 1));
		g.setSingleValue(5, g.getCellByCoord(1, 1));
		g.setSingleValue(3, g.getCellByCoord(2, 1));
		g.setSingleValue(1, g.getCellByCoord(3, 1));
		g.setSingleValue(6, g.getCellByCoord(4, 1));
		g.setSingleValue(2, g.getCellByCoord(5, 1));
		
		g.setSingleValue(1, g.getCellByCoord(0, 2));
		g.setSingleValue(2, g.getCellByCoord(1, 2));
		g.setSingleValue(5, g.getCellByCoord(2, 2));
		g.setSingleValue(4, g.getCellByCoord(3, 2));
		g.setSingleValue(3, g.getCellByCoord(4, 2));
		g.setSingleValue(6, g.getCellByCoord(5, 2));
		
		g.setSingleValue(3, g.getCellByCoord(0, 3));
		g.setSingleValue(6, g.getCellByCoord(1, 3));
		g.setSingleValue(4, g.getCellByCoord(2, 3));
		g.setSingleValue(2, g.getCellByCoord(3, 3));
		g.setSingleValue(1, g.getCellByCoord(4, 3));
		g.setSingleValue(5, g.getCellByCoord(5, 3));
		
		g.setSingleValue(5, g.getCellByCoord(0, 4));
		g.setSingleValue(3, g.getCellByCoord(1, 4));
		g.setSingleValue(2, g.getCellByCoord(2, 4));
		g.setSingleValue(6, g.getCellByCoord(3, 4));
		g.setSingleValue(4, g.getCellByCoord(4, 4));
		g.setSingleValue(1, g.getCellByCoord(5, 4));
		
		g.setSingleValue(6, g.getCellByCoord(0, 5));
		g.setSingleValue(4, g.getCellByCoord(1, 5));
		g.setSingleValue(1, g.getCellByCoord(2, 5));
		g.setSingleValue(5, g.getCellByCoord(3, 5));
		g.setSingleValue(2, g.getCellByCoord(4, 5));
		g.setSingleValue(3, g.getCellByCoord(5, 5));
		
		g.finaliseInitialPuzzle();
		/*
		 *   --- --- --- --- --- ---
			| 2 | 1 | 6 | 3 | 5 | 4 |
			 --- --- --- --- --- ---
			| 4 | 5 | 3 | 1 | 6 | 2 |
			 --- --- --- --- --- ---
			| 1 | 2 | 5 | 4 | 3 | 6 |
			 --- --- --- --- --- ---
			| 3 | 6 | 4 | 2 | 1 | 5 |
			 --- --- --- --- --- ---
			| 5 | 3 | 2 | 6 | 4 | 1 |
			 --- --- --- --- --- ---
			| 6 | 4 | 1 | 5 | 2 | 3 |
			 --- --- --- --- --- ---
		 */
		
	}
	
	// Must have 20, 21, 22: check if row/col/square completed
	
	@Test
	public void test_complete_row_pass() {		

		List<Cell> cellList = PuzzleHelper.getCellListByRow(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = true;
		boolean result = g.getChecker().isComplete();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	@Test
	public void test_complete_col_pass() {		
		
		g.setSingleValue(1, g.getCellByCoord(0, 0));
		
		List<Cell> cellList = PuzzleHelper.getCellListByColumn(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = true;
		boolean result = g.getChecker().isComplete();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	@Test
	public void test_complete_sq_pass() {		
		
		g.setSingleValue(1, g.getCellByCoord(0, 0));
		
		List<Cell> cellList = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = true;
		boolean result = g.getChecker().isComplete();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	// Must have 15: check if game is solved
	
	@Test
	public void test_solved_pass() {		
			
		boolean expected = true;
		boolean result = g.isSolved();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}

}

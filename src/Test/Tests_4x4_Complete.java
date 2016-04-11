package Test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;

import Model.Cell;
import Model.GameImpl;
import Model.PuzzleHelper;
import junit.framework.TestCase;

public class Tests_4x4_Complete extends TestCase {
	
	GameImpl g;

	protected String getErrorMsg(String expected, String result) {
		return "Expected: " + expected + ", got: " + result;
	}
	
	@Before
	public void setUp() throws Exception {
		g = new GameImpl();
		g.setMaxValue(16);
		
		g.setSingleValue(1, g.getCellByCoord(0, 0));
		g.setSingleValue(4, g.getCellByCoord(1, 0));
		g.setSingleValue(3, g.getCellByCoord(2, 0));
		g.setSingleValue(2, g.getCellByCoord(3, 0));
		
		g.setSingleValue(3, g.getCellByCoord(0, 1));
		g.setSingleValue(2, g.getCellByCoord(1, 1));
		g.setSingleValue(4, g.getCellByCoord(2, 1));
		g.setSingleValue(1, g.getCellByCoord(3, 1));
		
		g.setSingleValue(4, g.getCellByCoord(0, 2));
		g.setSingleValue(1, g.getCellByCoord(1, 2));
		g.setSingleValue(2, g.getCellByCoord(2, 2));
		g.setSingleValue(3, g.getCellByCoord(3, 2));
		
		g.setSingleValue(2, g.getCellByCoord(0, 3));
		g.setSingleValue(3, g.getCellByCoord(1, 3));
		g.setSingleValue(1, g.getCellByCoord(2, 3));
		g.setSingleValue(4, g.getCellByCoord(3, 3));
			
		
		g.finaliseInitialPuzzle();
		/*
		 *   --- --- --- ---
			| 1 | 4 | 3 | 2 |
			 --- --- --- ---
			| 3 | 2 | 4 | 1 |
			 --- --- --- ---
			| 4 | 1 | 2 | 3 |
			 --- --- --- ---
			| 2 | 3 | 1 | 4 |
			 --- --- --- ---
		 */
		
	}
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

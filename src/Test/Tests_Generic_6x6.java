package Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import junit.framework.*;
import org.junit.Before;
import org.junit.Test;
import Model.*;

public class Tests_Generic_6x6 extends TestCase {
	GameImpl g;

	protected String getErrorMsg(String expected, String result) {
		return "Expected: " + expected + ", got: " + result;
	}
	
	@Before
	public void setUp() throws Exception {
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
		
		g.finaliseInitialPuzzle();
		/*
		 *   --- --- --- --- --- ---
			|   | 1 | 6 |   | 5 |   |
			 --- --- --- --- --- ---
			|   |   |   |   | 6 | 2 |
			 --- --- --- --- --- ---
			|   |   |   |   |   | 6 |
			 --- --- --- --- --- ---
			| 3 |   |   |   |   |   |
			 --- --- --- --- --- ---
			| 5 | 3 |   |   |   |   |
			 --- --- --- --- --- ---
			|   | 4 |   | 5 | 2 |   |
			 --- --- --- --- --- ---
		 */
		
	}
	
	// Must have 1: cells can be fixed, can't be changed

	@Test
	public void test_fixCells_isFixed() {
		
		Cell theCell = g.getCellByCoord(1, 0);
		boolean expected = true;	
		boolean result = theCell.getIsFixed();		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
	}
	
	@Test
	public void test_fixCells_attemptUpdate() {
		
		Cell theCell = g.getCellByCoord(1, 0);
		int expectedArraySize = 1;
		int expectedValue = 1;
		
		g.setSingleValue(4, theCell);		
		
		int[] valueArray = theCell.getDigit().getValues();
		int result = valueArray[0];	
		
		// expect cell not to be updated - ie. digit's value size == 1, digit's value == 1		
		assertTrue(this.getErrorMsg(String.valueOf(expectedArraySize), String.valueOf(valueArray.length)), expectedArraySize == valueArray.length);
		
		assertTrue(this.getErrorMsg(String.valueOf(expectedValue), String.valueOf(result)), result == expectedValue);
	}
	
	@Test
	public void test_fixCells_notFixed() {
		
		Cell theCell = g.getCellByCoord(0, 0);
		boolean expected = false;
		boolean result = theCell.getIsFixed();	
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
	}
	
	
	// Must have 2: clear the game to start again
	
	@Test
	public void test_restart() {
		
		String startingPuzzle = g.exportMap();
		
		g.setSingleValue(2, g.getCellByCoord(0, 0));
		g.setSingleValue(3, g.getCellByCoord(3, 0));
		
		g.restart();
		
		String result = g.exportMap();
		
		assertTrue(this.getErrorMsg(String.valueOf(result), String.valueOf(startingPuzzle)), result.equals(startingPuzzle));	
		
	}
	
	@Test
	public void test_restart_notEquals() {
		
		String startingPuzzle = g.exportMap();
		
		g.setSingleValue(2, g.getCellByCoord(0, 0));
		g.setSingleValue(3, g.getCellByCoord(3, 0));
		
		String result = g.exportMap();
		
		assertFalse(this.getErrorMsg(String.valueOf(result), String.valueOf(startingPuzzle)), result.equals(startingPuzzle));	
		
	}
	
	
	// Must have 3: unfix a cell
	
	@Test
	public void test_fixCells_toggle() {
		
		Cell theCell = g.getCellByCoord(1, 0);
		boolean expected = true;	
		boolean result = theCell.getIsFixed();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);	
		
		boolean expected_After = false;
		
		theCell.toggleFixed();
		
		result = theCell.getIsFixed();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected_After), String.valueOf(result)), result == expected_After);	
		
	}
	
	// Must have 4: enter a value for a cell
	
	@Test
	public void test_setValue_single() {
		

		Cell theCell = g.getCellByCoord(0, 0);
		int[] expected = new int[] {2};
		
		g.setSingleValue(2, theCell);		
		
		int[] result = theCell.getDigit().getValues();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), Arrays.equals(expected, result));
		
	}
	
	@Test
	public void test_setValue_multiple() {
		
		Cell theCell = g.getCellByCoord(0, 1);
		int[] expected = new int[] {4, 5, 3};
		
		g.setSingleValue(4, theCell);
		g.setMultipleValues(new int[] {5, 3}, theCell);
		
		int[] result = theCell.getDigit().getValues();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), Arrays.equals(expected, result));
	}
	
	// Must have 5: keep track of # of moves
	
	@Test
	public void test_numMoves() {		

		int expected = 2;
		
		g.setSingleValue(2, g.getCellByCoord(0, 0));
		g.setSingleValue(3, g.getCellByCoord(3, 0));
		
		int result = g.getMoveCount();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected == result);
		
	}
	
	@Test
	public void test_numMoves_sadDay() {		

		int expected = 2;
		
		g.setSingleValue(2, g.getCellByCoord(0, 0));
		g.setSingleValue(3, g.getCellByCoord(3, 0));
		g.setSingleValue(4, g.getCellByCoord(5, 0));
		
		int result = g.getMoveCount();
		
		assertFalse(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected == result);
		
	}
	
	// Must have 6 & 7: keep a history, be able to undo repeatedly
	
	@Test
	public void test_history_undo1() {		

		g.setSingleValue(2, g.getCellByCoord(0, 0));
		String move1 = g.exportMap();
		
		g.setSingleValue(3, g.getCellByCoord(3, 0));
		String move2 = g.exportMap();
		
		g.undo();
		String undid = g.exportMap();
		
		// expect the undone puzzle to be the same as the state after move 1
		assertTrue(this.getErrorMsg(undid, move1), undid.equals(move1));
		
		// expect the undone puzzle to not be the same as the state after move 2
		assertFalse(this.getErrorMsg(undid, move2), undid.equals(move2));
		
		// expect move1 != move2
		assertFalse(this.getErrorMsg(move1, move2), move1.equals(move2));
		
	}
	
	@Test
	public void test_history_undoMultiple() {		

		g.setSingleValue(2, g.getCellByCoord(0, 0));
		String move1 = g.exportMap();
		
		g.setSingleValue(3, g.getCellByCoord(3, 0));
		String move2 = g.exportMap();
		
		g.setSingleValue(4, g.getCellByCoord(5, 0));
		String move3 = g.exportMap();
		
		g.setSingleValue(4, g.getCellByCoord(0, 1));
		
		g.undo();
		String undone1 = g.exportMap();
		
		g.undo();
		String undone2 = g.exportMap();
		
		g.undo();
		String undone3 = g.exportMap();
		
		
		// expect undone 1 == move3
		assertTrue(this.getErrorMsg(undone1, move3), undone1.equals(move3));
		
		// expect undone 2 == move2
		assertTrue(this.getErrorMsg(undone2, move2), undone2.equals(move2));
		
		// expect undone 3 == move1
		assertTrue(this.getErrorMsg(undone3, move1), undone3.equals(move1));
		
	}
	
	// Must have 8, 16, 17, 18, 19: list of possible and impossible values for a cell
	
	// get possible values by row
	@Test
	public void test_posValues_row() {		

		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(2);
		expected.add(3);
		expected.add(4);
		
		Checker c = g.getChecker();
		c.set(PuzzleHelper.getCellListByRow(g.getPuzzle(), 0));
		List<Integer> result = c.getUnusedValues();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
	
	// get possible values by column
	@Test
	public void test_posValues_col() {		

		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(1);
		expected.add(2);
		expected.add(4);
		expected.add(6);
		
		Checker c = g.getChecker();
		c.set(PuzzleHelper.getCellListByColumn(g.getPuzzle(), 0));
		List<Integer> result = c.getUnusedValues();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
	
	// get possible values by square
	@Test
	public void test_posValues_sq() {		

		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(1);
		expected.add(2);
		expected.add(6);
		
		Checker c = g.getChecker();
		c.set(PuzzleHelper.getCellListBySquare(g.getPuzzle(), 4));
		List<Integer> result = c.getUnusedValues();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
	
	// get impossible values by row
	@Test
	public void test_imposValues_row() {		

		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(1);
		expected.add(6);
		expected.add(5);
		
		Checker c = g.getChecker();
		c.set(PuzzleHelper.getCellListByRow(g.getPuzzle(), 0));
		List<Integer> result = c.getUsedValues();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
		
	// get impossible values by column
	@Test
	public void test_imposValues_col() {		

		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(3);
		expected.add(5);
		
		Checker c = g.getChecker();
		c.set(PuzzleHelper.getCellListByColumn(g.getPuzzle(), 0));
		List<Integer> result = c.getUsedValues();
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
	
	// get impossible values by square
		@Test
		public void test_imposValues_sq() {		

			List<Integer> expected = new ArrayList<Integer> ();
			expected.add(5);
			expected.add(3);
			expected.add(4);
			
			Checker c = g.getChecker();
			c.set(PuzzleHelper.getCellListBySquare(g.getPuzzle(), 4));
			List<Integer> result = c.getUsedValues();
			
			assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
			
		}
	
	// get all possible values
	@Test
	public void test_posValues_all_1() {		

		Cell theCell = g.getCellByCoord(0, 0);
		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(2);
		expected.add(4);
		
		List<Integer> result = g.getAllPossibleValues(theCell);
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
	
	@Test
	public void test_posValues_all_2() {		

		Cell theCell = g.getCellByCoord(5, 5);
		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(1);
		expected.add(3);
		
		List<Integer> result = g.getAllPossibleValues(theCell);
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
	
	@Test
	public void test_posValues_all_3() {		

		Cell theCell = g.getCellByCoord(2, 5);
		List<Integer> expected = new ArrayList<Integer> ();
		expected.add(1);

		List<Integer> result = g.getAllPossibleValues(theCell);
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
		
	}
	
	// get all possible values
		@Test
		public void test_imposValues_all_1() {		

			Cell theCell = g.getCellByCoord(0, 0);
			List<Integer> expected = new ArrayList<Integer> ();
			expected.add(1);
			expected.add(3);
			expected.add(5);
			expected.add(6);
						
			List<Integer> result = g.getAllImpossibleValues(theCell);
			
			assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
			
		}
		
		@Test
		public void test_imposValues_all_2() {		

			Cell theCell = g.getCellByCoord(5, 5);
			List<Integer> expected = new ArrayList<Integer> ();
			expected.add(2);
			expected.add(4);
			expected.add(5);
			expected.add(6);
			
			List<Integer> result = g.getAllImpossibleValues(theCell);
			
			assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
			
		}
		
		@Test
		public void test_imposValues_all_3() {		

			Cell theCell = g.getCellByCoord(2, 5);
			List<Integer> expected = new ArrayList<Integer> ();
			
			expected.add(2);
			expected.add(3);
			expected.add(4);
			expected.add(5);
			expected.add(6);

			List<Integer> result = g.getAllImpossibleValues(theCell);
			
			assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), expected.equals(result));
			
		}
	
	// Must have 9, 10, 11: Get by column, row, square
	
	@Test
	public void test_getByRow_1() {		

		List<Cell> row1 = PuzzleHelper.getCellListByRow(g.getPuzzle(), 0);
		
		// expect 6 cells
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(row1.size())), row1.size() == 6);
		
		// expect cell 1.digit == 1
		Cell theCell = row1.get(1);
		int value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(1), String.valueOf(value)), value == 1);
		
		// expect cell 2.digit == 6
		theCell = row1.get(2);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(value)), value == 6);
		
		// expect cell 4.digit == 5
		theCell = row1.get(4);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(5), String.valueOf(value)), value == 5);
		
	}
	
	@Test
	public void test_getByRow_2() {		

		List<Cell> row2 = PuzzleHelper.getCellListByRow(g.getPuzzle(), 1);
		
		// expect 6 cells
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(row2.size())), row2.size() == 6);
		
		// expect cell 4.digit == 6
		Cell theCell = row2.get(4);
		int value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(value)), value == 6);
		
		// expect cell 5.digit == 2
		theCell = row2.get(5);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(2), String.valueOf(value)), value == 2);
		
	}
	
	@Test
	public void test_getByCol_1() {		

		List<Cell> col1 = PuzzleHelper.getCellListByColumn(g.getPuzzle(), 0);
		
		// expect 6 cells
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(col1.size())), col1.size() == 6);
		
		// expect cell 3.digit == 3
		Cell theCell = col1.get(3);
		int value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(3), String.valueOf(value)), value == 3);
		
		// expect cell 4.digit == 5
		theCell = col1.get(4);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(5), String.valueOf(value)), value == 5);		
		
	}
	
	@Test
	public void test_getByCol_2() {		

		List<Cell> col2 = PuzzleHelper.getCellListByColumn(g.getPuzzle(), 1);
		
		// expect 6 cells
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(col2.size())), col2.size() == 6);
		
		// expect cell 0.digit == 1
		Cell theCell = col2.get(0);
		int value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(1), String.valueOf(value)), value == 1);
		
		// expect cell 4.digit == 3
		theCell = col2.get(4);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(3), String.valueOf(value)), value == 3);
		
		// expect cell 5.digit == 4
		theCell = col2.get(5);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(4), String.valueOf(value)), value == 4);
		
	}
	
	@Test
	public void test_getBySq_1() {		

		List<Cell> sq1 = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 0);
		
		// expect 6 cells
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(sq1.size())), sq1.size() == 6);
		
		// expect cell 1.digit == 1
		Cell theCell = sq1.get(1);
		int value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(1), String.valueOf(value)), value == 1);
		
		// expect cell 2.digit == 6
		theCell = sq1.get(2);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(value)), value == 6);		
		
	}
	
	@Test
	public void test_getBySq_2() {		

		List<Cell> sq2 = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 1);
		
		// expect 6 cells
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(sq2.size())), sq2.size() == 6);
		
		// expect cell 1.digit == 5
		Cell theCell = sq2.get(1);
		int value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(5), String.valueOf(value)), value == 5);
		
		// expect cell 4.digit == 6
		theCell = sq2.get(4);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(6), String.valueOf(value)), value == 6);
		
		// expect cell 5.digit == 2
		theCell = sq2.get(5);
		value = theCell.getDigit().getValues()[0];
		
		assertTrue(this.getErrorMsg(String.valueOf(2), String.valueOf(value)), value == 2);
		
	}
	
	// Must have 12, 13, 14: no duplicates
	
	@Test
	public void test_duplicates_row_false() {		

		List<Cell> cellList = PuzzleHelper.getCellListByRow(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = false;
		boolean result = g.getChecker().hasDuplicates();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	@Test
	public void test_duplicates_row_true() {		
		
		g.setSingleValue(1, g.getCellByCoord(0, 0));
		
		List<Cell> cellList = PuzzleHelper.getCellListByRow(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = true;
		boolean result = g.getChecker().hasDuplicates();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	@Test
	public void test_duplicates_col_false() {		

		List<Cell> cellList = PuzzleHelper.getCellListByColumn(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = false;
		boolean result = g.getChecker().hasDuplicates();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	@Test
	public void test_duplicates_col_true() {		
		
		g.setSingleValue(3, g.getCellByCoord(0, 0));
		
		List<Cell> cellList = PuzzleHelper.getCellListByColumn(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = true;
		boolean result = g.getChecker().hasDuplicates();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
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
	
	// Must have 20, 21, 22: row/col/square complete
	
	@Test
	public void test_complete_row_fail() {		

		List<Cell> cellList = PuzzleHelper.getCellListByRow(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = false;
		boolean result = g.getChecker().isComplete();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	@Test
	public void test_complete_col_fail() {		
		
		
		List<Cell> cellList = PuzzleHelper.getCellListByColumn(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = false;
		boolean result = g.getChecker().isComplete();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	@Test
	public void test_complete_sq_fail() {		
		
		
		List<Cell> cellList = PuzzleHelper.getCellListBySquare(g.getPuzzle(), 0);
		g.getChecker().set(cellList);
		
		boolean expected = false;
		boolean result = g.getChecker().isComplete();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
	
	// Must have 15: Check if game is solved
	
	@Test
	public void test_solved_fail() {		
			
		boolean expected = false;
		boolean result = g.isSolved();
		
		
		assertTrue(this.getErrorMsg(String.valueOf(expected), String.valueOf(result)), result == expected);		
		
	}
}

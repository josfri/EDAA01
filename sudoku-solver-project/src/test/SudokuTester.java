package test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import sudoku.SudokuSolver;
import sudoku.SudokuSolverInterface;

class SudokuTester {

	private SudokuSolverInterface s;

	@BeforeEach
	void setUp() throws Exception {
		s = new SudokuSolver();
	}

	@AfterEach
	void tearDown() throws Exception {
		s = null;
	}

	@Test
	void solveEmpty() {

		assertTrue(s.solve());
	}

	@Test
	void testWrongRow() {

		s.add(0, 2, 5);
		s.add(0, 4, 5);
		assertFalse(s.solve());
	}

	@Test
	void testWrongColumn() {

		s.add(0, 0, 5);
		s.add(3, 0, 5);
		assertFalse(s.solve());
	}

	@Test
	void testWrongGrid() {

		s.add(0, 1, 5);
		s.add(1, 0, 5);
		assertFalse(s.solve());
	}

	@Test
	void solveFigure1() {

		int[][] m = new int[9][9];
		m[0][2] = 8;
		m[0][5] = 9;
		m[0][7] = 6;
		m[0][8] = 2;
		m[1][8] = 5;
		m[2][0] = 1;
		m[2][2] = 2;
		m[2][3] = 5;
		m[3][3] = 2;
		m[3][4] = 1;
		m[3][7] = 9;
		m[4][1] = 5;
		m[4][6] = 6;
		m[5][0] = 6;
		m[5][7] = 2;
		m[5][8] = 8;
		m[6][0] = 4;
		m[6][1] = 1;
		m[6][3] = 6;
		m[6][5] = 8;
		m[7][0] = 8;
		m[7][1] = 6;
		m[7][4] = 3;
		m[7][6] = 1;
		m[8][6] = 4;

		s.setMatrix(m);
		assertTrue(s.solve());

	}

	@Test
	void testAddAndGet() {

		s.add(0, 3, 7);
		assertEquals(s.get(0, 3), 7);

		assertThrows(IllegalArgumentException.class, () -> s.get(10, 0), "");
		assertThrows(IllegalArgumentException.class, () -> s.add(10, 0, 0), "");
		assertThrows(IllegalArgumentException.class, () -> s.add(1, 0, 10), "");
	}

	@Test
	void testRemove() {

		s.add(0, 3, 7);
		s.remove(0, 3);
		s.remove(0, 4);

		assertEquals(s.get(0, 3), 0);
		assertEquals(s.get(0, 4), 0);
	}


	@Test
	void testClear() {

		s.add(0, 3, 7);
		s.add(0, 4, 7);

		assertFalse(s.solve());
		s.clear();
		assertTrue(s.solve());
	}

	@Test
	void testGetMatrix() {

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				assertTrue(s.getMatrix()[r][c] == 0);
			}
		}

		s.add(3, 7, 7);
		assertTrue(s.getMatrix()[3][7] == 7);

		s.solve();

		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {
				assertTrue(s.getMatrix()[r][c] != 0);
			}
		}
	}

	@Test
	void testSetMatrix() {
		int[][] temp = new int[9][9];
		temp[3][4] = 6;
		s.setMatrix(temp);
		assertEquals(s.get(3, 4), 6);

		int[][] fel = new int[3][4];
		assertThrows(IllegalArgumentException.class, () -> s.setMatrix(fel));

	}
}

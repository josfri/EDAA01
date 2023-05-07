package sudoku;

public class SudokuApplication {

	public static void main(String[] args) {

		SudokuSolverInterface s = new SudokuSolver();
		new SudokuController(s);
	}
}

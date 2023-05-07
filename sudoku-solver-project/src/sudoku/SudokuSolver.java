package sudoku;

public class SudokuSolver implements SudokuSolverInterface {

	private int[][] board;

	public SudokuSolver() {
		board = new int[9][9];

		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[0].length; k++) {
				board[i][k] = 0;
			}
		}
	}

	@Override
	public boolean solve() {
		return solve(0, 0);
	}

	private boolean solve(int row, int col) {

		if (row == board.length) { // kollar om vi är på rad 9 - och sudokut är klart
			return true; // börja returnera true
		}

		int nextR, nextC; // inför variabler för rutan efter aktuell

		if (col == board[0].length - 1) { // ger variablerna värden
			nextR = row + 1;
			nextC = 0;
		} else {
			nextR = row;
			nextC = col + 1;
		}

		if (board[row][col] == 0) { // om rutan tom

			for (int i = 1; i < 10; i++) { // testar 1-9

				if (isDigitValid(row, col, i)) {
					add(row, col, i);

					if (solve(nextR, nextC)) { // om finns en lösning efter detta
						return solve(nextR, nextC); // rekursivt anrop
					}
				}
			}

			board[row][col] = 0; // om inte gick att lösa nästa på något sätt, återställ rutan

		} else { // om rutan redan ifylld av användare

			if (isValid()) { // om sudokut ser bra ut
				return solve(nextR, nextC); // lös nästa
			}

		}
		return false;

	}

	@Override
	public void add(int row, int col, int digit) {
		if (row < 0 || row > 9 || col < 0 || col > 9 || digit < 0 || digit > 9) {
			throw new IllegalArgumentException();
		}
		board[row][col] = digit;
	}

	@Override
	public void remove(int row, int col) {
		if (row < 0 || row > 9 || col < 0 || col > 9) {
			throw new IllegalArgumentException();
		}
		board[row][col] = 0;
	}

	@Override
	public int get(int row, int col) {
		if (row < 0 || row > 9 || col < 0 || col > 9) {
			throw new IllegalArgumentException();
		}
		return board[row][col];
	}

	@Override
	public boolean isValid() {

		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[0].length; k++) {
				if (!isDigitValid(k, i, board[k][i])) {
					return false;
				}
			}
		}
		return true;
	}

	private boolean isDigitValid(int row, int col, int nbr) {

		if (nbr > 9 || nbr < 0) {
			return false;
		}

		if (nbr == 0) {
			return true;
		}

		return checkCol(row, col, nbr) && checkRow(row, col, nbr) && checkGrid(row, col, nbr);
	}

	private boolean checkCol(int row, int col, int nbr) {

		board[row][col] = 0;
		boolean check = true;

		for (int i = 0; i < board[0].length; i++) {
			if (board[i][col] == nbr) {
				check = false;
			}
		}

		board[row][col] = nbr;
		return check;
	}

	private boolean checkRow(int row, int col, int nbr) {

		board[row][col] = 0;
		boolean check = true;

		for (int i = 0; i < board.length; i++) {
			if (board[row][i] == nbr) {
				check = false;
			}
		}

		board[row][col] = nbr;
		return check;
	}

	private boolean checkGrid(int row, int col, int nbr) {

		board[row][col] = 0;
		boolean check = true;

		int rowStart = (row / 3) * 3;
		int colStart = (col / 3) * 3;

		for (int i = rowStart; i < rowStart + 3; i++) {
			for (int k = colStart; k < colStart + 3; k++) {
				if (board[i][k] == nbr) {
					check = false;
				}
			}
		}

		board[row][col] = nbr;
		return check;
	}

	@Override
	public void clear() {

		for (int i = 0; i < board.length; i++) {
			for (int k = 0; k < board[0].length; k++) {

				board[i][k] = 0;
			}
		}
	}

	@Override
	public void setMatrix(int[][] m) {

		if (m.length != 9 || m[0].length != 9) {
			throw new IllegalArgumentException();
		}

		for (int i = 0; i < m.length; i++) {
			for (int k = 0; k < m[0].length; k++) {

				if (m[i][k] > 9 || m[i][k] < 0) {
					throw new IllegalArgumentException();
				}

				board[i][k] = m[i][k];
			}
		}

	}

	@Override
	public int[][] getMatrix() {
		int[][] tempBoard = board;
		return tempBoard;

	}

	public void printMatrix() {

		for (int i = 0; i < board.length; i++) {
			StringBuilder sb = new StringBuilder();

			for (int k = 0; k < board[0].length; k++) {
				sb.append(board[i][k] + " ");

			}
			System.out.println(sb.toString());
		}

	}

}

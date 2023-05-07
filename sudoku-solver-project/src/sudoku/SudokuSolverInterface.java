package sudoku;

public interface SudokuSolverInterface {
	/**
	 * Attempts to solve the sudoku.
	 * 
	 * @return true if sudoku is solvable
	 */
	boolean solve();

	/**
	 * Puts digit in the box row, col.
	 * 
	 * @param row   The row
	 * @param col   The column
	 * @param digit The digit to insert in box row, col
	 * @throws IllegalArgumentException if row, col or digit is outside the range
	 *                                  [0..9]
	 */
	void add(int row, int col, int digit);

	/**
	 * Removes digit in the box row, col.
	 * 
	 * @param row The row
	 * @param col The column
	 * @throws IllegalArgumentException if row or col is outside the range [0..9]
	 */
	void remove(int row, int col);

	/**
	 * Returns digit in the box row, col. If box is empty, 0 is returned.
	 * 
	 * @param row The row
	 * @param col The column
	 * @return number in box r,c or 0 if empty
	 * @throws IllegalArgumentException if row or col is outside the range [0..9]
	 */
	int get(int row, int col);


	/**
	 * Checks that all filled in digits follows the the sudoku rules.
	 * 
	 * @return true if all digits are valid
	 */
	boolean isValid();

	/**
	 * Clears all boxes by giving them value 0.
	 */
	void clear();

	/**
	 * Fills the grid with the digits in m. The digit 0 represents an empty box.
	 * 
	 * @param m the matrix with the digits to insert
	 * @throws IllegalArgumentException if m has the wrong dimension or contains
	 *                                  values outside the range [0..9]
	 */
	void setMatrix(int[][] m);

	/**
	 * Returns a matrix with the digits in the grid. The digit 0 represents an empty
	 * box.
	 * 
	 * @return digits in grid
	 */
	int[][] getMatrix();

}
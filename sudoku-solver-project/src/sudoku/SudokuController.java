package sudoku;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.*;

public class SudokuController {

	private SudokuSolverInterface s;

	public SudokuController(SudokuSolverInterface s) {
		this.s = s;

		SwingUtilities.invokeLater(() -> createWindow("Sudoku", 380, 380));
	}

	private void createWindow(String title, int width, int height) {

		JFrame frame = new JFrame(title);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container pane = frame.getContentPane();

		// Skapar en kontrollpanel och tillhörande knappar
		JPanel controls = new JPanel();
		JButton solve = new JButton("Solve");
		JButton clear = new JButton("Clear");

		// Skapar en JPanel med GridLayout för att återskapa sudokubräde med vald
		// storlek
		JPanel sudokuGrid = new JPanel();
		GridLayout grid = new GridLayout(9, 9);
		sudokuGrid.setPreferredSize(new Dimension(width, height));
		sudokuGrid.setLayout(grid);

		// Lägger till knappar till JPanel
		controls.add(solve);
		controls.add(clear);

		// skapar matrix för att hålla koll på JTextFields
		JTextField[][] fields = new JTextField[9][9];

		// sätter ut siffror i sudokut
		for (int r = 0; r < 9; r++) {
			for (int c = 0; c < 9; c++) {

				int n = s.get(r, c);
				JTextField field = new JTextField();

				// ger rutan värde 1-9 eller tom
				if (n == 0) {
					field.setText("");
				} else {
					field.setText(String.valueOf(n));
				}

				// gör fields i valda 3x3 grid färgade
				if ((r / 3 + c / 3) % 2 == 0) {
					field.setBackground(Color.LIGHT_GRAY);
				}

				// väljer storlek samt sätter siffran i mitten
				field.setHorizontalAlignment(JTextField.CENTER);
				field.setPreferredSize(new Dimension(10, 10));

				// lägger till field i JPanel samt i egen matrix
				sudokuGrid.add(field);
				fields[r][c] = field;

			}
		}

		// rensar både sudokusolver och fönstret med clear
		clear.addActionListener(event -> {
			s.clear();

			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {
					fields[r][c].setText("");
				}
			}
		});

		// hämtar information från JTextFields samt försöker lösa sudokut
		solve.addActionListener(event -> {

			// skapar en matrix med användarens värden
			int[][] inputMatrix = new int[9][9];

			// loopar igenom JTextFields för att hitta användarens ifyllda värden
			for (int r = 0; r < 9; r++) {
				for (int c = 0; c < 9; c++) {

					int nbr;
					String str = fields[r][c].getText();

					// ger nbr värdet av siffran i rutan eller 0 om tom
					try {
						if (str.isEmpty()) {
							nbr = 0;
						} else {
							nbr = Integer.valueOf(str);
							if (nbr > 9 || nbr < 0) {
								throw new Exception();
							}
						}

						inputMatrix[r][c] = nbr;

					} catch (Exception e) { // om ej tillåtna värden är med i sudokut
						JOptionPane.showMessageDialog(null, "Only numbers between 1 and 9 are allowed.");
						return;
					}

				}
			}

			s.setMatrix(inputMatrix);

			// försöker lösa sudokut
			if (s.solve()) {
				for (int r = 0; r < 9; r++) {
					for (int c = 0; c < 9; c++) {
						fields[r][c].setText(String.valueOf(s.getMatrix()[r][c]));
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "The Sudoku is unsolvable.");
			}

		});

		// Lägger till båda JPanels i Containern
		pane.add(sudokuGrid, BorderLayout.NORTH);
		pane.add(controls);

		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);

	}
}
package game;

import javax.swing.JOptionPane;
import java.util.Scanner;

public class UserInterface {

	JOptionPane jp = new JOptionPane();
	Scanner scan = new Scanner(System.in);

	public static void printMessage(String msg) {

		JOptionPane.showMessageDialog(null, msg);
	}

	public static int askForInt(String msg) {

		String answer;
		answer = JOptionPane.showInputDialog(null, msg);

		if (answer == null) {
			return -2;
		} else {

			try {
				Integer number = Integer.valueOf(answer);
				return number;
			} catch (NumberFormatException ex) {
				return -1;
			}
		}

	}
}

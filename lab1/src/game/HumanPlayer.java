package game;

import java.util.Scanner;

public class HumanPlayer extends Player {

	Scanner scan = new Scanner(System.in);

	public HumanPlayer(String userId) {
		super(userId);
	}

	public void takePins(Board b) {

		if (b.getNoPins() != 0) {

			// if (b.getNoPins() > 0) {

			// }
			int choice = UserInterface.askForInt("Skriv in antal stickor du vill plocka.");

			if (choice == 1) {

				b.takePins(1);

			} else if (choice == 2) {

				if (b.getNoPins() == 1) {
					UserInterface.printMessage("Det finns bara en sticka kvar att plocka.");
					b.takePins(1);
				} else {
					b.takePins(2);
				}

			} else {
				UserInterface.printMessage("Det är endast tillåtet att ta 1  eller 2 stickor!");
			}

		} else {
			UserInterface.printMessage("Spelbrädan är tom!");
		}

		if (b.getNoPins() == 0) {
			UserInterface.printMessage("Du tog sista stickan och har vunnit!");

		}
	}

}

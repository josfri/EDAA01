package game;

import java.util.Random;

public class ComputerPlayer extends Player {

	Random rand = new Random();

	public ComputerPlayer(String userId) {
		super(userId);
	}

	public void takePins(Board b) {

		int no = rand.nextInt(2) + 1;

		if (no < b.getNoPins()) {
			b.takePins(no);
			UserInterface
					.printMessage("Datorn tog " + no + " stickor och det finns nu " + b.getNoPins() + " stickor kvar.");
		} else if (no == b.getNoPins()) {
			b.takePins(no);
			UserInterface.printMessage("Datorn tog " + no + " stickor och vann!");
		} else if (b.getNoPins() == 1) {
			b.takePins(1);
			UserInterface.printMessage("Datorn tog sista stickan och vann!");
		}

	}
}

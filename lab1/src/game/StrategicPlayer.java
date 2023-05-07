package game;

public class StrategicPlayer extends Player {

	public StrategicPlayer(String userId) {
		super(userId);
	}

	public void takePins(Board b) {

		if (b.getNoPins() > 2) {

			if (b.getNoPins() % 2 == 0) {
				b.takePins(1);
				UserInterface.printMessage("Datorn tog en sticka och det finns nu " + b.getNoPins() + " stickor kvar.");
			} else if (b.getNoPins() % 2 == 1) {
				b.takePins(2);
				UserInterface
						.printMessage("Datorn tog två stickor och det finns nu " + b.getNoPins() + " stickor kvar.");
			}

		} else {
			b.takePins(b.getNoPins());
			UserInterface.printMessage("Datorn tog sista stickan och vann!");
		}

	}
}

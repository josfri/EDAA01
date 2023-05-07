package game;

public class TakePinsGame {

	public static void main(String[] args) {

		runGame(UserInterface.askForInt("Dags att spela! Skriv in antal stickor spelplanen ska ha."));
	}

	public static void runGame(int startNo) {

		Board b = new Board();
		b.setUp(startNo);

		HumanPlayer h = new HumanPlayer("Jag");
		// ComputerPlayer c = new ComputerPlayer("Dator");
		StrategicPlayer c = new StrategicPlayer("Dator");

		while (b.getNoPins() != 0) {

			h.takePins(b);
			c.takePins(b);

		}
	}
}

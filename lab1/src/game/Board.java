package game;

public class Board {

	private int noPins;

	public Board() {
	}

	public void setUp(int n) {
		noPins = n;
	}

	public void takePins(int n) {
		noPins -= n;
	}

	public int getNoPins() {
		return noPins;
	}
}

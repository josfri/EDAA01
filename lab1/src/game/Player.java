package game;

public abstract class Player {

	protected String userId;
	
	public Player (String userId) {
		this.userId = userId;
	}
	
	
	public String getUserId() {
		return userId;
	}
	
	public abstract void takePins(Board b);
		
	
}

package entity;

public enum StatusGame {
	New("New"), Started("Started"), Complete("Complete");

	private String name;

	StatusGame(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return name;
	}
}

package entity;

public enum Signs {
	X("X"), O("O");

	private String name;

	Signs(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

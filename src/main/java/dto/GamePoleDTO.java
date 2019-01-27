package dto;

import org.hibernate.validator.constraints.Length;
import java.util.HashMap;
import java.util.Map;

public class GamePoleDTO {
	@Length(max = 1)
	private String g0;
	@Length(max = 1)
	private String g1;
	@Length(max = 1)
	private String g2;
	@Length(max = 1)
	private String g3;
	@Length(max = 1)
	private String g4;
	@Length(max = 1)
	private String g5;
	@Length(max = 1)
	private String g6;
	@Length(max = 1)
	private String g7;
	@Length(max = 1)
	private String g8;


	public GamePoleDTO() {
	}


	public String getG0() {
		return g0;
	}

	public void setG0(String g0) {
		this.g0 = g0;
	}

	public String getG1() {
		return g1;
	}

	public void setG1(String g1) {
		this.g1 = g1;
	}

	public String getG2() {
		return g2;
	}

	public void setG2(String g2) {
		this.g2 = g2;
	}

	public String getG3() {
		return g3;
	}

	public void setG3(String g3) {
		this.g3 = g3;
	}

	public String getG4() {
		return g4;
	}

	public void setG4(String g4) {
		this.g4 = g4;
	}

	public String getG5() {
		return g5;
	}

	public void setG5(String g5) {
		this.g5 = g5;
	}

	public String getG6() {
		return g6;
	}

	public void setG6(String g6) {
		this.g6 = g6;
	}

	public String getG7() {
		return g7;
	}

	public void setG7(String g7) {
		this.g7 = g7;
	}

	public String getG8() {
		return g8;
	}

	public void setG8(String g8) {
		this.g8 = g8;
	}

	public Map<Integer, String> getgAll() {
		Map<Integer, String> gAll = new HashMap<>();
		gAll.put(0, g0);
		gAll.put(1, g1);
		gAll.put(2, g2);
		gAll.put(3, g3);
		gAll.put(4, g4);
		gAll.put(5, g5);
		gAll.put(6, g6);
		gAll.put(7, g7);
		gAll.put(8, g8);
		return gAll;
	}

}

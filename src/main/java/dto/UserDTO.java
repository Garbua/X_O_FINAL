package dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

	private Long id;
	private String login;


	public UserDTO() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}
}

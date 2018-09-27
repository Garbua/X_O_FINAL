package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "users")
public class UserEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column
	private String login;

	@Column
	private String email;

	@Column(name = "pass")
	private String password;

	@Column(name = "firstName")
	private String firstName;

	@Column(name = "lastName")
	private String lastName;


	//Устанавливаем связь с таблицей games

	@OneToMany(targetEntity = GamesEntity.class, mappedBy = "winner",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<GamesEntity> winners;

	public List<GamesEntity> getWinners() {
		return winners;
	}

	public void setWinners(List<GamesEntity> winners) {
		this.winners = winners;
	}

	//Устанавливаем связь с таблицей move

	@OneToMany(targetEntity = MoveEntity.class, mappedBy = "user_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MoveEntity> userMoves;

	public List<MoveEntity> getUserMoves() {
		return userMoves;
	}

	public void setUserMoves(List<MoveEntity> userMoves) {
		this.userMoves = userMoves;
	}

	public UserEntity() {
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Override
	public String toString() {
		return "UserEntity{" +
				"id=" + id +
				", login='" + login + '\'' +
				", email='" + email + '\'' +
				", password='" + password + '\'' +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				'}';
	}
}

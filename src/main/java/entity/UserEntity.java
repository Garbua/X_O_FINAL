package entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
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

	@OneToMany(targetEntity = Game.class, mappedBy = "winner",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Game> winners;

	public List<Game> getWinners() {
		return winners;
	}

	public void setWinners(List<Game> winners) {
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

	//Устанавливаем связь с таблицей user_games
	@OneToMany(mappedBy = "pk.user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User_Games> user_games= new ArrayList<User_Games>();

	public List<User_Games> getUser_games() {
		return this.user_games;
	}

	public void setUser_games(List<User_Games> user_games) {
		this.user_games = user_games;
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

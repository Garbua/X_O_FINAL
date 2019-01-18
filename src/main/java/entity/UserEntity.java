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

	//Устанавливаем связь с таблицей move
	@OneToMany(targetEntity = MoveEntity.class, mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MoveEntity> userMoves = new ArrayList<>();

	//Устанавливаем связь с таблицей games
	@OneToMany(targetEntity = Game.class, mappedBy = "winner",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Game> games = new ArrayList<>();


	//Устанавливаем связь с таблицей user_games
	@OneToMany(targetEntity = Player.class, mappedBy = "user",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Player> players = new ArrayList<>();

	public UserEntity() {
	}

	public void addPlayer(Player player) {
		player.setUser(this);
		players.add(player);
	}

	public void addGame(Game game) {
		game.setWinner(this);
		games.add(game);
	}

	public void addMove(MoveEntity moveEntity) {
		moveEntity.setUser(this);
		userMoves.add(moveEntity);
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

	public List<MoveEntity> getUserMoves() {
		return userMoves;
	}

	public void setUserMoves(List<MoveEntity> userMoves) {
		this.userMoves = userMoves;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public List<Player> getPlayers() {
		return players;
	}

	public void setPlayers(List<Player> players) {
		this.players = players;
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

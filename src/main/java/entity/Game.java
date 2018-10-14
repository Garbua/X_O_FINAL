package entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "games")
public class Game implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_game")
	private Long id_game;

	@ManyToOne(targetEntity = UserEntity.class, cascade = CascadeType.REFRESH , fetch = FetchType.LAZY)
	@JoinColumn(name = "winner")
	private UserEntity winner;

	@Column(name = "status")
	private String status;

	//Устанавливаем связь с таблицей user_games
//	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//	@JoinTable(name = "user_games",
//			joinColumns = @JoinColumn(name = "game_id"),
//			inverseJoinColumns = @JoinColumn(name = "user_id"))
//	private List<Player> users= new ArrayList<Player>();

	//Устанавливаем связь с таблицей user_games
	@OneToMany(mappedBy = "game",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Player> users = new ArrayList<>();

	//Устанавливаем связь с таблицей move
	@OneToMany(targetEntity = MoveEntity.class, mappedBy = "game_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MoveEntity> moves;

	public Game() {
	}

	public Game(String status) {
		this.status = status;
	}



	public Game(UserEntity winner, String status) {
		this.winner = winner;
		this.status = status;
	}

	public Long getId_game() {
		return id_game;
	}

	public void setId_game(Long id_game) {
		this.id_game = id_game;
	}

	public UserEntity getWinner() {
		return winner;
	}

	public void setWinner(UserEntity winner) {
		this.winner = winner;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public List<Player> getUsers() {
		return users;
	}

	public void setUsers(List<Player> users) {
		this.users = users;
	}

	public List<MoveEntity> getMoves() {
		return moves;
	}

	public void setMoves(List<MoveEntity> moves) {
		this.moves = moves;
	}
}

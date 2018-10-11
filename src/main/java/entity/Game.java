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
	@JoinColumn(name = "id")
	private UserEntity winner;

	@Column(name = "status")
	private String status;

	//Устанавливаем связь с таблицей user_games
	@OneToMany(mappedBy = "pk.game", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<User_Games> user_games= new ArrayList<User_Games>();

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

	public Game(UserEntity winner, String status, List<User_Games> user_games) {
		this.winner = winner;
		this.status = status;
		this.user_games = user_games;
	}

	public Game(UserEntity winner, String status, List<User_Games> user_games, List<MoveEntity> moves) {
		this.winner = winner;
		this.status = status;
		this.user_games = user_games;
		this.moves = moves;
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

	public List<User_Games> getUser_games() {
		return user_games;
	}

	public void setUser_games(List<User_Games> user_games) {
		this.user_games = user_games;
	}

	public List<MoveEntity> getMoves() {
		return moves;
	}

	public void setMoves(List<MoveEntity> moves) {
		this.moves = moves;
	}


}

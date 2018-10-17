package entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "move")
public class MoveEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_move")
	private Long id_move;

	@ManyToOne(targetEntity = Game.class, fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "game_id")
	private Game game;

	@Column(name = "pole")
	private String pole;

	@Column(name = "move")
	private String move;

	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "user_id")
	private UserEntity user;

	public MoveEntity() {
	}

	public MoveEntity(Game game, String pole, String move, UserEntity user) {
		this.game = game;
		this.pole = pole;
		this.move = move;
		this.user = user;
	}

	public Long getId_move() {
		return id_move;
	}

	public void setId_move(Long id_move) {
		this.id_move = id_move;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getPole() {
		return pole;
	}

	public void setPole(String pole) {
		this.pole = pole;
	}

	public String getMove() {
		return move;
	}

	public void setMove(String move) {
		this.move = move;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}
}

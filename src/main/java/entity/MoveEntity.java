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
	@JoinColumn(name = "id_game")
	private Game game_id;

	@Column(name = "pole")
	private String pole;

	@Column(name = "move")
	private String move;

	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id")
	private UserEntity user_id;

	public MoveEntity() {
	}

	public MoveEntity(Game game_id, String pole, String move, UserEntity user_id) {
		this.game_id = game_id;
		this.pole = pole;
		this.move = move;
		this.user_id = user_id;
	}

	public Long getId_move() {
		return id_move;
	}

	public void setId_move(Long id_move) {
		this.id_move = id_move;
	}

	public Game getGame_id() {
		return game_id;
	}

	public void setGame_id(Game game_id) {
		this.game_id = game_id;
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

	public UserEntity getUser_id() {
		return user_id;
	}

	public void setUser_id(UserEntity user_id) {
		this.user_id = user_id;
	}
}

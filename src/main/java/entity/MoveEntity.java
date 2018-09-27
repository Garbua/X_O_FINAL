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

	@ManyToOne(targetEntity = GamesEntity.class, fetch = FetchType.LAZY,cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id_game")
	private GamesEntity game_id;

	@Column(name = "pole")
	private String pole;

	@Column(name = "move")
	private String move;

	@ManyToOne(targetEntity = UserEntity.class, fetch = FetchType.LAZY, cascade = CascadeType.REFRESH)
	@JoinColumn(name = "id")
	private UserEntity user_id;
}

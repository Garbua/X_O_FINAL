package entity;


import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "games")
public class GamesEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_game")
	private Long id_game;

	@ManyToOne(targetEntity = UserEntity.class, cascade = CascadeType.REFRESH , fetch = FetchType.LAZY)
	@JoinColumn(name = "id")
	private UserEntity winner;

	@Column(name = "status")
	private String status;


	//Устанавливаем связь с таблицей move
	@OneToMany(targetEntity = MoveEntity.class, mappedBy = "game_id", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<MoveEntity> moves;
}

package entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "user_games")
public class UserGamesEntity implements Serializable {

	@Column
	private Long user_id;

	@Column
	private Long game_id;

	@Column
	private String sign;
}

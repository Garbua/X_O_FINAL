package entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;


public class UserGamesEntity implements Serializable {

	@Column
	private Long user_id;

	@Column
	private Long game_id;

	@Column
	private String sign;
}

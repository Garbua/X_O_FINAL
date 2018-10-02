package entity;


import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_games", catalog = "xo_final")
@AssociationOverrides({
		@AssociationOverride(name = "pk.user", joinColumns = @JoinColumn(name = "user_id")),
		@AssociationOverride(name = "pk.game", joinColumns = @JoinColumn(name = "game_id"))
})
public class User_Games implements Serializable {

	private User_GamesID pk = new User_GamesID();
	private String sign;

	public User_Games() {
	}

	@EmbeddedId
	public User_GamesID getPk() {
		return pk;
	}

	public void setPk(User_GamesID pk) {
		this.pk = pk;
	}

	@Column(name = "sign")
	public String getSign() {
		return this.sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	@Transient
	public UserEntity getUser() {
		return getPk().getUser();
	}

	public void setUser(UserEntity user) {
		getPk().setUser(user);
	}

	@Transient
	public Game getGame() {
		return getPk().getGame();
	}

	public void setGame(Game game) {
		getPk().setGame(game);
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User_Games that = (User_Games) o;
		if (getPk() != null ? !getPk().equals(that.getPk())
				: that.getPk() != null)
			return false;

		return true;
	}

	@Override
	public int hashCode() {

		return (getPk() != null ? getPk().hashCode() : 0);
	}
}

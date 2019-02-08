package dao;

import entity.Game;
import entity.Player;
import entity.UserEntity;

public interface PlayerDAO {

	Player createPlayer(Player player);
	Player getPlayerByUserGame(UserEntity user, Game game);
	void updatePlayer(Player player);

}

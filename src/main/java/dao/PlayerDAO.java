package dao;

import entity.Game;
import entity.Player;
import entity.UserEntity;

public interface PlayerDAO {

	Player saveOfUpdate(Player player);
	Player getPlayerByUserGame(UserEntity user, Game game);
	Player getPlayerByGameSign(Game game, String sign);

}

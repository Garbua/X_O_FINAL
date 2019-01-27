package dao;

import entity.Player;

public interface PlayerDAO {

	Player createPlayer(Player player);
	Player getPlayerByID (Long id);
	Player updatePlayer(Player player);
	void refreshPlayer(Player player);
}

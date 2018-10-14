package dao;

import entity.Player;

public interface PlayerDAO {

	Player createSign (Player sign);
	Player getPlayerByID (Long id);
}

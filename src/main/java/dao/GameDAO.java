package dao;

import entity.Game;
import entity.String;

public interface GameDAO {

	Game getGameByStatus(String statusGame);
	Game getGameByID (Long id);
	Game createGame (Game game);
	void update (Game game);
	void delete (Game game);

}

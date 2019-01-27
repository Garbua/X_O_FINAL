package dao;

import entity.Game;


public interface GameDAO {

	Game getGameByStatus(String statusGame);
	Game getGameByID (Long id);
	Game createGame (Game game);
	void update (Game game);
	void delete (Game game);
	void refresh (Game game);

}

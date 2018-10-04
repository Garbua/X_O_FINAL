package dao;

import entity.Game;
import entity.StatusGame;

public interface GameDAO {

	Game getGameByStatus(String statusGame);
	Game getGameByID (Long id);
	void createGame (Game game);
	void update (Game game);
	void delete (Game game);
}

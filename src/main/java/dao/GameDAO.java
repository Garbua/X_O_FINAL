package dao;

import entity.Game;


public interface GameDAO {

	Game getGameByStatus(String statusGame);
	Game getGameByID (Long id);
	Game saveOfUpdate(Game game);
	void delete (Game game);


}

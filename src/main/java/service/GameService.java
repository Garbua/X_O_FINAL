package service;

import entity.Game;


public interface GameService {

	Game getGameByStatus (String status);
	Game getGameByID (Long id);
	void saveOfUpdate(Game game);
	void delete (Game game);

}

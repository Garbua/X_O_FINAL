package service;

import entity.Game;


public interface GameService {

	Game getGameByStatus (String status);
	Game getGameByID (Long id);
	void createGame (Game game);
	void update (Game game);
	void delete (Game game);

}

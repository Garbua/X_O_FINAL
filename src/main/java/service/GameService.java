package service;

import entity.Game;
import entity.String;

public interface GameService {

	Game getGameByStatus (String status);
	Game getGameByID (Long id);
	void createGame (Game game);
	void update (Game game);
	void delete (Game game);

}

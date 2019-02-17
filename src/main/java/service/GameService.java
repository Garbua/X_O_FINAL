package service;

import entity.Game;

import java.util.List;


public interface GameService {

	List<Game> getGameByStatus (String status);
	Game getGameByID (Long id);
	void saveOfUpdate(Game game);
	void delete (Game game);

}

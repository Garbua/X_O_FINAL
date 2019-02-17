package dao;

import entity.Game;

import java.util.List;


public interface GameDAO {

	List<Game> getGameByStatus(String statusGame);
	Game getGameByID (Long id);
	Game saveOfUpdate(Game game);
	void delete (Game game);


}

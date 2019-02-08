package service;


import entity.Game;
import entity.Player;
import entity.UserEntity;

public interface PlayerService {

	void createPlayer (Player player);
	Player getPlayerByUserGame(UserEntity user, Game game);
	void updatePlayer(Player player);
}

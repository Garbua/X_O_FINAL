package service;


import entity.Player;

public interface PlayerService {

	void createPlayer (Player player);
	void getPlayerByID (Long id);
	void updatePlayer(Player player);
}

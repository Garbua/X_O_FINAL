package service;


import entity.Player;

public interface PlayerService {

	void createSign (Player player);
	void getPlayerByID (Long id);
}

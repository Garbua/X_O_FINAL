package service;


import entity.Player;

public interface PlayerService {

	void createSign (Player sign);
	void getPlayerByID (Long id);
}

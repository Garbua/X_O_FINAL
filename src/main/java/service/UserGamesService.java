package service;

import entity.User_Games;

public interface UserGamesService {
	User_Games getSignById(User_Games user_games);
	void createSign (User_Games user_games);
	void updateSign (User_Games user_games);
	void deleteSign (User_Games user_games);
}

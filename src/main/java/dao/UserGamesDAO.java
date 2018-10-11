package dao;

import entity.Game;
import entity.UserEntity;
import entity.User_Games;

public interface UserGamesDAO {

	User_Games getSignById(User_Games user_games);
	UserEntity getBySign(Game game, String sign);
	void createSign (User_Games user_games);
	void updateSign (User_Games user_games);
	void deleteSign (User_Games user_games);
}

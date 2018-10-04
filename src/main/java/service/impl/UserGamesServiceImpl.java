package service.impl;

import dao.UserGamesDAO;
import entity.User_Games;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserGamesService;

@Service
public class UserGamesServiceImpl implements UserGamesService {

	@Autowired
	UserGamesDAO userGamesDAO;

	@Override
	@Transactional
	public User_Games getSignById(User_Games user_games) {
		return userGamesDAO.getSignById(user_games);
	}

	@Override
	@Transactional
	public void createSign(User_Games user_games) {
		userGamesDAO.createSign(user_games);

	}

	@Override
	@Transactional
	public void updateSign(User_Games user_games) {
		userGamesDAO.updateSign(user_games);

	}

	@Override
	@Transactional
	public void deleteSign(User_Games user_games) {
		userGamesDAO.deleteSign(user_games);

	}
}

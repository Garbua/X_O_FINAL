package service.impl;

import dao.GameDAO;
import entity.Game;
import entity.StatusGame;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.GameService;

@Service
public class GameServiceImpl implements GameService {

	@Autowired
	GameDAO gameDAO;

	@Override
	public Game getGameByStatus(String status) {
		return gameDAO.getGameByStatus(status);
	}

	@Override
	@Transactional
	public Game getGameByID(Long id) {
		return gameDAO.getGameByID(id);
	}

	@Override
	@Transactional
	public void createGame(Game game) {
		gameDAO.createGame(game);
	}

	@Override
	@Transactional
	public void update(Game game) {
		gameDAO.update(game);
	}

	@Override
	@Transactional
	public void delete(Game game) {
		gameDAO.delete(game);
	}


}

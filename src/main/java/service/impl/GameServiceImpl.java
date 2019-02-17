package service.impl;

import dao.GameDAO;
import entity.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.GameService;

import java.util.List;

@Service
@Transactional
public class GameServiceImpl implements GameService {

	@Autowired
	GameDAO gameDAO;

	@Override
	public List<Game> getGameByStatus(String status) {
		return gameDAO.getGameByStatus(status);
	}

	@Override
	public Game getGameByID(Long id) {
		return gameDAO.getGameByID(id);
	}

	@Override
	public void saveOfUpdate(Game game) {
		gameDAO.saveOfUpdate(game);
	}

	@Override
	public void delete(Game game) {
		gameDAO.delete(game);
	}


}

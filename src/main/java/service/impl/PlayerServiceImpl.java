package service.impl;
import dao.PlayerDAO;
import entity.Game;
import entity.Player;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.PlayerService;

@Service
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDAO playerDAO;

	@Override
	public void createPlayer(Player player) {
		playerDAO.createPlayer(player);
	}

	@Override
	public Player getPlayerByUserGame(UserEntity user, Game game) {
		return playerDAO.getPlayerByUserGame( user, game);
	}

	@Override
	public void updatePlayer(Player player) {
		playerDAO.updatePlayer(player);
	}
}

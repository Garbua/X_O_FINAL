package service.impl;
import dao.PlayerDAO;
import entity.Game;
import entity.Player;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.PlayerService;

@Service
@Transactional
public class PlayerServiceImpl implements PlayerService {

	@Autowired
	private PlayerDAO playerDAO;

	@Override
	public void saveOfUpdate(Player player) {
		playerDAO.saveOfUpdate(player);
	}

	@Override
	public Player getPlayerByUserGame(UserEntity user, Game game) {
		return playerDAO.getPlayerByUserGame( user, game);
	}
	@Override
	public Player getPlayerByGameSign(Game game, String sign) {
		return playerDAO.getPlayerByGameSign( game, sign);
	}
}

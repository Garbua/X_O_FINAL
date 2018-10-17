package service.impl;
import dao.PlayerDAO;
import entity.Player;
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
	public void getPlayerByID(Long id) {
		playerDAO.getPlayerByID(id);

	}

	@Override
	public void updatePlayer(Player player) {
		playerDAO.updatePlayer(player);
	}
}

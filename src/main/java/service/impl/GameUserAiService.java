package service.impl;

import dto.GamePoleDTO;
import entity.EnumConstants;
import entity.Game;
import entity.MoveEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.GameService;
import service.MoveService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
	public class GameUserAiService {

	@Autowired
	private GameService gameService;
	@Autowired
	private MoveService moveService;

	// value = "/aigame", method = RequestMethod.GET
	public GamePoleDTO getNewGameAi(){
		GamePoleDTO gamePoleDTO = new GamePoleDTO();
		Game game = new Game();
		game.setStatus(EnumConstants.NEW);
		gameService.saveOfUpdate(game);
		List<Game> games = gameService.getGameByStatus(EnumConstants.NEW);
		for (Game g : games) {
			if (g.getStatus().equalsIgnoreCase(EnumConstants.NEW) && g.getStatus() != null) {
				gamePoleDTO.setIdGame(g.getId_game());
			}
		}
		return gamePoleDTO;
	}


	// value = "/aigame", method = RequestMethod.GET, params = "continue"
	public GamePoleDTO getContinueGameAi(){
		GamePoleDTO gamePoleDTO = new GamePoleDTO();
		displayGame(gameService.getGameByStatus(EnumConstants.STARTED).get(0), gamePoleDTO);
		gamePoleDTO.setIdGame(gameService.getGameByStatus(EnumConstants.STARTED).get(0).getId_game());
		return gamePoleDTO;
	}

	public void displayGame(Game game, GamePoleDTO gamePoleDTO) {
		Map<Integer, String> gPole2 = new HashMap<>();
		for (MoveEntity m : moveService.getMoveByGame(game)) {
			gPole2.put(Integer.valueOf(m.getPole()), m.getMove());
		}
		gamePoleDTO.setgAll(gPole2);
	}
}

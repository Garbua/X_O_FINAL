package controllers;


import dao.GameDAO;
import dao.MoveDAO;
import dao.PlayerDAO;
import dao.UserDAO;
import dto.UserDTO;
import entity.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@Controller
public class GameUserAiController extends ExceptionHandlerController {


	@Autowired
	private GameDAO gameDAO;


	@Autowired
	private MoveDAO moveDAO;

	@Autowired
	private PlayerDAO playerDAO;

	@Autowired
	private UserDAO userDAO;

	private UserEntity user;
	private Game gameAi;
	private Integer pole = 3;

	@RequestMapping(value = "/aistartgame", method = RequestMethod.GET)
	public String startGameAi(){
		gameAi = new Game(StatusGame.Started.getName());
		gameDAO.createGame(gameAi);

		return "pages/aiGame";
	}


	@RequestMapping(value = "/aigame", method = RequestMethod.GET)
	public String gameUserAiGet(Model model){
		Player player = new Player();
		playerDAO.createPlayer(player);
		player.setGame(gameAi);

		user = userDAO.getUserByLogin(user.getLogin());

		player.setUser(user);
		player.setSign(Signs.X.getName());
		playerDAO.createPlayer(player);

		return "pages/aiGame";
	}

	@RequestMapping(value = "/aigame", method = RequestMethod.POST)
	public String gameUserAiPost(Model model, HttpServletRequest request){
		for (int i = 0; i <= (pole * pole)-1 ; i++) {
			String param = String.valueOf(i);
			MoveEntity moveEntity = new MoveEntity();
			moveEntity.setGame_id(gameAi);
			moveEntity.setPole(param);
			moveEntity.setMove(request.getParameter(param));
			moveEntity.setUser_id(user);
			moveDAO.createMove(moveEntity);
			request.setAttribute("p" + i, request.getParameter(param));

		}
		return "pages/aiGame";
	}
}

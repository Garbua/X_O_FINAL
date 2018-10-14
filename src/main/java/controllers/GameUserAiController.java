package controllers;

import dao.GameDAO;
import dao.MoveDAO;
import dao.PlayerDAO;
import entity.Game;
import entity.MoveEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class GameUserAiController extends ExceptionHandlerController {


	@Autowired
	private GameDAO gameDAO;


	@Autowired
	private MoveDAO moveDAO;

	@Autowired
	private PlayerDAO playerDAO;

	private Game gameAi;
	private Integer pole = 3;

	@RequestMapping(value = "/aistartgame", method = RequestMethod.GET)
	public String startGameAi(){
		gameAi = gameDAO.createGame(new Game("started"));
		return "pages/aiGame";
	}


	@RequestMapping(value = "/aigame", method = RequestMethod.GET)
	public String gameUserAiGet(Model model){

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
			moveDAO.createMove(moveEntity);
			request.setAttribute("p" + i, request.getParameter(param));

		}
		return "pages/aiGame";
	}
}

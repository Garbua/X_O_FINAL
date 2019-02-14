package service.impl;

import dao.MoveDAO;
import entity.Game;
import entity.MoveEntity;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.MoveService;

import java.util.List;


@Service
@Transactional
public class MoveServiceImpl implements MoveService {


	@Autowired
	MoveDAO moveDAO;

	@Override
	public List<MoveEntity> getMoveByGame(Game game) {
		return moveDAO.getMoveByGame(game);
	}

	@Override
	public List<MoveEntity> getMoveByUser(UserEntity user) {
		return moveDAO.getMoveByUser(user);
	}

	@Override
	public MoveEntity saveOfUpdate(MoveEntity moveEntity) {
		return moveDAO.saveOfUpdate(moveEntity);
	}

	@Override
	public void deleteMove(MoveEntity moveEntity) {
		moveDAO.deleteMove(moveEntity);
	}

	@Override
	public MoveEntity getMoveByGamePole(Game game, String pole){
		return moveDAO.getMoveByGamePole(game, pole);
	}
}

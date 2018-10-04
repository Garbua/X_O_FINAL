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
public class MoveServiceImpl implements MoveService {


	@Autowired
	MoveDAO moveDAO;

	@Override
	@Transactional
	public List<MoveEntity> getMoveByGame(Game game) {
		return moveDAO.getMoveByGame(game);
	}

	@Override
	@Transactional
	public List<MoveEntity> getMoveByUser(UserEntity user) {
		return moveDAO.getMoveByUser(user);
	}

	@Override
	@Transactional
	public MoveEntity createMove(MoveEntity moveEntity) {
		return moveDAO.createMove(moveEntity);
	}

	@Override
	@Transactional
	public void updateMove(MoveEntity moveEntity) {
		moveDAO.updateMove(moveEntity);

	}

	@Override
	@Transactional
	public void deleteMove(MoveEntity moveEntity) {
		moveDAO.deleteMove(moveEntity);

	}
}

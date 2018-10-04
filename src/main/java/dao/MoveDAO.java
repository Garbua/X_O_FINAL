package dao;

import entity.Game;
import entity.MoveEntity;
import entity.UserEntity;

import java.util.List;

public interface MoveDAO {

	List<MoveEntity> getMoveByGame (Game game);
	List<MoveEntity> getMoveByUser (UserEntity user);
	MoveEntity createMove (MoveEntity moveEntity);
	void updateMove (MoveEntity moveEntity);
	void deleteMove (MoveEntity moveEntity);
}

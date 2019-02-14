package dao;

import entity.Game;
import entity.MoveEntity;
import entity.UserEntity;

import java.util.List;

public interface MoveDAO {

	List<MoveEntity> getMoveByGame (Game game);
	List<MoveEntity> getMoveByUser (UserEntity user);
	MoveEntity saveOfUpdate(MoveEntity moveEntity);
	MoveEntity getMoveByGamePole(Game game, String pole);
	void deleteMove (MoveEntity moveEntity);

}

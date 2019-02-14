package service;

import entity.Game;
import entity.MoveEntity;
import entity.UserEntity;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface MoveService {
	List<MoveEntity> getMoveByGame (Game game);
	List<MoveEntity> getMoveByUser (UserEntity user);
	MoveEntity saveOfUpdate(MoveEntity moveEntity);
	MoveEntity getMoveByGamePole(Game game, String pole);
	void deleteMove (MoveEntity moveEntity);

}

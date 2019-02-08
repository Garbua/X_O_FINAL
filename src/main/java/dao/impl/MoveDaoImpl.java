package dao.impl;

import dao.MoveDAO;
import entity.Game;
import entity.MoveEntity;
import entity.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Repository("move")
@Transactional
public class MoveDaoImpl implements MoveDAO {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	private MessageSource messageSource;


	@Override
	public List<MoveEntity> getMoveByGame(Game game) {
		String moveHQL = "FROM MoveEntity WHERE game_id = :game_id";
		Query query = sessionFactory.getCurrentSession().createQuery(moveHQL);
		query.setParameter("game_id", game.getId_game());
//		LOGGER.info(messageSource.getMessage("dao.move.getMoveByGame", new Object[]{game}, Locale.ENGLISH));
		return (List<MoveEntity>) query.getResultList();
	}

	@Override
	public List<MoveEntity> getMoveByUser(UserEntity user) {
		String moveHQL = "FROM MoveEntity WHERE user_id = :user_id";
		Query query = sessionFactory.getCurrentSession().createQuery(moveHQL);
		query.setParameter("user_id", user.getId());
//		LOGGER.info(messageSource.getMessage();
		return (List<MoveEntity>) query.getSingleResult();
	}


	@Override
	public MoveEntity createMove(MoveEntity moveEntity) {
		sessionFactory.getCurrentSession().saveOrUpdate(moveEntity);
		//		LOGGER.info(messageSource.getMessage();
		return moveEntity;
	}

	@Override
	public MoveEntity getMoveByGamePole(Game game, String pole) {
		String movHQL = "FROM MoveEntity WHERE game = :game AND pole = :pole";
		Query query = sessionFactory.getCurrentSession().createQuery(movHQL);
		query.setParameter("game", game);
		query.setParameter("pole", pole);
		return (MoveEntity) query.getSingleResult();
	}

	@Override
	public long getCountPoleDb(Game game) {
		String moveHQL = "SELECT count (pole) FROM MoveEntity WHERE game_id = :game_id";
		Query query = sessionFactory.getCurrentSession().createQuery(moveHQL);
		query.setParameter("game_id", game.getId_game());
		List listResult = query.list();
		Number number = (Number) listResult.get(0);
		return number.longValue();
	}

	@Override
	public void updateMove(MoveEntity moveEntity) {
		sessionFactory.getCurrentSession().saveOrUpdate(moveEntity);
//		LOGGER.info(messageSource.getMessage();
	}

	@Override
	public void deleteMove(MoveEntity moveEntity) {
		sessionFactory.getCurrentSession().delete(moveEntity);
		//		LOGGER.info(messageSource.getMessage();

	}

}

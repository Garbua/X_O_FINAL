package dao.impl;

import dao.GameDAO;
import entity.Game;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository("game")
@Transactional
public class GameDaoImpl implements GameDAO {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	private MessageSource messageSource;

	@Override
	public Game getGameByStatus(String statusGame) {
		String gameHQL = "FROM Game WHERE status = :status";
		Query query = sessionFactory.getCurrentSession().createQuery(gameHQL);
		query.setParameter("status", statusGame);
//		LOGGER.info(messageSource.getMessage("dao.game.getGameById", new Object[]{id}, Locale.ENGLISH));
		return (Game) query.uniqueResult();
	}

	@Override
	public Game getGameByID(Long id) {
		String gameHQL = "FROM Game WHERE id_game = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(gameHQL);
		query.setParameter("id", id);
//		LOGGER.info(messageSource.getMessage("dao.game.getGameById", new Object[]{id}, Locale.ENGLISH));
		return (Game) query.uniqueResult();
	}

	@Override
	public Game createGame(Game game) {
		sessionFactory.getCurrentSession().saveOrUpdate(game);
//		LOGGER.info(messageSource.getMessage();

		return game;
	}


	@Override
	public void update(Game game) {
		Game mergeGame = (Game)sessionFactory.getCurrentSession().merge(game);
		sessionFactory.getCurrentSession().update(mergeGame);
//		LOGGER.info(messageSource.getMessage();
	}

	@Override
	public void delete(Game game) {
		sessionFactory.getCurrentSession().delete(game);
//		LOGGER.info(messageSource.getMessage();
	}

	@Override
	public void refresh(Game game) {
		sessionFactory.getCurrentSession().refresh(game);
	}
}

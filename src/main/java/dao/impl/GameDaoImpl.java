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

import java.util.List;
import java.util.Locale;


@Repository("game")
@Transactional
public class GameDaoImpl implements GameDAO {

	private static final Logger LOGGER = Logger.getLogger(GameDaoImpl.class);

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	private MessageSource messageSource;

	@Override
	public List<Game> getGameByStatus(String statusGame) {
		String gameHQL = "FROM Game WHERE status = :status";
		Query query = sessionFactory.getCurrentSession().createQuery(gameHQL);
		query.setParameter("status", statusGame);
		LOGGER.info(messageSource.getMessage("dao.game.getGameByStatus", new Object[]{statusGame}, Locale.ENGLISH));
		return (List<Game>) query.getResultList();
	}

	@Override
	public Game getGameByID(Long id) {
		String gameHQL = "FROM Game WHERE id_game = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(gameHQL);
		query.setParameter("id", id);
		LOGGER.info(messageSource.getMessage("dao.game.getGameById", new Object[]{id}, Locale.ENGLISH));
		return (Game) query.uniqueResult();
	}

	@Override
	public Game saveOfUpdate(Game game) {
		sessionFactory.getCurrentSession().saveOrUpdate(game);
		LOGGER.info(messageSource.getMessage("dao.game.saveOfUpdate", new Object[]{game}, Locale.ENGLISH));

		return game;
	}

	@Override
	public void delete(Long id) {
		Game game = getGameByID(id);
		sessionFactory.getCurrentSession().delete(game);
		LOGGER.info(messageSource.getMessage("dao.game.delete", new Object[]{game}, Locale.ENGLISH));
	}

}

package dao.impl;

import dao.UserGamesDAO;
import entity.User_Games;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("user_game")
@Transactional
public class UserGamesDaoImpl implements UserGamesDAO {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	private MessageSource messageSource;

	@Autowired
	public SessionFactory sessionFactory;


	@Override
	public User_Games getSignById(User_Games user_games) {
		String userGameHQL = "FROM User_Games WHERE pk = :pk";
		Query query = sessionFactory.getCurrentSession().createQuery(userGameHQL);
		query.setParameter("pk", user_games.getPk());
		//		LOGGER.info(messageSource.getMessage();
		return (User_Games) query.uniqueResult();
	}

	@Override
	public void createSign(User_Games user_games) {
		sessionFactory.getCurrentSession().save(user_games);
		//		LOGGER.info(messageSource.getMessage();
	}

	@Override
	public void updateSign(User_Games user_games) {
		User_Games mergeUserGame = (User_Games) sessionFactory.getCurrentSession().merge(user_games);
		sessionFactory.getCurrentSession().update(mergeUserGame);
		//		LOGGER.info(messageSource.getMessage();
	}

	@Override
	public void deleteSign(User_Games user_games) {
		sessionFactory.getCurrentSession().delete(user_games);
		//		LOGGER.info(messageSource.getMessage();
	}
}

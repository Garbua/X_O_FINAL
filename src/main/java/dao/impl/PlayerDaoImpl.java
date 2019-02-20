package dao.impl;

import dao.PlayerDAO;
import entity.Game;
import entity.Player;
import entity.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;

@Repository("player")
@Transactional
public class PlayerDaoImpl implements PlayerDAO {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	public SessionFactory sessionFactory;

	@Autowired
	private MessageSource messageSource;


	@Override
	public Player saveOfUpdate(Player player) {
		sessionFactory.getCurrentSession().saveOrUpdate(player);
		LOGGER.info(messageSource.getMessage("dao.player.saveOfUpdate", new Object[]{player}, Locale.ENGLISH));
		return  player;
	}

	@Override
	public Player getPlayerByUserGame(UserEntity user, Game game) {
		String playerHQL = "FROM Player WHERE user = :user AND game = :game";
		Query query = sessionFactory.getCurrentSession().createQuery(playerHQL);
		query.setParameter("user", user);
		query.setParameter("game", game);
		LOGGER.info(messageSource.getMessage("dao.player.getPlayerByUserGame",  new Object[]{user,game},Locale.ENGLISH));
		return (Player) query.getSingleResult();

	}

	@Override
	public Player getPlayerByGameSign(Game game, String sign) {
		String playerHQL = "FROM Player WHERE game = :game AND sign = :sign";
		Query query = sessionFactory.getCurrentSession().createQuery(playerHQL);
		query.setParameter("game", game);
		query.setParameter("sign", sign);
		LOGGER.info(messageSource.getMessage("dao.player.getPlayerByGameSign",  new Object[]{game,sign},Locale.ENGLISH));
		return (Player) query.getSingleResult();

	}
}

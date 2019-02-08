package dao.impl;

import dao.PlayerDAO;
import entity.Game;
import entity.Player;
import entity.UserEntity;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository("player")
@Transactional
public class PlayerDaoImpl implements PlayerDAO {

	@Autowired
	public SessionFactory sessionFactory;


	@Override
	public Player createPlayer(Player player) {
		sessionFactory.getCurrentSession().saveOrUpdate(player);
		return  player;
	}

	@Override
	public Player getPlayerByUserGame(UserEntity user, Game game) {
		String playerHQL = "FROM Player WHERE user = :user AND game = :game";
		Query query = sessionFactory.getCurrentSession().createQuery(playerHQL);
		query.setParameter("user", user);
		query.setParameter("game", game);
		return (Player) query.uniqueResult();

	}

	@Override
	public void updatePlayer(Player player) {
		sessionFactory.getCurrentSession().saveOrUpdate(player);
	}

}

package dao.impl;

import dao.PlayerDAO;
import entity.Player;
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
	public Player getPlayerByID(Long id) {
		String playerHQL = "FROM Player WHERE id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(playerHQL);
		query.setParameter("id", id);
		return (Player) query.uniqueResult();

	}

	@Override
	public Player updatePlayer(Player player) {
		sessionFactory.getCurrentSession().update(player);
		return player;
	}

	@Override
	public void refreshPlayer(Player player) {
		sessionFactory.getCurrentSession().refresh(player);
		return ;
	}
}

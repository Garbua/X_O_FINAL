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
	public Player createSign(Player sign) {
		sessionFactory.getCurrentSession().save(sign);
		return  sign;
	}

	@Override
	public Player getPlayerByID(Long id) {
		java.lang.String playerHQL = "FROM Player WHERE id = :id";
		Query query = sessionFactory.getCurrentSession().createQuery(playerHQL);
		query.setParameter("id", id);
		return (Player) query.uniqueResult();

	}
}

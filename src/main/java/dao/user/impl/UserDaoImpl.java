package dao.user.impl;

import dao.UserDAO;
import entity.UserEntity;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Locale;


@Repository("userDaoImpl")
@Transactional
public class UserDaoImpl implements UserDAO {

	private final Logger LOGGER = Logger.getLogger(getClass());

	@Autowired
	private MessageSource messageSource;

	@Autowired
	public SessionFactory sessionFactory;

	@Override
	public UserEntity getUserByLogin(String login) {
		String userHQL = "FROM UserEntity WHERE login = :login";
		Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
		query.setParameter("login", login);
		LOGGER.info(messageSource.getMessage("dao.user.getByLogin", new Object[]{login}, Locale.ENGLISH));
		return (UserEntity) query.uniqueResult();
	}

	@Override
	public UserEntity createUser(UserEntity user) {
		sessionFactory.getCurrentSession().save(user);
		LOGGER.info(messageSource.getMessage("dao.user.save", new Object [] {user},Locale.ENGLISH));
		return user;
	}

	@Override
	public void updateUser(UserEntity user) {
		UserEntity mergedUser = (UserEntity) sessionFactory.getCurrentSession().merge(user);
		sessionFactory.getCurrentSession().update(mergedUser);
		LOGGER.info(messageSource.getMessage("dao.user.update", new Object[]{user}, Locale.ENGLISH));

	}

	@Override
	public void deleteUser(UserEntity user) {
		UserEntity mergedUser = (UserEntity) sessionFactory.getCurrentSession().merge(user);
		sessionFactory.getCurrentSession().delete(mergedUser);
		LOGGER.info(messageSource.getMessage("dao.user.delete", new Object[]{user}, Locale.ENGLISH));

	}

	@Override
	public boolean passwordCorrect(String pass, String login) {
		String userHQL = "FROM UserEntity WHERE pass = :pass AND login = :login";
		Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
		query.setParameter("pass", pass);
		query.setParameter("login", login);

		List userEntityes = query.list();

		return userEntityes.size() > 0;
	}

	@Override
	public boolean loginExists(String login) {
		String userHQL = "FROM UserEntity WHERE login = :login";
		Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
		query.setParameter("login", login);
		return query.list().size() > 0;
	}

	@Override
	public boolean emailExists(String email) {
		String userHQL = "FROM UserEntity WHERE email = :email";
		Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
		query.setParameter("email", email);

		List userEntityes = query.list();

		return userEntityes.size() > 0;
	}
}

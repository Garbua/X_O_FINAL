package dao.impl;

import dao.UserDAO;
import dto.UserDTO;
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


	/**
	 * Метод поиска пользователя в БД по его логину
	 * @param login - пользователь который передаётся в запрос поиска в БД
	 * @return - результат поиска
	 */
	@Override
	public UserEntity getUserByLogin(String login) {
		String userHQL = "FROM UserEntity WHERE login = :login";
		Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
		query.setParameter("login", login);
		LOGGER.info(messageSource.getMessage("dao.user.getByLogin", new Object[]{login}, Locale.ENGLISH));
		return (UserEntity) query.uniqueResult();
	}

	/**
	 * Метод записи нового пользователя в БД.
	 * @param user - переданный объект пользователя для записи в БД.
	 * @return - сохранённый пользователь
	 */
	@Override
	public UserEntity createUser(UserEntity user) {
		sessionFactory.getCurrentSession().saveOrUpdate(user);
		LOGGER.info(messageSource.getMessage("dao.user.save", new Object [] {user},Locale.ENGLISH));
		return user;
	}

	/**
	 * Метод редактирования данных пользователя в БД
	 * @param user -  редактруемый объект пользователя
	 */
	@Override
	public void updateUser(UserEntity user) {
		UserEntity mergedUser = (UserEntity) sessionFactory.getCurrentSession().merge(user);
		sessionFactory.getCurrentSession().update(mergedUser);
		LOGGER.info(messageSource.getMessage("dao.user.update", new Object[]{user}, Locale.ENGLISH));

	}

	/**
	 * Метод удаления пользователя из БД
	 * @param user - удаляемый объект пользователя
	 */
	@Override
	public void deleteUser(UserDTO user) {
		sessionFactory.getCurrentSession().delete(getUserByLogin(user.getLogin()));
		LOGGER.info(messageSource.getMessage("dao.user.delete", new Object[]{user}, Locale.ENGLISH));

	}

	/**
	 * Метод проверки существует ли в БД такой пароль у пользователя
	 * @param pass - проверяемый пароль
	 * @param login - пользователь у которого проверяется пароль
	 * @return - если пароль есть в БД - true , если нет - false.
	 */
	@Override
	public boolean passwordCorrect(String pass, String login) {
		String userHQL = "FROM UserEntity WHERE pass = :pass AND login = :login";
		Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
		query.setParameter("pass", pass);
		query.setParameter("login", login);

		List userEntityes = query.list();

		return userEntityes.size() > 0;
	}

	/**
	 * Метод проверки пользователя в БД
	 * @param login - имя пользователя для проверки
	 * @return - если пользователь есть в БД - true , если нет - false.
	 */
	@Override
	public boolean loginExists(String login) {
		String userHQL = "FROM UserEntity WHERE login = :login";
		Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
		query.setParameter("login", login);
		return query.list().size() > 0;
	}

	/**
	 * Метод проверки почты в БД
	 * @param email - адресс почты для проверки
	 * @return - если почта есть в БД - true , если нет - false.
	 */
	@Override
	public boolean emailExists(String email) {
		String userHQL = "FROM UserEntity WHERE email = :email";
		Query query = sessionFactory.getCurrentSession().createQuery(userHQL);
		query.setParameter("email", email);

		List userEntityes = query.list();

		return  userEntityes.size() > 0;
	}
}

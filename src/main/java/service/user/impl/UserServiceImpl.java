package service.user.impl;

import dao.user.UserDAO;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.user.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	@Transactional
	public UserEntity getUserByLogin(String login) {
		return userDAO.getUserByLogin(login);
	}

	@Override
	@Transactional
	public void createUser(UserEntity user) {
		userDAO.createUser(user);

	}

	@Override
	@Transactional
	public void updateUser(UserEntity user) {
		userDAO.updateUser(user);

	}

	@Override
	@Transactional
	public void deleteUser(UserEntity user) {
		userDAO.deleteUser(user);

	}

	@Override
	@Transactional
	public boolean passwordCorrect(String pass, String login) {
		return userDAO.passwordCorrect(pass, login);
	}

	@Override
	@Transactional
	public boolean loginExists(String login) {
		return userDAO.loginExists(login);
	}

	@Override
	@Transactional
	public boolean emailExists(String email) {
		return userDAO.emailExists(email);
	}
}

package service.impl;

import dao.UserDAO;
import dto.UserDTO;
import entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.UserService;


@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	UserDAO userDAO;

	@Override
	public UserEntity getUserByLogin(String login) {
		return userDAO.getUserByLogin(login);
	}

	@Override
	public void saveOfUpdate(UserEntity user) {
		userDAO.saveOfUpdate(user);
	}

	@Override
	public void deleteUser(UserDTO user) {
		userDAO.deleteUser(user);
	}

	@Override
	public boolean passwordCorrect(String pass, String login) {
		return userDAO.passwordCorrect(pass, login);
	}

	@Override
	public boolean loginExists(String login) {
		return userDAO.loginExists(login);
	}

	@Override
	public boolean emailExists(String email) {
		return userDAO.emailExists(email);
	}
}

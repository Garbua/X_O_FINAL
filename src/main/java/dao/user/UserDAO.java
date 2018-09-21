package dao.user;

import entity.UserEntity;

public interface UserDAO {

	UserEntity getUserByLogin (String login);
	UserEntity createUser (UserEntity user);
	void updateUser (UserEntity user);
	void deleteUser (UserEntity user);
	boolean passwordCorrect (String pass, String login);
	boolean loginExists (String login);
	boolean emailExists (String email);
}

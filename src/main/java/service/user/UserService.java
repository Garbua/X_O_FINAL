package service.user;

import entity.UserEntity;

public interface UserService {
	UserEntity getUserByLogin (String login);
	void createUser (UserEntity user);
	void updateUser (UserEntity user);
	void deleteUser (UserEntity user);
	boolean passwordCorrect (String pass, String login);
	boolean loginExists (String login);
	boolean emailExists (String email);
}

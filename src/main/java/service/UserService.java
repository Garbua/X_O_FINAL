package service;


import entity.UserEntity;

public interface UserService {
	UserEntity getUserById (Long id);
	UserEntity getUserByLogin (String login);
	void saveOfUpdate(UserEntity user);
	void deleteUser (Long id);
	boolean passwordCorrect (String pass, String login);
	boolean loginExists (String login);
	boolean emailExists (String email);
}

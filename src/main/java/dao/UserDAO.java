package dao;

import dto.UserDTO;
import entity.UserEntity;

public interface UserDAO {
	UserEntity getUserById (Long id);
	UserEntity getUserByLogin (String login);
	UserEntity saveOfUpdate(UserEntity user);
	void deleteUser (Long id);
	boolean passwordCorrect (String pass, String login);
	boolean loginExists (String login);
	boolean emailExists (String email);
}

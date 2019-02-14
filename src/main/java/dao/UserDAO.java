package dao;

import dto.UserDTO;
import entity.UserEntity;

public interface UserDAO {

	UserEntity getUserByLogin (String login);
	UserEntity saveOfUpdate(UserEntity user);
	void deleteUser (UserDTO user);
	boolean passwordCorrect (String pass, String login);
	boolean loginExists (String login);
	boolean emailExists (String email);
}

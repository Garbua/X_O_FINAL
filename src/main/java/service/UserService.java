package service;

import dto.UserDTO;
import entity.UserEntity;

public interface UserService {
	UserEntity getUserByLogin (String login);
	void saveOfUpdate(UserEntity user);
	void deleteUser (UserDTO user);
	boolean passwordCorrect (String pass, String login);
	boolean loginExists (String login);
	boolean emailExists (String email);
}

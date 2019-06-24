package converter;


import dto.ProfileDTO;
import entity.UserEntity;

public interface GenericConverter {

	UserEntity createFrom(ProfileDTO dto);
	ProfileDTO createFrom(UserEntity entity);
}

package converter;

import dto.ProfileDTO;
import entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class ConverterImpl implements GenericConverter{


	@Override
	public UserEntity createFrom(ProfileDTO dto) {
		UserEntity userEntity = new UserEntity();
		userEntity.setLogin(dto.getLogin());
		userEntity.setPassword(dto.getPassword());
		userEntity.setLastName(dto.getFirstName());
		userEntity.setFirstName(dto.getFirstName());
		userEntity.setEmail(dto.getEmail());
//		BeanUtils.copyProperties(dto, userEntity );
		return userEntity;
	}

	@Override
	public ProfileDTO createFrom(UserEntity entity) {
		ProfileDTO profileDTO = new ProfileDTO();
		if (entity != null){
			profileDTO.setId(entity.getId());
			profileDTO.setLogin(entity.getLogin());
			profileDTO.setEmail(entity.getEmail());
			profileDTO.setPassword(entity.getPassword());
			profileDTO.setFirstName(entity.getFirstName());
			profileDTO.setLastName(entity.getLastName());
//			BeanUtils.copyProperties(entity, profileDTO);
		}
		return profileDTO;
	}


}

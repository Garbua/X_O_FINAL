package service.profile.Impl;

import dao.profile.ProfileDAO;
import entity.Profile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.profile.ProfileService;

@Service("profileService")
public class ProfileServiceImpl implements ProfileService {


	@Autowired
	private ProfileDAO profileDAO;

	@Transactional
	@Override
	public Profile getProfileById(Long profile_id) {
		return profileDAO.getProfileById(profile_id);
	}

	@Override
	public Profile createProfile(Profile profile) {
		return profileDAO.createProfile(profile);
	}

	@Transactional
	@Override
	public void updateProfile(Profile profile) {
		profileDAO.updateProfile(profile);

	}

	@Transactional
	@Override
	public void deleteProfile(Profile profile) {
		profileDAO.deleteProfile(profile);

	}
}

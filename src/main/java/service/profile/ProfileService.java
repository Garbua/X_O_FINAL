package service.profile;

import entity.Profile;

public interface ProfileService {

	Profile getProfileById(Long profile_id);
	Profile createProfile(Profile profile);
	void updateProfile(Profile profile);
	void deleteProfile(Profile profile);
}

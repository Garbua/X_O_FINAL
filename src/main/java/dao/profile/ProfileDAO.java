package dao.profile;

import entity.Profile;

public interface ProfileDAO {

	Profile createProfile(Profile profile);

	Profile getProfileById(Long profileId);

	void updateProfile(Profile profile);

	void deleteProfile(Profile profile);
}

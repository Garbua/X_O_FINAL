package dao.profile.impl;

import dao.profile.ProfileDAO;
import entity.Profile;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Repository("profileDAO")
@Transactional
public class ProfileDaoImpl implements ProfileDAO {

	@Resource(name = "sessionFactory")
	public SessionFactory sessionFactory;

	@Override
	public Profile createProfile(Profile profile) {
		sessionFactory.getCurrentSession().save(profile);
		return profile;
	}

	@Override
	public Profile getProfileById(Long profileId) {
		Profile profile = sessionFactory.getCurrentSession().get(Profile.class, profileId);
		return profile;
	}

	@Override
	public void updateProfile(Profile profile) {
		sessionFactory.getCurrentSession().update(profile);

	}

	@Override
	public void deleteProfile(Profile profile) {
		sessionFactory.getCurrentSession().delete(profile);

	}
}

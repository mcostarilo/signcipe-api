package com.st.signservice.security.jwt.service;

import java.util.List;
import com.st.signservice.security.jwt.entity.Profile;

public interface ProfileService {

	List<Profile> getProfiles();
	
	Profile createProfile(Profile profile);
	
	Profile updateProfile(Profile profile);
	
	Profile findById(Integer id);
	
	Profile findByCode(String code);
	
	List<Profile> findByName(String name);
	
	void deleteById(Integer id);

}
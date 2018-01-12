package com.st.signservice.security.jwt.service;

import java.util.List;

import com.st.signservice.security.jwt.entity.ProfileRole;

public interface ProfileRolService {

	List<ProfileRole> getProfileRoles();
	
	ProfileRole findByProfileId(Integer profileId);
	
	ProfileRole findByRolId(Integer rolId);
	
	ProfileRole createProfileRol(ProfileRole profileRole);
	
	void deleteProfileRol(ProfileRole profileRole);

	ProfileRole findByProfileIdAndRolId(Integer profileId, Integer rolId);
}

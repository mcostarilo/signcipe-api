package com.st.signservice.security.jwt.service;

import java.util.List;
import javax.annotation.Resource;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.st.signservice.security.jwt.entity.ProfileRole;
import com.st.signservice.security.jwt.repository.ProfileRolRepository;


@Service
public class ProfileRolServiceImpl implements ProfileRolService {

	@Resource 
	ProfileRolRepository profileRolRepository;
	
	@Override
	public List<ProfileRole> getProfileRoles() {
		return profileRolRepository.findAll();
	}

	@Override
	public ProfileRole findByProfileId(Integer profileId) {
		return profileRolRepository.findByProfileId(profileId);
	}

	@Override
	public ProfileRole findByRolId(Integer rolId) {
		return profileRolRepository.findByRolId(rolId);
	}

	@Override
	@CacheEvict(value = "Role", allEntries = true)
	public ProfileRole createProfileRol(ProfileRole profileRole) {
		return profileRolRepository.save(profileRole);
	}

	@Override
	public void deleteProfileRol(ProfileRole profileRole) {
		profileRolRepository.delete(profileRole.getId());
	}

	@Override
	public ProfileRole findByProfileIdAndRolId(Integer profileId, Integer rolId) {
		return profileRolRepository.findByProfileId(profileId);
	}
	
}
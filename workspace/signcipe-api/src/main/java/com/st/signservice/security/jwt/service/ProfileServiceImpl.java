package com.st.signservice.security.jwt.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.st.signservice.config.ConstantConfig;
import com.st.signservice.security.jwt.entity.Profile;
import com.st.signservice.security.jwt.entity.ProfileRole;
import com.st.signservice.security.jwt.repository.ProfileRepository;

@Service
public class ProfileServiceImpl implements ProfileService {

	@Resource
	ProfileRepository profileRepository;
	
	@Resource
	ProfileRolService profileRolService;
	
	@Resource
	RolService rolService;
	
	@Override
	public List<Profile> getProfiles() {
		return profileRepository.findAll();
	}

	@Override
	public Profile findById(Integer id) {
		return profileRepository.findById(id);
	}

	@Override
	public Profile findByCode(String code) {
		return profileRepository.findByCode(code);
	}

	@Override
	public List<Profile> findByName(String name) {
		return profileRepository.findByName(name);
	}
	
	@Override
	public void deleteById(Integer profileId) {
		Profile profile = profileRepository.findOne(profileId);
		profile.setStatus(ConstantConfig.PROFILE_STATUS_BAJ);
		profileRepository.save(profile);
	}

	@Override 
	@Transactional(rollbackFor = Exception.class)
	public Profile createProfile(Profile profile) {
		List<ProfileRole> profileRoleList = profile.getProfileRoles();
		Profile newProfile = profileRepository.save(profile);
		if (profileRoleList != null) {
			for (ProfileRole profileRole : profileRoleList) {
				if (newProfile != null) {
					profileRole.setProfile(profileRepository.findOne(newProfile.getId()));
				}
				if (profileRole.getRol() != null) {
					profileRole.setRol(rolService.findByName(profileRole.getRol().getName()));
				}
				profile.setProfileRoles(profileRoleList);
				profileRolService.createProfileRol(profileRole);
			}
		}
		return newProfile;
	}	

//	@Override
//	public Profile createProfile(Profile profile) {
//		List<ProfileRole> profileRoleList = profile.getProfileRoles();
//		if (profileRoleList != null) {
//			for (ProfileRole profileRole : profileRoleList) {
//				if (profile != null) {
//					profileRole.setProfile(profileRepository.findOne(profile.getId()));
//				}
//				if (profileRole.getRol() != null) {
//					profileRole.setRol(rolService.findByName(profileRole.getRol().getName()));
//				}
//			}
//		}
//		profile.setProfileRoles(profileRoleList);
//		return profileRepository.save(profile) ;
//	}
	
	@Override
	public Profile updateProfile(Profile profile) {
		Profile profileToSave = profileRepository.findOne(profile.getId());
		profileToSave.setCode(profile.getCode());
		profileToSave.setDescription(profile.getDescription());
		profileToSave.setName(profile.getName());
		profileToSave.setStatus(profile.getStatus());
		
		List<ProfileRole> profileRoleList = profile.getProfileRoles();
		if (profileRoleList != null && !profileRoleList.isEmpty()) {
			// borrar los perfiles - roles actuales
			List<ProfileRole> profileRoleCurrentList = profileToSave.getProfileRoles();
			if (profileRoleCurrentList != null) {
				for (int i = 0; i < profileRoleCurrentList.size(); i++) {
					profileRolService.deleteProfileRol(profileRoleCurrentList.get(i));
				}
			}
			// agregar los perfiles - roles nuevos
			for (ProfileRole profileRole : profileRoleList) {
				if (profile != null) {
					profileRole.setProfile(profileRepository.findOne(profile.getId()));
				}
				if (profileRole.getRol() != null) {
					profileRole.setRol(rolService.findByName(profileRole.getRol().getName()));
				}
				profileRolService.createProfileRol(profileRole);
			}
			
			profileToSave.setProfileRoles(profileRoleList);
		}
		return profileRepository.save(profileToSave);
	}
}
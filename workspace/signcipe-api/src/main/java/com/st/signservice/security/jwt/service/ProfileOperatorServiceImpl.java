package com.st.signservice.security.jwt.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.st.signservice.security.jwt.entity.ProfileOperator;
import com.st.signservice.security.jwt.repository.ProfileOperatorRepository;


@Service
public class ProfileOperatorServiceImpl implements ProfileOperatorService {

	@Resource 
	ProfileOperatorRepository profileOperatorRepository;
	
	@Override
	public List<ProfileOperator> getProfileOperators() {
		return profileOperatorRepository.findAll();
	}

	@Override
	public ProfileOperator findByProfileOperatorId(int operatorId) {
		return profileOperatorRepository.findByOperatorId(operatorId);
	}

	@Override
	public ProfileOperator createProfileOperator(ProfileOperator profileOperator) {
		return profileOperatorRepository.save(profileOperator);
	}

	@Override
	public ProfileOperator findProfileOperatorByOperator(String email, String rolName) {
		return profileOperatorRepository.findProfileOperatorByOperator(email, rolName);
	}
}
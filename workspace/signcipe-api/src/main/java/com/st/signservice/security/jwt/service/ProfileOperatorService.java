package com.st.signservice.security.jwt.service;

import java.util.List;

import com.st.signservice.security.jwt.entity.ProfileOperator;

public interface ProfileOperatorService {

	List<ProfileOperator> getProfileOperators();
	
	ProfileOperator findByProfileOperatorId(int operatorId);
	
	ProfileOperator createProfileOperator(ProfileOperator profileOperator);
	
	ProfileOperator findProfileOperatorByOperator(String email, String rolName);
	
}

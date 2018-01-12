package com.st.signservice.remote.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.st.signservice.service.ConfigService;
import com.st.signservice.remote.dto.PersonRemoteDto;

@Service
public class PersonRemoteServiceImpl implements PersonRemoteService {

	@Resource 
	ConfigService configService;
	
	@Override
	public PersonRemoteDto getPersonRemoteByDocNumberAndSex(String docNumber, String sex) {
		String url = configService.findByCode("cipeWSUrl").getValue() + "/CipeServiceWS/cipe/person/" + docNumber + "/" + sex;
		PersonRemoteDto person = new RestTemplate().getForObject(url, PersonRemoteDto.class);
		return person;
	}
}
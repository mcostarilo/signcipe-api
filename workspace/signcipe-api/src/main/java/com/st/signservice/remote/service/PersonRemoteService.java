package com.st.signservice.remote.service;

import com.st.signservice.remote.dto.PersonRemoteDto;

public interface PersonRemoteService {
	PersonRemoteDto getPersonRemoteByDocNumberAndSex(String docNumber, String sex);

}
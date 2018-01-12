package com.st.signservice.service;

import java.util.List;

import com.st.signservice.entity.Config;

public interface ConfigService {
	
	List<Config> getConfig();
	
	Config findByCode(String code);
	
	void deleteByCode(String code);

}

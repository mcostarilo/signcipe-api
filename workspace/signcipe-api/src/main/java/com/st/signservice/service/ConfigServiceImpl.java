package com.st.signservice.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import com.st.signservice.entity.Config;
import com.st.signservice.repository.ConfigRepository;

@Service
public class ConfigServiceImpl implements ConfigService {

	@Resource 
	ConfigRepository configRepository;

	@Override
	public List<Config> getConfig() {
		return configRepository.findAll();
	}

	@Override
	public Config findByCode(String code) {
		return configRepository.findByCode(code);
	}

	@Override
	public void deleteByCode(String code) {
		// TODO Auto-generated method stub
	}
}
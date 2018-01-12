package com.st.signservice.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

import com.st.signservice.entity.Config;
import com.st.signservice.service.ConfigService;
import javax.annotation.Resource;

@ConfigurationProperties("storage")
public class StorageProperties {

	@Resource
	ConfigService configService;

	private String location = "C:/archivos/hcd/pdf/";

	public String getLocation() {

		Config config = configService.findByCode("pathFile");

		if (config != null) {
			String locLoation = config.getValue();
			this.location = locLoation;
		}
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
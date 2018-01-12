package com.st.signservice.entity;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="config")
@NamedQuery(name="Config.findAllConfig", query="SELECT c FROM Config c")
public class Config implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	
	@Column(name = "codigo")
	private String code;
	
	@Column(name = "valor")
	private String value;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


}
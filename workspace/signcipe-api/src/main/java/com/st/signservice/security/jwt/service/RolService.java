package com.st.signservice.security.jwt.service;

import java.util.List;

import com.st.signservice.security.jwt.entity.Rol;

public interface RolService {

	List<Rol> getRoles();
	
	Rol createRol(Rol rol);
	
	Rol findById(Integer rolId);
	
	Rol findByName(String name);
	
	void deleteById(Integer rolId);
	
}
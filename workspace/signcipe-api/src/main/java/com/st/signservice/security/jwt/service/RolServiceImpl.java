package com.st.signservice.security.jwt.service;

import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;

import com.st.signservice.security.jwt.entity.Rol;
import com.st.signservice.security.jwt.repository.RolRepository;


@Service
public class RolServiceImpl implements RolService {

	@Resource 
	RolRepository rolRepository;
	
	@Override
	public List<Rol> getRoles() {
		return rolRepository.findAll();
	}

	@Override
	public Rol findById(Integer rolId) {
		return rolRepository.findById(rolId);
	}

	@Override
	public Rol findByName(String name) {
		return rolRepository.findByName(name);
	}

	@Override
	public void deleteById(Integer id) {
		rolRepository.delete(id);
	}

	@Override
	public Rol createRol(Rol rol) {
		return rolRepository.save(rol); 
	}
}
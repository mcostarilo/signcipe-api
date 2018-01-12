package com.st.signservice.security.jwt.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.st.signservice.security.jwt.entity.Rol;

@Repository
public interface RolRepository extends JpaRepository<Rol, Integer> {

	List<Rol> findAll();

	Rol findById(int id);
	
	@Query("SELECT r FROM Rol r WHERE r.name = :name")
	Rol findByName(@Param("name") String name);
	
}
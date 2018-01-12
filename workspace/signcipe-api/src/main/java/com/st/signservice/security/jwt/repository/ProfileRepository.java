package com.st.signservice.security.jwt.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.st.signservice.security.jwt.entity.Profile;

@Repository
public interface ProfileRepository extends JpaRepository<Profile, Integer> {

	@Query("SELECT p FROM Profile p WHERE p.status = 'ACT'")
	List<Profile> findAll();

	Profile findById(Integer id);
	
	@Query("SELECT p FROM Profile p WHERE p.code = :code AND p.status = 'ACT'")
	Profile findByCode(@Param("code") String code);

	@Query("SELECT p FROM Profile p WHERE p.name like concat('%', :name ,'%' ) AND p.status = 'ACT'")
	List<Profile> findByName(@Param("name") String name);
	
}
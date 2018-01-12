package com.st.signservice.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.st.signservice.security.jwt.entity.ProfileRole;

@Repository
public interface ProfileRolRepository extends JpaRepository<ProfileRole, Integer> {

	ProfileRole findByRolId(int operatorId);
	
	ProfileRole findByProfileId(int profileId);
	
	@Query("SELECT pr FROM ProfileRole pr WHERE pr.profile.id = :profileId and pr.rol.id = :rolId")
	ProfileRole findByProfileIdAndRolId(@Param("profileId") Integer profileId, @Param("rolId") Integer rolId);
	
}

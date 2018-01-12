package com.st.signservice.security.jwt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.st.signservice.security.jwt.entity.ProfileOperator;

@Repository
public interface ProfileOperatorRepository extends JpaRepository<ProfileOperator, Long> {

	ProfileOperator findByOperatorId(int operatorId);
	
	int findByProfileId(int profileId);
	
    @Query("SELECT po FROM ProfileOperator po JOIN po.operator op JOIN po.profile pe JOIN pe.profileRoles pr JOIN pr.rol rol WHERE rol.name = :rolName AND op.email = :email AND rol.status='ACT'" )
    ProfileOperator findProfileOperatorByOperator(@Param("email") String email,@Param("rolName") String rolName);
    
}

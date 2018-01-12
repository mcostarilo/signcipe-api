package com.st.signservice.security.jwt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.st.signservice.security.jwt.entity.OperatorRole;

@Repository
public interface OperatorRoleRepository extends JpaRepository<OperatorRole, Long> {

	int findByOperatorId(int operatorId);

	@Query("SELECT o FROM OperatorRole o WHERE o.operator.id = :operatorId")
	List<OperatorRole> findOperatorRoleByOperatorId(@Param("operatorId") Integer operatorId);

    @Query("SELECT opr FROM OperatorRole opr JOIN opr.operator op JOIN opr.rol r WHERE opr.rol.name = :rolName AND op.email = :email AND r.status='ACT'" )
	OperatorRole findOperatorRoleByOperator(@Param("email") String email,@Param("rolName") String rolName);
	
}

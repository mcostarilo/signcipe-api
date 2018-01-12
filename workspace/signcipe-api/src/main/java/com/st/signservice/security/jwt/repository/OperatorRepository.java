package com.st.signservice.security.jwt.repository;

import java.util.List;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.st.signservice.security.jwt.entity.Operator;

@Repository
public interface OperatorRepository extends JpaRepository<Operator, Integer> {


//	@Query("SELECT distinct o FROM Operator o JOIN o.healthCenterHasOperator hho JOIN o.person per "
//			+ "WHERE hho.healthCenter.id = :healthCenterId "
//			+ "AND (upper(per.firstName) LIKE concat('%',:search,'%') "
//			+ "OR upper(per.lastName) LIKE concat('%',:search,'%') "
//			+ "OR per.docNumber LIKE concat('%',:search ,'%')) ")
//	Page<Operator> findAll(@Param("search") String search, Pageable pageable, @Param("healthCenterId") Integer healthCenterId);

	@Query("SELECT distinct o FROM Operator o JOIN o.person per "
	+ "WHERE (upper(per.firstName) LIKE concat('%',:search,'%') "
	+ "OR upper(per.lastName) LIKE concat('%',:search,'%') "
	+ "OR per.docNumber LIKE concat('%',:search ,'%')) ")
	Page<Operator> findAll(@Param("search") String search, Pageable pageable);
	
	@Query("SELECT o FROM Operator o WHERE o.person.id = :personId")
	List<Operator> findByPersonId(@Param("personId") Integer personId);

	@Query("SELECT o FROM Operator o WHERE o.userName = :userName")
	List<Operator> findByUserName(@Param("userName") String userName);
	
	@Override
    @Query("SELECT o FROM Operator o WHERE o.id = :id")
    @Cacheable("Operator")
	public Operator findOne(@Param("id") Integer id);
	
	@Query("SELECT o FROM Operator o WHERE o.systemOwner is not null AND o.systemOwner = 1")
	List<Operator> findSystemOwners();

}

package com.st.signservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.st.signservice.entity.Person;

@Repository
public interface PersonRepository extends JpaRepository<Person, Integer> {

	@Query("SELECT p FROM Person p where p.id = :id")
	Person findById(@Param("id") Integer id);
	
	@Query("SELECT p FROM Person p where p.email = :email")
	Person findPersonByEmail(@Param("email") String email);
	
	@Query("SELECT p FROM Person p where p.documentType.id = :documentTypeId and p.docNumber = :docNumber")
	Person findPersonByDocument(@Param("documentTypeId") Integer documentTypeId, @Param("docNumber") String docNumber);
	
	@Query("SELECT p FROM Person p where p.docNumber = :docNumber and p.sex = :sex")
	Person findPersonByDocNumberAndSex(@Param("docNumber") String docNumber, @Param("sex") String sex);

	@Query("SELECT DISTINCT per FROM Person per "
			+ "WHERE upper(per.firstName) LIKE concat('%',:search,'%') "
			+ "OR upper(per.lastName) LIKE concat('%',:search,'%') "
			+ "OR per.docNumber LIKE concat('%',:search ,'%') ")
	Page<Person> findFilteredPersons(@Param("search") String search, Pageable pageable);

}


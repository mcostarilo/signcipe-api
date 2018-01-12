package com.st.signservice.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.st.signservice.entity.Document;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Integer> {

	Document findById(Integer documentId);
	
	List<Document> findAllDocument();
	
	@Query("SELECT d FROM Document d where d.pathName = :pathName")
	Document findByPathName(@Param("pathName") String pathName);
	
}
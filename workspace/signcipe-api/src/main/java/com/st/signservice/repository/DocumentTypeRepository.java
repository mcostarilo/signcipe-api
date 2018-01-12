package com.st.signservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.st.signservice.entity.DocumentType;

@Repository
public interface DocumentTypeRepository extends JpaRepository<DocumentType, Integer> {

	DocumentType findById(Integer documentTypeId);
	
	//List<DocumentType> findAllDocumentType();

	@Query("SELECT d FROM DocumentType d where d.code = :documentTypeCode")
	DocumentType findByCode(@Param("documentTypeCode") String documentTypeCode);
	
}

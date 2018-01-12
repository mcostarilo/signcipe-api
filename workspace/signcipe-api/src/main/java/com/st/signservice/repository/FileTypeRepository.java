package com.st.signservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.st.signservice.entity.FileType;

@Repository
public interface FileTypeRepository extends JpaRepository<FileType, Integer> {
	@Query("SELECT f FROM FileType f where f.code = :code")
	FileType findByCode(@Param("code") String code);
}
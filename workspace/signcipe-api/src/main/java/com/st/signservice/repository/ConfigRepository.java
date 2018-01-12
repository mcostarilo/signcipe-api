package com.st.signservice.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.st.signservice.entity.Config;

@Repository
public interface ConfigRepository extends JpaRepository<Config, String> {

	@Query("SELECT c FROM Config c where c.code = :code")
	Config findByCode(@Param("code") String code);

	List<Config> findAllConfig();
}

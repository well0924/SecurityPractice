package com.spring.security.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.security.domain.entity.LoginEntity;

public interface LoginRepository extends JpaRepository<LoginEntity, Long>{
	Optional<LoginEntity> findByEmail(String email);
}

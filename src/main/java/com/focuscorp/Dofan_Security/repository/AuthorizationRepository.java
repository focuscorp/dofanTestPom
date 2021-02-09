package com.focuscorp.Dofan_Security.repository;

import com.focuscorp.Dofan_Security.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorizationRepository extends JpaRepository<Authorization, Long>{
}
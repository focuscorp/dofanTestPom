package com.focuscorp.Dofan_Security.repository;

import com.focuscorp.Dofan_Security.model.Authorization;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorizationRepository extends MongoRepository<Authorization, Long>{
    Optional<Authorization> findByLabel(String label);
}
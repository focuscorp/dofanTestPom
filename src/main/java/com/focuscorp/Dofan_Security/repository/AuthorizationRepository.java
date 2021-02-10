package com.focuscorp.Dofan_Security.repository;

import com.focuscorp.Dofan_Security.model.Authorization;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorizationRepository extends MongoRepository<Authorization, Long>{

}
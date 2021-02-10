package com.focuscorp.Dofan_Security.repository;

import com.focuscorp.Dofan_Security.model.Authorization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthorizationRepository extends MongoRepository<Authorization, Long>{

 //   Could not safely identify store assignment for repository candidate interface com.focuscorp.Dofan_Security.repository.AuthorizationRepository. If you want this repository to be a MongoDB repository, consider annotating your entities with one of these annotations: org.springframework.data.mongodb.core.mapping.Document (preferred), or consider extending one of the following types with your repository: org.springframework.data.mongodb.repository.MongoRepository.
}
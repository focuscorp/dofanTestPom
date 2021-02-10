package com.focuscorp.Dofan_Security.repository;

import com.focuscorp.Dofan_Security.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RoleRepository extends MongoRepository<Role, Long>{
}
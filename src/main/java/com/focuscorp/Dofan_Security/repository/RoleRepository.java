package com.focuscorp.Dofan_Security.repository;

import com.focuscorp.Dofan_Security.model.ERole;
import com.focuscorp.Dofan_Security.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, Long>{
    Optional<Role> findByName(ERole name);
}
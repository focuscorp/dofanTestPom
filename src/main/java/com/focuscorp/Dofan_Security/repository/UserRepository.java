package com.focuscorp.Dofan_Security.repository;

import com.focuscorp.Dofan_Security.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, Long>{
    //public User findByName(String name);

    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}
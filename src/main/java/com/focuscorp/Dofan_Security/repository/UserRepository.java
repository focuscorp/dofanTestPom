package com.focuscorp.Dofan_Security.repository;

import com.focuscorp.Dofan_Security.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, Long>{
}
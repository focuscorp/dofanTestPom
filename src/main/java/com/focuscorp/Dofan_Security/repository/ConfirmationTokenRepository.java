package com.focuscorp.Dofan_Security.repository;

import com.focuscorp.Dofan_Security.model.ConfirmationToken;

import org.springframework.data.repository.CrudRepository;

public interface ConfirmationTokenRepository extends CrudRepository<ConfirmationToken, String> {
        ConfirmationToken findByConfirmationToken(String confirmationToken);
}

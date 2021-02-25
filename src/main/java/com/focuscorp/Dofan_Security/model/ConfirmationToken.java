package com.focuscorp.Dofan_Security.model;

import java.util.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "confirmation_token")
public class ConfirmationToken {

    @Id
    private String tokenid;

    private String confirmationToken;

    private Date createdDate;

    private User user;

    public ConfirmationToken(User user) {
        this.setUser(user);
        setCreatedDate(new Date());
        this.setConfirmationToken(UUID.randomUUID().toString());
    }

    // getters and setters
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public String getConfirmationToken() {
        return confirmationToken;
    }

    public void setConfirmationToken(String confirmationToken) {
        this.confirmationToken = confirmationToken;
    }
    
    public String getTokenId() {
        return tokenid;
    }

    public void setTokenId(String id) {
        this.tokenid = id;
    }
}

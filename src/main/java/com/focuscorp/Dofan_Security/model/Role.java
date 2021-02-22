package com.focuscorp.Dofan_Security.model;


import java.util.Set;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "roles")
public class Role {

    @Id
    private String id;

    private ERole name;

//    @OneToMany(mappedBy = "userRole", cascade = CascadeType.ALL)
/*    @DBRef
    private Set<User> users;*/

    public Role() {
    }

    public Role(ERole name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ERole getName() {
        return name;
    }

    public void setName(ERole name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Role{" +
                "name=" + name +
                '}';
    }
}
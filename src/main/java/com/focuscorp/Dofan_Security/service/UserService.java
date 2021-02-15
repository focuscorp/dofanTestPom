package com.focuscorp.Dofan_Security.service;

import java.util.List;
import com.focuscorp.Dofan_Security.model.User;

public interface UserService {

    public List<User> findAllUsers();

    public List<User> searchUsers(String keyword);

    public User findByUsername(String username);

    public void createUser(User user);

    public void updateUser(User user);

    public void deleteUser(Long id);
    }

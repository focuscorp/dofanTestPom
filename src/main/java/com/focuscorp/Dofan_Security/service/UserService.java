package com.focuscorp.Dofan_Security.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.focuscorp.Dofan_Security.model.User;
import com.focuscorp.Dofan_Security.repository.UserRepository;

@Service
public class UserService {
    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void addUser(User user)


    {

        userRepository.save(user);
        System.out.println("mchetttt w tzed el user");
    }

    public void deleteById(String userId)
    {
        userRepository.deleteById(userId);
        System.out.println("********* Success Delete **************");
    }

    public User findUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    public Optional<User> findById(String id){

        return userRepository.findById(id);
    }
    public Iterable<User> findAllUsers() {

        System.out.println("tt les users");
        return userRepository.findAll();

    }
}

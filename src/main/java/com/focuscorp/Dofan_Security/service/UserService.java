package com.focuscorp.Dofan_Security.service;

import com.focuscorp.Dofan_Security.exception.UserNotFoundException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.focuscorp.Dofan_Security.model.User;
import com.focuscorp.Dofan_Security.repository.UserRepository;

@Service
public class UserService {

    private static final Logger logger = Logger.getLogger(UserService.class);

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    ////////////////////// Create/Save user //////////////////////////////////////////////////
    public void addUser(User user) throws UserNotFoundException {

        logger.info("UserService.addUser() execution started");

        if (!userRepository.existsById(user.getEmail())) {
            logger.info("UserService.addUser() execution finished");
            userRepository.save(user);
        } else {
            logger.error("User Email: " + user.getEmail() + " Already exists");
            throw new UserNotFoundException("User with Email: " + user.getEmail() + " Already exists");
        }

    }

    ////////////////////// Delete user //////////////////////////////////////////////////
    public void deleteById(String userId)
    {
        logger.info("UserService.deleteById() execution started");
        userRepository.deleteById(userId);
        logger.info("UserService.saveEditedUser() execution finished");
    }

    ////////////////////// findUserByEmail //////////////////////////////////////////////////
    public User findUserByEmail(String email)
    {
        return userRepository.findByEmail(email);
    }

    ////////////////////// findById ////////////////////////////////////////////////////////
    public User findById(String id){

        return (User)userRepository.findById(id).get();
    }

    ////////////////////// findAllUsers ////////////////////////////////////////////////////////
    public Iterable<User> findAllUsers() {

        logger.info("*********** UserService.findAllUsers() execution ***********");
        return userRepository.findAll();
    }
}

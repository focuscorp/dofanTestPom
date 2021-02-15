package com.focuscorp.Dofan_Security.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.focuscorp.Dofan_Security.model.User;
import com.focuscorp.Dofan_Security.exception.NotFoundException;
import com.focuscorp.Dofan_Security.repository.UserRepository;
import com.focuscorp.Dofan_Security.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<User> findAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<User> searchUsers(String keyword) {
        return null;
    }

   /* @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public List<User> searchUsers(String keyword) {
        if (keyword != null) {
            return userRepository.search(keyword);
        }
        return userRepository.findAll();
    }*/

    @Override
    public User findByUsername(String username) {
        return null;
    }

   /* @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    @Override
    public Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("Book not found with ID %d", id)));
    }*/

    @Override
    public void createUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void updateUser(User user) {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {

    }

    /*@Override
    public void deleteUser(Long id) {
        final User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(String.format("User not found with ID %d", id)));

        userRepository.deleteById(user.getId());
    }*/

}

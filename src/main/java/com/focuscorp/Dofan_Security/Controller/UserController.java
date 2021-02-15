package com.focuscorp.Dofan_Security.Controller;

import com.focuscorp.Dofan_Security.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.data.repository.query.Param;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.focuscorp.Dofan_Security.model.User;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/users")
    public List<User> findAllUsers(Model model) {
        final List<User> users = userService.findAllUsers();

        model.addAttribute("users", users);
        return users ;
    }

    @RequestMapping("/searchUser")
    public String searchUser(@Param("keyword") String keyword, Model model) {
        final List<User> users = userService.searchUsers(keyword);

        model.addAttribute("users", users);
        model.addAttribute("keyword", keyword);
        return "list-users";
    }

    @RequestMapping("/user/{username}")
    public String findByUsername(@PathVariable("username") String username, Model model) {
        final User user = userService.findByUsername(username);

        model.addAttribute("user", user);
        return "list-user";
    }

    /*@RequestMapping("/searchUser")
    public String searchUser(@Param("username") String username, Model model) {
        final List<User> users = userService.searchUsers(username);

        model.addAttribute("users", users);
        model.addAttribute("username", username);
        return "list-users";
    }*/

    @RequestMapping("/add-user")
    public String createUser(User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            return "add-user";
        }

        userService.createUser(user);
        model.addAttribute("user", userService.findAllUsers());
        return "redirect:/users";
    }

    /*@RequestMapping("/update-user/{id}")
    public String updateUser(@PathVariable("id") Long id, User user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            user.setId(id);
            return "update-user";
        }

        userService.updateUser(user);
        model.addAttribute("user", userService.findAllUsers());
        return "redirect:/users";
    }*/

    @RequestMapping("/remove-user/{id}")
    public String deleteUser(@PathVariable("id") Long id, Model model) {
        userService.deleteUser(id);

        model.addAttribute("user", userService.findAllUsers());
        return "redirect:/users";
    }

}
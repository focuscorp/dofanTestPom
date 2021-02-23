package com.focuscorp.Dofan_Security.Controller;

import com.focuscorp.Dofan_Security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

import com.focuscorp.Dofan_Security.model.User;

import java.util.List;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    public UserController() {
    }

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public String newUser(Model model) {
        model.addAttribute("newuser", new User());
        List<User> list = (List<User>)userService.findAllUsers( );
        model.addAttribute("users",list);
        return "/users";
    }


    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("newuser") User user, Model model) {
        /*if (result.hasErrors()) {
            return "add-user";
        }*/

        userService.addUser(user);
        //model.addAttribute("user", userService.findAllUsers());
        return "redirect:/users";
    }

    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable("id") String userId, Model model) {
        System.out.println("Deleeeeeeeeeeeeeeeeeeeeeeeeeeteeeeeee");

        System.out.println(userId);
        userService.deleteById(userId);
        return "redirect:/users";
    }


    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String  editUser(@PathVariable("id") String userId, Model model){
        System.out.println("EDIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIIITtttttt");
        User user1=   (User)userService.findById(userId).get();
        model.addAttribute("EditableUser",user1 );
        System.out.println(model.getAttribute("EditableUser"));
        System.out.println(userService.findById(userId));
        System.out.println(user1);
        return "/edit_user";
    }

    @RequestMapping(value = "/edit", method = RequestMethod.POST)
    public String saveEditedUser(@ModelAttribute("EditableUser") User user) {

        userService.addUser(user);
        System.out.println(" iam here ");
        return "redirect:/users";
    }

}
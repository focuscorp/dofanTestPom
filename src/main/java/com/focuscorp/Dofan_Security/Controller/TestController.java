package com.focuscorp.Dofan_Security.Controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/*@RequestMapping("/test")
@Controller*/
@RestController
@RequestMapping("/api/test")
public class TestController {

    //@RequestMapping(value = "/all", method = RequestMethod.GET)
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }


    // @RequestMapping(value = "/user", method = RequestMethod.GET)
    @GetMapping("/user")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public String userAccess() {
        return "User Content.";
    }


    // @RequestMapping(value = "/mod", method = RequestMethod.GET)
    @GetMapping("/mod")
    @PreAuthorize("hasRole('MODERATOR')")
    public String moderatorAccess() {
        return "Moderator Board.";
    }


    //@RequestMapping(value = "/admin", method = RequestMethod.GET)
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminAccess() {
        return "Admin Board.";
    }
}

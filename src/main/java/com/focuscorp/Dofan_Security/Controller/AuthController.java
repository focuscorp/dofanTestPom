package com.focuscorp.Dofan_Security.Controller;

import com.focuscorp.Dofan_Security.model.ERole;
import com.focuscorp.Dofan_Security.model.Role;
import com.focuscorp.Dofan_Security.model.User;
import com.focuscorp.Dofan_Security.payload.request.LoginRequest;
import com.focuscorp.Dofan_Security.payload.request.SignupRequest;
import com.focuscorp.Dofan_Security.payload.response.JwtResponse;
import com.focuscorp.Dofan_Security.payload.response.MessageResponse;
import com.focuscorp.Dofan_Security.repository.RoleRepository;
import com.focuscorp.Dofan_Security.repository.UserRepository;
import com.focuscorp.Dofan_Security.security.jwt.JwtUtils;
import com.focuscorp.Dofan_Security.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


//@RequestMapping("/")
@Controller
/*@RestController
@RequestMapping("/api/auth")*/
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

   // @PostMapping("/signin")
   @RequestMapping(value = "/dashboard", method = RequestMethod.POST)
    public String authenticateUser(@Valid LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
        System.out.println("dashboard ppst : " + authentication);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        /*return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));*/
        return "dashboard";
    }

    @RequestMapping(value = "/signup", method = RequestMethod.GET)
    public String signupGet(Model model) {
        User user = new User();
        model.addAttribute("registereduser", user);
        System.out.println("signup get : "+user.toString());
        return "signup";
    }
   // @PostMapping("/signup")
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String registerUser(@Valid SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            //return ResponseEntity.badRequest().body(new MessageResponse("Error: Username is already taken!"));
            System.out.println("Error: Username is already taken!");
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            //return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already in use!"));
            System.out.println("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User(signUpRequest.getUsername(),
                             signUpRequest.getEmail(),
                             encoder.encode(signUpRequest.getPassword()));

        // strRoles will return null if the roles is not passed as parameter
        Set<String> strRoles = signUpRequest.getRoles();
        System.out.println("signup post : strRoles as param : "+strRoles);
        Set<Role> roles = new HashSet<>();

        /*
        * commented this code, it can be reused later on user creation
        *
        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                      .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "mod":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
                         .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                        .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        */

        //Register page will create only admin user
        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
        roles.add(adminRole);
        System.out.println("signup post : user with role admin : "+roles);

        user.setRoles(roles);
        userRepository.save(user);

        //return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
        return "index";
    }
}

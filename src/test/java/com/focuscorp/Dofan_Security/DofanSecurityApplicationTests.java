package com.focuscorp.Dofan_Security;

import com.focuscorp.Dofan_Security.model.Role;
import com.focuscorp.Dofan_Security.model.ERole;
import com.focuscorp.Dofan_Security.repository.RoleRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.focuscorp.Dofan_Security.model.User;
import com.focuscorp.Dofan_Security.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@SpringBootTest
class DofanSecurityApplicationTests {

	@Autowired
	private UserRepository repository;

	@Autowired
	private RoleRepository roleRepo;

	@Autowired
	PasswordEncoder encoder;

	@Test
	void contextLoads() {
		//repository.deleteAll();


		//add roles
		//roleRepo.save(new Role(ERole.ROLE_USER));
		/*roleRepo.save(new Role(ERole.ROLE_MODERATOR));
		/*roleRepo.save(new Role(ERole.ROLE_ADMIN));
		System.out.println("Roles added successfully");*/

		// save a couple of customers

		/*Set<Role> roles = new HashSet<>();


		Role adminRole = roleRepo.findByName(ERole.ROLE_ADMIN);
		roles.add(adminRole);


        User user = new User("mohta","mohta@gmail.com", encoder.encode("test12345"));
		user.setRoles(roles);
		repository.save(user);
		System.out.println("user saved youpppppyyyyyyyyyyyy");*/

		// fetch all customers
        /*System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User user : repository.findAll()) {
            System.out.println(user);
        }
        System.out.println();*/

		// fetch an individual customer
		/*System.out.println("Customer found with findByName('Bayrem'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByUsername("Bayrem"));*/
	}

}

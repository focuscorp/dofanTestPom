package com.focuscorp.Dofan_Security;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.beans.factory.annotation.Autowired;
import com.focuscorp.Dofan_Security.model.User;
import com.focuscorp.Dofan_Security.repository.UserRepository;

@SpringBootTest
class DofanSecurityApplicationTests {

	@Autowired
	private UserRepository repository;

	@Test
	void contextLoads() {
		//repository.deleteAll();

		// save a couple of customers
		repository.save(new User("Bayrem"));
		repository.save(new User("Taym"));

		// fetch all customers
        System.out.println("Customers found with findAll():");
        System.out.println("-------------------------------");
        for (User user : repository.findAll()) {
            System.out.println(user);
        }
        System.out.println();

		// fetch an individual customer
		System.out.println("Customer found with findByName('Bayrem'):");
		System.out.println("--------------------------------");
		System.out.println(repository.findByName("Bayrem"));
	}


}

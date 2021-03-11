package com.focuscorp.Dofan_Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.apache.log4j.Logger;

//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class })
@SpringBootApplication
public class DofanSecurityApplication {

    private static final Logger logger = Logger.getLogger(DofanSecurityApplication.class);
    
	public static void main(String[] args) {

        logger.info("*********** DofanSecurityApplication class execution started ***********");

		SpringApplication.run(DofanSecurityApplication.class, args);

        logger.info("*********** DofanSecurityApplication execution finished ***********");

	}

}

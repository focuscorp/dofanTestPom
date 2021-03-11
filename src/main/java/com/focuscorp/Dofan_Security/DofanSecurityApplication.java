package com.focuscorp.Dofan_Security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;


//@SpringBootApplication(exclude = { SecurityAutoConfiguration.class, DataSourceAutoConfiguration.class })
@SpringBootApplication
public class DofanSecurityApplication {
	public static Logger log = Logger.getLogger(DofanSecurityApplication.class);
	public static void main(String[] args) {
		//SpringApplication.run(DofanSecurityApplication.class, args);

		//--- Print Msg ---
        log.debug("From debug");
        log.info("From Info");
        log.warn("From warn");
        log.error("From error");
        log.fatal("From fatal");
	}

}

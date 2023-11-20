package com.odk3.vetcare;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
public class VetcareApplication {

	public static void main(String[] args) {
		SpringApplication.run(VetcareApplication.class, args);
	}

}

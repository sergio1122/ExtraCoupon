package com.ExtraCoupon.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@SpringBootApplication
@CrossOrigin("*")
public class ConfigurationGrailApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationGrailApplication.class, args);
	}

}

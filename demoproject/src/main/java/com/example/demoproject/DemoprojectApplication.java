package com.example.demoproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class DemoprojectApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(DemoprojectApplication.class, args);
	}
	
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	       return application.sources(DemoprojectApplication.class);
	    }
}

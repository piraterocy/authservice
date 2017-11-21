package com.kb.auth.jwt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
@EnableEurekaClient
@SpringBootApplication
//@ServletComponentScan
public class AuthserviceApplication {

	public static void main(String[] args) {
		//test
		SpringApplication.run(AuthserviceApplication.class, args);
	}
}

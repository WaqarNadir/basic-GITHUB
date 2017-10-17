package com.erp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.erp.repo.OrderDetailRepo;



@Configuration
@EntityScan(basePackages = {"com.erp.classes"})
@EnableJpaRepositories(basePackages = {"com.erp.classes", "com.erp.repo","com.erp.controllers","com.erp.services"})
@ComponentScan(basePackages = {"com.erp.classes", "com.erp.repo","com.erp.controllers","com.erp.services"})
@SpringBootApplication(scanBasePackages={"com.erp.classes"})
public class DemoApplication {

	@Autowired
	static
	OrderDetailRepo repo;
	
	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	
			}
}

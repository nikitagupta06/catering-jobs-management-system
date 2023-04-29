package com.nikitagupta06.cateringmanagementsystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class CateringJobsManagementSystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(CateringJobsManagementSystemApplication.class, args);
	}

}

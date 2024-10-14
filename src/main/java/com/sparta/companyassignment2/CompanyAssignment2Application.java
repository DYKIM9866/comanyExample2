package com.sparta.companyassignment2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class CompanyAssignment2Application {

    public static void main(String[] args) {
        SpringApplication.run(CompanyAssignment2Application.class, args);
    }

}

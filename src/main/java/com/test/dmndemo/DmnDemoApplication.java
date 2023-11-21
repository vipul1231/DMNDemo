package com.test.dmndemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "com.test")
@EnableJpaRepositories(basePackages = "com.test")
public class DmnDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DmnDemoApplication.class, args);
    }

}

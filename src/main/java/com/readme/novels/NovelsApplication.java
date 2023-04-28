package com.readme.novels;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class NovelsApplication {

    public static void main(String[] args) {
        SpringApplication.run(NovelsApplication.class, args);
    }

}

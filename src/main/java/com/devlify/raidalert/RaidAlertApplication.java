package com.devlify.raidalert;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RaidAlertApplication {

    public static void main(String[] args) {
        SpringApplication.run(RaidAlertApplication.class, args);
    }

}

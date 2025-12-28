package com.pharmacoldtrack.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
public class PharmaColdTrackApplication {

    public static void main(String[] args) {
        SpringApplication.run(PharmaColdTrackApplication.class, args);
    }

}

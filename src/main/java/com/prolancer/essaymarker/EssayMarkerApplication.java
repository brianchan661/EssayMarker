package com.prolancer.essaymarker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDateTime;

@SpringBootApplication
public class EssayMarkerApplication {
    public static void main(String[] args) {
        System.out.print(LocalDateTime.now());
        SpringApplication.run(EssayMarkerApplication.class, args);
    }
}

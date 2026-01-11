package com.example.medapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;

@SpringBootApplication
@EnableJms
public class MedappApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedappApplication.class, args);
    }

}
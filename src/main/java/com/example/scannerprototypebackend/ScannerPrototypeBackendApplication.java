package com.example.scannerprototypebackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.security.autoconfigure.SecurityAutoConfiguration;


//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
@SpringBootApplication
public class ScannerPrototypeBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(ScannerPrototypeBackendApplication.class, args);
    }

}

package com.dunin.medicalvaccinatesystem;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.dunin.medicalvaccinatesystem")
public class MedicalVaccinateSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(MedicalVaccinateSystemApplication.class, args);
    }

}

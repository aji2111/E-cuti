package com.ecuti.permission_service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients; // <-- Wajib di-import

@SpringBootApplication
@EnableFeignClients // <-- Wajib ditambahkan
public class PermissionServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PermissionServiceApplication.class, args);
    }

}
package com.ecuti.employee_service.config;

import com.ecuti.employee_service.entity.Department;
import com.ecuti.employee_service.repository.DepartmentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initData(DepartmentRepository departmentRepository) {
        return args -> {
            if (departmentRepository.count() == 0) {
                departmentRepository.save(new Department(null, "IT-001", "IT Development"));
                departmentRepository.save(new Department(null, "HR-001", "Human Resources"));
                departmentRepository.save(new Department(null, "FIN-001", "Finance"));
            }
        };
    }
}
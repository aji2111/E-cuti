package com.ecuti.employee_service.repository;

import com.ecuti.employee_service.entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<Employee> findByNip(String nip);
    Optional<Employee> findByPublicId(String publicId);
}
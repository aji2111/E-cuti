package com.ecuti.permission_service.repository;

import com.ecuti.permission_service.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    Optional<Permission> findByPublicId(String publicId);
    List<Permission> findByEmployeePublicId(String employeePublicId);
}
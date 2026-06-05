package com.ecuti.permission_service.client;

import com.ecuti.permission_service.dto.response.EmployeeResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "employee-service", url = "http://localhost:8081")
public interface EmployeeClient {
    // Note the added "/public" in the path
    @GetMapping("/api/employees/public/{publicId}")
    EmployeeResponse getEmployeeByPublicId(@PathVariable("publicId") String publicId);
}
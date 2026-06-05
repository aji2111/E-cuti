package com.ecuti.employee_service.service;

import com.ecuti.employee_service.dto.request.EmployeeRequest;
import com.ecuti.employee_service.dto.response.EmployeeResponse;
import java.util.List;

public interface EmployeeService {
    EmployeeResponse createEmployee(EmployeeRequest request);
    List<EmployeeResponse> getAllEmployees();
    EmployeeResponse getEmployeeByNip(String nip);
    EmployeeResponse getEmployeeByPublicId(String publicId);
}
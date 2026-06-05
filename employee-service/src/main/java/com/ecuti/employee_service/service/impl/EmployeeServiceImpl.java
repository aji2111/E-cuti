package com.ecuti.employee_service.service.impl;

import com.ecuti.employee_service.dto.request.EmployeeRequest;
import com.ecuti.employee_service.dto.response.EmployeeResponse;
import com.ecuti.employee_service.entity.Department;
import com.ecuti.employee_service.entity.Employee;
import com.ecuti.employee_service.repository.DepartmentRepository;
import com.ecuti.employee_service.repository.EmployeeRepository;
import com.ecuti.employee_service.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Override
    public EmployeeResponse createEmployee(EmployeeRequest request) {
        Department department = departmentRepository.findById(request.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("Department not found with ID: " + request.getDepartmentId()));

        Employee employee = Employee.builder()
                .nip(request.getNip())
                .name(request.getName())
                .email(request.getEmail())
                .department(department)
                .position(request.getPosition())
                .build();

        Employee savedEmployee = employeeRepository.save(employee);
        return mapToResponse(savedEmployee);
    }

    @Override
    public List<EmployeeResponse> getAllEmployees() {
        return employeeRepository.findAll().stream()
                .map(this::mapToResponse)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeResponse getEmployeeByNip(String nip) {
        Employee employee = employeeRepository.findByNip(nip)
                .orElseThrow(() -> new RuntimeException("Employee not found with NIP: " + nip));
        return mapToResponse(employee);
    }

    private EmployeeResponse mapToResponse(Employee employee) {
        return EmployeeResponse.builder()
                .id(employee.getId())
                .publicId(employee.getPublicId())
                .nip(employee.getNip())
                .name(employee.getName())
                .email(employee.getEmail())
                .departmentName(employee.getDepartment() != null ? employee.getDepartment().getName() : null)
                .position(employee.getPosition())
                .leaveBalance(employee.getLeaveBalance())
                .build();
    }
@Override
public EmployeeResponse getEmployeeByPublicId(String publicId) {
    Employee employee = employeeRepository.findByPublicId(publicId)
            .orElseThrow(() -> new RuntimeException("Employee not found with Public ID: " + publicId));
    return mapToResponse(employee);
}
}
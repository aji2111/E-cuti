package com.ecuti.employee_service.controller;

import com.ecuti.employee_service.dto.request.EmployeeRequest;
import com.ecuti.employee_service.dto.response.EmployeeResponse;
import com.ecuti.employee_service.service.EmployeeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("/api/employees")
@RequiredArgsConstructor
@Tag(name = "Employee Controller", description = "API for managing employee data")
@SecurityRequirement(name = "bearerAuth")
public class EmployeeController {

    private final EmployeeService employeeService;

    @PostMapping
    @Operation(summary = "Create a new employee")
    @ApiResponse(responseCode = "200", description = "Employee successfully created")
    public ResponseEntity<EmployeeResponse> create(@RequestBody EmployeeRequest request) {
        return ResponseEntity.ok(employeeService.createEmployee(request));
    }

    @GetMapping
    @Operation(summary = "Get all employees")
    @ApiResponse(responseCode = "200", description = "List of employees retrieved successfully")
    public ResponseEntity<List<EmployeeResponse>> getAll() {
        return ResponseEntity.ok(employeeService.getAllEmployees());
    }

    @GetMapping("/{nip}")
    @Operation(summary = "Find employee by NIP")
    @ApiResponse(responseCode = "200", description = "Employee found")
    @ApiResponse(responseCode = "404", description = "Employee not found")
    public ResponseEntity<EmployeeResponse> getByNip(@PathVariable String nip) {
        return ResponseEntity.ok(employeeService.getEmployeeByNip(nip));
    }
    
    @GetMapping("/public/{publicId}")
@Operation(summary = "Find employee by Public ID")
@ApiResponse(responseCode = "200", description = "Employee found")
@ApiResponse(responseCode = "404", description = "Employee not found")
public ResponseEntity<EmployeeResponse> getByPublicId(@PathVariable String publicId) {
    return ResponseEntity.ok(employeeService.getEmployeeByPublicId(publicId));
}
}
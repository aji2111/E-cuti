package com.ecuti.permission_service.service.impl;

import com.ecuti.permission_service.client.EmployeeClient;
import com.ecuti.permission_service.dto.request.PermissionRequest;
import com.ecuti.permission_service.dto.response.EmployeeResponse;
import com.ecuti.permission_service.dto.response.PermissionResponse;
import com.ecuti.permission_service.entity.Permission;
import com.ecuti.permission_service.exception.ResourceNotFoundException;
import com.ecuti.permission_service.repository.PermissionRepository;
import com.ecuti.permission_service.service.PermissionService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PermissionServiceImpl implements PermissionService {

    private final PermissionRepository permissionRepository;
    private final EmployeeClient employeeClient;

    @Override
    public PermissionResponse createPermission(PermissionRequest request) {
        // Ambil nama dari employee-service
        EmployeeResponse emp;
        try {
            emp = employeeClient.getEmployeeByPublicId(request.getEmployeePublicId());
        } catch (Exception e) {
            throw new ResourceNotFoundException("Employee not found with ID: " + request.getEmployeePublicId());
        }

        if (emp == null) {
            throw new ResourceNotFoundException("Employee not found with ID: " + request.getEmployeePublicId());
        }

        Permission permission = Permission.builder()
                .reason(request.getReason())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .status("PENDING")
                .employeePublicId(request.getEmployeePublicId())
                .build();

        return mapToResponse(permissionRepository.save(permission), emp.getName());
    }

    @Override
    public List<PermissionResponse> getPermissionsByEmployeePublicId(String employeePublicId) {
        // Ambil data karyawan sekali untuk ditampilkan di list
        String empName = "Unknown";
        try {
            EmployeeResponse emp = employeeClient.getEmployeeByPublicId(employeePublicId);
            if (emp != null) empName = emp.getName();
        } catch (Exception ignored) {
            // Abaikan jika service mati, kembalikan "Unknown"
        }

        final String finalEmpName = empName;
        return permissionRepository.findByEmployeePublicId(employeePublicId).stream()
                .map(p -> mapToResponse(p, finalEmpName))
                .collect(Collectors.toList());
    }

    @Override
    public PermissionResponse getPermissionByPublicId(String publicId) {
        Permission p = permissionRepository.findByPublicId(publicId)
                .orElseThrow(() -> new ResourceNotFoundException("Permission not found with ID: " + publicId));
        
        String empName = "Unknown";
        try {
            EmployeeResponse emp = employeeClient.getEmployeeByPublicId(p.getEmployeePublicId());
            if (emp != null) empName = emp.getName();
        } catch (Exception ignored) {}

        return mapToResponse(p, empName);
    }

    private PermissionResponse mapToResponse(Permission p, String empName) {
        return PermissionResponse.builder()
                .publicId(p.getPublicId())
                .reason(p.getReason())
                .startDate(p.getStartDate())
                .endDate(p.getEndDate())
                .status(p.getStatus())
                .employeeName(empName)
                .build();
    }
}
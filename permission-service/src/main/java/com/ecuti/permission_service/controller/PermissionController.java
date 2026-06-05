package com.ecuti.permission_service.controller;

import com.ecuti.permission_service.dto.request.PermissionRequest;
import com.ecuti.permission_service.dto.response.PermissionResponse;
import com.ecuti.permission_service.service.PermissionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.security.SecurityRequirement; 

@RestController
@RequestMapping("/api/permissions")
@RequiredArgsConstructor
@Tag(name = "Permission Management", description = "API for managing employee leave/permission requests and history")
@SecurityRequirement(name = "bearerAuth")
public class PermissionController {

    private final PermissionService permissionService;

    @PostMapping
    @Operation(summary = "Create a new permission request", description = "Saves permission data and fetches the employee name from the employee-service")
    public ResponseEntity<PermissionResponse> createPermission(@RequestBody PermissionRequest request) {
        return new ResponseEntity<>(permissionService.createPermission(request), HttpStatus.CREATED);
    }

    @GetMapping("/employee/{employeePublicId}")
    @Operation(summary = "Get employee permission history", description = "Fetches all permission records based on the employee's publicId")
    public ResponseEntity<List<PermissionResponse>> getPermissionsByEmployee(@PathVariable String employeePublicId) {
        return ResponseEntity.ok(permissionService.getPermissionsByEmployeePublicId(employeePublicId));
    }

    @GetMapping("/{publicId}")
    @Operation(summary = "Get single permission details", description = "Fetches detailed permission information based on the permission's publicId")
    public ResponseEntity<PermissionResponse> getPermissionByPublicId(@PathVariable String publicId) {
        return ResponseEntity.ok(permissionService.getPermissionByPublicId(publicId));
    }
}
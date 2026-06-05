package com.ecuti.permission_service.dto.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PermissionRequest {
    private String employeePublicId;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
}
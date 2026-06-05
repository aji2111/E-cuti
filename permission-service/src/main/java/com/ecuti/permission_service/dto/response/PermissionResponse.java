package com.ecuti.permission_service.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PermissionResponse {
    private String publicId;
    private String reason;
    private LocalDate startDate;
    private LocalDate endDate;
    private String status;
    private String employeeName;
}
package com.ecuti.employee_service.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeResponse {
    private Long id;
    private String publicId;
    private String nip;
    private String name;
    private String email;
    private String departmentName;
    private String position;
    private Integer leaveBalance;
}
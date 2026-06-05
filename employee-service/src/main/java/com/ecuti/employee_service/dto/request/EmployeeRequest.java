package com.ecuti.employee_service.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRequest {
    private String nip;
    private String name;
    private String email;
    private Long departmentId;
    private String position;
}
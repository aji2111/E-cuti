package com.ecuti.permission_service.service;

import com.ecuti.permission_service.dto.request.PermissionRequest;
import com.ecuti.permission_service.dto.response.PermissionResponse;
import java.util.List;

public interface PermissionService {
    PermissionResponse createPermission(PermissionRequest request);
    List<PermissionResponse> getPermissionsByEmployeePublicId(String employeePublicId);
    PermissionResponse getPermissionByPublicId(String publicId);
}
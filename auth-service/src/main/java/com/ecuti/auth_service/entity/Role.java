package com.ecuti.auth_service.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Role extends BaseEntity {

    @Enumerated(EnumType.STRING)
    private RoleType type;
}
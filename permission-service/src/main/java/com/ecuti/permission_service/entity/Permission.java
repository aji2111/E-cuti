package com.ecuti.permission_service.entity;

import java.time.LocalDate;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter @Setter @Builder
@NoArgsConstructor @AllArgsConstructor
@Table(name = "permissions")
public class Permission {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false, updatable = false)
    private String publicId;

    @Column(nullable = false)
    private String employeePublicId;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private LocalDate startDate;

    @Column(nullable = false)
    private LocalDate endDate;

    @Column(nullable = false)
    private String status;

    @PrePersist
    public void generatePublicId() {
        if (this.publicId == null) {
            this.publicId = UUID.randomUUID().toString();
        }
    }
}
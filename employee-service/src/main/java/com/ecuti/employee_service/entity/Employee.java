package com.ecuti.employee_service.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.UUID;

@Entity
@Table(name = "employees")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(nullable = false, unique = true, updatable = false)
    private String publicId;
    
    @Column(nullable = false, unique = true)
    private String nip;
    
    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false, unique = true)
    private String email;
    
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;
    
    private String position;
    
    @Builder.Default
    private Integer leaveBalance = 12;

    @PrePersist
    protected void onCreate() {
        this.publicId = "EMP-" + UUID.randomUUID().toString();
    }
}
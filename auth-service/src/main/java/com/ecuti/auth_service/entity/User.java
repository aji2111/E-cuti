package com.ecuti.auth_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private RoleType role;

    // Tambahkan Getter & Setter Manual agar compiler tidak bergantung pada Lombok
    public String getUsername() { return username; }
    public void setUsername(String username) { this.username = username; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public RoleType getRole() { return role; }
    public void setRole(RoleType role) { this.role = role; }
}
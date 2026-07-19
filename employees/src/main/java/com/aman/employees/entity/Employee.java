package com.aman.employees.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "employees", uniqueConstraints = {
        @UniqueConstraint(name = "uk_employees_email", columnNames = "email"),
        @UniqueConstraint(name = "uk_employees_phone", columnNames = "phone"),
})
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(unique = true, nullable = false, length = 150)
    private String email;

    @NotNull(message = "Department is required")
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 50)
    private Department department;

    @Column(nullable = false)
    private Double salary;

    @PastOrPresent(message = "Joining date cannot be in the future")
    @Column(nullable = false)
    private LocalDateTime joiningDate;

    @Column(unique = true, nullable = false, length = 20)
    private String phone;

    @Column(name = "is_active")
    private Boolean isActive = true;

    @Column(nullable = false)
    private int age;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    @Transient
    public String getDepartmentDisplayName() {
        return this.department != null ? this.department.getDisplayName() : null;
    }
}

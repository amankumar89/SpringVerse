package com.aman.employees.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmployeeResponseDto {
    private Long id;
    private String name;
    private Double salary;
    private String phone;
    private Boolean isActive = true;
    private int age;
}

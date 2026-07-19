package com.aman.springverse.entity.employees;

import lombok.Getter;

@Getter
public enum Department {
    ENGINEERING("Engineering"),
    HUMAN_RESOURCES("Human Resources"),
    MARKETING("Marketing"),
    SALES("Sales"),
    FINANCE("Finance");

    private final String displayName;

    Department(String displayName) {
        this.displayName = displayName;
    }

    public static Department fromDisplayName(String displayName) {
        for (Department dept : Department.values()) {
            if (dept.displayName.equalsIgnoreCase(displayName)) {
                return dept;
            }
        }
        throw new IllegalArgumentException("Unknown department: " + displayName);
    }
}

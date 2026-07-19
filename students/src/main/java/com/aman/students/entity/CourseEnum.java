package com.aman.springverse.entity.students;

import lombok.Getter;

@Getter
public enum CourseEnum {
    JAVA("JAVA"),
    CORE_JAVA("Core Java"),
    SPRING("Spring"),
    SPRING_BOOT("Spring Boot"),
    MERN("MERN"),
    REACT("React.js"),
    NODE("Node.js"),
    EXPRESS("Express.js"),
    TYPESCRIPT("TypeScript"),
    JAVASCRIPT("JavaScript"),
    REDIS("Redis"),
    ;

    private final String displayName;

    CourseEnum(String displayName) {
        this.displayName = displayName;
    }

}

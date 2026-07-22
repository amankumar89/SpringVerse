package com.aman.library.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
        "success",
        "message",
        "data",
        "timestamp"
})
public class ApiResponse<T> {
    private boolean success;
    private String message;
    private T data;

    @Builder.Default
    private LocalDateTime timestamp = LocalDateTime.now();

    public static <T> ResponseEntity<ApiResponse<T>> ok(T data) {
        return ok("Success", data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> ok(String message, T data) {
        return build(HttpStatus.OK, true, message, data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(T data) {
        return created("Resource created successfully", data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> created(String message, T data) {
        return build(HttpStatus.CREATED, true, message, data);
    }

    public static <T> ResponseEntity<ApiResponse<T>> badRequest(String message) {
        return build(HttpStatus.BAD_REQUEST, false, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> unauthorized(String message) {
        return build(HttpStatus.UNAUTHORIZED, false, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> forbidden(String message) {
        return build(HttpStatus.FORBIDDEN, false, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> notFound(String message) {
        return build(HttpStatus.NOT_FOUND, false, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> noContent(String message) {
        return build(HttpStatus.NO_CONTENT, true, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> conflict(String message) {
        return build(HttpStatus.CONFLICT, false, message, null);
    }

    public static <T> ResponseEntity<ApiResponse<T>> internalServerError(String message) {
        return build(HttpStatus.INTERNAL_SERVER_ERROR, false, message, null);
    }

    private static <T> ResponseEntity<ApiResponse<T>> build(
            HttpStatus status,
            boolean success,
            String message,
            T data
    ) {

        ApiResponse<T> response = ApiResponse.<T>builder()
                .success(success)
                .message(message)
                .data(data)
                .build();

        return ResponseEntity.status(status).body(response);
    }
}
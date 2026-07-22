package com.aman.products.exception;

import com.aman.products.dto.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DuplicateResourceException.class)
    public ResponseEntity<ApiResponse<Void>> handleDuplicateResourceException(DuplicateResourceException ex) {
        return ApiResponse.conflict(
                ex.getMessage() != null ? ex.getMessage() : "Resource already exists!"
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse<Void>> handleResourceNotFoundException(ResourceNotFoundException ex) {
        return ApiResponse.notFound(
                ex.getMessage() != null ? ex.getMessage() : "Resource not found"
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Void>> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex
    ) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult()
                .getFieldErrors()
                .forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));

        Map.Entry<String, String> firstEntry = errors.entrySet().iterator().next();
        String firstValue = firstEntry.getValue();

        return ApiResponse.badRequest(firstValue != null ? firstValue : ex.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<Void>> handleException(RuntimeException ex) {
        return ApiResponse.internalServerError(
                ex.getMessage() != null ? ex.getMessage() : "An error occurred"
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Void>> handleException(Exception ex) {
        String message = ex.getMessage() != null ? ex.getMessage() : "Internal Server Error";
        return ApiResponse.internalServerError(message);
    }
}

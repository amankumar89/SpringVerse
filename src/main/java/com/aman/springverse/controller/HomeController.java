package com.aman.springverse.controller;

import com.aman.springverse.utils.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {
    @GetMapping
    public ResponseEntity<ApiResponse<Void>> home() {
        return ApiResponse.ok("Server is healthy", null);
    }
}

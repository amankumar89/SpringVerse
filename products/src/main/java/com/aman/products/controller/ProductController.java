package com.aman.products.controller;

import com.aman.products.dto.ApiResponse;
import com.aman.products.dto.CreateProductRequestDto;
import com.aman.products.dto.ProductResponseDto;
import com.aman.products.dto.UpdateProductRequestDto;
import com.aman.products.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/products")
public class ProductController {
    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponseDto>> createProduct(
            @Valid @RequestBody CreateProductRequestDto createDto
    ) {
        return ApiResponse.created(
                "Product created successfully",
                productService.createProduct(createDto));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> getAllProducts() {
        return ApiResponse.ok(
                "Products retrieved successfully",
                productService.getAllProducts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> getProductById(@PathVariable Long id) {
        return ApiResponse.ok(
                "Product retrieved successfully",
                productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponseDto>> updateProduct(
            @PathVariable Long id,
            @Valid @RequestBody UpdateProductRequestDto updateDTO) {
        return ApiResponse.ok("Product updated successfully",
                productService.updateProduct(id, updateDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
        return ApiResponse.noContent("Product deleted successfully");
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<ProductResponseDto>>> searchProducts(@RequestParam String keyword) {
        return ApiResponse.ok("Products found successfully",
                productService.searchProducts(keyword));
    }
}

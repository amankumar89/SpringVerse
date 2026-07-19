package com.aman.products.dto;

import com.aman.products.entity.Category;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductResponseDto {
    private Long id;
    private String name;
    private Double price;
    private int stock;
    private Category category;
    private String brand;
    private String description;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}

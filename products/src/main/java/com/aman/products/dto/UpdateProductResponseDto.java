package com.aman.products.dto;

import com.aman.products.entity.Category;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductResponseDto {
    private Long id;
    private String name;
    private Double price;
    private int stock;
    private Category category;
    private String brand;
    private String description;
}

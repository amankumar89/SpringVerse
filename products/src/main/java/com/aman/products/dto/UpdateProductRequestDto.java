package com.aman.products.dto;

import com.aman.products.entity.Category;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UpdateProductRequestDto {
    private String name;

    @Positive(message = "Price must be greater than 0")
    private Double price;

    @Min(value = 0, message = "Stock cannot be negative")
    private int stock;

    private Category category;

    private String brand;

    private String description;
}

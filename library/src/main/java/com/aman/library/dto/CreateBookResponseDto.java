package com.aman.library.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateBookResponseDto {
    private Long id;
    private String title;
    private String authorName;
    private String isbn;
    private Double price;
    private Integer quantity;
    private String category;
    private Integer publishedYear;
}

package com.aman.springverse.dto.books;

import com.aman.springverse.entity.books.Category;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookRequestDto {

    @NotNull(message = "Price is required")
    @Positive(message = "Price must be greater than 0")
    private Double price;

    @NotNull(message = "Quantity is required")
    @Positive(message = "Quantity must be greater than 0")
    private Integer quantity;

    @NotNull(message = "Category is required")
    private Category category;

    @NotBlank(message = "Published year is required")
    @PastOrPresent(message = "Published year cannot be in the future")
    private String publishedYear;

    @NotNull(message = "Author ID is required")
    private Long authorId;
}

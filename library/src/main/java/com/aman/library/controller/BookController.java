package com.aman.springverse.controller;

import com.aman.springverse.service.BookService;
import com.aman.springverse.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/books")
public class BookController {
    private final BookService bookService;

    @PostMapping
    public ResponseEntity<ApiResponse<CreateBookResponseDto>> createBook(@Valid @RequestBody CreateBookRequestDto requestDto) {
        return ApiResponse.created(
                "Book created successfully",
                bookService.createBook(requestDto)
        );
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<BookResponseDto>>> getAllBooks() {
        return ApiResponse.ok("Books fetched successfully", bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<BookResponseDto>> getBookById(@PathVariable Long id) {
        return ApiResponse.ok("Book fetched successfully", bookService.getBookById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<UpdateBookResponseDto>> updateBook(
            @PathVariable Long id,
            @Valid @RequestBody UpdateBookRequestDto requestDto) {
        return ApiResponse.ok("Book updated successfully", bookService.updateBook(id, requestDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ApiResponse.noContent("Book deleted");
    }

    @GetMapping("/search")
    public ResponseEntity<ApiResponse<List<BookResponseDto>>> searchBooks(@RequestParam String keyword) {
        return ApiResponse.ok("Books fetched successfully", bookService.searchBooks(keyword));
    }
}

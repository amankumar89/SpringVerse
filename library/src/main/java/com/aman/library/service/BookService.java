package com.aman.library.service;

import com.aman.library.dto.*;
import com.aman.library.entity.Author;
import com.aman.library.entity.Book;
import com.aman.library.exception.DuplicateResourceException;
import com.aman.library.exception.ResourceNotFoundException;
import com.aman.library.repository.AuthorRepository;
import com.aman.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;
    private final ModelMapper modelMapper;

    public CreateBookResponseDto createBook(CreateBookRequestDto requestDto) {
        if (bookRepository.existsByIsbn(requestDto.getIsbn())) {
            throw new DuplicateResourceException("Book with ISBN " + requestDto.getIsbn() + " already exists");
        }

        Author author = authorRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + requestDto.getAuthorId()));

        Book book = modelMapper.map(requestDto, Book.class);
        Book savedBook = bookRepository.save(book);
        return modelMapper.map(savedBook, CreateBookResponseDto.class);
    }

    @Transactional(readOnly = true)
    public BookResponseDto getBookById(Long id) {
        Book book = findBookById(id);
        return modelMapper.map(book, BookResponseDto.class);
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map((book -> modelMapper.map(book, BookResponseDto.class)))
                .collect(Collectors.toList());
    }

    public UpdateBookResponseDto updateBook(Long id, UpdateBookRequestDto requestDto) {
        Book book = findBookById(id);
        Author author = authorRepository.findById(requestDto.getAuthorId())
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with ID: " + requestDto.getAuthorId()));

        book.setAuthor(author);
        book.setPrice(requestDto.getPrice());
        book.setQuantity(requestDto.getQuantity());
        book.setCategory(requestDto.getCategory());
        book.setPublishedYear(requestDto.getPublishedYear());

        Book updatedBook = bookRepository.save(book);
        return modelMapper.map(updatedBook, UpdateBookResponseDto.class);
    }

    public void deleteBook(Long id) {
        Book book = findBookById(id);
        bookRepository.delete(book);
    }

    @Transactional(readOnly = true)
    public List<BookResponseDto> searchBooks(String keyword) {
        return bookRepository.searchBooks(keyword).stream()
                .map((book -> modelMapper.map(book, BookResponseDto.class)))
                .collect(Collectors.toList());
    }

    private Book findBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with ID: " + id));
    }
}

package com.aman.springverse.entity.books;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity(name = "books")
@NoArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    private Author author;

    @Column(unique = true, nullable = false)
    private String isbn;

    private Double price;

    private Integer quantity;

    @Enumerated(EnumType.STRING)
    private Category category;

    private String publishedYear;

}

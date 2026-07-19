package com.aman.springverse.entity.books;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity(name = "authors")
@NoArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Book> books = new ArrayList<>();
}

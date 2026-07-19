package com.aman.springverse.entity.books;

import lombok.Getter;

@Getter
public enum Category {
    FICTION("Fiction"),
    NON_FICTION("Non-Fiction"),
    SCIENCE("Science"),
    HISTORY("History"),
    BIOGRAPHY("Biography"),
    FANTASY("Fantasy"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    THRILLER("Thriller"),
    HORROR("Horror"),
    SELF_HELP("Self-Help"),
    TECHNOLOGY("Technology");

    private final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }

}
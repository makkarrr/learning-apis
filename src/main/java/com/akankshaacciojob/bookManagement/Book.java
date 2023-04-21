package com.akankshaacciojob.bookManagement;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter // Lombok dependency
public class Book {

    public static final String CONTINUE = "Continue";
    private int bookId;
    private String title;
    private String author;
    private int pages;

    public Book(int bookId, String title, String author, int pages) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.pages = pages;
    }
}

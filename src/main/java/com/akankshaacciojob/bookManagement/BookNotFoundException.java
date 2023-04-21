package com.akankshaacciojob.bookManagement;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(int id) {
        super("Book does not exist for id : " + id);
    }
}

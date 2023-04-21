package com.akankshaacciojob.bookManagement;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Repository
public class BookRepository {

    public BookRepository() {
        System.out.println("creating bean of book repo class");
    }
    Map<Integer, Book> data = new HashMap<>();

    public Boolean add(Book book) {
        data.put(book.getBookId(), book);
        return true;
    }

    public Optional<Book> getById(int id) {
        if(data.containsKey(id)) {
            return Optional.of(data.get(id));
        }
        return Optional.empty();
    }

    public void removeById(int id) {
        data.remove(id);
    }
}

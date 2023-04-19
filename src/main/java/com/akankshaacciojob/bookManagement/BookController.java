package com.akankshaacciojob.bookManagement;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@RestController
public class BookController {
    Map<Integer, Book> data = new HashMap<>();

    @PostMapping("/book")
    public String addBook(@RequestBody Book book){
        data.put(book.getBookId(), book);
        return "added successfully";
    }

    @GetMapping("/book") // ? id = 1
    public Book findBook(@RequestParam int id) {
        return data.get(id);
    }

    @GetMapping("/book/{id}") // find-books/1
    public Book findBookByParam(@PathVariable int id) {
        return data.get(id);
    }

    @PutMapping("update-book/{id}")
    public String updateBook(@PathVariable int id, @RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) int pages) {
        Book book = data.get(id);
        if(Objects.nonNull(title)) {
            book.setTitle(title);
        }
        if(Objects.nonNull(author)) {
            book.setAuthor(author);
        }
        if(Objects.nonNull(pages)) {
            book.setPages(pages);
        }
        data.put(id,book);
        return "book updated";
    }

    @DeleteMapping("/remove-book/{id}")
    public String deleteBook(@PathVariable int id) {
        data.remove(id);
        return "book removed";
    }
}

package com.akankshaacciojob.bookManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/book")
public class BookController {
    public BookController() {
        System.out.println("Book Controller is being instantiated");
    }
    @Autowired
    BookService bookService;

    @PostMapping("/add")
    public ResponseEntity addBook(@RequestBody Book book){
        try {
            Boolean added = bookService.addBook(book);
            return new ResponseEntity("Added successfully", HttpStatus.CREATED); //200
        } catch(BookAlreadyExistsException ex) {
            return new ResponseEntity("unable to add book as it already exists", HttpStatus.valueOf(400));
        }
    }

    @GetMapping("/get")
    public ResponseEntity findBook(@RequestParam int id) {
        try{
            Book book = bookService.getBook(id);
            return new ResponseEntity(book, HttpStatus.OK);
        } catch(BookNotFoundException e) {
            return new ResponseEntity("Book not found", HttpStatus.valueOf(404));
        } catch(Exception ex) {
            return new ResponseEntity("Something went wrong", HttpStatus.valueOf(500));
        }
    }
//
//    @GetMapping("/all-books")
//    public List<Book> getAllBooks() {
//        return data.values().stream().toList();
//    }
//
//    @GetMapping("/book/{id}") // find-books/1
//    public Book findBookByParam(@PathVariable int id) {
//        return data.get(id);
//    }
//
    @PutMapping("update/{id}")
    public String updateBook(@PathVariable int id, @RequestParam(required = false) String title, @RequestParam(required = false) String author, @RequestParam(required = false) Integer pages) {
        String response = bookService.updateBook(id, title, author, pages);
        return response;
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity deleteBook(@PathVariable int id) {
        try {
            bookService.deleteBook(id);
            return new ResponseEntity("book removed successfully", HttpStatus.OK);
        } catch(BookNotFoundException ex) {
            return new ResponseEntity(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }
}

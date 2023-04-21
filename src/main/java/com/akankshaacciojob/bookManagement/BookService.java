package com.akankshaacciojob.bookManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;


@Service
public class BookService {

    public BookService() {
        System.out.println("created bean of book service class");
    }

    @Autowired
    BookRepository bookRepository;
    public Boolean addBook(Book book) throws BookAlreadyExistsException{
        Optional<Book> bookOpt = bookRepository.getById(book.getBookId()); // bookId already exists
        if(bookOpt.isPresent()) {
            throw new BookAlreadyExistsException(book.getBookId());
        }
        return bookRepository.add(book);
    }

    public Book getBook(int id) throws BookNotFoundException{
        Optional<Book> bookOpt = bookRepository.getById(id);
        if(bookOpt.isEmpty()) {
            throw new BookNotFoundException(id);
        }
        return bookOpt.get();
    }

    //1, harry potter 2, AKanksha, 600
    public String updateBook(int id, String title, String author, Integer pages) {
        try {
            Book book = getBook(id);
            if(Objects.nonNull(title)) {
                book.setTitle(title);
            }

            if(Objects.nonNull(author)) {
                book.setAuthor(author); // throw ArithmeticException
            }
            if(Objects.nonNull(pages)) {
                book.setPages(pages);
            }
            bookRepository.add(book);
            return "book updated";
        } catch(BookNotFoundException ex) {
            Book book = new Book(id, title, author, pages);
            bookRepository.add(book);
            return "book created";
        }
    }

    public Boolean deleteBook(int id) throws BookNotFoundException{
        Optional<Book> bookOpt = bookRepository.getById(id);
        if(bookOpt.isEmpty()) {
            throw new BookNotFoundException(id);
        }
        bookRepository.removeById(id);
        return true;
    }
}

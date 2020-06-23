package com.library.rest.springboot.libraryrest.rest;

import com.library.rest.springboot.libraryrest.entity.Books;
import com.library.rest.springboot.libraryrest.entity.Members;
import com.library.rest.springboot.libraryrest.exceptions.CustomException;
import com.library.rest.springboot.libraryrest.service.BooksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BooksRestController {

    private BooksService booksService;

    @Autowired
    public BooksRestController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public List<Books> findAll() {
        return booksService.findAll();
    }

    @GetMapping("/books/{bookId}")
    public Books findById(@PathVariable int bookId) {
        Books book = booksService.findById(bookId);
        if (book == null) {
            throw new CustomException("Book with id " + bookId + " not found");
        }
        return book;
    }

    @PostMapping("/books")
    public Books addBook(@RequestBody Books book) {
        book.setId(0);
        booksService.save(book);
        return book;
    }

    @PutMapping("/books")
    public Books updateBook(@RequestBody Books book) {
        booksService.save(book);
        return book;
    }

    @DeleteMapping("/books/{bookId}")
    public String deleteBook(@PathVariable int bookId) {
        Books book = booksService.findById(bookId);
        if (book == null) {
            throw new CustomException("Book with id " + bookId + " not found");
        }
        Members member = book.getMembers();
        if (member == null) {
            booksService.deleteById(bookId);
            return "Deleted id - " + bookId;
        } else {
            throw new CustomException("Book with id " + bookId + " not can't be deleted because it is on loan");
        }
    }
}

package io.atlassian.micros.myservice.controller;

import io.atlassian.micros.myservice.entity.BookData;
import io.atlassian.micros.myservice.entity.BookRequest;
import io.atlassian.micros.myservice.entity.BookResponse;
import io.atlassian.micros.myservice.exception.BookNotFoundException;
import io.atlassian.micros.myservice.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


@RestController
public class BookDataController {

    @Autowired
    private BookService bookService;


    @PostMapping("/savebook")
    public BookResponse saveBook(@RequestBody BookRequest bookRequest) {
        return bookService.saveBook(bookRequest);
    }

    @GetMapping("/getbook/{id}")
    public BookResponse getBook(@PathVariable("id") String bookId) {
        try {
            return bookService.getBook(bookId);
        }
        catch(BookNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found", exc);
        }
    }

    @DeleteMapping("/deletebook/{id}")
    public BookResponse deleteBook(@PathVariable("id") String bookId) {
        try {
            return bookService.deleteBook(bookId);
        }
        catch(BookNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Book not found", exc);
        }
    }

    @PutMapping("/updatebook/{id}")
    public BookResponse updateBook(@PathVariable("id") String bookId,@RequestBody BookRequest bookRequest) {
        try {
            return bookService.updateBook(bookId, bookRequest);
        }
        catch(BookNotFoundException exc) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found", exc);
        }
    }
}

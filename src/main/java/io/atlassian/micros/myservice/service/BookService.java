package io.atlassian.micros.myservice.service;

import io.atlassian.micros.myservice.entity.BookData;
import io.atlassian.micros.myservice.entity.BookMapper;
import io.atlassian.micros.myservice.entity.BookRequest;
import io.atlassian.micros.myservice.entity.BookResponse;
import io.atlassian.micros.myservice.exception.BookNotFoundException;
import io.atlassian.micros.myservice.repository.BookRepository;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookResponse saveBook(BookRequest bookRequest) {
        BookData bookData = BookMapper.INSTANCE.bookRequestToBookData(bookRequest);
        BookData bookDb = bookRepository.save(bookData);
        BookResponse bookResponse = BookMapper.INSTANCE.bookDataToBookResponse(bookDb);
        return bookResponse;
    }

    public BookResponse getBook(String bookId) {
        BookData bookDb = bookRepository.getBook(bookId);
        System.out.println(bookDb);
        if(ObjectUtils.isEmpty(bookDb))
            throw new BookNotFoundException("BookId is invalid");
        BookResponse bookResponse = BookMapper.INSTANCE.bookDataToBookResponse(bookDb);
        return bookResponse;
    }

    public BookResponse deleteBook(String bookId) {
        BookData bookDb = bookRepository.getBook(bookId);
        if(ObjectUtils.isEmpty(bookDb))
            throw new BookNotFoundException("BookId is invalid");
        BookData book = bookRepository.delete(bookId);
        BookResponse bookResponse = BookMapper.INSTANCE.bookDataToBookResponse(book);
        return bookResponse;
    }

    public BookResponse updateBook(String bookId, BookRequest bookRequest) {
        BookData bookData = bookRepository.getBook(bookId);
        if(ObjectUtils.isEmpty(bookData))
            throw new BookNotFoundException("BookId is invalid");
        BookData newBook = BookMapper.INSTANCE.bookRequestToBookData(bookRequest);
        newBook.setBookId(bookId);
        BookData bookDb = bookRepository.update(bookId, newBook);
        BookResponse bookResponse = BookMapper.INSTANCE.bookDataToBookResponse(bookDb);
        return bookResponse;
    }

}

package io.atlassian.micros.myservice.entity;

import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-03-09T15:00:06+0530",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.11 (AdoptOpenJDK)"
)
public class BookMapperImpl implements BookMapper {

    @Override
    public BookData bookRequestToBookData(BookRequest bookRequest) {
        if ( bookRequest == null ) {
            return null;
        }

        BookData bookData = new BookData();

        bookData.setBookName( bookRequest.getBookName() );
        bookData.setAuthorName( bookRequest.getAuthorName() );

        return bookData;
    }

    @Override
    public BookResponse bookDataToBookResponse(BookData bookData) {
        if ( bookData == null ) {
            return null;
        }

        String bookId = null;
        String bookName = null;
        String authorName = null;

        bookId = bookData.getBookId();
        bookName = bookData.getBookName();
        authorName = bookData.getAuthorName();

        BookResponse bookResponse = new BookResponse( bookId, bookName, authorName );

        return bookResponse;
    }
}

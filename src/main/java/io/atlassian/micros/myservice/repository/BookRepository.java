package io.atlassian.micros.myservice.repository;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBSaveExpression;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ExpectedAttributeValue;
import io.atlassian.micros.myservice.entity.BookData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class BookRepository {

    @Autowired
    private DynamoDBMapper dynamoDBMapper;


    public BookData save(BookData bookData) {

        dynamoDBMapper.save(bookData);
        return bookData;
    }

    public BookData getBook(String bookId) {
        return dynamoDBMapper.load(BookData.class, bookId);
    }

    public BookData delete(String bookId) {
        BookData book = dynamoDBMapper.load(BookData.class, bookId);
        dynamoDBMapper.delete(book);
        return book;
    }

    public BookData update(String bookId, BookData bookData) {
        dynamoDBMapper.save(bookData,
                new DynamoDBSaveExpression()
                        .withExpectedEntry("bookId",
                                new ExpectedAttributeValue(
                                        new AttributeValue().withS(bookId)
                                )));
        return bookData;
    }
}

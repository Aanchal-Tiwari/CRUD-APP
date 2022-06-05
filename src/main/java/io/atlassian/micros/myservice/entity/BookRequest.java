package io.atlassian.micros.myservice.entity;
import lombok.Getter;

import javax.validation.constraints.Size;

@Getter
public class BookRequest {
    public BookRequest() {
    }

    @Size(min=2, message="Name should have atleast 2 characters")
    private String bookName;

    @Size(min=2, message="Name should have atleast 2 characters")
    private String authorName;

    public BookRequest(String bookName, String authorname) {
        this.bookName = bookName;
        this.authorName = authorname;
    }

    public String getBookName() {
        return bookName;
    }

    public void setbookName(String name) {
        this.bookName = name;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String name) {
        this.authorName = name;
    }
}

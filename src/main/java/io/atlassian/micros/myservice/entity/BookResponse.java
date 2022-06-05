package io.atlassian.micros.myservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
public class BookResponse {

    private String bookId;

    private String bookName;

    private String authorName;

    public BookResponse(String bookId, String bookName, String authorName) {
        this.bookId = bookId;
        this.bookName= bookName;
        this.authorName = authorName;
    }

    public String getBookId() {
        return bookId;
    }

    public void setbookId(String bookId) {
        this.bookId = bookId;
    }

    public String getbookName() {
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

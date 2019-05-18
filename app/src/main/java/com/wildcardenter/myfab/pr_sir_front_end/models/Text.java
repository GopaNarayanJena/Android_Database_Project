package com.wildcardenter.myfab.pr_sir_front_end.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "TEXT")
public class Text {
    @PrimaryKey
    @NonNull
    private int book_isbn;
    @NonNull
    private String book_title;
    @NonNull
    private String publisher;
    @NonNull
    private String author;

    @Ignore
    @NonNull
    @ColumnInfo(name = "course")
    private int course;

    public Text(int book_isbn, @NonNull String book_title, @NonNull String publisher, @NonNull String author) {
        this.book_isbn = book_isbn;
        this.book_title = book_title;
        this.publisher = publisher;
        this.author = author;
    }

    public int getBook_isbn() {
        return book_isbn;
    }

    public void setBook_isbn(int book_isbn) {
        this.book_isbn = book_isbn;
    }

    @NonNull
    public String getBook_title() {
        return book_title;
    }

    public void setBook_title(@NonNull String book_title) {
        this.book_title = book_title;
    }

    @NonNull
    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(@NonNull String publisher) {
        this.publisher = publisher;
    }

    @NonNull
    public String getAuthor() {
        return author;
    }

    public void setAuthor(@NonNull String author) {
        this.author = author;
    }

    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }
}

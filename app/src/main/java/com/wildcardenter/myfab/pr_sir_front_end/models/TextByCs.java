package com.wildcardenter.myfab.pr_sir_front_end.models;

import androidx.annotation.NonNull;

public class TextByCs {


    private int book_isbn;

    private String book_title;

    private String publisher;

    private String author;
    private int course;

    public TextByCs(int book_isbn, @NonNull String book_title, @NonNull String publisher, @NonNull String author) {
        this.book_isbn = book_isbn;
        this.book_title = book_title;
        this.publisher = publisher;
        this.author = author;
    }

    public TextByCs(int book_isbn, String book_title, String publisher, String author, int course) {
        this.book_isbn = book_isbn;
        this.book_title = book_title;
        this.publisher = publisher;
        this.author = author;
        this.course = course;
    }

    public TextByCs() {
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

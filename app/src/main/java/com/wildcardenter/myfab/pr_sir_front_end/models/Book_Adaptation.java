package com.wildcardenter.myfab.pr_sir_front_end.models;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

import static androidx.room.ForeignKey.CASCADE;

@Entity(tableName = "BOOK_ADAPTATION", primaryKeys = {"course", "sem"}
        , foreignKeys = @ForeignKey(entity = Text.class, parentColumns = "book_isbn",
        childColumns = "book_isbn", onUpdate = CASCADE,onDelete = CASCADE))
public class Book_Adaptation {


    @NonNull
    private int course;

    @NonNull
    private int sem;

    @NonNull
    private int book_isbn;

    public Book_Adaptation(int course, int sem, int book_isbn) {
        this.course = course;
        this.sem = sem;
        this.book_isbn = book_isbn;
    }


    public int getCourse() {
        return course;
    }

    public void setCourse(int course) {
        this.course = course;
    }

    public int getSem() {
        return sem;
    }

    public void setSem(int sem) {
        this.sem = sem;
    }

    public int getBook_isbn() {
        return book_isbn;
    }

    public void setBook_isbn(int book_isbn) {
        this.book_isbn = book_isbn;
    }
}

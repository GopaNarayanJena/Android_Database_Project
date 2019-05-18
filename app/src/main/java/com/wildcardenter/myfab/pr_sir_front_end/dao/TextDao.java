package com.wildcardenter.myfab.pr_sir_front_end.dao;

import com.wildcardenter.myfab.pr_sir_front_end.models.Text;
import com.wildcardenter.myfab.pr_sir_front_end.models.TextByCs;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.RoomWarnings;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
@SuppressWarnings(RoomWarnings.CURSOR_MISMATCH)
public interface TextDao {
    @Insert(onConflict = REPLACE)
    void insertText(Text text);

    @Delete
    void deleteText(Text text);

    @Query("UPDATE TEXT SET book_isbn=:book_isbn,book_title=:book_title," +
            "publisher=:publisher,author=:author where book_isbn=:pk")
    void updateText(int book_isbn,String book_title,String publisher,String author,int pk);

    @Query("select * from TEXT order by book_isbn")
    LiveData<List<Text>> getAllText();

    @Query("select course,book_isbn,book_title from TEXT " +
            "natural join COURSE natural join BOOK_ADAPTATION where course in " +
            "(select course from TEXT natural join COURSE natural join BOOK_ADAPTATION " +
            "where dept like 'cse' group by course having count(course)>2) order by book_title")
    LiveData<List<TextByCs>> getAllBookOfferedByCs();

}

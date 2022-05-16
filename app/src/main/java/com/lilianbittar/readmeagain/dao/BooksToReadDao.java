package com.lilianbittar.readmeagain.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface BooksToReadDao {

    @Insert
    void insert(BookToRead bookToRead);

    @Update
    void update(BookToRead bookToRead);

    @Delete
    void delete(BookToRead bookToRead);

    @Query("DELETE FROM books_to_read")
    void deleteAllToReadBooks();

    @Query("SELECT * FROM books_to_read")
    LiveData<List<BookToRead>> getAllToReadBooks();
}

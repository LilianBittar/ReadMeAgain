package com.lilianbittar.readmeagain.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import java.util.List;

@Dao
public interface ReadBooksDao {

    @Insert
    void insert(ReadBook readBook);

    @Update
    void update(ReadBook readBook);

    @Delete
    void delete(ReadBook readBook);

    @Query("DELETE FROM read_books")
    void deleteAllReadBooks();

    @Query("SELECT * FROM read_books")
    LiveData<List<ReadBook>> getAllReadBooks();
}
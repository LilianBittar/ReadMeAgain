package com.lilianbittar.readmeagain.dao;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {BookToRead.class, ReadBook.class}, version = 1)
public abstract class BookDatabase extends RoomDatabase {

    private static BookDatabase instance;
    public abstract BooksToReadDao booksToReadDao();
    public abstract ReadBooksDao readBooksDao();

    public static synchronized BookDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), BookDatabase.class, "book_database")
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }
}

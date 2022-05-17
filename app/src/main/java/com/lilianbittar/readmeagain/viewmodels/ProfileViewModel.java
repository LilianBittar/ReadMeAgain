package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import android.icu.text.SimpleDateFormat;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.lilianbittar.readmeagain.dao.BookToRead;
import com.lilianbittar.readmeagain.dao.ReadBook;
import com.lilianbittar.readmeagain.repositories.BookRepo;
import com.lilianbittar.readmeagain.repositories.UserRepository;

import java.util.Date;
import java.util.List;

public class ProfileViewModel extends AndroidViewModel {

    private final BookRepo bookRepo;

    public ProfileViewModel(Application app){
        super(app);
        bookRepo = BookRepo.getInstance(app);
    }

    public void init() {
        bookRepo.init();
    }

    public LiveData<List<BookToRead>> getToReadBooks() {
        return bookRepo.getAllBooksToRead();
    }

    public LiveData<List<ReadBook>> getReadBooks() {
        return bookRepo.getAllReadBooks();
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void markBookAsRead(BookToRead bookToRead) {
        ReadBook tmp = new ReadBook(
                bookToRead.getTitle(),
                bookToRead.getNumber_of_pages_median(),
                bookToRead.getIsbn(),
                bookToRead.getAuthor(),
                bookToRead.getSubject(),
                bookToRead.getCoverId(),
                new SimpleDateFormat("yyyy-MM-dd").format(new Date())
        );
        bookRepo.insert(tmp);
        bookRepo.delete(bookToRead);
    }

    public void markBookAsExchange(ReadBook readBook) {
       bookRepo.addBookToExchange(readBook);
       bookRepo.delete(readBook);
    }
}

package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.repositories.BookRepo;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final BookRepo bookRepo;

    public SearchViewModel(Application app) {
        super(app);
        bookRepo = BookRepo.getInstance();
    }

    public LiveData<List<Book>> getSearchedBook() {
        return bookRepo.getSearchedBook();
    }

    public void searchForBook(String s) {
        bookRepo.searchForBook(s);
    }
}

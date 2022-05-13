package com.lilianbittar.readmeagain.viewmodels;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.lilianbittar.readmeagain.model.Book;
import com.lilianbittar.readmeagain.repositories.BookRepo;
import java.util.List;

public class SearchViewModel extends AndroidViewModel {

    private final BookRepo bookRepo;
    private final MutableLiveData<Boolean> loading;

    public SearchViewModel(Application app) {
        super(app);
        bookRepo = BookRepo.getInstance();
        loading = new MutableLiveData<>(false);
    }

    public LiveData<List<Book>> getSearchedBook() {
        return bookRepo.getSearchedBook();
    }

    public void searchForBook(String s) {
        loading.setValue(true);
        bookRepo.searchForBook(() -> loading.setValue(false), s);
    }

    public LiveData<Boolean> getLoading() {
        return loading;
    }
}
